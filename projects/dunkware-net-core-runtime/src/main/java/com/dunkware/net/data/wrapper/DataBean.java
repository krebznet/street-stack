package com.dunkware.net.data.wrapper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.net.proto.core.GBean;
import com.dunkware.net.proto.core.GField;
import com.dunkware.net.proto.core.GFieldType;

public class DataBean {
	
	public static DataBean newInstance(int id, String name) { 
		return new DataBean(id, name);
	}
	
	Map<String,Object> fields = new ConcurrentHashMap<String,Object>();
	Map<String,GFieldType> fieldTypes = new ConcurrentHashMap<String, GFieldType>();
	
	private  int id; 
	private String name; 
	
	
	
	private DataBean(int id, String name) { 
		this.id = id; 
		this.name = name; 
	}
	
	public DataBean setDouble(String field, Double value) { 
		this.fields.put(field, value);
		fieldTypes.put(field, GFieldType.DOUBLE);
		return this; 
	}
	
	public DataBean setLong(String field, Long value) { 
		return this; 
	}
	
	public DataBean setString(String field, String value) { 
		return this;
	}
	
	public GBean toGBean() { 
		GBean.Builder builder = GBean.newBuilder();
		builder.setId(id);
		builder.setName(name);
		for (String key : fields.keySet()) {
			GFieldType type = fieldTypes.get(key);
			GField.Builder fieldBuilder = GField.newBuilder();
			fieldBuilder.setName(key);
			fieldBuilder.setType(type);
			if(type == GFieldType.DOUBLE) { 
				fieldBuilder.setDoubleValue((Double)fields.get(key));
			}
			if(type == GFieldType.STRING) { 
				fieldBuilder.setDoubleValue((Double)fields.get(key));
			}
			if(type == GFieldType.LONG) { 
				fieldBuilder.setDoubleValue((Double)fields.get(key));
			}
			if(type == GFieldType.INT) { 
				fieldBuilder.setIntValue((Integer)fields.get(key));
			}
			builder.addFields(fieldBuilder.build());
		}
		return builder.build();
	}
	
	

}
