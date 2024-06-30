package com.dunkware.trade.service.stream.worker.session.query;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.xstream.api.XStream;

public class AsyncQueryContext {

	
	private XStream stream;
	
	private DunkNet dunkNet;
	

	public XStream getStream() {
		return stream;
	}

	public void setStream(XStream stream) {
		this.stream = stream;
	}

	public DunkNet getDunkNet() {
		return dunkNet;
	}

	public void setDunkNet(DunkNet dunkNet) {
		this.dunkNet = dunkNet;
	}

	
	
}
