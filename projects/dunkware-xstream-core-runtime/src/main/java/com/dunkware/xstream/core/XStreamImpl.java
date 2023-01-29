package com.dunkware.xstream.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamClock;
import com.dunkware.xstream.api.XStreamException;
import com.dunkware.xstream.api.XStreamExecutor;
import com.dunkware.xstream.api.XStreamExtension;
import com.dunkware.xstream.api.XStreamInput;
import com.dunkware.xstream.api.XStreamListener;
import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.api.XStreamRowListener;
import com.dunkware.xstream.api.XStreamRowSignal;
import com.dunkware.xstream.api.XStreamRuntimeException;
import com.dunkware.xstream.api.XStreamService;
import com.dunkware.xstream.api.XStreamSignalListener;
import com.dunkware.xstream.api.XStreamStatus;
import com.dunkware.xstream.api.XStreamTickRouter;
import com.dunkware.xstream.model.metrics.XStreamMetrics;
import com.dunkware.xstream.util.XStreamStatsBuilder;
import com.dunkware.xstream.xproject.model.XStreamExtensionType;

public class XStreamImpl implements XStream {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private volatile ConcurrentHashMap<String, XStreamRow> rows = new ConcurrentHashMap<String, XStreamRow>();

	private XStreamStatus status = XStreamStatus.Created;

	// Components
	private XStreamClockImpl clock;
	private XStreamTickRouterImpl tickRouter;
	private XStreamInput input;

	// Stream Listeners
	private List<XStreamListener> streamListeners = new ArrayList<XStreamListener>();
	private Semaphore streamListenerLock = new Semaphore(1);

	// Row Listeners
	private List<XStreamRowListener> rowListeners = new ArrayList<XStreamRowListener>();
	private Semaphore rowListenerLock = new Semaphore(1);
	private RowListener rowListener = new RowListener();

	private List<XStreamSignalListener> signalListeners = new ArrayList<XStreamSignalListener>();
	private Semaphore signalListenerLock = new Semaphore(1);

	// Extensions & Services
	private List<XStreamExtension> extensions = new ArrayList<XStreamExtension>();
	private List<XStreamService> services = new ArrayList<XStreamService>();

	private XStreamExecutor executor;

	private String sessionId;

	@Override
	public void start(XStreamInput input) throws XStreamException {
		if (logger.isDebugEnabled()) {
			logger.debug("{} Starting", input.getIdentifier());
		}

		this.input = input;
		sessionId = input.getIdentifier() + DUUID.randomUUID(5);
		executor = new XStreamExecutorImpl(input.getExecutor());
		clock = new XStreamClockImpl(this);
		tickRouter = new XStreamTickRouterImpl(this);

		services = input.getRegistry().createServices();
		for (XStreamService service : services) {
			service.init(this);
		}
		for (XStreamExtensionType extType : input.getExtensions()) {
			XStreamExtension ext = input.getRegistry().createExtension(extType);
			ext.init(this, extType);
			extensions.add(ext);
		}

		for (XStreamService service : services) {
			service.preStart();
		}

		for (XStreamExtension ext : extensions) {
			ext.preStart();
		}

		for (XStreamService service : services) {
			service.start();
		}

		for (XStreamExtension ext : extensions) {
			ext.start();
		}

		status = XStreamStatus.Running;
	}

	@Override
	public void dispose() throws XStreamException {
		for (XStreamExtension ext : extensions) {
			ext.preDispose();
		}

		for (XStreamService service : services) {
			service.preDispose();
		}

		for (XStreamExtension ext : extensions) {
			ext.dispose();
		}
		for (XStreamService service : services) {
			service.dispose();
		}

		for (XStreamRow row : rows.values()) {
			row.dispose();
		}

		status = XStreamStatus.Disposed;
	}

	@Override
	public XStreamRow getRow(String id) {
		if (!rows.containsKey(id)) {
			throw new XStreamRuntimeException("Row " + id + " does not exist");
		}
		return rows.get(id);
	}

	@Override
	public List<XStreamRow> getRows() {
		List<XStreamRow> list = new ArrayList<XStreamRow>();
		for (Map.Entry<String, XStreamRow> entry : rows.entrySet()) {
			list.add(entry.getValue());
		}
		return list;
	}

	@Override
	public XStreamRow createRow(String rowId, int rowIdentifier) {
		if (logger.isDebugEnabled()) {
			logger.debug(MarkerFactory.getMarker("EntityCreated"), "{} {} {}", rowId, rowIdentifier,
					input.getSessionId());
		}
		XStreamRowImpl row = new XStreamRowImpl();
		row.start(rowId, rowIdentifier, this);
		row.addRowListener(rowListener);
		rows.put(rowId, row);
		try {
			streamListenerLock.acquire();
			for (XStreamListener listener : streamListeners) {
				try {
					listener.rowInsert(row);
				} catch (Exception e) {
					throw new XStreamRuntimeException(
							"Stream Listnener exception " + e.toString() + " " + listener.getClass().getName());
				}
			}
		} catch (Exception e) {

		} finally {
			streamListenerLock.release();
		}
		return row;
	}

	@Override
	public XStreamExecutor getExecutor() {
		return executor;
	}

	@Override
	public boolean hasRow(String key) {
		return rows.containsKey(key);
	}

	@Override
	public XStreamStatus getStatus() {
		return status;
	}

	@Override
	public XStreamTickRouter getTickRouter() {
		return tickRouter;
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
	public void addSignalListener(final XStreamSignalListener list) {
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
	public void removeSignalListener(final XStreamSignalListener list) {
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
	public <T> T getService(Class<T> clazz) throws XStreamException {
		for (XStreamService service : services) {
			if (clazz.isInstance(service)) {
				return (T) service;
			}
		}
		throw new XStreamException("Service Class " + clazz.getName() + " not found");
	}

	@Override
	public String getSessionId() {
		return sessionId;
	}

	@Override
	public void addRowListener(XStreamRowListener listener) {
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
	public void removeRowListener(XStreamRowListener listener) {
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

	/**
	 * RowListener for routing stream row events to RowListeners registered on the
	 * stream level.
	 */
	private class RowListener implements XStreamRowListener {

		@Override
		public void rowSignal(XStreamRow row, XStreamRowSignal signal) {
			try {
				signalListenerLock.acquire();
				for (XStreamSignalListener list : signalListeners) {
					list.onSignal(signal);
				}
			} catch (Exception e) {
				logger.error("Exception Outer Row Listener " + e.toString(), e);

			} finally {
				signalListenerLock.release();
			}
		}

	}

}
