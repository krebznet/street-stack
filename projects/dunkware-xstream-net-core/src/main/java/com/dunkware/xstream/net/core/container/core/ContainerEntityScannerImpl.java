package com.dunkware.xstream.net.core.container.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.net.proto.netstream.GEntityMatcher;
import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerException;
import com.dunkware.xstream.net.core.container.ContainerSearchResults;
import com.dunkware.xstream.net.core.container.ContainerTimeListener;
import com.dunkware.xstream.net.core.container.search.ContainerEntityMatcherPredicateBuilder;
import com.dunkware.xstream.net.core.scanner.StreamEntityScanner;
import com.dunkware.xstream.net.core.scanner.StreamEntityScannerItem;
import com.dunkware.xstream.net.core.scanner.StreamEntityScannerListener;

public class ContainerEntityScannerImpl implements StreamEntityScanner, ContainerTimeListener {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private GEntityMatcher matcher; 
	
	private String scannerIdentifier;
	
	private List<Predicate<ContainerEntity>> matcherPredicates;
	private Container container;
	
	private List<StreamEntityScannerListener> handlers = new ArrayList<StreamEntityScannerListener>();
	private Semaphore handlerLock = new Semaphore(1);
	
	private boolean running = false; 
	
	private Map<String,StreamEntityScannerItem> items = new ConcurrentHashMap<String,StreamEntityScannerItem>();
	
	
	
	public void start(GEntityMatcher matcher, String scannerIdent, Container container	) throws ContainerException { 
		this.matcher = matcher; 
		this.scannerIdentifier = scannerIdent;
		try {
			matcherPredicates = ContainerEntityMatcherPredicateBuilder.build(matcher, container);
		} catch (Exception e) {
			throw new ContainerException("Exception Creating Matcher Predicates " + e.getLocalizedMessage());
		}
		
		container.getExecutor().execute(new ScannerUpdater());
		container.addTimeListener(this);
		running = true;
	}
	@Override
	public void addListener(StreamEntityScannerListener listener) {
		try {
			handlerLock.acquire();
			handlers.add(listener);
		} catch (Exception e) {
			logger.error("Exception adding listener " + e.toString());
		} finally { 
			handlerLock.release();
		}
	}

	@Override
	public void removeListener(StreamEntityScannerListener listener) {
		try {
			handlerLock.acquire();
			handlers.remove(listener);
		} catch (Exception e) {
			// TODO: handle exception
		} finally { 
			handlerLock.release();
		}
	}

	@Override
	public Collection<StreamEntityScannerItem> getItems() {
		return items.values();
		
	}
	

	@Override
	public void timeUpdate(Container container) {
		container.getExecutor().execute(new ScannerUpdater());
		
	}
	
	@Override
	public String getIdentifier() {
		return scannerIdentifier;
		
	}
	
	@Override
	public void dispose() {
		if(running) { 
			container.removeTimeListener(this);
			running = false;
		}

	}
	
	private class ScannerUpdater implements Runnable { 
		
		public void run() { 
			try {
				List<StreamEntityScannerItem> deletes = new ArrayList<StreamEntityScannerItem>();
				List<StreamEntityScannerItem> updates = new ArrayList<StreamEntityScannerItem>();
				List<StreamEntityScannerItem> inserts = new ArrayList<StreamEntityScannerItem>();
				// populate the deletes 
				ContainerSearchResults<ContainerEntity> results = container.entitySearch(matcherPredicates);
				for (StreamEntityScannerItem streamEntityScannerItem : items.values()) {
					ContainerEntityScannerItemImpl implItem = (ContainerEntityScannerItemImpl)streamEntityScannerItem;
					if(results.getResults().contains(implItem.getEntity()) == false) { 
						deletes.add(implItem);
					}
				}
				for (ContainerEntity entity : results.getResults()) {
					StreamEntityScannerItem item = items.get(entity.getIdent());
					if(item != null) { 
						// need to update the data here 
						updates.add(item);
					} else { 
						ContainerEntityScannerItemImpl newItem = new ContainerEntityScannerItemImpl();
						newItem.setEntity(entity);
						newItem.setEntityIdent(entity.getIdent());
						newItem.setTimeInScanner(1);
						//newItem.setData(null);
						inserts.add(newItem);
						items.put(entity.getIdent(), newItem);
					}
				}
				
			 try {
				handlerLock.acquire();
				for (StreamEntityScannerListener list : handlers) {
					try {
						list.scannerUpdate(inserts, updates, deletes);						
					} catch (Exception e) {
						logger.error("Entity Scanner Listener " + list.getClass().getName() + " exception on update " + e.toString(),e);
					}

				}
			} catch (Exception e) {
				logger.error("LIstener lock acquere exception " + e.toString());
			} finally { 
				handlerLock.release();
			}
				
			} catch (Exception e) {
				logger.error("Exception outer scanner updater " + e.toString());
			}

			
		}
	}

	

	
	
	

}
