package com.dunkware.common.util.data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.executor.DExecutor;

public class NetScanner implements NetListWatcher {
	
	private List<NetScannerWatcher> watchers = new ArrayList<NetScannerWatcher>();
	private Semaphore watcherLock = new Semaphore(1);
	private int flushInterval = 500; 
	
	private NetScannerDelta activeDelta = new NetScannerDelta();
	private AtomicInteger deltaCount = new AtomicInteger(0);
	private boolean disposed = false; 
	private DeltaNotifyer notifier;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	// need a list of updates that get flushed 
	private NetList list; 
	private String keyId; 
	private DExecutor executor; 
		
	
	// delay update interval for deltas? 
	public static NetScanner newInstance(NetList list, String keyId, DExecutor executor) { 
		return new NetScanner(list, keyId,executor);
	}
	

	public static NetScanner newInstance(NetList list, String keyId, int flushInterval, DExecutor executor) { 
		return new NetScanner(list, keyId, flushInterval, executor);
	}
	

	private NetScanner(NetList list, String keyId, DExecutor executor) { 
		this.list = list; 
		this.keyId = keyId; 
		this.executor = executor; 
		init();
	}
	
	private NetScanner(NetList list, String keyId, int flushInterval, DExecutor executor) { 
		this.list = list; 
		this.keyId = keyId; 
		this.executor = executor; 
		this.flushInterval = flushInterval; 
		init();
	}
	
	private void init() { 
		list.addWatcher(this);
		notifier = new DeltaNotifyer();
		notifier.start();
		
	}
	
	public void dispose() { 
		if(!disposed) { 
			list.removeWatcher(this);
			notifier.interrupt();
		}
	}
	
	public String getKeyId() { 
		return keyId;
	}
	
	
	/**
	 * Used for components starting and want to send entire data set to client. 
	 * @return
	 */
	public NetScannerDelta listDelta() { 
		NetScannerDelta delta = new NetScannerDelta();
		for (NetBean bean : list.getBeans()) {
			delta.getInserts().add(bean);
		}
		return delta; 
	}
	
	
	public void addWatcher(NetScannerWatcher watcher) { 
		Runnable runner = new Runnable() {
			
			@Override
			public void run() {
				try {
					watcherLock.acquire();
					watchers.add(watcher);
				} catch (Exception e) {
					// TODO: handle exception
				} finally { 
					watcherLock.release();
				}
			}
		};
		
		executor.execute(runner);
	}
	
	public void removeWatcher(NetScannerWatcher watcher) { 
	Runnable runner = new Runnable() {
			
			@Override
			public void run() {
				try {
					watcherLock.acquire();
					watchers.remove(watcher);
				} catch (Exception e) {
					// TODO: handle exception
				} finally { 
					watcherLock.release();
				}
			}
		};
		
		executor.execute(runner);
	}


	@Override
	public void beanInsert(NetList list, NetBean bean) {
		//this.list.insert(bean);
		activeDelta.getInserts().add(bean);
		deltaCount.incrementAndGet();
	}


	@Override
	public void beanUpdate(NetList list, NetBean bean) {
		//this.list.update(bean);
		activeDelta.getUpdates().add(bean);
		deltaCount.incrementAndGet();
	}


	@Override
	public void beanRemove(NetList list, NetBean bean) {
		//this.list.remove(bean);
		activeDelta.getDeletes().add(bean.getValue(keyId));
		deltaCount.incrementAndGet();
	}
	
	
	private class DeltaNotifyer extends Thread { 
		
		public void run() { 
			setName("Net Scanner Notifier");
			while(!interrupted()) { 
				try {
					Thread.sleep(flushInterval);;					
				} catch (Exception e) {
					if (e instanceof InterruptedException)
						return;
					}
				
				if(deltaCount.get() > 0) { 
					NetScannerDelta delta = NetScanner.this.activeDelta;
					deltaCount.set(0);
					activeDelta = new NetScannerDelta();
					
					try {
						watcherLock.acquire();
						for (NetScannerWatcher netScannerWatcher : watchers) {
							try {
								netScannerWatcher.scannerDelta(NetScanner.this, delta);								
							} catch (Exception e) {
								if (e instanceof InterruptedException) {
									return;
								}	
								logger.error("Exception calling net scanner watcher " + netScannerWatcher.getClass().getName() + " " + e.toString());
							}

						}
					} catch (Exception e) {
						if (e instanceof InterruptedException) {
							return;
						}
					} finally { 
						watcherLock.release();
					}
					
			
			}
				

			}
		}
	}
	
	
	
	
	
	

}
