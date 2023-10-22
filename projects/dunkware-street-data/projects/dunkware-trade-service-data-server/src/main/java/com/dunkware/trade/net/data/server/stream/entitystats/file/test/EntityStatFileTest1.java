package com.dunkware.trade.net.data.server.stream.entitystats.file.test;

import java.time.LocalDate;
import java.util.List;

import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.trade.net.data.server.stream.entitystats.file.EntityStatFileReader;
import com.dunkware.trade.net.data.server.stream.entitystats.file.EntityStatFileWriter;
import com.dunkware.trade.net.data.server.stream.entitystats.statgen.EntityStatGenBean;
import com.dunkware.trade.net.data.server.stream.entitystats.statgen.EntityStatGenFactory;
import com.dunkware.xstream.model.stats.entity.EntityStat;

public class EntityStatFileTest1 {
	
	public static void main(String[] args) {
		EntityStatGenBean bean = new EntityStatGenBean();
		bean.setDayCount(1);
		bean.setEntityCount(8000);
		bean.setSignalCount(10);
		bean.setStartDate(LocalDate.now());
		bean.setVarCount(55);
		bean.setVarStatCount(5);
		
		List<EntityStat> stats = EntityStatGenFactory.build(bean);
		System.out.println("Created " + stats.size() + " stats");
		System.out.println("Writing to /tmp/stats/test1.csv");
		DStopWatch timer = DStopWatch.create();
		timer.start();
		try {
			EntityStatFileWriter writer = EntityStatFileWriter.newInstance("/tmp/stats", "test1.csv", true);
			for (EntityStat entityStat : stats) {
				writer.writeStat(entityStat);
			}
			writer.close();
			timer.stop();
			System.out.println(" Wrote " + stats.size() + " to file in  "+  timer.getCompletedSeconds());
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		System.out.println("Reading Stats Back");
		
		try {
			timer.start();
			List<EntityStat> results = EntityStatFileReader.parse("/tmp/stats/test1.csv");
			timer.stop();
			System.out.println("read " + results.size() +  " stats in " + timer.getCompletedSeconds());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
