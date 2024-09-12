package com.dunkware.trade.tick.service.server.scrape.nasdaq;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.utils.core.helpers.DunkFile;

public class NasdaqParser {
	
	public static void main(String[] args) {
		try {
			List<NasdaqTicker> tickers = parse("/Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-trade-service-tick-server/src/main/resources/tickers/fuckyou.csv");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public static List<NasdaqTicker> parse(String filepath) throws Exception { 
		try {
			List<NasdaqTicker> tickers = new ArrayList<NasdaqTicker>();
			File file = new File(filepath);
			List<String> lines = DunkFile.readFileLines(file);
			int goodCount = 0;
			int count = 0;
			boolean first = true; 
			for (String string : lines) {
				if(first) { 
					first = false;
					continue;
				}
				String[] parsed = string.split(",");
				NasdaqTicker ticker = new NasdaqTicker();
				ticker.setVolume(-1);
				ticker.setSymbol(parsed[0]);
				ticker.setName(parsed[1]);
				String last = parsed[2];
				if(last.startsWith("$")) { 
					last = last.substring(1,last.length());
				}
				Double lastDouble = Double.valueOf(last);
				ticker.setLast(lastDouble);
				String volume = parsed[8];
				if(volume.length() > 0) { 
					ticker.setVolume(Long.valueOf(volume));
				}
				count++;
				if(ticker.getVolume() > 100000 && ticker.getLast() > 3.0) { 
					goodCount++;
				}
				tickers.add(ticker);
				
			}
			
			System.out.println("Count:" + count  + "Good: " + goodCount);
			return tickers;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			
		}
	}

}
