package com.dunkware.net.core.data.core;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.net.core.data.NetBean;
import com.dunkware.net.core.data.NetBeanList;
import com.dunkware.net.core.data.NetBeanListObserver;
import com.dunkware.net.core.data.NetBeanObserver;
import com.dunkware.net.proto.data.GField;

public class NetBeanListImpl implements NetBeanList, NetBeanObserver  {
	
	private List<NetBeanListObserver> observers = new ArrayList<NetBeanListObserver>();
	private Semaphore observerLock = new Semaphore(1);
	
	private List<NetBean> beans = new ArrayList<NetBean>();

	private DExecutor executor;
	
	public NetBeanListImpl(DExecutor executor) { 
		this.executor = executor; 
	}
	@Override
	public List<NetBean> getBeans() {
		return beans;
	}

	@Override
	public void insert(NetBean bean) {
		beans.add(bean);
		notifyBeanInsert(bean);
		
	}

	@Override
	public void remove(NetBean bean) {
		if(beans.contains(bean)) { 
			beans.remove(bean);
			notifyBeanRemove(bean);
		}
	}

	@Override
	public void addObserver(final NetBeanListObserver observer) {
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
	public void removeObserver(NetBeanListObserver observer) {
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
	public void fieldUpdate(NetBean bean, GField field) {
		notifyFieldUpdate(bean, field);
	}
	
	private void notifyFieldUpdate(NetBean bean, GField field) { 
		try {
			observerLock.acquire();
			for (NetBeanListObserver observer : observers) {
				observer.fieldUpdate(bean, field);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			observerLock.release();
		}
	}
	
	private void notifyBeanInsert(NetBean bean) { 
		try {
			observerLock.acquire();
			for (NetBeanListObserver observer : observers) {
				observer.beanInsert(bean);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			observerLock.release();
		}
	}
	
	private void notifyBeanRemove(NetBean bean) { 
		try {
			observerLock.acquire();
			for (NetBeanListObserver observer : observers) {
				observer.beanRemove(bean);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			observerLock.release();
		}
	}
	
	

	

	
}
