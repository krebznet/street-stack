package com.dunkware.trade.tick.service.server.test;

import java.io.File;
import java.util.List;

import com.dunkware.utils.core.helpers.DunkFile;

public class SymbolsToCsv {
	
	public static void main(String[] args) {
		try {
			
			
			File file = new File("/Users/duncankrebs/dunkware/street/cloud/main/dunkware-street-cloud/projects/dunkware-trade-service-tick-server/src/main/resources/tickers/5K");
			List<String> lines = DunkFile.readFileLines(file);
			int count = 0;
			StringBuilder out = new StringBuilder();
			for (String line : lines) {
				if(count > 0) { 
					out.append(",");
					out.append(line);
				} else { 
					out.append(line);
				}
				count++;
			}
			System.out.println(out.toString());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
