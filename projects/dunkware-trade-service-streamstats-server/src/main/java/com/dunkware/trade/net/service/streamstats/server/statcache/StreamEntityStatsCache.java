package com.dunkware.trade.net.service.streamstats.server.statcache;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.dtime.DDate;
import com.dunkware.xstream.model.stats.EntityStatReq;
import com.dunkware.xstream.model.stats.EntityStatReqType;
import com.dunkware.xstream.model.stats.EntityStatResp;
import com.dunkware.xstream.model.stats.EntityStatRespBuilder;
import com.dunkware.xstream.model.stats.EntityStatsAgg;
import com.dunkware.xstream.model.stats.EntityStatsAggVar;

public class StreamEntityStatCache {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("streamcache");
	
	private String ident; 
	private Map<String,StreamEntityVarStatCache> varCache = new ConcurrentHashMap<String,StreamEntityVarStatCache>();
	private Map<LocalDate,StreamEntityVarStatCache> varCacheIndex = new ConcurrentHashMap<LocalDate, StreamEntityVarStatCache>();
	private EntityStatsAgg agg; 
	private boolean aggInitialized = false;
	
	
	
	public void init(String ident) { 
		this.ident = ident;
		agg = new EntityStatsAgg();
		agg.setIdent(ident);;
		agg.setSessions(0);
	}
	
	public String getIdent() { 
		return ident; 
	}
	
	public EntityStatsAgg getAgg() {
		List<EntityStatsAggVar> varAggs = new ArrayList<EntityStatsAggVar>();
		for (StreamEntityVarStatCache var : varCache.values()) {
			varAggs.add(var.getAgg());
		}
		agg.setVars(varAggs);
		return agg;
	}
	
	
	public StreamEntityVarStatCache getVar(String ident) throws Exception { 
		StreamEntityVarStatCache var = varCache.get(ident);
		if(var == null) { 
			throw new Exception("Stream Var Not Found " + ident);
		}
		return var;
		
	}
	
	
	public void consumeSession(Document doc) { 
		Date date = doc.getDate("date");
		
		//Date date = new Date();
		OffsetDateTime offsetDateTime = date.toInstant()
		  .atOffset(ZoneOffset.UTC);
		date = Date.from(offsetDateTime.withOffsetSameInstant(ZoneOffset.UTC).toInstant());
		LocalDate hmm = offsetDateTime.toLocalDate();
		DDate ddate = DDate.from(hmm);
		if(varCacheIndex.keySet().contains(hmm)) { 
			return;
		}
		if(!aggInitialized) { 
			agg.setStart(ddate.get());
			agg.setEnd(ddate.get());
			agg.setSessions(1);
			aggInitialized = true;
		} else { 
			if(ddate.isAfter(DDate.from(agg.getEnd()))) {
				agg.setEnd(ddate.get());
			}
			if(ddate.isBefore(DDate.from(agg.getStart()))) {
				agg.setStart(ddate.get());
			}
			agg.setSessions(agg.getSessions() + 1);
		}
		
		Iterator<Document> vars = (Iterator<Document>)doc.getList("vars", Document.class).iterator();

		int count = 1;
		List<String> varNames = new ArrayList<String>();
		while(vars.hasNext()) {
			
			Document varDoc = vars.next();
		
			count++;
			String ident = varDoc.getString("ident");
			if(varNames.contains(ident)) { 
			
			}
			StreamEntityVarStatCache statCache = varCache.get(ident);
			if(statCache == null) { 
				statCache = new StreamEntityVarStatCache(ident);
				varCache.put(ident, statCache);
				varCacheIndex.put(hmm, statCache);
			}
		//	System.out.println("added varDoc to var cache " + statCache.getIdent() + " date " + ddate.toMMDDYY());
			statCache.consumeSession(varDoc,ddate);
			varCache.put(ident, statCache);
		}
		
	}
	
	
	EntityStatResp getStat(EntityStatReq req) { 
		
		EntityStatRespBuilder builder = EntityStatRespBuilder.newInstance();
		if(req.getType() == EntityStatReqType.VarHighRelative || req.getType() == EntityStatReqType.VarLowRleative) { 
			StreamEntityVarStatCache varStats = varCache.get(req.getTarget());
			if(varStats == null) { 
				return builder.exception("Entity variable " + req.getTarget() + " not found").build();
			}
			if(req.getType() == EntityStatReqType.VarHighRelative) {
				try {
					StreamEntityVarSession session = varStats.resolveRelativeHigh(DDate.from(req.getDate()), 
							req.getRelativeDays());
					return builder.resolved(session.getHigh(), session.getDate(), session.getHighTIme()).build();
				} catch (StreamStatResolveException e) {
					return builder.unresolved().build();
				}
			}
		}
		return builder.exception("Internal exception stat req type " + req.getType().name() + " not implemented").build();
	}
	

}
