package com.dunkware.xstream.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

import com.dunkware.xstream.api.XStreamExpression;
import com.dunkware.xstream.api.XStreamExpressionListener;
import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.api.XStreamRuntimeException;
import com.dunkware.xstream.api.XStreamVar;
import com.dunkware.xstream.api.XStreamVarListener;
import com.dunkware.xstream.model.metrics.XStreamVarMetrics;
import com.dunkware.xstream.xScript.VarType;

public class XStreamVarImpl implements XStreamVar, XStreamExpressionListener, XStreamVarListener {

	private XStreamRow row;
	private VarType varType;

	private XStreamExpression expression;

	private ConcurrentHashMap<Integer, Object> values = new ConcurrentHashMap<Integer, Object>();

	private volatile Integer maxSize = 5000;
	private volatile boolean maxSizeEnabled = true;

	private volatile Integer currentIndex = -1;
//	private volatile Integer updateCount = 0;
	private volatile Integer currentOffset = -1;

	private List<XStreamVar> downstreamVars = new ArrayList<XStreamVar>();
	private Semaphore downstreamVarLock = new Semaphore(1);

	private List<XStreamExpression> containedExpressions = new ArrayList<XStreamExpression>();

	private List<XStreamVarListener> varListeners = new ArrayList<XStreamVarListener>();
	private Semaphore varListenerLock = new Semaphore(1);

	private AtomicLong updateCounter = new AtomicLong(0);
	
	private AtomicLong getCounter  = new AtomicLong(0);

	@Override
	public void init(XStreamRow row, VarType varType) {
		this.row = row;
		this.varType = varType;
		expression = row.getStream().getInput().getRegistry().createVarExpression(varType.getExpression());
		expression.init(row, varType.getExpression());
	}

	@Override
	public void start() {
		if (expression.isInput()) {
			expression.addExpressionListener(this);
		}
		expression.start();
		if (!expression.excludeDownStream()) {
			expression.containedVariables(downstreamVars);
			if (downstreamVars.size() > 0) {
				// in ref impl it only adds listener
				// to first downstream var? not exactly
				// sure but doing that for now
				downstreamVars.get(0).addVarListener(this);
			}
		}

	}

	@Override
	public void dispose() {
		if (expression.isInput()) {
			expression.removeExpressionListener(this);
		}
		if (!expression.excludeDownStream()) {
			if (downstreamVars.size() > 0) {
				// againn we are only adding listener
				// to first downstream var, if that changes
				// so should this
				downstreamVars.get(0).removeVarListener(this);
			}
		}
		expression.dispose();

	}

	@Override
	public void setValue(Object value) {
		// value.toString());
		if (currentIndex == -1) {
			currentIndex = 0;
			currentOffset = 0;
		
			values.put(0, value);
		} else {
			if (currentIndex + 1 == maxSize) {
				
				values.put(0, value);
				currentIndex = 0;
				currentOffset = 0;
			} else {
				
				values.put(currentIndex + 1, value);
				currentIndex++;
				currentOffset++;

			}
		}
		updateCounter.incrementAndGet();
		row.getStream().getExecutor().execute(new VarListenerRunnable());
	}

	@Override
	public Object getValue(int arg0) {
		int index = resolveIndex(arg0);

		if (index < 0 || index > values.size()) {
			throw new XStreamRuntimeException("getValue resolved index + 1 is greater than values size");
		}
		getCounter.incrementAndGet();
		return values.get(index);
		
	}

	@Override
	public void getValues(Object[] data, int startIndex, int endIndex) {
		if (getSize() < endIndex) {
			throw new XStreamRuntimeException("getValues() size is less than requested endIndex");
		}
		int histSize = endIndex - startIndex;
		int i = 0;
		while (i != histSize) {
			int nextIndex = startIndex + i;
			data[i] = getValue(nextIndex);
			i++;
		}
	}

	@Override
	public VarType getVarType() {
		return varType;
	}

	@Override
	public void addVarListener(XStreamVarListener listener) {
		try {
			varListenerLock.acquire();
			varListeners.add(listener);
		} catch (InterruptedException e) {
			return;
		} finally {
			varListenerLock.release();
		}
	}

	@Override
	public void removeVarListener(XStreamVarListener listener) {
		try {
			varListenerLock.acquire();
			varListeners.remove(listener);
		} catch (InterruptedException e) {
			return;
		} finally {
			varListenerLock.release();
		}
	}

	@Override
	public int getSize() {
		return values.size();
	}

	@Override
	public void setMaxSize(int size) {
		if (this.maxSize < size) {
			this.maxSize = size;
		}
	}

	@Override
	public XStreamExpression getExpression() {
		return expression;
	}

	@Override
	public XStreamRow getRow() {
		return row;
	}

	@Override
	public void addDownStreamVar(XStreamVar var) {
		try {
			downstreamVarLock.acquire();
			downstreamVars.add(var);
		} catch (Exception e) {
			return;
		} finally {
			downstreamVarLock.release();
		}
	}

	@Override
	public void setMaxSizeEnabled(boolean value) {
		this.maxSizeEnabled = true;
	}

	@Override
	public boolean isMaxSizeEnabled() {
		return maxSizeEnabled;
	}

	@Override
	public Collection<XStreamVar> getDownStreamVars() {
		return downstreamVars;
	}

	@Override
	public Collection<XStreamExpression> getContainedExpressions() {
		return containedExpressions;
	}

	@Override
	public void update() {
		if (expression.canExecute()) {
			boolean results = expression.execute();

			setValue(expression.getValue());

		}
	}

	@Override
	public void expressionUpdate(XStreamExpression exp) {
		setValue(exp.getValue());
	}

	@Override
	public void varUpdate(XStreamVar var) {
		if (expression.canExecute()) {
			if (expression.execute()) {
				setValue(expression.getValue());
			}
		}
	}
	
	

	@Override
	public XStreamVarMetrics getStats() {
		XStreamVarMetrics stats = new XStreamVarMetrics();
		stats.setGetCount(getCounter.get());
		stats.setSetCount(updateCounter.get());
		stats.setName(getVarType().getName());
		return stats;
	}

	private Integer resolveIndex(int valueIndex) {
		if (updateCounter.get() - 1 < maxSize) {
			return currentIndex - valueIndex;
		} else {
			int index = currentIndex - valueIndex;
			if (index < 0) {
				// need to iterate back on off maxsize
				int value = maxSize - Math.abs(index);
				return value;
			} else {
				return index;
			}

		}

	}

	private class VarListenerRunnable implements Runnable {

		public void run() {
			try {
				varListenerLock.acquire();
				for (XStreamVarListener xStreamVarListener : varListeners) {
					try {
						xStreamVarListener.varUpdate(XStreamVarImpl.this);
					} catch (Exception e) {
						throw new XStreamRuntimeException("XStream Var Listener "
								+ xStreamVarListener.getClass().getName() + " exception " + e.toString());
					}
				}
			} catch (InterruptedException e) {
				return;
			} finally {
				varListenerLock.release();
			}
		}
	}

}
