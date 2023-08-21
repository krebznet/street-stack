package com.dunkware.common.util.glazed;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlazedDataGridService {

	public static final int UPDATE_INTERVAL = 400;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static GlazedDataGridService instance;
	private ConcurrentHashMap<String, GlazedDataGrid> grids = new ConcurrentHashMap<String, GlazedDataGrid>();
	private Semaphore gridLock = new Semaphore(1);
	private UpdateDispatcher dispatcher = new UpdateDispatcher();

	public static GlazedDataGridService get() {
		if (instance == null) {
			instance = new GlazedDataGridService();
		}
		return instance;
	}

	private GlazedDataGridService() {
		dispatcher = new UpdateDispatcher();
		dispatcher.start();
	}

	public void register(GlazedDataGrid grid) {
		try {
			gridLock.acquire();
			grids.put(grid.getId(), grid);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			gridLock.release();
		}

	}

	public void unregister(GlazedDataGrid grid) {
		try {
			gridLock.acquire();
			grids.remove(grid.getId());
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			gridLock.release();
		}

	}

	private class UpdateDispatcher extends Thread {

		public void run() {

			while (!interrupted()) {
				try {
					Thread.sleep(UPDATE_INTERVAL);	
				} catch (Exception e) {
					logger.error("Should not be interruped in update dispatcher" );
				}
				
				try {
					gridLock.acquire();
					for (GlazedDataGrid grid : grids.values()) {
						if (grid.isRunning()) {
							grid.emitUpdates();
						}
					}
				} catch (Exception e) {
					logger.error("Exception dispatching grid updates " + e.toString());
				} finally { 
					gridLock.release();
				}

			}
		}

	}

}
