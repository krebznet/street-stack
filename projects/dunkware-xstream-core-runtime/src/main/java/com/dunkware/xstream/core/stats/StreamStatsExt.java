package com.dunkware.xstream.core.stats;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.helpers.DHttpHelper;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamException;
import com.dunkware.xstream.api.XStreamExtension;
import com.dunkware.xstream.api.XStreamListener;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.core.annotations.AXStreamExtension;
import com.dunkware.xstream.core.stats.builders.EntityStatsBuilder;
import com.dunkware.xstream.model.stats.EntityStatsConstants;
import com.dunkware.xstream.model.stats.EntityStatsSession;
import com.dunkware.xstream.model.stats.EntityStatsSessionVar;
import com.dunkware.xstream.model.stats.EntityStatsSessions;
import com.dunkware.xstream.model.stats.StreamStatsPayload;
import com.dunkware.xstream.xScript.DataType;
import com.dunkware.xstream.xScript.VarType;
import com.dunkware.xstream.xproject.model.XStreamExtensionType;

@AXStreamExtension(type = StreamStatsExtType.class)
public class StreamStatsExt implements XStreamExtension, XStreamListener {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private StreamStatsExtType myType;
	private XStream stream;

	private List<EntityStatsSession> entityStats = new ArrayList<EntityStatsSession>();

	private ConcurrentHashMap<String, EntityStatsBuilder> entityStatBuilders = new ConcurrentHashMap<String, EntityStatsBuilder>();

	private Marker marker = MarkerFactory.getMarker("xstream.session.stats");

	private boolean disposed = false;

	private LocalDate today;

	private String id = DUUID.randomUUID(5);
	
	private EntityStatsService entityStatsService;

	@Override
	public void init(XStream stream, XStreamExtensionType type) throws XStreamException {
		if (logger.isDebugEnabled()) {
			logger.debug(marker, "Initializing StreamStatsExt");
		}
		this.stream = stream;
		this.myType = (StreamStatsExtType) type;
		today = this.stream.getInput().getDate().get();
		entityStatsService = new EntityStatsService();
	}

	// EntityStatsSession

	@Override
	public void preStart() throws XStreamException {
		stream.addStreamListener(this);

	}

	@Override
	public void start() throws XStreamException {
		// okay so we need to manage

	}

