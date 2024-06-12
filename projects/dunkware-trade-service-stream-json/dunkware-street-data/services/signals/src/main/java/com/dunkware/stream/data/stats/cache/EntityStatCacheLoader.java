package com.dunkware.stream.data.stats.cache;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.stream.data.cassy.entity.sesion.StreamSessionRow;
import com.dunkware.stream.data.cassy.entity.stats.SessionEntityStatRow;
import com.dunkware.stream.data.cassy.repository.stats.SessionEntityStatRepo;

public class EntityStatCacheLoader {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("EntityStatCache");
	
	@Autowired
	private SessionEntityStatRepo statRepo;
	
	
	
	public void start(List<StreamSessionRow> sessions) {
		
	}
	
	
	// Jedis Pool somehow and see how that works correctly
	// need a pipeline for correctly
	// return Jedis connection
	
	// we need a utility
	
	private class SessionStatLoader implements Runnable { 
		
		private int streamId; 
		private LocalDate date;
		
		public SessionStatLoader(int streamId, LocalDate date) { 
			this.streamId = streamId;
			this.date = date; 
		}
		
		public void run() { 
			List<SessionEntityStatRow> rows = statRepo.findByStreamAnLocalDate(streamId,date);
			for (SessionEntityStatRow row : rows) {
				// now w needtocache
			}
			
		}
	}

}
