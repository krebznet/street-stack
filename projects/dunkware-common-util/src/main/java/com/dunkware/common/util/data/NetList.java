package com.dunkware.common.util.data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * Not going to make this thread safe we don't fuckin care on the user of the shit 
 * 
 * @author Duncan Krebs 
 *
 */
public class NetList {

	private List<NetBean> beans = new ArrayList<NetBean>();
	private List<NetListWatcher> watchers = new ArrayList<NetListWatcher>();
	private Semaphore watcherLock = new Semaphore(1);
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("NetList");
	public void insert(NetBean bean) { 
		try {
			
			watcherLock.acquire();
			if(logger.isDebugEnabled()) { 
				logger.debug(marker,"insert watch lock acquired");
			}
			beans.add(bean);
			for (NetListWatcher watcher : watchers) {
				watcher.beanInsert(this, bean);
			}
		} catch (Exception e) {
			logger.error("Internal Hide Me Error : Net List Insert Watcher Notify Exception " + e.toString());
		} finally { 
			
			watcherLock.release();
			if(logger.isDebugEnabled()) { 
				logger.debug(marker,"insert watch lock released");
			}
		}
	}
	
	public List<NetBean> getBeans() { 
		return beans;
	}
	
	/**
	 * This is mostly for notification of watchers 
	 * @param bean
	 */
	public void update(NetBean bean) { 
		try {
			watcherLock.acquire();
			if(logger.isDebugEnabled()) { 
				logger.debug(marker,"update watch lock acquired");
			}
			// right if its updated it could have new values
			// net beans ? are immutable 
			beans.remove(bean);
			beans.add(bean);
			for (NetListWatcher watcher : watchers) {
				watcher.beanUpdate(this, bean);
			}
		} catch (Exception e) {
			logger.error("Internal Hide Me Error : Net List Insert Watcher Notify Exception " + e.toString());
		} finally { 
			watcherLock.release();
			if(logger.isDebugEnabled()) { 
				logger.debug(marker,"update watch lock released");
			}
		}
		
	}
	
	/**
	 * Removes a bean from the list and notify's watchers 
	 * @param bean
	 */
	public void remove(NetBean bean) { 
		try {
			watcherLock.acquire();
			if(logger.isDebugEnabled()) { 
				logger.debug(marker,"remove watch lock acquired");
			}
			beans.remove(bean);
			for (NetListWatcher watcher : watchers) {
				watcher.beanRemove(this, bean);
			}
		} catch (Exception e) {
			logger.error("Internal Hide Me Error : Net List Insert Watcher Notify Exception " + e.toString());
		} finally { 
			watcherLock.release();
			if(logger.isDebugEnabled()) { 
				logger.debug(marker,"remove watch lock acquired");
			}
		}
	}
	
	public void addWatcher(NetListWatcher watcher) { 
		try {
			watcherLock.acquire();
			if(logger.isDebugEnabled()) { 
				logger.debug(marker,"add watcger watch lock acquired");
			}
			watchers.add(watcher);
		} catch (Exception e) {
			logger.error("Internal Hide Me Error : Net List Insert Watcher Add Exception " + e.toString());
		} finally { 
			watcherLock.release();
			if(logger.isDebugEnabled()) { 
				logger.debug(marker,"add watcger watch lock acquired");
			}
		}
		
	}
	
	public void removeWatcher(NetListWatcher watcher) { 
		try {
			watcherLock.acquire();
			if(logger.isDebugEnabled()) { 
				logger.debug(marker,"remove watcher watch lock acquired");
			}
			watchers.remove(watcher);
		} catch (Exception e) {
			logger.error("Internal Hide Me Error : Net List Insert Watcher Remove Exception " + e.toString());
		} finally { 
			watcherLock.release();
			if(logger.isDebugEnabled()) { 
				logger.debug(marker,"remove watcher watch lock acquired");
			}
		}
		
	}
}
