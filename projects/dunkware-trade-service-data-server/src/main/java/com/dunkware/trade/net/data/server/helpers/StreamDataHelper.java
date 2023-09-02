package com.dunkware.trade.net.data.server.helpers;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.dunkware.trade.service.data.model.domain.EntitySignal;

public class StreamDataHelper {

	 
	public static Document toSignalDocument(EntitySignal signal) {
		Document container = new Document();
		signal.setEntity(0);
		container.append("timestamp", signal.getTime());
		container.append("type", signal.getType());
		container.append("entity", signal.getEntity());
		
	
	//ASYNC CALL IT AND IF WE HTO HAVE I RETURN PROMSE BEORE GOD
		
		// todo handle variables 
		return container;
	}
	
	public static List<Document> buildVarSnapshots(List<EntitySignal> snapshots) throws Exception{
		List<Document> vars = new ArrayList<Document>();
			return null;
		
	
		

		

	}
}
