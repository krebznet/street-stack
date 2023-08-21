package com.dunkware.trade.service.beach.server.controller.mock;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.common.util.observable.ObservableBeanListConnector;
import com.dunkware.trade.service.beach.server.runtime.BeachBrokerBean;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.ObservableElementList;

public class MockBrokerEventList   {

	
	public volatile int counter = 1;
	public static volatile int insertThreads = 1;


	private Logger logger = LoggerFactory.getLogger(getClass());

	private ObservableElementList<BeachBrokerBean> list;
	private AtomicInteger idSeq = new AtomicInteger(1);

	private Inserter inserter;
	private Deleter deleter;
	private Updater updater;
	
	private int size;
	private int updateInterval;
	
	public static MockBrokerEventList newInstance(DExecutor executor, int size, int updateInterval) { 
		return new MockBrokerEventList(executor, size, updateInterval);
	}

	private MockBrokerEventList(DExecutor executor, int size, int updatInterval) {
		this.size = size;
		this.updateInterval = updatInterval;
		list = new ObservableElementList<BeachBrokerBean>(
				GlazedLists.threadSafeList(new BasicEventList<BeachBrokerBean>()),
				new ObservableBeanListConnector<BeachBrokerBean>());
		
	}
	
	public ObservableElementList<?> getList() { 
		return list;
	}

	public void start() {
		inserter = new Inserter();
		inserter.start();	
		updater = new Updater();
		updater.start();
		// deleter = new Deleter();
		// deleter.start();
	}

	public void dispose() {
		
		inserter.interrupt();
		// deleter.interrupt();
		updater.interrupt();
		
	//	dataGrid.dispose();
	}

	



	private class Inserter extends Thread {

		public void run() {
			setName("Inserter Thread " + insertThreads);
			insertThreads++;
			// create the intial ones from size
			int i = 0;

			while (i < size) {
				counter = counter + 1;
				BeachBrokerBean b = new BeachBrokerBean();
				b.setId(counter);
				b.setName("Broker" + counter);
				b.setAccounts(3);
				b.setSummary("Healthy");
				b.setStatus(randomStatus());

				list.getReadWriteLock().writeLock().lock();

				list.add(b);
				list.getReadWriteLock().writeLock().unlock();
				i++;
			}

		}

	}

	private String randomStatus() {
		int value = DRandom.getRandom(1, 5);
		switch (value) {
		case 1:
			return "Connecting";
		case 2:
			return "Connected";
		case 3:
			return "Disconnected";
		case 4:
			return "Exception";
		case 5:
			return "Maintaince";

		}
		return "Connected";
	}

	private class Deleter extends Thread {

		public void run() {
			try {
				while (!interrupted()) {
					Thread.sleep(1500);

					try {
						list.getReadWriteLock().writeLock().lock();
						if (list.size() == 0) {
							continue;
						}
						
						int deleteIndex = DRandom.getRandom(0, list.size() - 1);
						list.remove(deleteIndex);
						list.getReadWriteLock().writeLock().unlock();

					} catch (Exception e) {
						if (e instanceof InterruptedException) {
							return;
						}

						logger.error("deleter " + e.toString());
					} finally { 
						list.getReadWriteLock().writeLock().unlock();
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
			int count = 0;
			while (!interrupted()) {
				try {
					Thread.sleep(updateInterval);
					count++;

					list.getReadWriteLock().readLock().lock();
					for (BeachBrokerBean beachBrokerBean : list) {
						beachBrokerBean.setStatus(randomStatus());
						beachBrokerBean.setName(beachBrokerBean.getName() + 1);

					}
					list.getReadWriteLock().readLock().unlock();

					for (BeachBrokerBean beachBrokerBean : list) {
						beachBrokerBean.notifyUpdate();

					}
				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
					logger.error("deleter " + e.toString());
				} finally { 
					

				}

			}
		}

	}

}
