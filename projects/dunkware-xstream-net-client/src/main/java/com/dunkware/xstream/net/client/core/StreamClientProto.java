package com.dunkware.xstream.net.client.core;

import com.dunkware.net.proto.netstream.GNetClientConnectRequest;
import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.net.proto.netstream.GNetEntityMatcher;
import com.dunkware.net.proto.netstream.GNetEntitySearchRequest;
import com.dunkware.net.proto.netstream.GNetServerMessage;
import com.dunkware.net.proto.netstream.GNetServerMessage.TypeCase;

public class StreamClientProto {
	
	public static boolean isEntitySearchResponse(GNetServerMessage message	, int searchId) { 
		if(message.getTypeCase() == TypeCase.ENTITYSEARCHRESPONSE) { 
			if(message.getEntitySearchResponse().getSearchId() == searchId) { 
				return true;
			}
		}
		return false;
	}
	
	public static boolean isConnectionResponse(GNetServerMessage message) { 
		if(message.getTypeCase() == TypeCase.CONNECTRESPONSE) { 
			return true;
		}
		return false; 
	}
	
	public static boolean isEntitySearchException(GNetServerMessage message	, int searchId) { 
		if(message.getTypeCase() == TypeCase.ENTITYSEARCHEXCEPTION) { 
			if(message.getEntitySearchException().getSearchId() == searchId) { 
				return true;
			}
		}
		return false;
	}
	
	public static boolean isEntitySearchResults(GNetServerMessage message, int searchId) { 
		if(message.getTypeCase() == TypeCase.ENTITYSEARCHRESULTS) { 
			if(message.getEntitySearchResults().getSearchId() == searchId) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isEntitySearchComplete(GNetServerMessage message, int searchId) { 
		if(message.getTypeCase() == TypeCase.ENTITYSEARCHCOMPLETE) { 
			if(message.getEntitySearchResults().getSearchId() == searchId) {
				return true;
			}
		}
		return false;
	}
	
	public static GNetClientMessage entitySearchRequest(GNetEntityMatcher matcher, String retVars, int searchId) { 
		GNetEntitySearchRequest req = GNetEntitySearchRequest.newBuilder().setSearchId(searchId).setMatcher(matcher).setRetVars(retVars).setSource(retVars).build();
		return GNetClientMessage.newBuilder().setEntitySearchRequest(req).build();
	}
	
	public static GNetClientMessage connectRequest(String clientIdent, String stream) { 
		return GNetClientMessage.newBuilder().setConnectRequest(GNetClientConnectRequest.newBuilder().setClientIdent(clientIdent).setStream(stream).build()).build();
		
	}

}
