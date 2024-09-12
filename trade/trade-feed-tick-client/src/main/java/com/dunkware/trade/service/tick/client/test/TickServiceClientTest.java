package com.dunkware.trade.service.tick.client.test;

import com.dunkware.trade.service.tick.client.TickServiceClient;
import com.dunkware.trade.service.tick.client.TickServiceClientFactory;
import com.dunkware.trade.service.tick.client.TickServiceClientFeed;
import com.dunkware.trade.tick.model.TradeTicks;
import com.dunkware.trade.tick.model.consumer.TickConsumerSpec;
import com.dunkware.trade.tick.model.consumer.TickConsumerSpecBuilder;
import com.dunkware.utils.core.helpers.DunkUUID;
import com.dunkware.utils.tick.TickHandler;
import com.dunkware.utils.tick.proto.TickProto.Tick;

public class TickServiceClientTest implements TickHandler  {
	
	public static void main(String[] args) {
		new TickServiceClientTest();
		
	}
	
	public TickServiceClientTest() { 
		
		
		  try { //TickServiceClient client =
		//TickServiceClient client = TickServiceClientFactory.connect("http://192.168.4.6:31890"); //
		  TickServiceClient client =  TickServiceClientFactory.connect("http://localhost:8987"); 
		 
		  System.out.println("success"); TickConsumerSpec spec =
		  TickConsumerSpecBuilder.newInstance("Test" + DunkUUID.randomUUID(5))
		  .addEquity("GRUB")
		  
		  .addTickType(TradeTicks.TickSnapshot).build(); TickServiceClientFeed feed =
		  client.createFeed(spec); feed.getTickStream().addTickHandler(this); } catch
		  (Exception e) { System.out.println("exception " + e.toString());
		  
		  }
		 
	}

	@Override
	public void onTick(Tick tick) {
	//	System.out.println(TickHelper.printTick(tick));
	}
	
	

}
