package com.dunkware.net.core.runtime.data.api.bean;

import com.dunkware.net.core.runtime.data.api.DDataException;
import com.dunkware.net.core.runtime.data.api.clazz.DClass;
import com.dunkware.net.core.runtime.data.api.list.DList;

public interface DBean {
	
	public DClass getClazz();
	
	public void setString(String bute, String value); 
	
	public void setDouble(String bute, String value);
	
	public void setList(String nname, DList list); 
	
	public String getString(String name);
	
	public DList getList(String name) throws DDataException;
	

}
