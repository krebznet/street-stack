package com.dunkware.net.core.data.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.net.core.data.NetBean;
import com.dunkware.net.core.data.NetBeanList;
import com.dunkware.net.core.data.NetBeanListObserver;
import com.dunkware.net.proto.data.GBeanDelete;
import com.dunkware.net.proto.data.GBeanInsert;
import com.dunkware.net.proto.data.GBeanListUpdate;
import com.dunkware.net.proto.data.GBeanUpdate;
import com.dunkware.net.proto.data.GField;

public class GBeanListUpdateStream implements NetBeanListObserver  {

	private List<GBeanListUpdateConsumer> consumers = new ArrayList<GBeanListUpdateConsumer>();
	private Semaphore consumerLock = new Semaphore(1);
	
	private Semaphore updateLock = new Semaphore(1);
	private Map<NetBean,BeanFieldUpdates> pendingFieldUpdates = new ConcurrentHashMap<NetBean,BeanFieldUpdates>();
	private Vector<NetBean> pendingBeanInserts = new Vector<NetBean>();
	private Vector<NetBean> pendingBeanDeletes = new Vector<NetBean>();
	
	private ConsumerUpdater updater = new ConsumerUpdater();
	private NetBeanList list; 
	private DExecutor executor;
	public void start(NetBeanList list, DExecutor executor) { 
		this.list = list;
		this.executor = executor;
		
		updater.start();
		for (NetBean bean : list.getBeans()) {
			pendingBeanInserts.add(bean);
		}
		list.addObserver(this);
	}
	
	public void dispose() { 
		updater.interrupt();	
		list.removeObserver(this);
		
	}
	
	@Override
	public void fieldUpdate(NetBean bean, GField field) {
		try {
			updateLock.acquire();
			if(pendingFieldUpdates.get(bean) == null) { 
				BeanFieldUpdates updates = new BeanFieldUpdates();
				updates.bean = bean;
				updates.fields.put(field.getName(), field);
				pendingFieldUpdates.put(bean, updates);
			} else { 
				pendingFieldUpdates.get(bean).fields.put(field.getName(), field);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally { 
			updateLock.release();
		}
		
	}

	@Override
	public void beanInsert(NetBean bean) {
		try {
			updateLock.acquire();
			pendingBeanInserts.add(bean);
		} catch (Exception e) {
			// TODO: handle exception
		} finally { 
			updateLock.release();
		}
			
	}

	@Override
	public void beanRemove(NetBean bean) {
		try {
			updateLock.acquire();
			pendingBeanDeletes.add(bean);
		} catch (Exception e) {
			// TODO: handle exception
		} finally { 
			updateLock.release();
		}
		
	}

	private class BeanFieldUpdates  { 
		public NetBean bean;
		public Map<String,GField> fields = new ConcurrentHashMap<String, GField>();
	}
	
	public void addConsumer(GBeanListUpdateConsumer consumer) { 
	Runnable runner = new Runnable() {
			
			@Override
			public void run() {
				try {
					consumerLock.acquire();
					consumers.add(consumer);
				} catch (Exception e) {
					// TODO: handle exception
				} finally { 
					consumerLock.release();
				}
			}
		};
		executor.execute(runner);
	}
	
	public void removeConsumer(GBeanListUpdateConsumer consumer) { 
		Runnable runner = new Runnable() {
			
			@Override
			public void run() {
				try {
					consumerLock.acquire();
					consumers.remove(consumer);
				} catch (Exception e) {
					// TODO: handle exception
				} finally { 
					consumerLock.release();
				}
			}
		};
		executor.execute(runner);
	}
	
	private class ConsumerUpdater extends Thread { 
		
		public void run() { 
			try {
				while(!interrupted()) { 
					 List<BeanFieldUpdates> beanFieldUpdates = new ArrayList<BeanFieldUpdates>();
					 Vector<NetBean> inserts = new Vector<NetBean>();
					 Vector<NetBean> deletes = new Vector<NetBean>();
					try {	
						Thread.sleep(500);
						updateLock.acquire();
						for (BeanFieldUpdates beanUpdats : pendingFieldUpdates.values()) {
							beanFieldUpdates.add(beanUpdats);
						}
						pendingFieldUpdates.clear();
						inserts.addAll(pendingBeanInserts);
						pendingBeanInserts.clear();
						deletes.addAll(pendingBeanDeletes);
						pendingBeanDeletes.clear();
					} catch (Exception e) {
						return;
					} finally { 
						updateLock.release();
					}
					if(beanFieldUpdates.size() == 0 && deletes.size() == 0 && inserts.size() == 0) { 
						continue;
					}
					GBeanListUpdate.Builder updateBuilder = GBeanListUpdate.newBuilder(); 
						for (BeanFieldUpdates updates : beanFieldUpdates) {
							GBeanUpdate.Builder builder = 	GBeanUpdate.newBuilder();
							builder.setId(updates.bean.getId());
							for (GField field : updates.fields.values()) {
								builder.addFields(field);
							}
							updateBuilder.addUpdates(builder.build());
						}
						for (NetBean netBean : deletes) {
							updateBuilder.addDeletes(GBeanDelete.newBuilder().setId(netBean.getId()).build());
						}
						for (NetBean netBean : inserts) {
							updateBuilder.addInsets(GBeanInsert.newBuilder().setBean(netBean.convert()));
						}
						GBeanListUpdate listUpdate = updateBuilder.build();
						for (GBeanListUpdateConsumer consumer : consumers) {
							consumer.listUpdate(list, listUpdate);
						}
				}
			} catch (Exception e) {
				if (e instanceof InterruptedException) {
					return;
				}
			}
		}
	}
	
}
