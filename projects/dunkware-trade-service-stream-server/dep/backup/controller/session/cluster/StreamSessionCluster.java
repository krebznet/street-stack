package com.dunkware.trade.service.stream.server.controller.session.cluster;

import com.dunkware.net.cluster.node.anot.AClusterExtension;
import com.dunkware.net.cluster.node.anot.AClusterService;
import com.dunkware.xstream.model.search.SessionEntitySearchReq;
import com.dunkware.xstream.model.search.SessionEntitySearchResp;

@AClusterExtension()
public class StreamSessionCluster {
	
	
	@AClusterService
	public SessionEntitySearchResp entityQuery(SessionEntitySearchReq req) { 
		return null;
	}

}
