package com.dunkware.trade.service.stream.worker.session.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.spring.cluster.anot.ADunkNetService;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerEntitiesReq;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerEntitiesResp;
import com.dunkware.trade.service.stream.worker.session.StreamWorker;
import com.dunkware.trade.service.stream.worker.session.StreamWorkerExtension;
import com.dunkware.trade.service.stream.worker.session.anot.AStreamWorkerExtension;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamListener;

@AStreamWorkerExtension
public class StreamWorkerEntityList implements StreamWorkerExtension, XStreamListener {

	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private StreamWorker worker; 
	private XStream stream; 
	
	private List<Integer> entities = new ArrayList<Integer>();
	
	@Override
	public void init(StreamWorker worker) throws Exception {
		this.worker = worker;
		worker.getChannel().addExtension(this);
	}

	@Override
	public void start() throws Exception {
		this.stream = worker.getStream();
		stream.addStreamListener(this);
	}

	@Override
	public void stop() throws Exception {
		stream.removeStreamListener(this);
	}

	@Override
	public void rowInsert(XStreamEntity row) {
		entities.add(row.getIdentifier());
	}
	
	@ADunkNetService(label = "Stream Session Entity List Service")
	public StreamSessionWorkerEntitiesResp workerEntities(StreamSessionWorkerEntitiesReq req) { 
		StreamSessionWorkerEntitiesResp resp =  new StreamSessionWorkerEntitiesResp();
		resp.setEntities(entities);
		return resp;
	}


}
