package com.dunkware.xstream.core.search.row.dev;

import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.xstream.api.XStreamEntityQuery;
import com.dunkware.xstream.core.search.row.builder.XStreamEntityQueryBuilder;
import com.dunkware.xstream.core.search.row.criteria.XStreamEntityPredicate;
import com.dunkware.xstream.model.query.XStreamEntityQueryModel;

public class XStreamQueryEntityTest1 {
	
	public static void main(String[] args) {
		new XStreamQueryEntityTest1();
	}

	public XStreamQueryEntityTest1() { 
		Thread runner = new Thread() { 
			
			public void start() { 
				
				try {
					XStreamEntityQueryModel model = XStreamEnttiyQueryModels.test1();
					DStopWatch timer = DStopWatch.create();
					timer.start();
					XStreamEntityQuery query = XStreamEntityQueryBuilder.newInstance(model).build();
					timer.stop();
					System.out.println("Completed in " + timer.getCompletedSeconds() + " Seconds");
					for (XStreamEntityPredicate	 predicate : query.getPredicates()) {
						System.out.println(predicate.getModel().getType());
					}
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}		
				
			}
			
		};
		runner.start();
		
	}
	
	
}
