package com.dunkware.net.core.data.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.net.core.data.NetBean;
import com.dunkware.net.core.data.NetBeanObserver;
import com.dunkware.net.core.data.NetDataFactory;
import com.dunkware.net.core.util.GNetFactory;
import com.dunkware.net.proto.data.GBean;
import com.dunkware.net.proto.data.GField;
import com.dunkware.net.proto.data.GIntValue;
import com.dunkware.net.proto.data.GValue;

public class NetBeanImpl implements NetBean {

	private Map<String, GField> fields = new ConcurrentHashMap<String, GField>();

	private List<NetBeanObserver> observers = new ArrayList<NetBeanObserver>();
	private Semaphore observerLock = new Semaphore(1);

	private int id;
	private DExecutor executor;

	public NetBeanImpl(int id, DExecutor executor) {
		this.id = id;
		this.executor = executor;
	}

	@Override
	public void setInt(String key, Integer value) {
		GField field = GNetFactory.intField(key, value);
		fields.put(key, field);
		fieldUpdate(field);
	}

	@Override
	public int getId() {
		return id;
	}
	
	
	@Override
	public void setDouble(String key, Double value) {
		GField field = GNetFactory.doubleField(key, id);
		fields.put(key, field);
		fieldUpdate(field);
	}

	@Override
	public void setString(String key, String value) {
		GField field = GNetFactory.stringField(key, value);
		fields.put(key, field);
		fieldUpdate(field);
	}
	
	@Override
	public void clone(GBean bean) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addObserver(NetBeanObserver observer) {
		Runnable runner = new Runnable() {

			@Override
			public void run() {
				try {
					observerLock.acquire();
					observers.add(observer);
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					observerLock.release();

				}
			}
		};
		executor.execute(runner);

	}

	@Override
	public void removeObserver(NetBeanObserver observer) {

		Runnable runner = new Runnable() {

			@Override
			public void run() {
				try {
					observerLock.acquire();
					observers.remove(observer);
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					observerLock.release();

				}
			}
		};
		executor.execute(runner);
	}

	@Override
	public GBean convert() {
		GBean.Builder builder = GBean.newBuilder();
		builder.addAllFields(fields.values());
		builder.setId(id);
		return builder.build();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NetBeanImpl) {
			NetBeanImpl compare = (NetBeanImpl) obj;
			if (compare.getId() == id) {
				return true;
			}
		}
		return false;
	}
	
	private void fieldUpdate(GField field) { 
		try {
			observerLock.acquire();
			for (NetBeanObserver netBeanObserver : observers) {
				netBeanObserver.fieldUpdate(this, field);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally { 
			observerLock.release();
		}
	}

}
