package com.dunkware.net.core.runtime.data.helpers;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import com.dunkware.net.proto.core.GBean;
import com.dunkware.net.proto.core.GField;
import com.dunkware.net.proto.core.GFieldType;
import com.dunkware.net.proto.core.GList;


public class GDataJson {
	
	public static String listToJsonString(GList list) {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		for (GBean bean : list.getBeansList()) {
			builder.add(beanToJsonObject(bean));
		}
		return builder.build().toString();
	}

	public static JsonObject beanToJsonObject(GBean bean) { 
		JsonObjectBuilder builder  = Json.createObjectBuilder();
	    for (GField field : bean.getFieldsList()) {
			if(field.getType() == GFieldType.BOOL) {
				builder.add(field.getName(), field.getBooleanValue());
			}
			if(field.getType() == GFieldType.DOUBLE) { 
				builder.add(field.getName(), field.getDoubleValue());
			}
			if(field.getType() == GFieldType.LONG) { 
				builder.add(field.getName(), field.getLongValue());
			}
			if(field.getType() == GFieldType.INT) { 
				builder.add(field.getName(), field.getIntValue());
			}
			if(field.getType() == GFieldType.STRING) { 
				builder.add(field.getName(), field.getStringValue());
			}
		}
	    return builder.build();
	    
	}
}
