package com.dunkware.net.core.data.core;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.net.core.data.NetBean;
import com.dunkware.net.core.data.NetBeanObserver;
import com.dunkware.net.core.data.NetDataException;
import com.dunkware.net.proto.data.GBean;
import com.dunkware.net.proto.data.GField;

public class NetBeanImpl implements NetBean {
	
	private Map<String,GField> fields = new ConcurrentHashMap<String,GField>();

	@Override
	public void setInt(String key, Integer value) {
		
	}

	@Override
	public void setDouble(String key, Double value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDateTime(String key, LocalDateTime dateTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTime(String key, LocalTime time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBytes(String key, byte[] bytes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setJson(String key, Object json) throws NetDataException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LocalDateTime getDateTime(String key) throws NetDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getString(String key) throws NetDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getDouble(String key) throws NetDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getBytes(String key) throws NetDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getJson(String key, Class type) throws NetDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addObserver(NetBeanObserver observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeObserver(NetBeanObserver observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GBean toProtoBean() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
