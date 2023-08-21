package com.dunkware.trade.net.service.streamstats.client;

import java.time.LocalDate;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.trade.net.service.streamstats.client.proto.StreamStatsRequest;
import com.dunkware.trade.net.service.streamstats.client.proto.StreamStatsResponse;
import com.dunkware.xstream.model.stats.EntityStatRespType;

public class StreamStatsClientTest {

	public static void main(String[] args) {
		new StreamStatsClientTest();
	}

	private StreamStatsClient client;

	private StreamStatsClientTest() {
		try {
			client = StreamStatNetClientFactory.create("172.16.16.55:31090", "statreq", "statresp",
					"testme1 " + DUUID.randomUUID(5), "testgroup1" + DUUID.randomUUID(5), 30, TimeUnit.SECONDS, LocalDate.now());
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		

		
		

			while(true) { 
				try {
					StreamStatsRequest req = null;
					req = StreamStatsRequest.builder().stream("us_equity").date(LocalDate.now()).entity("AAPL").relativeDays(DRandom.getRandom(3, 40)).varHighReative("TickLast").buid();
					
				
					
							
					Thread.sleep(250);
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
			
			
			
			}
			
		
		
	}
}
