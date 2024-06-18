package com.dunkware.xstream.core.xsignal;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.xstream.api.XSignalType;
import com.dunkware.xstream.api.XSignals;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityVar;
import com.dunkware.xstream.api.XStreamExpression;
import com.dunkware.xstream.api.XStreamListener;
import com.dunkware.xstream.xScript.SignalScannerElementType;
import com.dunkware.xstream.xScript.SignalScannerEntityLimitType;
import com.dunkware.xstream.xScript.SignalScannerEntityThrottleType;
import com.dunkware.xstream.xScript.SignalScannerIntervalType;
import com.dunkware.xstream.xScript.SignalScannerType;

public class XSignalTypeScannerImpl implements XStreamListener {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private XSignalType signalType;
	private SignalScannerType scannerType;
	private XStream stream;
	private XSignals signals;

	private int scanInterval;
	private boolean entityThrottle = false;
	private boolean error = false;
	private String errorMessage = null;
	private int entityThrottleSeconds = 0;
	private boolean entityLimit = false;
	private int entityLimitCount = 0;

	private Map<XStreamEntity, XSignalTypeEntityScanner> entityScanners = new ConcurrentHashMap<XStreamEntity, XSignalTypeEntityScanner>();

	public XSignalTypeScannerImpl() {

	}

