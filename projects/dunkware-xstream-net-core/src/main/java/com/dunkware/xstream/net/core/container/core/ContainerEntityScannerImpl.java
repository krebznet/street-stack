package com.dunkware.xstream.net.core.container.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.data.NetList;
import com.dunkware.common.util.data.NetScanner;
import com.dunkware.xstream.model.search.SessionEntityScanner;
import com.dunkware.xstream.model.search.SessionEntitySearch;
import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerEntityQuery;
import com.dunkware.xstream.net.core.container.ContainerEntityScanner;
import com.dunkware.xstream.net.core.container.ContainerListener;
import com.dunkware.xstream.net.core.container.ContainerSearchException;
import com.dunkware.xstream.net.core.container.ContainerSearchResults;

public class ContainerEntityScannerImpl implements ContainerEntityScanner, ContainerListener {

	private Container container;
	private SessionEntityScanner entityScanner;
	private boolean disposed = false;
	private NetScanner netScanner;
	private NetList netList = new NetList();
	private List<String> vars;

	private List<ContainerEntity> entities = new ArrayList<ContainerEntity>();
	private Semaphore entityLock = new Semaphore(1);
	
	private ContainerEntityQuery entityQuery;
	private AtomicInteger timeUpdateCounter = new AtomicInteger();
	private int scanInterval = 1;
	
	private boolean running = true; 

	private ScannerUpdater scannerUpdater;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void init(Container container, SessionEntityScanner scanner) throws ContainerSearchException {
		this.entityScanner = scanner;
		this.container = container;
		this.scanInterval = scanner.getScanInterval();
		this.vars = scanner.getSearch().getRetVars();
		scannerUpdater = new ScannerUpdater();
		netList = new NetList();
		netScanner = NetScanner.newInstance(netList, "id");
		try {
			entityQuery = container.entityQuery(scanner.getSearch());
		} catch (Exception e) {
			throw new ContainerSearchException("Exception Creating Entity Scanner + " + e.toString());
		}
		container.addListener(this);
	}

	@Override
	public NetScanner netScanner() {
		return netScanner;
	}

	@Override
	public void dispose() {
		if(!disposed) { 
			container.removeListener(this);
		}
	}

	@Override
	public void update(SessionEntitySearch search, List<String> vars) throws ContainerSearchException {
		entityQuery.dispose();
		try {
			entityQuery = container.entityQuery(search);
			
		} catch (Exception e) {
			running = false; 
			throw new ContainerSearchException("Updated Filters Threw Exception " + e.toString());
		}
	}

	@Override
	public void timeUpdate(Container container) {
		if (timeUpdateCounter.incrementAndGet() == scanInterval) {
			timeUpdateCounter.set(0);
			if(running & !disposed) { 
				container.getExecutor().execute(scannerUpdater);
			}
		}

	}

	@Override
	public void newSession() {
		// hmm not sure 
	}

	private class ScannerUpdater implements Runnable {

		@Override
		public void run() {
			// okay here we go 
			try {
				ContainerSearchResults<ContainerEntity> results = entityQuery.execute();
				if(results.getResults().size() == 0) { 
					return; 
				}
				for (ContainerEntity resultEntity : results.getResults()) {
					// if already in scanner entity list update it 
					if(entities.contains(resultEntity)) { 
						netList.update(resultEntity.toBean(vars));
					} else { 
						// else insert it into entities and net list 
						entities.add(resultEntity);
						netList.insert(resultEntity.toBean(vars));
					}
					// iterate through scanner entities and remove the ones that are not in the results 
					try {
						List<ContainerEntity> removals = new ArrayList<ContainerEntity>();
						
						entityLock.acquire();
						for (ContainerEntity scannerEntity : entities) {
							if(results.getResults().contains(scannerEntity) == false) { 
								removals.add(resultEntity);
								netList.remove(scannerEntity.toBean(vars));
							}
						
						}
						for (ContainerEntity containerEntity : removals) {
							entities.remove(containerEntity);
						}
						
					} catch (Exception e) {
						logger.error("Internal Exception updating scanner entity " + e.toString());
					} finally { 
						entityLock.release();
					}
					
				}
			} catch (Exception e) {
				logger.error("Outside exception in entity container scanner updater " + e.toString());
			}
		}

	}

}
