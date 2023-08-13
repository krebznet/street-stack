package com.dunkware.trade.net.service.streamstats.server.service.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.common.util.time.DunkTime;
import com.dunkware.trade.net.service.streamstats.server.repository.EntityStatsSessionDoc;
import com.dunkware.trade.net.service.streamstats.server.repository.EntityStatsSessionDocVar;
import com.dunkware.trade.net.service.streamstats.server.repository.StreamEntityDayStatsDoc;
import com.dunkware.trade.net.service.streamstats.server.sequence.MongoSequenceService;
import com.dunkware.xstream.model.stats.EntityStatsAgg;
import com.dunkware.xstream.model.stats.EntityStatsAggVar;
import com.dunkware.xstream.model.stats.EntityStatsSession;
import com.dunkware.xstream.model.stats.EntityStatsSessionVar;
import com.dunkware.xstream.model.stats.EntityStatsSessionVarDep;

public class StreamStatsHelper {
	
	public static final int DATA_TYPE_LONG = 1; 
	public static final int DATA_TYPE_DOUBLE = 2; 
	public static final int DATA_TYPE_INT = 3; 
	
	
	public static int getDataType(Number number)  { 
	
		if (number instanceof Long) {
			return DATA_TYPE_LONG;
		}
		if (number instanceof Double) {
			return DATA_TYPE_DOUBLE;
		}
		if (number instanceof Integer) {
			return DATA_TYPE_INT;
		}
		return DATA_TYPE_INT;
	}
	
	public static Number stringToNumber(String input, int dataType) { 
		if(dataType == DATA_TYPE_DOUBLE) { 
			return Double.valueOf(input);
		}
		if(dataType == DATA_TYPE_LONG) { 
			return Integer.valueOf(input);
		}
		// else assuming its long
		return Long.valueOf(input);
	}
	
	public static String numberToString(Number number) { 
		if (number instanceof Long) {
			Long longValue = (Long) number;
			return longValue.toString();
		}
		if (number instanceof Double) {
			Double longValue = (Double) number;
			return longValue.toString();
		}
		if (number instanceof Integer) {
			Integer longValue = (Integer) number;
			return longValue.toString();
		}
		return number.toString();
	}
	
	public static EntityStatsSession buildEntityStatsSession(EntityStatsSessionDoc doc) { 
		EntityStatsSession session = new EntityStatsSession();
		session.setDate(doc.getDate());
		session.setId(doc.getId());
		session.setIdent(doc.getIdent());
		session.setStream(doc.getStream());
		for (EntityStatsSessionDocVar	docVar : doc.getVars()) {
			EntityStatsSessionVar var = buildEntityStatsSessionVar(docVar);
			session.getVars().add(var);
		}
		return session;
		
	}
	
	public static EntityStatsSessionVar buildEntityStatsSessionVar(EntityStatsSessionDocVar docVar) {
		EntityStatsSessionVar var = new EntityStatsSessionVar();
		var.setId(docVar.getId());
		var.setIdent(docVar.getIdent());
		var.setValueCount(docVar.getValueCount());
		var.setHigh(stringToNumber(docVar.getHigh(), docVar.getDataType()));
		LocalDateTime highDateTime = LocalDateTime.parse(docVar.getHighTimeString(),DateTimeFormatter.ofPattern(DunkTime.YYYY_MM_DD_HH_MM_SS));
		var.setHighDateTime(highDateTime);
		LocalDateTime lowDateTime = LocalDateTime.parse(docVar.getLowTimeString(),DateTimeFormatter.ofPattern(DunkTime.YYYY_MM_DD_HH_MM_SS));
		var.setLowDateTime(lowDateTime); 
		var.setLow(stringToNumber(docVar.getLow(), docVar.getDataType()));
		var.setValueCount(docVar.getValueCount());
		return var;
	}
	
