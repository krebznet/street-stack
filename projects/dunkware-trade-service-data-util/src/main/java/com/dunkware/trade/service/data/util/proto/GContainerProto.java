package com.dunkware.trade.service.data.util.proto;

import com.dunkware.net.proto.data.cluster.GContainerServerMessage;
import com.dunkware.net.proto.data.cluster.GContainerServerMessage.TypeCase;
import com.dunkware.net.proto.data.cluster.GContainerWorkerMessage;
import com.dunkware.net.proto.netstream.GNetEntitySearchRequest;
import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GStreamTimeUpdate;

public class GContainerProto {
	
	public static GContainerWorkerMessage snapshotMessage(GEntitySnapshot snapshot) {
		return GContainerWorkerMessage.newBuilder().setEntitySnapshot(snapshot).build();
	}
	
	public static GContainerWorkerMessage signalMessage(GEntitySignal signal) { 
		return GContainerWorkerMessage.newBuilder().setEntitySignal(signal).build();
	}
	
	public static GContainerWorkerMessage timeUpdateMessage(GStreamTimeUpdate update) { 
		return GContainerWorkerMessage.newBuilder().setTimeUpdate(update).build();
	}
	
	public static GContainerWorkerMessage searchRequestMessage(GNetEntitySearchRequest req) { 
		return GContainerWorkerMessage.newBuilder().setEntitySearchRequest(req).build();
	}
	
	public static boolean isEntitySearchResults(GContainerServerMessage message, int searchId) { 
		if(message.getTypeCase() == TypeCase.ENTITYSEARCHRESULTS) {
			if(message.getEntitySearchResults().getSearchId() == searchId) { 
				return true;
			}
		}
		return false; 
	}
	
	public static boolean isEntitySearchException(GContainerServerMessage message, int searchId) { 
		if(message.getTypeCase() == TypeCase.ENTITYSEARCHEXCEPTION) {
			if(message.getEntitySearchException().getSearchId() == searchId) { 
				return true;
			}
		}
		return false; 
	}
	
	public static boolean isEntitySearchComplete(GContainerServerMessage message, int searchId) { 
		if(message.getTypeCase() == TypeCase.ENTITYSEARCHCOMPLETE) {
			if(message.getEntitySearchComplete().getSearchId() == searchId) { 
				return true;
			}
		}
		return false; 
	}
	public static boolean isEntitySearchResponse(GContainerServerMessage message, int searchId) { 
		if(message.getTypeCase() == TypeCase.ENTITYSEARCHRESPONSE) {
			if(message.getEntitySearchResponse().getSearchId() == searchId) { 
				return true;
			}
		}
		return false; 
	}

}
