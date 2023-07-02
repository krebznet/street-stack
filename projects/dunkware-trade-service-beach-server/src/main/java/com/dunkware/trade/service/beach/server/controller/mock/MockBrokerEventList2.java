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
import com.dunkware.trade.service.beach.server.runtime.BeachBrokerBean;
import com.dunkware.trade.service.beach.server.runtime.BeachService;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.ObservableElementList;

public class MockBrokerEventList2 implements DataGridConsumer {

	public volatile int counter = 1;
	public static volatile int insertThreads = 1;

	public static MockBrokerEventList2 newInstance(DExecutor executor, int size) {
		return new MockBrokerEventList2(executor, size);
	}

	private Logger logger = LoggerFactory.getLogger(getClass());

	private ObservableElementList<BeachBrokerBean> list;
	private AtomicInteger idSeq = new AtomicInteger(1);

	private Inserter inserter;
	private Deleter deleter;
	private Updater updater;
	private GlazedDataGrid dataGrid;

	private int size;
	private BlockingQueue<DataGridUpdate> updateQueue = new LinkedBlockingQueue<DataGridUpdate>();

	private MockBrokerEventList2(DExecutor executor, int size) {

		this.size = 5;
		list = BeachService.brokerBeans;
		dataGrid = new GlazedDataGrid(list, executor, "getId");
		dataGrid.addConsumer(this);
	}

	public void start() {
		dataGrid.start();
		//inserter = new Inserter();
		//inserter.start();

		//updater = new Updater();
		//updater.start();
		// deleter = new Deleter();
		// deleter.start();
	}

	public void dispose() {
		//inserter.interrupt();
		// deleter.interrupt();
		//updater.interrupt();
		dataGrid.removeConsumer(this);
		dataGrid.dispose();
	}

	public DataGridUpdate nextUpdate(int timeout, TimeUnit unit) {
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
			setName("Inserter Thread " + insertThreads);
			insertThreads++;
			// create the intial ones from size
			int i = 0;

			while (i < 6) {
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
						if (list.size() == 0) {
							continue;
						}
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
			int count = 0;
			while (!interrupted()) {
				try {
					Thread.sleep(500);
					count++;

					list.getReadWriteLock().readLock().lock();
					for (BeachBrokerBean beachBrokerBean : list) {
						beachBrokerBean.setStatus(randomStatus());

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
				}

			}
		}

	}

}
