package com.dunkware.trade.service.stream.server.stats.core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.trade.service.stream.server.stats.repository.StreamEntityDayStatsDoc;
import com.dunkware.xstream.model.stats.EntityStatsAgg;
import com.dunkware.xstream.model.stats.EntityStatsAggVar;
import com.dunkware.xstream.model.stats.EntityStatsSession;
import com.dunkware.xstream.model.stats.EntityStatsSessionVar;

public class StreamStatsUtils {
	
	
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
			for (EntityStatsSessionVar var : doc.getVars()) {
				EntityStatsAggVar aggVar = vars.get(var.getIdent());
				if(aggVar == null) { 
					aggVar = new EntityStatsAggVar();
					aggVar.setSessionCount(1);
					aggVar.setIdent(var.getIdent());
					aggVar.setHigh(var.getHigh());
					aggVar.setLow(var.getLow());
					aggVar.setHighDateTime(var.getHighDateTime());
					aggVar.setLowDateTime(var.getLowDateTime());		
					vars.put(var.getIdent(), aggVar);
				} else { 
					aggVar.setSessionCount(aggVar.getSessionCount() + 1);
					
					if(var.getHigh().doubleValue() > aggVar.getHigh().doubleValue()) { 
						aggVar.setHigh(var.getHigh());
						aggVar.setHighDateTime(var.getHighDateTime());
					}
					if(var.getLow().doubleValue() < aggVar.getLow().doubleValue()) {
						aggVar.setLow(var.getLow());
						aggVar.setLowDateTime(var.getLowDateTime());
					}
					vars.put(var.getIdent(), aggVar);
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
