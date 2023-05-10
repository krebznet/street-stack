package com.dunkware.trade.tick.service.server.scrape.nasdaq;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.dunkware.trade.tick.service.server.ticker.repsoitory.TickerDO;
import com.dunkware.trade.tick.service.server.ticker.repsoitory.TickerRepository;

@Profile("Nasdaq")
@Service
public class NasdaqService {

	@Autowired
	private TickerRepository repo;

	@PostConstruct
	private void load() {
		Runner runner = new Runner();
		runner.start();
	}

	private class Runner extends Thread {

		public void run() {
			List<NasdaqTicker> tickers = null;
			try {
				tickers = NasdaqParser.parse(
						"/Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-trade-service-tick-server/src/main/resources/tickers/fuckyou.csv");

			} catch (Exception e) {
				e.printStackTrace();
			}
			Iterable<TickerDO> ents = repo.findAll();
			Map<String,Boolean> fuck = new ConcurrentHashMap<String,Boolean>();
			
			for (TickerDO tickerDO : ents) {
				fuck.put(tickerDO.getSymbol().toUpperCase(), true);
			}
			List<TickerDO> inserts = new ArrayList<TickerDO>();
			for (NasdaqTicker ticker : tickers) {
				
				if (ticker.getVolume() > 0 && ticker.getLast() > 2.50) {
					boolean exists = false;
					if(fuck.containsKey(ticker.getSymbol().toUpperCase())) {
						exists = true;
					}
					if (!exists) {
						if(ticker.getSymbol().contains("^") == false) { 
							TickerDO insert = new TickerDO();
							insert.setPrice(ticker.getLast());
		
							insert.setAverageVolume(ticker.getVolume());
							insert.setSymbol(ticker.getSymbol().toUpperCase());
							insert.setSecurityName(ticker.getName());
							inserts.add(insert);
						} else { 
							System.out.println("bad " + ticker.getSymbol());
						}
						
					}
				}
				System.out.println(ticker.getSymbol());
			}
			System.out.println("inserting " + inserts.size() + "tickers ");

			repo.saveAll(inserts);
			System.out.println("saved");
		}
	}

}
