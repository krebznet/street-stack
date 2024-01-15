package com.dunkware.trade.service.data.common.cassandra;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.oss.driver.api.core.CqlSession;
import com.dunkware.xstream.model.snapshot.SnapshotEntity;
import com.dunkware.xstream.model.snapshot.SnapshotType;
import com.dunkware.xstream.model.snapshot.SnapshotValue;

public class SnapshotInjestor {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private BlockingQueue<SnapshotValue> queue = new LinkedBlockingQueue<SnapshotValue>();
	
	private CqlSession session; 
	private AtomicBoolean disposed = new AtomicBoolean(false);
	private SnapshotInjestorBean bean;
	
	public SnapshotInjestor(CqlSession session) { 
		this.session = session;
	}
	
	
	
	public SnapshotInjestorBean getBean() { 
		return bean;
	}
	
	private class Injestor extends Thread { 
		
		public void run() { 
			while(true) { 
				SnapshotValue value = null;
				try {
					value = queue.poll(5, TimeUnit.SECONDS);
					if(value == null) { 
						if(disposed.get() == true) {
							return;
						}
					}
				} catch (Exception e) {
					if(disposed.get() == true) { 
						return;
					}
					logger.error("Exception polling snapshot value " + e.toString());
				}
				if(value.getType() == SnapshotType.ENTITY) { 
					SnapshotEntity entity = value.getEntity();
					
				}
				
				
			}
		}
		
	}

}
