package com.dunkware.xstream.core.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamClock;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityListener;
import com.dunkware.xstream.api.XStreamEntityQuery;
import com.dunkware.xstream.api.XStreamException;
import com.dunkware.xstream.api.XStreamExecutor;
import com.dunkware.xstream.api.XStreamExtension;
import com.dunkware.xstream.api.XStreamInput;
import com.dunkware.xstream.api.XStreamListener;
import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.api.XStreamRowSignal;
import com.dunkware.xstream.api.XStreamService;
import com.dunkware.xstream.api.XStreamRowSignalListener;
import com.dunkware.xstream.api.XStreamRuntimeErrorListener;
import com.dunkware.xstream.api.XStreamStatService;
import com.dunkware.xstream.api.XStreamStatus;
import com.dunkware.xstream.api.XStreamTickRouter;
import com.dunkware.xstream.core.XStreamClockImpl;
import com.dunkware.xstream.core.XStreamTickRouterImpl;
import com.dunkware.xstream.model.entity.query.type.XStreamEntityQueryType;
import com.dunkware.xstream.model.metrics.XStreamMetrics;
import com.dunkware.xstream.util.XStreamStatsBuilder;

import io.vertx.core.Future;

public class MockXStream implements XStream {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private volatile ConcurrentHashMap<String, XStreamEntity> rows = new ConcurrentHashMap<String, XStreamEntity>();

	private XStreamStatus status = XStreamStatus.Created;

	// Components
	private XStreamClockImpl clock;
	private XStreamTickRouterImpl tickRouter;
	private XStreamInput input;

	// Stream Listeners
	private List<XStreamListener> streamListeners = new ArrayList<XStreamListener>();
	private Semaphore streamListenerLock = new Semaphore(1);

	// Row Listeners
	private List<XStreamEntityListener> rowListeners = new ArrayList<XStreamEntityListener>();
	private Semaphore rowListenerLock = new Semaphore(1);
	private RowListener rowListener = new RowListener();

	private List<XStreamRowSignalListener> signalListeners = new ArrayList<XStreamRowSignalListener>();
	private Semaphore signalListenerLock = new Semaphore(1);

	// Extensions & Services
	private List<XStreamExtension> extensions = new ArrayList<XStreamExtension>();
	private List<XStreamService> services = new ArrayList<XStreamService>();

	private XStreamExecutor executor;

	private String sessionId;
	
	private List<String> rowIdentifiers = new ArrayList<String>();
	
	
	

	@Override
	public void start(XStreamInput input) throws XStreamException {
		rowIdentifiers.add("AAPL");
		rowIdentifiers.add("SPY");
		
	}

	@Override
	public void dispose() throws XStreamException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public XStreamStatus getStatus() {
		return status;
	}

