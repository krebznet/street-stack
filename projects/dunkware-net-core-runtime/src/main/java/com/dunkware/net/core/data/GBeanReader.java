package com.dunkware.net.core.data;

import com.dunkware.net.core.util.GBeanHelper;
import com.dunkware.net.proto.data.GBean;

public class GBeanReader {
	
	public static GBeanReader newInstance(GBean bean) { 
		return new GBeanReader(bean);
	}
	
	private GBean bean;
	private GBeanReader(GBean bean) { 
		this.bean = bean;
	}
	
	public String getString(String field) throws NetBeanException { 
		return GBeanHelper.getStringValue(bean, field);
	}
	
	public Integer getInt(String field) throws NetBeanException {
		return GBeanHelper.getIntValue(bean, field);
	}
	
}
