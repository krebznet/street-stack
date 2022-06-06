package com.dunkware.xstream.net.core.util;

import java.util.List;

import com.dunkware.net.proto.netstream.GNetClientConnectResponse;
import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.net.proto.netstream.GNetEntity;
import com.dunkware.net.proto.netstream.GNetEntityScannerRequest;
import com.dunkware.net.proto.netstream.GNetEntitySearchComplete;
import com.dunkware.net.proto.netstream.GNetEntitySearchException;
import com.dunkware.net.proto.netstream.GNetEntitySearchResponse;
import com.dunkware.net.proto.netstream.GNetEntitySearchResults;
import com.dunkware.net.proto.netstream.GNetServerMessage;
import com.dunkware.net.proto.netstream.GNetServerMessage.TypeCase;
import com.dunkware.net.proto.stream.GEntityMatcher;

public class GNetProto {

	public static GNetClientMessage startEntityScannerRequest(int scannerId, int scanInterval, GEntityMatcher matcher, String retValues) { 
		return GNetClientMessage.newBuilder().setEntityScannerRequest(GNetEntityScannerRequest.newBuilder().setMatcher(matcher).setScanInterval(scanInterval).setScannerId(scannerId).setRetVars(retValues).build()).build();
		
	}
	public static GNetEntitySearchResponse entitySearchResponse( int searchId, String source) { 
		return GNetEntitySearchResponse.newBuilder().setSource(source).setSearchId(searchId).build();
		
	}
	
	
	public static GNetEntitySearchException entitySearchException(int searchId, String source, String exception) { 
		return GNetEntitySearchException.newBuilder().setSearchId(searchId).setException(exception).setSource(source).build();
		
	}
	
	public static GNetEntitySearchComplete entitySearchComplete(int searchId, String source) { 
		return GNetEntitySearchComplete.newBuilder().setSearchId(searchId).setSource(source).build();
	}
	public static boolean isStartEntityScannerResponse(int scannerId, GNetServerMessage message) { 
		if(message.getTypeCase() == TypeCase.ENTITYSCANNERRESPONSE) { 
			if(message.getEntityScannerResponse().getScannerId() == scannerId) { 
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
	
	public static boolean isClientConnectMessage(GNetClientMessage message) { 
		if(message.getTypeCase() == GNetClientMessage.TypeCase.CONNECTREQUEST) {
			return true;
		}
		return false;
	}
	
	public static boolean isClientEntitySearchRequest(GNetClientMessage message) { 
		if(message.getTypeCase() == GNetClientMessage.TypeCase.ENTITYSEARCHREQUEST) {
			return true;
		}
		return false;
	}
	
	public static GNetServerMessage connectResponse(boolean connected, String exception) { 
		return GNetServerMessage.newBuilder().
		setConnectResponse(GNetClientConnectResponse.newBuilder().setConnected(true).setError(exception).build()).build();
	}
	
	public static GNetEntitySearchResults entitySearchResults(List<GNetEntity> entities, int searchId, String source) { 
		return GNetEntitySearchResults.newBuilder().addAllEntities(entities).setSearchId(searchId).setSource(source).build();

	}
	
	
	
	
}
