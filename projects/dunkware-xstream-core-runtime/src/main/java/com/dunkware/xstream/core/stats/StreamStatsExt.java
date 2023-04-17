package com.dunkware.xstream.core.stats;


import java.util.ArrayList;
import java.util.List;
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
import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.core.annotations.AXStreamExtension;
import com.dunkware.xstream.core.stats.builders.EntityStatsBuilder;
import com.dunkware.xstream.model.stats.EntityStatsSession;
import com.dunkware.xstream.xproject.model.XStreamExtensionType;

@AXStreamExtension(type = StreamStatsExtType.class)
public class StreamStatsExt implements XStreamExtension, XStreamListener {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private StreamStatsExtType myType; 
	private XStream stream; 
	
	private List<EntityStatsSession> entityStats = new ArrayList<EntityStatsSession>();
	
	private ConcurrentHashMap<String,EntityStatsBuilder> entityStatBuilders = new ConcurrentHashMap<String, EntityStatsBuilder>(); 
	
	private Marker marker = MarkerFactory.getMarker("xstream.session.stats");
	
	private boolean disposed = false; 
	
	private String id = DUUID.randomUUID(5);
	@Override
	public void init(XStream stream, XStreamExtensionType type) throws XStreamException {
		if(logger.isDebugEnabled()) { 
			logger.debug(marker, "Initializing StreamStatsExt");
		}
		this.stream = stream; 
		this.myType = (StreamStatsExtType)type; 
	}

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
		if(disposed) { 
			logger.error("Calling preDispose twice on StreamStatsExt");
			return;
		}
		disposed = true;

		if(logger.isDebugEnabled()) { 
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
			StreamStats stats = new StreamStats();
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
	public void rowInsert(XStreamRow row) {
		if(entityStatBuilders.containsKey(row.getId())) { 
			logger.warn("Row Insert event on entity " + row.getId() + "already has stat builder");
			return;
		}
		EntityStatsBuilder statsBuilder = EntityStatsBuilder.newInstance(this, row);
		this.entityStatBuilders.put(row.getId(), statsBuilder);
		
	}
	
	
	
	
	

}
