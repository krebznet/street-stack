package com.dunkware.xstream.net.core.util;

import com.dunkware.net.proto.netstream.GEntityMatcher;
import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.net.proto.netstream.GNetEntityScannerStartRequest;
import com.dunkware.net.proto.netstream.GNetServerMessage;
import com.dunkware.net.proto.netstream.GNetServerMessage.TypeCase;

public class GNetProto {

	public static GNetClientMessage scannerStartRequestMessage(String identifier, GEntityMatcher matcher) { 
		return GNetClientMessage.newBuilder().setScannerStart(GNetEntityScannerStartRequest.newBuilder().setMatcher(matcher).setScannerIdent(identifier).build()).build();
	}
	
	public static boolean isEntityScannerResponse(String scannerIdentifier, GNetServerMessage message) { 
		if(message.getTypeCase() == TypeCase.ENTITYSCANNERSTART) { 
			if(message.getEntityScannerStart().getScannerIdent().equals(scannerIdentifier)) { 
				return true;
			}
		}
		return false; 
	}
	
	public static boolean isEntityScannerUpdate(String identifier, GNetServerMessage message) { 
		if(message.getTypeCase() == TypeCase.ENTITYSCANNERUPDATE) {
			
		}
		return false;
	}
	
}
