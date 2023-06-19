package com.dunkware.trade.service.beach.server.controller.mock;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.databean.DataBeanConnector;
import com.dunkware.common.util.datagrid.DataGridConsumer;
import com.dunkware.common.util.datagrid.DataGridUpdate;
import com.dunkware.common.util.datagrid.GlazedDataGrid;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.trade.service.beach.server.runtime.BeachTradeBean;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.ObservableElementList;

public class MockTradeEventList implements DataGridConsumer {
	
	public static MockTradeEventList newInstance(DExecutor executor, int size) { 
		return new MockTradeEventList(executor,size);
	}
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private ObservableElementList<BeachTradeBean> list;
	private AtomicInteger idSeq = new AtomicInteger();
	
	private Inserter inserter; 
	private Deleter deleter; 
	private Updater updater; 
	
	private DExecutor executor;
	
	private GlazedDataGrid dataGrid;
	
	private int size;
	private BlockingQueue<DataGridUpdate> updateQueue = new LinkedBlockingQueue<DataGridUpdate>();
	
	private MockTradeEventList(DExecutor executor, int size) { 
		this.executor = executor;
		this.size = size;
		list = new ObservableElementList<BeachTradeBean>(GlazedLists.threadSafeList(new BasicEventList<BeachTradeBean>()), new DataBeanConnector<BeachTradeBean>());
		dataGrid = new GlazedDataGrid(list, executor,"getId");
		dataGrid.addConsumer(this);
	}
	
	public void start() { 
		dataGrid.start();
		inserter = new Inserter();
		inserter.start();
		updater = new Updater();
		updater.start();
		deleter = new Deleter();
		deleter.start();
	}
	
	public void dispose() {
		inserter.interrupt();
		deleter.interrupt();
		updater.interrupt();
		dataGrid.removeConsumer(this);
		dataGrid.dispose();
	}
	
	
	public DataGridUpdate nextUpdate(int timeout, TimeUnit unit)  {
		try {
			return updateQueue.poll(timeout, unit);
		} catch (Exception e) {
			return null;
		}
		
	}
	
	
	
	@Override
	public void consumeUpdate(DataGridUpdate update) {
		updateQueue.add(update);
	}

	
	
	private class Inserter extends Thread {
		
		public void run() { 
			// create the intial ones from size
			int i = 0; 
			list.getReadWriteLock().writeLock().lock();
			while(i < size) { 
				BeachTradeBean b = new BeachTradeBean();
				b.setId(idSeq.incrementAndGet());
				b.setSymbol(DUUID.randomUUID(4));
				b.setStatus(getName());
				b.setStatus(randomStatus());
				b.setUpl(2.23);
				b.setRpl(3.42);
				b.setAllocatedSize(234);
				b.setFilledSize(200);
				b.setIdent(b.getSymbol() + "_" + b.getId());
				b.setCloseTime("todo");
				b.setClosingTime("todo");
				b.setEntryCommission(DRandom.getRandom(3, 2392));
				i++;
				list.add(b);
			}
			list.getReadWriteLock().writeLock().unlock();
			while(!interrupted()) {
				// lets insert a trade every 10 seconds
				
				try {
					Thread.sleep(10000);
					BeachTradeBean b = new BeachTradeBean();
					b.setId(idSeq.incrementAndGet());
					b.setSymbol(DUUID.randomUUID(4));
					b.setStatus(getName());
					b.setStatus(randomStatus());
					b.setUpl(2.23);
					b.setRpl(3.42);
					b.setAllocatedSize(234);
					b.setFilledSize(200);
					b.setIdent(b.getSymbol() + "_" + b.getId());
					b.setCloseTime("todo");
					b.setClosingTime("todo");
					b.setEntryCommission(DRandom.getRandom(3, 2392));
					list.getReadWriteLock().writeLock().lock();
					list.add(b);
					list.getReadWriteLock().writeLock().unlock();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		
	}
	
	private String randomStatus() { 
		int value = DRandom.getRandom(1, 5); 
		switch(value) { 
		case 1: 
			return "Opening";
		case 2: 
			return "Open"; 
		case 3: 
			return "Closed"; 
		case 4: 
			return "Closing";
		case 5:
			return "Rejected";
			
		}
		return "Closed";
	}
	
	private class Deleter extends Thread { 
		
		public void run() { 
			try {
				while(!interrupted()) { 
					Thread.sleep(10000);
				
					try {
						list.getReadWriteLock().writeLock().lock();
						int deleteIndex = DRandom.getRandom(0, list.size() - 1);
						list.remove(deleteIndex);
						list.getReadWriteLock().writeLock().unlock();
		
					} catch (Exception e) {
						if (e instanceof InterruptedException) { 
							return;
						}
			
						logger.error("deleter " + e.toString());
					}
				}
			} catch (Exception e) {
				if (e instanceof InterruptedException) { 
					return;
				}
				logger.error("deleter " + e.toString());
			}
		}
		
	}
	
	private class Updater extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				try {
					Thread.sleep(1000);
					list.getReadWriteLock().readLock().lock();
					for (BeachTradeBean beachTradeBean : list) {
						beachTradeBean.setStatus(randomStatus());
						beachTradeBean.setFilledSize(beachTradeBean.getFilledSize() + 1);
					
					
					}
					list.getReadWriteLock().readLock().unlock();
					for (BeachTradeBean beachTradeBean : list) {
						beachTradeBean.notifyUpdate();
					
					}
				} catch (Exception e) {
					if (e instanceof InterruptedException) { 
						return;
					}
					logger.error("deleter " + e.toString());
				}
			
			}
		}
		
	}
	
}
