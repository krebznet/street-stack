package com.dunkware.net.data.builders;

import com.dunkware.net.proto.core.GBean;
import com.dunkware.net.proto.core.GField;
import com.dunkware.net.proto.core.GFieldType;

public class GBeanBuilder {
	
	public static GBeanBuilder newBuilder(int id, String name) { 
		return new GBeanBuilder(id,name);
	}
	
	
	private GBean.Builder builder = GBean.newBuilder();
	
	private GBeanBuilder(int id, String name) { 
		builder.setId(id);
		builder.setName(name);
	}
	
	public GBeanBuilder setDouble(String field, double value) { 
		builder.addFields(GField.newBuilder().setType(GFieldType.DOUBLE).setDoubleValue(value).setName(field).build());
		return this;
	}
	
	public GBeanBuilder setLong(String field, long value) { 
		builder.addFields(GField.newBuilder().setType(GFieldType.LONG).setLongValue(value).setName(field).build());
		return this;
	}

	public GBeanBuilder setString(String field, String value) { 
		builder.addFields(GField.newBuilder().setType(GFieldType.STRING).setStringValue(value).setName(field).build());
		return this;
	}
	public GBeanBuilder setInt(String field, int value) { 
		builder.addFields(GField.newBuilder().setType(GFieldType.INT).setIntValue(value).setName(field).build());
		return this;
	}
	
	public GBean build() { 
		return builder.build();
	}
	
}