	@Override
	public void preDispose() {
		if (disposed) {
			logger.error("Calling preDispose twice on StreamStatsExt");
			return;
		}
		disposed = true;

		if (logger.isDebugEnabled()) {
			logger.debug(marker, "Disposing StreamStatsExt");
		}
		for (EntityStatsBuilder statBuilder : entityStatBuilders.values()) {
			EntityStatsSession dayStats = statBuilder.dispose();
			this.entityStats.add(statBuilder.dispose());
		}
		try {
			System.out.println(DJson.serializePretty(entityStats));
		} catch (Exception e) {
			logger.error("Exception serializing bullshit stats " + e.toString());

		}

		try {
			StreamStatsPayload stats = new StreamStatsPayload();
			stats.setStreamIdent(myType.getStreamIdent());
			stats.setEntities(entityStats);
			try {
				String out = DJson.serialize(stats);
				byte[] serialized = out.getBytes();
				DHttpHelper.multipartRequest(myType.getPostURL(), "test=me", serialized, "file");

			} catch (Exception e) {
				logger.error("Exception pusting session entity stats to service " + e.toString());
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void rowInsert(XStreamEntity row) {
		if (entityStatBuilders.containsKey(row.getId())) {
			logger.warn("Row Insert event on entity " + row.getId() + "already has stat builder");
			return;
		}
		EntityStatsBuilder statsBuilder = EntityStatsBuilder.newInstance(this, row);
		this.entityStatBuilders.put(row.getId(), statsBuilder);

	}

	public boolean canResolveVarStat(XStreamEntity row, VarType varType, int days, int stat) throws Exception {
		return entityStatsService.canResolveVarStat(row.getId(), varType.getName(), days, stat);
	}

	/**
	 * Okay this is a start to see what we can do here.
	 * 
	 * @param row
	 * @param varType
	 * @param stat
	 * @return
	 * @throws Exception
	 */
	public Object resolveVarStat(XStreamEntity row, VarType varType, int days, int stat) throws Exception {
		return entityStatsService.resolveVarStat(row, varType, days, stat);
		// so you will hit that web service 3,000 times? nice duncan! 
	}

	private class EntityStatsService {

		private Map<String, EntityStatsContainer> statsContainers = new ConcurrentHashMap<String, StreamStatsExt.EntityStatsContainer>();

		public Object resolveVarStat(XStreamEntity row, VarType type, int days, int stat) throws Exception { 
			if(!canResolveVarStat(row.getId(), type.getName(), days, stat)) { 
				throw new Exception("Trying to resolve var stat that cannot be rsolved check code");
			}
			try {
				EntityStatsContainer container = statsContainers.get(row.getId());
				Number resolved = container.resolveVarAgg(type.getName(), days, stat);
				if(type.getType() == DataType.DUB) { 
					return resolved.doubleValue();
				}
				if(type.getType() == DataType.INT) { 
					return resolved.intValue();
				}
				if(type.getType() == DataType.LONG) { 
					return resolved.longValue();
				}
				logger.warn("data type not handled for conversion " + type.getType().name());
				return resolved.doubleValue();
				
			} catch (Exception e) {
				logger.error("Exception calling canResolveVaragg on entity stats container " + e.toString());
				return false; 
			}
			
			
		}
		
		public boolean canResolveVarStat(String entityIdent, String varIdent, int days, int stat ) throws Exception { 
			if(statsContainers.get(entityIdent) == null) { 
				// make a web service call. 
				String url = myType.getBaseURL() + "/stream/stats/entity/sessions?stream=" + myType.getStreamIdent() + "&ident=" + entityIdent;
				String responseString = null;
				try {
					responseString = DHttpHelper.getURLContent(url);	
				} catch (Exception e) {
					logger.error("Exception getting entity stats sessions URL " + url + " " + e.toString());
					return false;
				}
				EntityStatsSessions statsSessions = null;
				try {
					statsSessions = DJson.getObjectMapper().readValue(responseString, EntityStatsSessions.class);
				} catch (Exception e) {
					logger.error("Exception deserializing entity stats sesions in stats ext " + e.toString());
					return false; 
				}
				EntityStatsContainer statsContainer = new EntityStatsContainer(entityIdent, statsSessions);
				this.statsContainers.put(entityIdent, statsContainer);
			}
			// now we assume stats container is there lets see if it resolves
			try {
				EntityStatsContainer container = statsContainers.get(entityIdent);
				return container.canResolveVarAgg(varIdent, days, stat);
			} catch (Exception e) {
				logger.error("Exception calling canResolveVaragg on entity stats container " + e.toString());
				return false; 
			}
		}

	}

	private class EntityStatsContainer {

		private String ident;
		private EntityStatsSessions sessions;

		public EntityStatsContainer(String ident, EntityStatsSessions sessions) {
			this.ident = ident;
			this.sessions = sessions;
		}

		public boolean canResolveVarAgg(String varIdent, int days, int stat) {
			if (sessions.isResolved() == false) {
				return false;
			}
			List<EntityStatsSession> relativeSessions = getRelativeSessions(days);
			if (relativeSessions.size() < days) {
				return false;
			}
			// now make sure each session has the var we want.
			for (EntityStatsSession entityStatsSession : relativeSessions) {
				// make sure the var is in the session
				boolean hasVar = false;
				for (EntityStatsSessionVar var : entityStatsSession.getVars()) {
					if (var.getIdent().equals(varIdent)) {
						hasVar = true;
					}
				}
				if (hasVar) {
					return false;
				}
			}
			// we got the correct number of sessions based on days back
			// we validated each session has the var return true
			return true;
		}

		public Number resolveVarAgg(String varIdent, int days, int stat) throws Exception {
			if(sessions.isResolved() == false) { 
				throw new Exception("can't resolve var agg sessions did not resolve on " + ident);
			}
			Number result = null;
			List<EntityStatsSession> relativeSessions = getRelativeSessions(days);
			for (EntityStatsSession entityStatsSession : relativeSessions) {
				// make sure the var is in the session
				EntityStatsSessionVar targetVar = null;
				for (EntityStatsSessionVar var : entityStatsSession.getVars()) {
					if (var.getIdent().equals(varIdent)) {
						targetVar = var;
					}
				}
				if (targetVar == null) {
					throw new Exception("resolving session does not have var " + varIdent + " on identity " + ident
							+ " on date " + entityStatsSession.getDate().toString());
				}
				if (result == null) {
					switch (stat) {
					case EntityStatsConstants.VAR_STAT_HIGH:
						result = targetVar.getHigh();
						break;
					case EntityStatsConstants.VAR_STAT_LOW:
						result = targetVar.getLow();
						break;
						default:
							throw new Exception("Invalid stat constant " + stat);
					}
				} else { 
					switch (stat) {
					case EntityStatsConstants.VAR_STAT_HIGH:
						if(targetVar.getHigh().doubleValue() > result.doubleValue()) { 
							result = targetVar.getHigh();
						}
						break;
					case EntityStatsConstants.VAR_STAT_LOW:
						if(targetVar.getLow().doubleValue() < result.doubleValue()) { 
							result = targetVar.getLow();
						}
						break;
						default:
							throw new Exception("Invalid stat constant " + stat);
					}
				}
			}
			return result;
		}

		private List<EntityStatsSession> getRelativeSessions(int days) {
			List<EntityStatsSession> relativeSessions = new ArrayList<EntityStatsSession>();
			LocalDate startDate = today.minusDays(days);
			for (EntityStatsSession session : sessions.getSessions()) {
				if (session.getDate().isEqual(startDate) || session.getDate().isAfter(startDate)) {
					relativeSessions.add(session);
				}
			}
			return relativeSessions;
		}

	}

}
