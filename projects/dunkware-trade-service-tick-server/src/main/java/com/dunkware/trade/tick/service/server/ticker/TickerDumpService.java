package com.dunkware.trade.tick.service.server.ticker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.trade.tick.service.server.ticker.repsoitory.TickerDO;
import com.dunkware.trade.tick.service.server.ticker.repsoitory.TickerRepository;
import com.dunkware.trade.tools.scrape.nasdaq.NasdaqSymbol;
import com.dunkware.trade.tools.scrape.nasdaq.NasdaqSymbolDump;

@Component
@Profile("TickerDump")
public class TickerDumpService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TickerRepository repo;
	
	@PostConstruct
	public void startDump() {
		Dumper updater = new Dumper();
		updater.start();
	}
	
	
	
	private class Dumper extends Thread { 
		
		@Transactional
		public void run() { 
			
			try {
				System.out.println("Starting Dump Import " + new Date().toGMTString());
				List<NasdaqSymbol> dumps = NasdaqSymbolDump.parseSymbols("/Users/dkrebs/dunkware/dunkware-trade-tools-scrape/src/main/java/com/dunkware/trade/tools/scrape/nasdaq/nasdaqlisted.txt");
				List<TickerDO> results = new ArrayList<TickerDO>();
				for (NasdaqSymbol symbol : dumps) {
					TickerDO ent = new TickerDO();
					ent.setSymbol(symbol.getSymbol());
					ent.setFinancialStatus(symbol.getFinancialStatus());
					ent.setMarketCategory(symbol.getMarketCategory());
					ent.setNextShares(symbol.getNextShares());
					ent.setRoundLotSize(symbol.getRoundLotSize());
					ent.setSecurityName(symbol.getSecurityName());
					ent.setEtf(symbol.getEtf());
					ent.setTestIssue(symbol.getTestIssue());
					ent.setStatus(TickerDO.STATUS_PENDING);
					results.add(ent);
				}
				DStopWatch stop = DStopWatch.create();
				stop.start();
				repo.saveAll(results);
				stop.stop();
				System.out.println(stop.getCompletedSeconds());
			} catch (Exception e) {
				e.printStackTrace();
				
			
			}
			System.out.println("Finished Dump Import " + new Date().toGMTString());

			
		}
	}
}
