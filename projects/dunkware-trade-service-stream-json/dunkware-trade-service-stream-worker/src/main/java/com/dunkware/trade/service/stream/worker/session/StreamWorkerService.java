package com.dunkware.trade.service.stream.worker.session;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.anot.ADunkNetChannel;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerCreateReq;

@Service()
public class StreamWorkerService  {
	
	@Autowired
	private ApplicationContext ac;
	
	@Autowired
	private DunkNet dunkNet;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamWorkerService");
	
	@PostConstruct
	private void init() { 
		try {
			dunkNet.extensions().addExtension(this);	
		} catch (Exception e) {
			logger.error(marker, "Exception adding DunkNet Extensions " + e.toString());
		}
		
	}
	
	@ADunkNetChannel(label = "Create Stream Session Worker Channel")
	public StreamWorker workerChannel(StreamSessionWorkerCreateReq req) throws Exception { 
		StreamWorker node = new StreamWorker();
		ac.getAutowireCapableBeanFactory().autowireBean(node);
		node.init();
		return node;
	}
	

	
	

}
