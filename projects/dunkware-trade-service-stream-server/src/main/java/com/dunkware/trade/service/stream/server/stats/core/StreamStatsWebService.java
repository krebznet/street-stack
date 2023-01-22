package com.dunkware.trade.service.stream.server.stats.core;

import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.bson.codecs.jsr310.LocalDateTimeCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.stream.server.stats.StreamStatsService;
import com.dunkware.trade.service.stream.server.stats.repository.SessionEntityStats;
import com.dunkware.trade.service.stream.server.stats.repository.SessionEntityStatsRepo;
import com.dunkware.trade.service.stream.server.stats.repository.SessionEntityVarStats;

@RestController
public class StreamStatsWebService {

	
	//@Autowired
	//private StreamStatsService statsService; 
	
	
	@Autowired
	private SessionEntityStatsRepo entityStats;
	
	public static void main(String[] args) {
		Double me = 23.234;
		Fuck fuck = new Fuck();
		fuck.setData(me);
		try {
			String pretty = DJson.serializePretty(fuck);
			System.out.println(pretty);
			Fuck fucker = DJson.getObjectMapper().readValue(pretty, Fuck.class);
			System.out.println(fucker.getData());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}
	
	public static class Fuck { 
		
		private Number data;

		public Number getData() {
			return data;
		}

		public void setData(Number data) {
			this.data = data;
		} 
		
		
	}
	
	@PostConstruct
	private void testInsert() { 
		
		
		try {
			int i = 0; 
			System.out.println("being insert");
			while(i < 100) {
			
				SessionEntityStats stats = new SessionEntityStats();
				stats.setId(DRandom.getRandom(4, 43343434));
				stats.setEndTime(LocalDateTime.now());
				stats.setStartTime(LocalDateTime.now().minusHours(4));
				stats.setSessionId(23323);
				stats.setStreamId(323);
				stats.setStreamIdent("Equity");
				SessionEntityVarStats vStats = new SessionEntityVarStats(); 
				vStats.setHigh(32);
				vStats.setHighTime(LocalDateTime.now());
				vStats.setLow(423.3);
				vStats.setLowTime( LocalDateTime.now().minusDays(323));
				stats.getVarStats().add(vStats);
				
				entityStats.save(stats);
				System.out.println("saved");
				i++;
			}
			System.out.println("end insert");
		
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
	}
	
	// it would be a list of stats. for a session
	
	
}

