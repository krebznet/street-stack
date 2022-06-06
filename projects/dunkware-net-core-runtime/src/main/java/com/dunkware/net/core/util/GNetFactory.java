package com.dunkware.net.core.util;

import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.net.proto.data.GBean;
import com.dunkware.net.proto.data.GBeanDelete;
import com.dunkware.net.proto.data.GBeanInsert;
import com.dunkware.net.proto.data.GDoubleValue;
import com.dunkware.net.proto.data.GField;
import com.dunkware.net.proto.data.GIntValue;
import com.dunkware.net.proto.data.GStringValue;
import com.dunkware.net.proto.data.GValue;
import com.dunkware.net.proto.net.GNetCallRequest;
import com.dunkware.net.proto.net.GNetCallResponse;
import com.dunkware.net.proto.net.GNetChannelResponse;
import com.dunkware.net.proto.net.GNetCode;
import com.dunkware.net.proto.net.GNetMessage;

public class GNetFactory {
	
	public static GNetMessage callRequest(String endpoint, int requestId, String source) {
		return GNetMessage.newBuilder().setCallReq(GNetCallRequest.newBuilder().setEndPoint(endpoint).setRequestId(requestId).setSource(source).build()).build();
	}
	
	public static GNetMessage callRequest(String endpoint, int requestiId, String source, GBean bean) {
		int requestId = DRandom.getRandom(1, 4000000);
		return GNetMessage.newBuilder().setCallReq(GNetCallRequest.newBuilder().setEndPoint(endpoint).setData(bean).setRequestId(requestId).setSource(source).build()).build();
	}
	
	public static GNetMessage callResponseError(int requestId, String error) { 
		return GNetMessage.newBuilder().setCallResp(GNetCallResponse.newBuilder().setCode(GNetCode.ERROR).setException(error).setRequestId(requestId).build()).build();
	}
	
	public static GNetMessage callResponseOK(int requestId, GBean data) { 
		return GNetMessage.newBuilder().setCallResp(GNetCallResponse.newBuilder().setCode(GNetCode.OKAY).setData(data).setRequestId(requestId).build()).build();
	}
	
	public static GNetMessage channelResponseError(String error, int requestId) { 
		return GNetMessage.newBuilder().setChanelResp(GNetChannelResponse.newBuilder().setCode(GNetCode.ERROR).setException(error).setRequestId(requestId).build()).build();
	}
	
	public static GNetMessage channelResponseOK(int channelId, int requestId) { 
		return GNetMessage.newBuilder().setChanelResp(GNetChannelResponse.newBuilder().setCode(GNetCode.OKAY).setChannelId(channelId).setRequestId(requestId).build()).build();
	}
	
	public static GValue intValue(int value) { 
		return GValue.newBuilder().setInt(GIntValue.newBuilder().setValue(value).build()).build();
	}
	
	public static GValue stringValue(String value) { 
		return 	GValue.newBuilder().setString(GStringValue.newBuilder().setValue(value).build()).build();
	}
	
	public static GValue doubleValue(Double value) { 
		return 	GValue.newBuilder().setDouble(GDoubleValue.newBuilder().setValue(value).build()).build();
	}
	
	public static GField stringField(String name, String value) { 
		return GField.newBuilder().setName(name).setValue(stringValue(value)).build();
	}

	public static GField intField(String name, int value) { 
		return GField.newBuilder().setName(name).setValue(intValue(value)).build();
	}

	public static GField doubleField(String name, double value) { 
		return GField.newBuilder().setName(name).setValue(doubleValue(value)).build();
	}
	
	public static GBeanInsert beanInsert(GBean bean) { 
		return GBeanInsert.newBuilder().setBean(bean).build();
	}
	
	public static GBeanDelete beanRemove(GBean bean) { 
		return GBeanDelete.newBuilder().setId(bean.getId()).build();
	}

}
