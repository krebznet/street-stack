/**
 * 
 */
package com.dunkware.trade.broker.tws.scanner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.trade.broker.tws.TwsBroker;
import com.dunkware.trade.broker.tws.connector.TwsConnector;
import com.dunkware.trade.broker.tws.connector.TwsConnectorService;
import com.dunkware.trade.broker.tws.connector.TwsSocketReader;
import com.ib.client.ContractDetails;
import com.ib.client.ScannerSubscription;

/**
 * @author Duncan Krebs
 * @category Day Trader
 * @date Aug 3, 2014
 */
public class TwsScannerService implements TwsSocketReader, TwsConnectorService {

	private Logger _logger = LoggerFactory.getLogger(getClass());
	
	private TwsBroker _broker;
	private Map<Integer,TwsScanner> _scanners = new HashMap<Integer,TwsScanner>();
	private Semaphore _scannerSemaphore = new Semaphore(1);
	private AtomicInteger _nextSubscriptionId = new AtomicInteger(1); 
	
	@Override
	public void initService(TwsBroker borker) {
		_broker = borker;
		
	}

	@Override
	public void startService() {
		_broker.addSocketReader(this);
		
	}

	@Override
	public void disposeService() {
		
		
	}
	
	public TwsScanner createScanner(TwsScannerInput input) throws Exception { 
		int scannerId = _nextSubscriptionId.incrementAndGet();
		ScannerSubscription sub = new ScannerSubscription();
		sub.scanCode(input.getScannerType().name());
		if(input.getAbovePrice() != null) { 
			sub.abovePrice(input.getAbovePrice());
		}
		if(input.getBelowPrice() != null) { 
			sub.belowPrice(input.getBelowPrice());
		}
		if(input.getAboveVolume() != null) { 
			sub.aboveVolume(input.getAboveVolume());
		}
		if(input.getAboveMarketCap() != null) { 
			sub.marketCapAbove(input.getAboveMarketCap());	
		}
		if(input.getBelowMarketCap() != null) { 
			sub.marketCapBelow(input.getBelowMarketCap());
		}
		if(input.getInstrument().equals(TwsScannerInstrument.USStocks)) {
			sub.locationCode("STK.US.MAJOR");
			sub.instrument("STK");
		} 
		if(input.getInstrument().equals(TwsScannerInstrument.USFutures)) { 
			sub.locationCode("FUT.US");
			sub.instrument("FUT.US");
		}
		sub.numberOfRows(50);
	
		TwsScanner scanner = new TwsScanner(scannerId, this);
		_scanners.put(scannerId, scanner);
		
		//_broker.getClientSocket().reqScannerSubscription(scannerId, sub);
		
		return scanner;
	}

	
	@Override
	public void scannerData(int reqId, int rank,
			ContractDetails contractDetails, String distance, String benchmark,
			String projection, String legsStr) {
		_logger.debug("Scanner data " + reqId + " rank:" + rank + " symbol:" + contractDetails.underSymbol());
		try {
			_scannerSemaphore.acquire();
			TwsScanner scanner = _scanners.get(reqId);
			if(scanner == null) { 
				_logger.error("Getting scanner data for scanner not found in map, cancelling sub data for id " + reqId);;
			}
			String symbol = contractDetails.underSymbol();
			
			TwsScannerHit hit = scanner.getHit(symbol);
			if(hit == null) { 
				hit = new TwsScannerHit(symbol, rank,contractDetails);
				scanner.getHits().add(hit);
				TwsScannerEvent event = new TwsScannerEvent(TwsScannerEvent.HIT_ADDED, hit, scanner);
				scanner.notifyEvent(event);
				return;
			}
			// hit != null -- update it ? 
			hit.setRank(rank);;
			TwsScannerEvent event = new TwsScannerEvent(TwsScannerEvent.HIT_UPDATED, hit, scanner);
			scanner.notifyEvent(event);
			
		} catch (Exception e) {
			_logger.error("Error handling scanner data event");
		} finally { 
			_scannerSemaphore.release();
		}
		
		
	}

	public void disposeScanner(TwsScanner scanner) { 
		_broker.getClientSocket().cancelScannerSubscription(scanner.getScannerId());
	}
	@Override
	public void scannerDataEnd(int reqId) {
	
			// really don't care right? 
	}
	
	
	
	
	
	
	
	

}
