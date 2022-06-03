package com.dunkware.net.core.data;

import java.util.List;

public interface NetBeanList {
	
	List<NetBean> getBeans();
	
	public void insert(NetBean bean); 
	
	public void remove(NetBean bean);
	
	public void addObserver(NetBeanListObserver observer);
	
	public void removeObserver(NetBeanListObserver observer);
	
	String getName();

}
