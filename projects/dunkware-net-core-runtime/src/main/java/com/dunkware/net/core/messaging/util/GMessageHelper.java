package com.dunkware.net.core.messaging.util;

import java.time.LocalDateTime;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.net.core.messaging.DException;
import com.dunkware.net.proto.message.GMessage;
import com.dunkware.net.proto.message.GProperty;
import com.google.protobuf.Timestamp;

public class GMessageHelper {
	
	public static LocalDateTime getDateTime(GMessage message, String key) throws DException { 
		GProperty prop = getProperty(message, key);
		Timestamp tm = prop.getValue().getDateTime().getValue();
		return DunkTime.toLocalDateTime(tm, DTimeZone.NewYork);
	}
	
	public static GProperty getProperty(GMessage message, String key) throws DException { 
		for (GProperty prop : message.getPropertiesList()) {
			if(prop.getName().equals(key)) { 
				return prop;
			}
		}
		throw new DException("Property " + key + " not found on GBean");
	}

}
