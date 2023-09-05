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
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityListener;
import com.dunkware.xstream.api.XStreamEntityQuery;
import com.dunkware.xstream.api.XStreamException;
import com.dunkware.xstream.api.XStreamExecutor;
import com.dunkware.xstream.api.XStreamExtension;
import com.dunkware.xstream.api.XStreamInput;
import com.dunkware.xstream.api.XStreamListener;
import com.dunkware.xstream.api.XStreamRowSignal;
import com.dunkware.xstream.api.XStreamRowSignalListener;
import com.dunkware.xstream.api.XStreamRuntimeErrorListener;
import com.dunkware.xstream.api.XStreamRuntimeException;
import com.dunkware.xstream.api.XStreamService;
import com.dunkware.xstream.api.XStreamSignals;
import com.dunkware.xstream.api.XStreamStatService;
import com.dunkware.xstream.api.XStreamStatus;
import com.dunkware.xstream.api.XStreamTickRouter;
import com.dunkware.xstream.core.signal.XStreamSignalsImpl;
import com.dunkware.xstream.model.entity.query.type.XStreamEntityQueryType;
import com.dunkware.xstream.model.metrics.XStreamMetrics;
import com.dunkware.xstream.util.XStreamStatsBuilder;
import com.dunkware.xstream.xproject.model.XStreamExtensionType;

import io.vertx.core.Future;

public class XStreamImpl implements XStream {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker stopMarker = MarkerFactory.getMarker("XStreamStop");
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

	private XStreamSignalsImpl signals;

	private String sessionId;

	private List<String> rowIdentifiers = new ArrayList<String>();

	private List<XStreamRuntimeErrorListener> errorListeners = new ArrayList<XStreamRuntimeErrorListener>();
	private Semaphore errorListenerLock = new Semaphore(1);

	@Override
	public void start(XStreamInput input) throws XStreamException {
		if (logger.isDebugEnabled()) {
			logger.debug("{} Starting", input.getIdentifier());
		}
		if (input.getQueryBuilder() == null) {
			throw new XStreamException("Entity Query Builder not set on XStream");
		}

		this.input = input;

		signals = new XStreamSignalsImpl();

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

		Thread signalStarter = new Thread() {

			public void run() {
				try {
					Thread.sleep(1000);
					signals.start(XStreamImpl.this);
				} catch (Exception e) {
					logger.error("Signal Starter Exception ! " + e.toString());
				}
				

				

			}
		};
		signalStarter.start();

		status = XStreamStatus.Running;
	}

	@Override
	public List<String> getRowIdentifiers() {
		return rowIdentifiers;
	}

	@Override
	public XStreamStatService getStatProvider() {
		return input.getStatProvider();
	}

	@Override
	public void dispose() throws XStreamException {

		for (XStreamExtension ext : extensions) {
			logger.info(stopMarker, "Ext Predispose Start {} ", ext.getClass().getName());
			ext.preDispose();
			logger.info(stopMarker, "Ext Predispose Done {} ", ext.getClass().getName());
		}

		for (XStreamService service : services) {
			logger.info(stopMarker, "Service Predispose Start {} ", service.getClass().getName());
			service.preDispose();
			logger.info(stopMarker, "Service Predispose Done {} ", service.getClass().getName());
		}

		for (XStreamExtension ext : extensions) {
			logger.info(stopMarker, "Ext Dispose Start {} ", ext.getClass().getName());
			ext.dispose();
			logger.info(stopMarker, "Ext Dispose Donet {} ", ext.getClass().getName());
		}
		for (XStreamService service : services) {
			logger.info(stopMarker, "Service Dispose Start {} ", service.getClass().getName());
			service.dispose();
			logger.info(stopMarker, "Service Dispose Done {} ", service.getClass().getName());
		}

		logger.info(stopMarker, "Entities Dispose Start");
		for (XStreamEntity row : rows.values()) {
			row.dispose();
		}
		logger.info(stopMarker, "Entities Dispose Done");
		logger.info(stopMarker, "XStream Disposed");
		status = XStreamStatus.Disposed;
	}

	@Override
	public XStreamSignals getSignals() {
		return signals;
	}

	@Override
	public void cancel() {
		for (XStreamExtension ext : extensions) {
			ext.cancel();
		}
		for (XStreamService service : services) {
			service.cancel();
		}

		for (XStreamEntity row : rows.values()) {
			row.dispose();
		}
		status = XStreamStatus.Cancelled;

	}

	@Override
	public XStreamEntity getRow(String id) {
		if (!rows.containsKey(id)) {
			throw new XStreamRuntimeException("Row " + id + " does not exist");
		}
		return rows.get(id);
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
	public XStreamEntity createRow(String rowId, int rowIdentifier) {
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
		rowIdentifiers.add(rowId);
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
			if (clazz.isInstance(extension)) {
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
	public String getSessionId() {
		return sessionId;
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
	public Future<XStreamEntityQuery> buildEntityQuery(XStreamEntityQueryType model) {
		return input.getQueryBuilder().build(this, model);
	}

	@Override
	public void runtimeError(String type, String message) {
		final XStreamRuntimeErrorImpl error = new XStreamRuntimeErrorImpl(type, message, clock.getLocalTime());
		executor.execute(new Runnable() {

			@Override
			public void run() {
				try {
					errorListenerLock.acquire();
					for (XStreamRuntimeErrorListener listener : errorListeners) {
						listener.onError(XStreamImpl.this, error);
					}
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					errorListenerLock.release();
				}
			}
		});
	}

	@Override
	public void addRuntimeErrorListener(XStreamRuntimeErrorListener listener) {
		executor.execute(new Runnable() {

			@Override
			public void run() {
				try {
					errorListenerLock.acquire();
					errorListeners.add(listener);
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					errorListenerLock.release();
				}
			}
		});
	}

	@Override
	public void removeRuntimeErrorListener(XStreamRuntimeErrorListener listener) {
		executor.execute(new Runnable() {

			@Override
			public void run() {
				try {
					errorListenerLock.acquire();
					errorListeners.remove(listener);
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					errorListenerLock.release();
				}
			}
		});
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

}
