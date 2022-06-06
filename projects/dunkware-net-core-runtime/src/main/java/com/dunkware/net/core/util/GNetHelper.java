package com.dunkware.net.core.util;

import com.dunkware.net.proto.net.GNetMessage;
import com.dunkware.net.proto.net.GNetMessage.ValueCase;

public class GNetHelper {

	public static boolean isCallRequest(GNetMessage message) { 
		if(message.getValueCase() == ValueCase.CALLREQ) { 
			return true;
		}
		return false;
	}
	
}
