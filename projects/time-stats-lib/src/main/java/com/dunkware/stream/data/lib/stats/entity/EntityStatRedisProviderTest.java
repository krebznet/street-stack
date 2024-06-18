package com.dunkware.stream.data.lib.stats.entity;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

import com.dunkware.utils.core.stopwatch.StopWatch;

import io.vertx.core.Handler;

public class EntityStatRedisProviderTest {
	
	public static void main(String[] args) {
		try {
			EntityStatRedisProvider provider = new EntityStatRedisProvider();
			provider.start("testrock1.dunkware.net", 32595);
			StopWatch watch = StopWatch.newInstance();
			watch.start();
			AtomicInteger respCount = new AtomicInteger(0);
			for(int i = 0; i < 1; i++) { 
			
				
				EntityStatReq req = EntityStatReq.relativeHigh(1, LocalDate.of(2024, 3, 05), 3 , 6, 3, 1);
				provider.request(req).onSuccess(new Handler<EntityStatResp>() {

					@Override
					public void handle(EntityStatResp event) {
						int count = respCount.incrementAndGet();
						
						StringBuilder b = new StringBuilder();
						b.append("Response ").append(count);
						if(event.isResolved()) { 
							b.append(" resolved ").append(event.getValue());
							//System.out.println(b.toString());
							return;
						}
						b.append(" Unresolved");
						//System.out.println(b.toString());
					}
					
				});
			}
			while(respCount.get() < 150) {
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			watch.stop();
			System.out.println("Seconds " + watch.seconds());
		
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	
		
	}

}
