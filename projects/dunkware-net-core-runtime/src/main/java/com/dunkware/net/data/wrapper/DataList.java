package com.dunkware.net.data.wrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import com.dunkware.net.proto.core.GBeanInsert;
import com.dunkware.net.proto.core.GBeanUpdate;
import com.dunkware.net.proto.core.GListUpdate;

public class DataList implements DataBeanListener  {
	
	
	public static DataList newInstance(int id) { 
		return new DataList(id);
	}
	
	private List<DataListLIstener> listeners = new ArrayList<DataListLIstener>();
	private Semaphore listenerLock = new Semaphore(1);
	
	private List<DataBean> beans = new ArrayList<DataBean>();
	
	private GListUpdate.Builder updateBuilder = GListUpdate.newBuilder();
	private Semaphore updateBuilderLock = new Semaphore(1);
	
	private int id ; 
	
	
	
	private DataList(int id) { 
		this.id = id; 
	}
	
	public void insertBean(DataBean bean) { 
		beans.add(bean);
		GListUpdate.Builder builder = GListUpdate.newBuilder();
		builder.addInsets(GBeanInsert.newBuilder().setBean(bean.toGBean()).build());
	}
	
	public void deleteBean(DataBean bean) { 
		// delete shit 
	}
	
	public void addListener(DataListLIstener listener) { 
		try {
			listenerLock.acquire();
			listeners.add(listener);
		} catch (Exception e) {
			// TODO: handle exception
		} finally { 
			listenerLock.release();
		}
	}
	
	public void removeListener(DataListLIstener listener) { 
		try {
			listenerLock.acquire();
			listeners.remove(listener);
		} catch (Exception e) {
			// TODO: handle exception
		} finally { 
			listenerLock.release();
		}
	}

	@Override
	public void beanUpdate(DataBean bean, GBeanUpdate update) {
		this.updateBuilder.addUpdates(update);
	}
	
	
	

}
