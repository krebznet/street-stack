package com.dunkware.net.core.util;

import com.dunkware.net.core.data.NetBeanException;
import com.dunkware.net.proto.data.GBean;
import com.dunkware.net.proto.data.GField;
import com.dunkware.net.proto.data.GValue;

public class GBeanHelper {
	
	public static String getStringValue(GBean bean, String field) throws NetBeanException { 
		GField gField = getField(bean, field);
		GValue value = gField.getValue();
		if(value.getString() == null) { 
			throw new NetBeanException("Get String value on field " + 
		"field is not string type but " + value.getTypeCase().toString());
		}
		return value.getString().getValue();
	}
	
	public static Integer getIntValue(GBean bean, String field) throws NetBeanException { 
		GField gField = getField(bean, field);
		GValue value = gField.getValue();
		if(value.getInt() == null) { 
			throw new NetBeanException("Get int value on field " + 
		"field is not int type but " + value.getTypeCase().toString());
		}
		return value.getInt().getValue();
	}
	
	public static GField getField(GBean bean, String field) throws NetBeanException { 
		for (GField gField : bean.getFieldsList()) {
			if(gField.getName().equals(field)) { 
				return gField; 
			}
		}
		throw new NetBeanException("Field " + field + " not found");
	}
	
	

}
