package com.dunkware.trade.tick.service.server.ticker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.dunkware.trade.tick.service.server.ticker.repsoitory.TickerRepository;

@Component
@Profile("TickerUpdate")
public class TickerUpdateService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private TickerRepository repo;
	
	/*
	 * @PostConstruct private void load() { Updater dumper = new Updater();
	 * dumper.start(); }
	 * 
	 * private class Updater extends Thread {
	 * 
	 * @Transactional public void run() { Iterable<TickerDO> ents = repo.findAll();
	 * for (TickerDO ent : ents) { if(ent.getStatus() == TickerDO.STATUS_PENDING) {
	 * try { System.out.println(" TIcker Status Pending " + ent.getSymbol());
	 * YahooQuote q = YahooQuoteScraper.newInstance().scrapeQuote(ent.getSymbol());
	 * ent.setAverageVolume(q.getAverageVolume());
	 * ent.setMarketCap(q.getMarketCap()); ent.setPrice(q.getMarketOpen());
	 * ent.setEps(q.getEps()); ent.setStatus(TickerDO.STATUS_GOOD);
	 * ent.setLastUpdate(new Date()); logger.debug("Update Success " +
	 * ent.getSymbol() + " " + q.getMarketOpen()); } catch (Exception e) {
	 * System.err.println(ent.getSymbol() + " " + e.toString());
	 * ent.setUpdateError(e.toString()); ent.setStatus(TickerDO.STATUS_BAD); }
	 * 
	 * repo.save(ent); System.out.println(ent.getSymbol() + " Price " +
	 * ent.getPrice() + " Market Cap " + ent.getMarketCap() + " Avg Vol " +
	 * ent.getAverageVolume()+ " EPS " + ent.getEps()); try { Thread.sleep(1000); }
	 * catch (Exception e) { System.err.println("Outer updater exception " +
	 * e.toString()); } } }
	 * 
	 * 
	 * }
	 * 
	 * }
	 */
	
}