	@Override
	public XStreamEntity getRow(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getRowIdentifiers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<XStreamEntity> getRows() {
		List<XStreamEntity> list = new ArrayList<XStreamEntity>();
		for (Map.Entry<String, XStreamEntity> entry : rows.entrySet()) {
			list.add(entry.getValue());
		}
		return list;
	}

	@Override
	public XStreamStatService getStatProvider() {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public XStreamEntity createRow(String rowId, int rowIdentifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XStreamExecutor getExecutor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasRow(String id) {
		return rows.containsKey(id);
	}

	@Override
	public XStreamTickRouter getTickRouter() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public XStreamClock getClock() {
		return clock;
	}

	@Override
	public XStreamInput getInput() {
		return input;
	}

	@Override
	public void addStreamListener(XStreamListener listener) {
		try {
			streamListenerLock.acquire();
			streamListeners.add(listener);
		} catch (Exception e) {

		} finally {
			streamListenerLock.release();
		}
	}

	@Override
	public void removeStreamListener(XStreamListener listener) {
		try {
			streamListenerLock.acquire();
			streamListeners.remove(listener);
		} catch (Exception e) {

		} finally {
			streamListenerLock.release();
		}
	}

	@Override
	public String[] getRowIds() {
		return rows.keySet().toArray(new String[rows.keySet().size()]);
	}

	@Override
	public int getRowCount() {
		return rows.size();
	}

	@Override
	public XStreamMetrics getStats(boolean rowStats, boolean varStats, String rows) throws XStreamException {
		return XStreamStatsBuilder.build(this, rowStats, varStats, rows);

	}

	@Override
	public void addSignalListener(final XStreamRowSignalListener list) {
		Runnable me = new Runnable() {

			@Override
			public void run() {
				try {
					signalListenerLock.acquire();
					signalListeners.add(list);
				} catch (Exception e) {
					logger.error("Exception adding signal listener " + e.toString());
				} finally {
					signalListenerLock.release();
				}

			}
		};
		getExecutor().execute(me);
	}

	@Override
	public void removeSignalListener(final XStreamRowSignalListener list) {
		Runnable me = new Runnable() {

			@Override
			public void run() {
				try {
					signalListenerLock.acquire();
					signalListeners.remove(list);
				} catch (Exception e) {
					logger.error("Exception adding signal listener " + e.toString());
				} finally {
					signalListenerLock.release();
				}

			}
		};
		getExecutor().execute(me);

	}

	@Override
	public Marker getSessionMarker() {
		return MarkerFactory.getMarker(sessionId);
	}
	
	

	@Override
	public XStreamExtension getExtension(Class clazz) throws XStreamException {
		for (XStreamExtension extension : extensions) {
			if(clazz.isInstance(extension)) { 
				return extension; 
			}
		}
		throw new XStreamException("Extension Class " + clazz.getName() + " not found");
	}

	@Override
	public <T> T getService(Class<T> clazz) throws XStreamException {
		for (XStreamService service : services) {
			if (clazz.isInstance(service)) {
				return (T) service;
			}
		}
		throw new XStreamException("Service Class " + clazz.getName() + " not found");
	}

	@Override
	public void addRowListener(XStreamEntityListener listener) {
		Runnable runner = new Runnable() {

			@Override
			public void run() {
				try {
					rowListenerLock.acquire();
					rowListeners.add(listener);
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					rowListenerLock.release();
				}
			}
		};

		getExecutor().execute(runner);
	}

	@Override
	public void removeRowListener(XStreamEntityListener listener) {
		Runnable runner = new Runnable() {

			@Override
			public void run() {
				try {
					rowListenerLock.acquire();
					rowListeners.remove(listener);
				} catch (Exception e) {
					logger.error("Remove Row Listener Exception " + e.toString(), e);
				} finally {
					rowListenerLock.release();
				}
			}
		};

		getExecutor().execute(runner);
	}
	

	@Override
	public Future<XStreamEntityQuery> buildEntityQuery(XStreamEntityQueryType model)   {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * RowListener for routing stream row events to RowListeners registered on the
	 * stream level.
	 */
	private class RowListener implements XStreamEntityListener {

		@Override
		public void rowSignal(XStreamEntity row, XStreamRowSignal signal) {
			try {
				signalListenerLock.acquire();
				for (XStreamRowSignalListener list : signalListeners) {
					list.onSignal(signal);
				}
			} catch (Exception e) {
				logger.error("Exception Outer Row Listener " + e.toString(), e);

			} finally {
				signalListenerLock.release();
			}
			try {
				rowListenerLock.acquire();
				for (XStreamEntityListener list : rowListeners) {
					list.rowSignal(row, signal);
				}
			} catch (Exception e) {
				logger.error("Exception Outer Row Listener " + e.toString(), e);

			} finally {
				rowListenerLock.release();
			}
		}

	}

	@Override
	public String getSessionId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void runtimeError(String type, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addRuntimeErrorListener(XStreamRuntimeErrorListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeRuntimeErrorListener(XStreamRuntimeErrorListener listener) {
		// TODO Auto-generated method stub
		
	}

	
	
}
