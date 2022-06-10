package com.dunkware.xstream.net.core.container.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Semaphore;

import com.dunkware.xstream.net.core.container.ContainerSearchException;
import com.dunkware.xstream.net.core.scanner.StreamEntityScanner;
import com.dunkware.xstream.net.core.scanner.StreamEntityScannerListener;
import com.dunkware.xstream.net.core.scanner.StreamEntityScannerRow;
import com.dunkware.xstream.net.model.search.SessionEntitySearch;

public class ContainerEntityScannerImpl implements StreamEntityScanner {

	private List<StreamEntityScannerListener> listeners = new ArrayList<StreamEntityScannerListener>();
	private Semaphore listenerLock = new Semaphore(1);
	private SessionEntitySearch search;
	private int scannerId;
	private ContainerImpl container;
	
	

	public void start(ContainerImpl container, SessionEntitySearch search, int scannerId)
			throws ContainerSearchException {
		this.scannerId = scannerId;
		this.search = search;
		this.container = container;

	}

	@Override
	public void addListener(final StreamEntityScannerListener listener) {
		Runnable adder = new Runnable() {

			@Override
			public void run() {
				try {
					listenerLock.acquire();
					listeners.add(listener);
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					listenerLock.release();
				}

			}
		};
		container.getExecutor().execute(adder);
	}

	@Override
	public void removeListener(StreamEntityScannerListener listener) {
		Runnable adder = new Runnable() {

			@Override
			public void run() {
				try {
					listenerLock.acquire();
					listeners.remove(listener);
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					listenerLock.release();
				}

			}
		};
		container.getExecutor().execute(adder);

	}

	@Override
	public Collection<StreamEntityScannerRow> getRows() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(SessionEntitySearch search) throws ContainerSearchException {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public int scannerId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