	public boolean hasError() {
		return error;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void init(XSignalType type, SignalScannerType scannerType, XSignals signals, XStream stream) {
		this.signalType = type;
		this.scannerType = scannerType;
		this.stream = stream;
		this.signals = signals;
		boolean intervalSet = false;
		for (SignalScannerElementType element : scannerType.getElements()) {

			if (element instanceof SignalScannerIntervalType) {
				SignalScannerIntervalType intervalType = (SignalScannerIntervalType) element;
				String unit = intervalType.getScanIntervalUnit();
				if (unit.equals("sec")) {
					scanInterval = intervalType.getInterval();
					intervalSet = true;
				}
				if (unit.equals("min")) {
					scanInterval = intervalType.getInterval() * 60;
					intervalSet = true;
				}
				if (unit.equals("hour")) {
					scanInterval = intervalType.getInterval() * 3600;
					intervalSet = true;
				}
				if (!intervalSet) {
					logger.error("Signal Scanner Interval Not Set with " + unit);
					error = true;
					errorMessage = "Signal Scanner Interval Not set with " + unit;
					stream.runtimeError("SignalScanner", "Invalid Signal Scanner Interval Type " + unit);
					return;
				}
			}

			if (element instanceof SignalScannerEntityThrottleType) {
				SignalScannerEntityThrottleType throttleType = (SignalScannerEntityThrottleType) element;
				entityThrottle = true;
				String unit = throttleType.getEntityThrottleUnit();
				boolean throttleSet = false;
				if (unit.equals("sec")) {
					entityThrottleSeconds = throttleType.getThrottle();
					throttleSet = true;
				}
				if (unit.equals("min")) {
					entityThrottleSeconds = throttleType.getThrottle() * 60;
					throttleSet = true;

				}
				if (unit.equals("hour")) {
					entityThrottleSeconds = throttleType.getThrottle() * 3600;
					throttleSet = true;

				}
				if (!throttleSet) {
					logger.error("Signal Scanner Interval Throttle Not Set with " + unit);
					error = true;
					errorMessage = "Signal Scanner Entity throttle Interval Not set with " + unit;
					stream.runtimeError("SignalScanner",
							"Invalid Signal Scanner entity throttle Interval Type " + unit);
					return;
				}

				if (element instanceof SignalScannerEntityLimitType) {
					SignalScannerEntityLimitType limitType = (SignalScannerEntityLimitType) element;
					entityLimit = true;
					entityLimitCount = limitType.getEntityLimit();

				}

				// the crossover shit -- not yet.

			}
		}

		if (!intervalSet) {
			logger.warn(
					"Scan Interval Not Set for signal type " + signalType.getName() + " setting it to default 1 sec");
			scanInterval = 1;

		} else {
			if (scanInterval == 0 || scanInterval < 0) {
				logger.error("Signal type interval less than 0 " + signalType.getName());
				scanInterval = 1;
			}
		}

		Runnable runner = new Runnable() {

			@Override
			public void run() {
				stream.addStreamListener(XSignalTypeScannerImpl.this);
			}
		};

		stream.getExecutor().execute(runner);

	}

	public void dispose() {
		stream.removeStreamListener(this);
		for (XSignalTypeEntityScanner entityScanner : entityScanners.values()) {
			entityScanner.dispose();
		}
	}

	@Override
	public void rowInsert(final XStreamEntity row) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				try {
					XSignalTypeEntityScanner entityScanner = new XSignalTypeEntityScanner(row);
					entityScanners.put(row, entityScanner);
				} catch (Exception e) {
					logger.error("Exception building XStreamEntity Type Scanner " + e.toString());
				}

			}
		};

		stream.getExecutor().execute(runnable);
	}

	/**
	 * Created for each entity, creates the expression and manages state
	 */
	private class XSignalTypeEntityScanner {

		private XStreamEntity entity;
		private XStreamExpression expression;
		private EntityScannerRunnable scannerRunnable;

		private AtomicInteger scanCount = new AtomicInteger();

		private volatile int triggerCount = 0;
		private volatile LocalDateTime triggerLastTime = null;

		private boolean running = false;

		public XSignalTypeEntityScanner(XStreamEntity entity) {
			try {
				this.entity = entity;
				expression = entity.createExpressoin(scannerType.getExpression());
				expression.init(entity, scannerType.getExpression());
				expression.start();
				scannerRunnable = new EntityScannerRunnable();
				stream.getClock().scheduleRunnable(scannerRunnable, scanInterval);
				running = true;
			} catch (Exception e) {
				stream.incrementErrorCount();
				logger.error("Exception Creating XSignalTypeEntity Scanner signal type " + signalType.getName()
						+ " exception " + e.toString());

			}
		}

		public XStreamEntity getEntity() {
			return entity;
		}

		public void dispose() {
			if (running) {
				stream.getClock().unscheduleRunnable(scannerRunnable);
				expression.dispose();
				running = false;
			}
		}

		private class EntityScannerRunnable implements Runnable {

			public EntityScannerRunnable() {

			}

			@Override
			public void run() {
				if (expression.canExecute()) {
					scanCount.incrementAndGet();
					try {
						if (expression.execute()) {
							Object value = expression.getValue();
							if (value instanceof Boolean) {
								Boolean expValue = (Boolean) value;
								if (expValue) {
									// check entity limit;
									if (entityThrottle) {
										if (triggerCount > 0) {
											LocalDateTime currentTime = stream.getClock().getLocalDateTime();
											Duration d = Duration.between(triggerLastTime, currentTime);
											if (d.getSeconds() > entityThrottleSeconds) {
												triggerCount++;
												triggerLastTime = currentTime;
												signalType.signal(entity, currentTime, entity.numericVarSnapshot());

											}
											return;
											
										}
										triggerCount++;
										LocalDateTime currentTime = stream.getClock().getLocalDateTime();
										triggerLastTime = currentTime;
										signalType.signal(entity, currentTime, entity.numericVarSnapshot());
										return;
										
									}
									if(entityLimit) { 
										if(triggerCount < entityLimitCount) { 
											triggerCount++;
											LocalDateTime currentTime = stream.getClock().getLocalDateTime();
											triggerLastTime = currentTime;
											signalType.signal(entity, currentTime, entity.numericVarSnapshot());
										}
										return;
 									}
									
									
								}
								// else no throttle or entity liit tritger
								triggerCount++;
								LocalDateTime currentTime = stream.getClock().getLocalDateTime();
								triggerLastTime = currentTime;
								signalType.signal(entity, currentTime, entity.numericVarSnapshot());
								
							}
						}

						// Object eval = expression.
					} catch (Exception e) {
						logger.error("Excepiton outer entitys canner runnable " + e.toString());
						stream.incrementErrorCount();
					}
				}
			}

		}
	}

}
