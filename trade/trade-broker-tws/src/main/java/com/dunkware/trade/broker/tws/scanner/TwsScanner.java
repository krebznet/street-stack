/**
 * 
 */
package com.dunkware.trade.broker.tws.scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author Duncan Krebs
 * @category Day Trader
 * @date Aug 3, 2014
 */
public class TwsScanner {

	private List<TwsScannerListener> _listeners = new ArrayList<TwsScannerListener>();
	private Semaphore _listenerSemaphore = new Semaphore(1);
	private int _scannerId; 
	private TwsScannerService _scannerService; 
	private List<TwsScannerHit> _hits = new ArrayList<TwsScannerHit>();
	
	public TwsScanner(int scannerId, TwsScannerService scannerService) { 
		_scannerId = scannerId;
		_scannerService = scannerService;
	}
	
	public void addListener(TwsScannerListener listener) {
		try {
			_listenerSemaphore.acquire();
			_listeners.add(listener);
		} catch (Exception e) {
			
		} finally {
			_listenerSemaphore.release();
		}
	}
	
	void removeListener(TwsScannerListener listener) {
		try {
			_listenerSemaphore.acquire();
			_listeners.remove(listener);
		} catch (Exception e) {
			
		} finally {
			_listenerSemaphore.release();
		}
		
	}
	
	public List<TwsScannerHit> getHits() { 
		return _hits;
	}
	
	public int getScannerId() { 
		return _scannerId;
	}
	
	public boolean hasHit(String symbol) { 
		for (TwsScannerHit twsScannerHit : _hits) {
			if(twsScannerHit.getSymbol().equals(symbol)) { 
				return true;
			}
		}
		return false;
	}
	
	public TwsScannerHit getHit(String symbol) { 
		for (TwsScannerHit twsScannerHit : _hits) {
			if(twsScannerHit.getSymbol().equals(symbol)) { 
				return  twsScannerHit;
			}
		}
		return null;
	}
	
	public TwsScannerHit getHit(int rank) { 
		for (TwsScannerHit twsScannerHit : _hits) {
			if(twsScannerHit.getRank() == rank) { 
				return  twsScannerHit;
			}
		}
		return null;
	}
	
	public void notifyEvent(TwsScannerEvent event) { 
		try {
			_listenerSemaphore.acquire();
			for (TwsScannerListener listener : _listeners) {
				listener.scannerEvent(event);
			}
		} catch (Exception e) {
			
		} finally { 
			_listenerSemaphore.release();
		}
	}
	
	public void disposeScanner() { 
		_scannerService.disposeScanner(this);
		
		
	}
}
