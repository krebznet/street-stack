package com.dunkware.trade.tick.service.server.scrape;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.dunkware.trade.tick.service.server.ticker.repsoitory.TickerDO;
import com.dunkware.trade.tick.service.server.ticker.repsoitory.TickerRepository;
import com.dunkware.trade.tools.scrape.dumper.DumpParser;
import com.dunkware.trade.tools.scrape.dumper.DumpSymbol;

@Component
@Profile("ScrapeNYSE")
public class ScapeNYSE {
	
	@Autowired
	TickerRepository tickerRepo;
	

	@PostConstruct
	public void start() { 
		try {
			List<DumpSymbol> symbols = DumpParser.SymbolDumpParser("/Users/dkrebs/Downloads/tickers/data/other-listed_csv.csv", ",", true, 0, 1);
			System.out.println(symbols.size());
			List<DumpSymbol> filtered = new ArrayList<DumpSymbol>();
			for (DumpSymbol dumpSymbol : symbols) {
				if(!dumpSymbol.getSymbol().contains("$") && !dumpSymbol.getName().contains("$")) { 
					filtered.add(dumpSymbol);
				}
			}
			System.out.println(filtered.size());
			for (DumpSymbol symbol : filtered) {
				List<TickerDO> results = tickerRepo.findBySymbol(symbol.getSymbol());
				if(results.size() == 0) { 
					TickerDO ticker = new TickerDO();
					ticker.setSymbol(symbol.getSymbol());
					ticker.setSecurityName(symbol.getName());
					ticker.setStatus(TickerDO.STATUS_PENDING);
					try {
						tickerRepo.save(ticker);
						System.out.println("Inserted " + ticker.getSymbol() + " " + ticker.getSecurityName());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
