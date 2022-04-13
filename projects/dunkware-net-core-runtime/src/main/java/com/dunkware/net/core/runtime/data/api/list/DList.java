package com.dunkware.net.core.runtime.data.api.list;

import java.util.List;

import com.dunkware.net.core.runtime.data.api.DDataException;
import com.dunkware.net.core.runtime.data.api.bean.DBean;

public interface DList {
	
	String getName();
	
	int size();
	
	List<DBean> elements();
	
	void insert(DBean bean);
	
	void remove(DBean bean) throws DDataException;
	
	void clear();
	
	DBean get(int index) throws DDataException;
	
	

}
