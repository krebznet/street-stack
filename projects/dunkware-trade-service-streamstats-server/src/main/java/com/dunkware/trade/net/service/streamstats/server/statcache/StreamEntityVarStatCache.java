package com.dunkware.trade.net.service.streamstats.server.statcache;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.dtime.DDate;
import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.helpers.DNumberHelper;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.xstream.model.stats.EntityStatsAggVar;

public class StreamEntityVarStatCache {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("statcache");
	
	private LocalDate startDate;
	private LocalDate endDate;
	
	private String ident;
	
	private List<StreamEntityVarSession> sessions = new ArrayList<StreamEntityVarSession>();
	private Map<DDate, StreamEntityVarSession> sessionIndex = new ConcurrentHashMap<DDate,StreamEntityVarSession>();
	private boolean sorted = false; 
	
	private EntityStatsAggVar agg;
	// so we know if we are consuming the first session
	private boolean aggInitialized = false;
	private static EntityVarSessionComparator sessionComparator = new EntityVarSessionComparator();
	
	private static class EntityVarSessionComparator implements Comparator<StreamEntityVarSession> {

		@Override
		public int compare(StreamEntityVarSession o1, StreamEntityVarSession o2) {
			if(o1.getDate().get().isBefore(o2.getDate().get())) { 
				return -1;
			}
			if(o1.getDate().get().isAfter(o2.getDate().get())) { 
				return 1;
			}
			return 0;
		} 
		
		
	}
	
	public StreamEntityVarStatCache(String ident) { 
		this.ident = ident;
		agg = new EntityStatsAggVar();
		agg.setIdent(ident);;
		
	}
	
	public String getIdent() { 
		return ident;
	}
	
	public StreamEntityVarSession getSession(DDate date) { 
		
		for (StreamEntityVarSession streamEntityVarSession : sessions) {
			if(streamEntityVarSession.getDate().equals(date)) { 
				return streamEntityVarSession;
			}
		}
		return null;
	}
	
	public EntityStatsAggVar getAgg() { 
		return agg;
	}
	
	public void consumeSession(Document doc, DDate date) { 
	
		if(sessionIndex.containsKey(date)) { 
 			logger.warn(marker, "consuming var stat session for existing date " + date.toMMDDYY());
			return;
		}
		StreamEntityVarSession session = new StreamEntityVarSession();
		String highTime = doc.getString("highTimeString");
		String lowTime = doc.getString("lowTimeString");
		String low = doc.getString("low");
		String high = doc.getString("high");
		int dataType = doc.getInteger("dataType");
		session.setHigh(StreamStatCacheHelper.stringToNumber(high, dataType));
		session.setLow(StreamStatCacheHelper.stringToNumber(low, dataType));
		session.setHighTIme(DTime.from(LocalDateTime.parse(highTime,DateTimeFormatter.ofPattern(DunkTime.YYYY_MM_DD_HH_MM_SS)).toLocalTime()));
		session.setLowTIme(DTime.from(LocalDateTime.parse(lowTime,DateTimeFormatter.ofPattern(DunkTime.YYYY_MM_DD_HH_MM_SS)).toLocalTime()));
		session.setDate(date);
		if(!aggInitialized) { 
			agg.setHigh(session.getHigh());
			agg.setLow(session.getLow());
			agg.setHighDateTime(LocalDateTime.of(session.getDate().get(), session.getHighTIme().get()));
			agg.setLowDateTime(LocalDateTime.of(session.getDate().get(),session.getLowTIme().get()));
			aggInitialized = true;
		}
		else { 
			if(DNumberHelper.isFirstGreater(session.getHigh(), agg.getHigh())) { 
				agg.setHigh(session.getHigh());
				agg.setHighDateTime(LocalDateTime.of(session.getDate().get(), session.getHighTIme().get()));
			}
			if(DNumberHelper.isFirstGreater(agg.getLow(), session.getLow())) {
				agg.setLow(session.getLow());
				agg.setHighDateTime(LocalDateTime.of(session.getDate().get(), session.getHighTIme().get()));
			}
		}
		agg.setValueCount(agg.getValueCount() + 1);
		sessionIndex.put(date, session);
		if(logger.isDebugEnabled()) { 
			logger.info("added" + date.toMMDDYY());	
		}
		
		sessions.add(session);
		
	}
	
	public void buildAgg() { 
		
	}
	
	
	private void checkSort() { 
		if(!sorted) { 
			Collections.sort(sessions,sessionComparator);
			sorted = true;
		}
	}
	
	
	
	public StreamEntityVarSession resolveRelativeHigh(DDate date, int days) throws StreamStatResolveException { 
		checkSort();
		StreamEntityVarSession startSession = getFirstRelativeSession(date);
		int startIndex = sessions.indexOf(startSession);
		if((((startIndex + 1) + days) < sessions.size())) { 
			throw new StreamStatResolveException("Not enugh session for days " + days);
		}
		StreamEntityVarSession highSession = sessions.get(startIndex);
		int index = startIndex; 
		int count = days;
		while(count != 0) { 
			StreamEntityVarSession session = sessions.get(index);
			if(DNumberHelper.isFirstGreater(session.getHigh(), highSession.getHigh())) { 
				highSession = session;
			}
			count--;
			index--;
		}
		return highSession;
	}
	
	
	private StreamEntityVarSession getFirstRelativeSession(DDate date) throws StreamStatResolveException { 

		StreamEntityVarSession minDaysApartSession = null;
		long minDaysApart = Long.MAX_VALUE;
		for (StreamEntityVarSession session : sessions) {
			if(session.getDate().isSameDay(date)) { 
				return session;
			}
			if(session.getDate().get().isBefore(date.get())) { 
				long daysApart = session.getDate().get().until(date.get(), ChronoUnit.DAYS);
				if(daysApart < minDaysApart) { 
					minDaysApartSession = session;
					if(minDaysApart == 1) { 
						break; // got the day before
					}
				}
			}
			
		}
		if(minDaysApartSession == null) { 
			throw new StreamStatResolveException("Cannot find recent date that is before target date " + date.toString());
		}
		return minDaysApartSession;
		
	}
	
	
	

}