	public static EntityStatsSessionDoc buildEntityStatsSessionDoc(EntityStatsSession input, MongoSequenceService sequenceService) throws Exception { 
		EntityStatsSessionDoc doc = new EntityStatsSessionDoc();
		doc.setDate(input.getDate());
		doc.setUid(sequenceService.generateSequence(EntityStatsSessionDoc.SEQUENCE_NAME));
		doc.setId(input.getId());
		doc.setIdent(input.getIdent());
		doc.setStream(input.getStream());
		for (EntityStatsSessionVar var : input.getVars()) {
			EntityStatsSessionDocVar docVar = buildEntityStatsSessionDocVar(var);
			doc.getVars().add(docVar);
		}
		return doc;
	}
	
	public static EntityStatsSessionDocVar buildEntityStatsSessionDocVar(EntityStatsSessionVar input) { 
		EntityStatsSessionDocVar var = new EntityStatsSessionDocVar();
		var.setId(input.getId());
		var.setIdent(input.getIdent());
		var.setDataType(getDataType(input.getLow()));
		var.setHigh(numberToString(input.getHigh()));
		var.setHighTime(input.getHighDateTime());
		var.setHighTimeString(DunkTime.format(input.getHighDateTime(), DunkTime.YYYY_MM_DD_HH_MM_SS));
		var.setLow(numberToString(input.getLow()));
		var.setLowTime(input.getLowDateTime());
		var.setLowTimeString(DunkTime.format(input.getLowDateTime(), DunkTime.YYYY_MM_DD_HH_MM_SS));
		var.setValueCount(input.getValueCount());
		return var; 
		
	}
	
	public static EntityStatsAgg buildEntityStatsAgg(List<StreamEntityDayStatsDoc> input, String entityIdent, int entityId) {
		EntityStatsAgg agg = new EntityStatsAgg();
		LocalDate startDate = null; 
		LocalDate endDate = null;
		agg.setSessions(0);
		Map<String,EntityStatsAggVar> vars = new ConcurrentHashMap<String,EntityStatsAggVar>();
		for (StreamEntityDayStatsDoc doc : input) {
			agg.setSessions(agg.getSessions() + 1);
			if(startDate == null && endDate == null) { 
				startDate = doc.getDate();
				endDate = doc.getDate();
			}
			if(doc.getDate().isAfter(endDate)) { 
				endDate = doc.getDate();
			}
			if(doc.getDate().isBefore(startDate)) { 
				startDate = doc.getDate();
			}
			for (EntityStatsSessionVarDep var : doc.getVars()) {
				EntityStatsAggVar aggVar = vars.get(var.getVarIdent());
				if(aggVar == null) { 
					aggVar = new EntityStatsAggVar();
					aggVar.setSessionCount(1);
					aggVar.setIdent(var.getVarIdent());
					aggVar.setHigh(var.getHigh());
					aggVar.setLow(var.getLow());
					aggVar.setHighDateTime(var.getHighT());
					aggVar.setLowDateTime(var.getLowT());		
					vars.put(var.getVarIdent(), aggVar);
				} else { 
					aggVar.setSessionCount(aggVar.getSessionCount() + 1);
					
					if(var.getHigh().doubleValue() > aggVar.getHigh().doubleValue()) { 
						aggVar.setHigh(var.getHigh());
						aggVar.setHighDateTime(var.getHighT());
					}
					if(var.getLow().doubleValue() < aggVar.getLow().doubleValue()) {
						aggVar.setLow(var.getLow());
						aggVar.setLowDateTime(var.getLowT());
					}
					vars.put(var.getVarIdent(), aggVar);
				}
			}
		}
		
		agg.setEnd(endDate);
		agg.setStart(startDate);
		agg.setId(entityId);
		agg.setIdent(entityIdent);
		ArrayList<EntityStatsAggVar> newList = new ArrayList<EntityStatsAggVar>(vars.values());
		Collections.sort(newList, new Comparator<EntityStatsAggVar>() {

			@Override
			public int compare(EntityStatsAggVar o1, EntityStatsAggVar o2) {
				return o1.getIdent().compareTo(o2.getIdent());
			}
			
		}	);
		
		agg.setVars(newList);
		return agg;
		
		
	}

}
