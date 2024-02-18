package com.dunkware.trade.net.data.server.stream.entitystats.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.dunkware.common.jfastparser.Parser;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.trade.net.data.server.stream.entitystats.cache.CacheHelper;
import com.dunkware.xstream.model.stats.entity.EntityStat;

public class EntityStatFileReader {
	
	public static void main(String[] args) {
		try {
			List<EntityStat> wow = parse("/Users/duncankrebs/dunkworld/street/cloud/branches/main/street-cloud/projects/dunkware-street-data/projects/dunkware-trade-service-data-server/out.csv");	
			EntityStat stat = wow.get(0);
			System.out.println(stat.getDate().toString());
			System.out.println("no way");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}
	
	


	public static List<EntityStat> parse(String filePath) throws Exception { 
		DStopWatch timer = DStopWatch.create();
		timer.start();
		List<EntityStat> results = new ArrayList<EntityStat>();
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String line = null;
		;
		int counter = 0;
		int sum = 0;
		while ((line = reader.readLine()) != null) {
			EntityStat stat  = new EntityStat();
			Parser parser = new Parser(line);
			int dateKey = parser.eatInt();
			stat.setDate(CacheHelper.date((long)dateKey));
			parser.eatChar(',');
			int ent = parser.eatInt();
			stat.setEntity(ent);
			parser.eatChar(',');
			int element = parser.eatInt();
			stat.setElement(element);
			parser.eatChar(',');
			int statp = parser.eatInt();
			stat.setStat(statp);
			parser.eatChar(',');
			double value = parser.eatDouble();
			stat.setValue(value);
			parser.eatChar(',');
			int time = parser.eatInt();
			if(time != -1) { 
			stat.setTime(DunkTime.toLocalTime((long)time));
			}
			sum++;
			counter++;
			if(counter == 15000) { 
				System.out.println("Parse 15000K");
				counter = 0;
			}
			results.add(stat);
		}
		reader.close();
		timer.stop();
		System.out.println("Parsed " + results.size() + " in " + timer.getCompletedSeconds() + " seconds");
		return results;
		
		
	}
	
	
	
}
