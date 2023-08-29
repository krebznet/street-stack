package com.dunkware.trade.net.data.server.helpers;

import org.bson.Document;

import com.dunkware.trade.service.data.model.domain.EntitySignal;

public class StreamDataHelper {

	 
	public static Document toSignalDocument(EntitySignal signal) {
		Document container = new Document();

		container.append("timestamp", signal.getTime());
	//	container.append("type", signal.getSignalId());
		container.append("entity", signal.getEntityId());
		// todo handle variables 
		return container;
	}
}
