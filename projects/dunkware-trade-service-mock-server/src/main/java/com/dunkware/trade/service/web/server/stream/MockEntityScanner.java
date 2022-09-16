package com.dunkware.trade.service.web.server.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.data.NetBean;
import com.dunkware.common.util.data.NetList;
import com.dunkware.common.util.data.NetScanner;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.common.util.uuid.DUUID;

public class MockEntityScanner {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private String scannerId;

	private NetScanner netScanner;
	private NetList netList;
	
	private int beanCount = 100; 
	
	private Mocker mocker; 

	public void start(String scannerId) {
		logger.info("starting mock entity scanner " + scannerId);
		this.scannerId = scannerId;
		netList = new NetList();
		netScanner = NetScanner.newInstance(netList, scannerId, new DExecutor(4));
		// insert a couple of objects with ids;
		int i = 0;
		while(i < beanCount) { 
			NetBean bean = randomBean(i);
			netList.insert(bean);
			i++;
			
		}
		mocker = new Mocker();
		mocker.start();
	}

	public void stop() {
		logger.info("Stopping mock enttity scanner " + scannerId);
		mocker.interrupt();
	}
	
	public NetScanner getScanner() { 
		return netScanner;
	}
	
	public NetBean randomBean(int id) { 
		NetBean bean = new NetBean(); 
		bean.setValue("id", id);
		bean.setValue("Last", DRandom.getRandom(4, 43));
		bean.setValue("Entity", DUUID.randomUUID(5));
		bean.setValue("Volume", DRandom.getRandom(3, 42323));
		return bean;
	}
	
	public NetBean randomUpdate(NetBean bean) { 
		bean.setValue("Last", DRandom.getRandom(4, 43));
		bean.setValue("Entity", DUUID.randomUUID(5));
		bean.setValue("Volume", DRandom.getRandom(3, 42323));
		return bean;
	}

	private class Mocker extends Thread {

		public void run() {
			try {
				
				while (!interrupted()) {
					Thread.sleep(1000); 
					// lets go through all the beans and do random updaes
					int i = 0; 
					while(i < beanCount) { 
						NetBean bean = netList.getBeans().get(i);
						bean = randomUpdate(bean);
						netList.update(bean);
						i++;
					}
				}
			} catch (Exception e) {
				if (e instanceof InterruptedException) {
					return;
					
				}
				logger.error("Error in entity scanner mocker " + e.toString());
				return;
			}
		}
	}

}
