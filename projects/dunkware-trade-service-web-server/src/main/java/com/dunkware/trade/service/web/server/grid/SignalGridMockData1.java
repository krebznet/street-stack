package com.dunkware.trade.service.web.server.grid;

import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.net.data.builders.GBeanBuilder;
import com.dunkware.net.data.builders.GListBuilder;
import com.dunkware.net.proto.core.GList;

public class SignalGridMockData1 {

	
	// signal string 
	// time string 
	// date string 
	// TickLast - double 
	// entity string 
	
	public static GList getList() { 
		int value = DRandom.getRandom(1, 3);
		if(value == 1) { 
			return list1();
		}
		if(value == 2) { 
			return list2();
		}
		if(value ==3 ) { 
			return list3();
		}
		
		return list1();
		
		
		
	}
	
	
	private static GList list1() { 
		GListBuilder builder = GListBuilder.newBuilder(1);
		GBeanBuilder beanBuilder = GBeanBuilder.newBuilder(1, "row1");
		beanBuilder.setString("id", "Goog");
		beanBuilder.setString("signal", "Signal1");
		beanBuilder.setString("time", "10:30:03");
		beanBuilder.setString("date", "02/12/22");
		beanBuilder.setDouble("TickLast", 32.3);
		beanBuilder.setString("symbol", "Goog");
		beanBuilder.setString("entity", "Entity");
		builder.addBean(beanBuilder.build());
		
		beanBuilder = GBeanBuilder.newBuilder(1, "row1");
		beanBuilder.setString("id", "FB");
		beanBuilder.setString("signal", "Signal2");
		beanBuilder.setString("time", "10:30:04");
		beanBuilder.setString("date", "02/12/22");
		beanBuilder.setDouble("TickLast", 36.3);
		beanBuilder.setString("symbol", "FB");
		beanBuilder.setString("entity", "Entity");
		builder.addBean(beanBuilder.build());
		
		return builder.build();
	}
	
	
	private static GList list2() { 
		GListBuilder builder = GListBuilder.newBuilder(1);
		GBeanBuilder beanBuilder = GBeanBuilder.newBuilder(1, "row1");
		beanBuilder.setString("id", "Feed");
		beanBuilder.setString("signal", "Signal2");
		beanBuilder.setString("time", "10:45:03");
		beanBuilder.setString("date", "02/11/22");
		beanBuilder.setDouble("TickLast", 32.3);
		beanBuilder.setString("symbol", "Feed");
		beanBuilder.setString("entity", "Entity");
		builder.addBean(beanBuilder.build());
		
		beanBuilder = GBeanBuilder.newBuilder(1, "row1");
		beanBuilder.setString("id", "Peep");
		beanBuilder.setString("signal", "Signal2");
		beanBuilder.setString("time", "10:30:04");
		beanBuilder.setString("date", "02/12/22");
		beanBuilder.setDouble("TickLast", 45.33);
		beanBuilder.setString("symbol", "Peep");
		beanBuilder.setString("entity", "Entity");
		builder.addBean(beanBuilder.build());
		
		return builder.build();
	}
	

	private static GList list3() { 
		GListBuilder builder = GListBuilder.newBuilder(1);
		GBeanBuilder beanBuilder = GBeanBuilder.newBuilder(1, "row1");
		beanBuilder.setString("signal", "Signal3");
		beanBuilder.setString("time", "10:45:03");
		beanBuilder.setString("date", "02/2/22");
		beanBuilder.setDouble("TickLast", 56.3);
		beanBuilder.setString("symbol", "AVE");
		beanBuilder.setString("entity", "Entity");
		builder.addBean(beanBuilder.build());
		
		beanBuilder = GBeanBuilder.newBuilder(1, "row1");
		beanBuilder.setString("signal", "Signal2");
		beanBuilder.setString("time", "10:35:04");
		beanBuilder.setString("date", "02/11/22");
		beanBuilder.setDouble("TickLast", 58.3);
		beanBuilder.setString("symbol", "POOP");
		beanBuilder.setString("entity", "Entity");
		builder.addBean(beanBuilder.build());
		
		return builder.build();
	}
}
