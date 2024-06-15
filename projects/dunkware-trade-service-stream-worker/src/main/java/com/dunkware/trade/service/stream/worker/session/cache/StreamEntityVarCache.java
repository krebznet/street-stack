package com.dunkware.trade.service.stream.worker.session.cache;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import com.dunkware.trade.service.stream.worker.session.StreamWorker;
import com.dunkware.trade.service.stream.worker.session.StreamWorkerExtension;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityVar;
import com.dunkware.xstream.api.XStreamEntityVarListener;
import com.dunkware.xstream.api.XStreamListener;

public class StreamEntityVarCache implements StreamWorkerExtension, XStreamListener, XStreamEntityVarListener {

	private StreamWorker worker; 
	private XStream stream; 
	

	private BlockingQueue<Object> varInsert; 
	
	// MetricCollector.newInstance("dd"); 
	// java-utils-metrics
		// MetricRegistry
		// 
	// MetricDash.createSet("S sdds") 
	//MetricGuage.setValue() = - MetricSet.createGuage("Lamesd", format")
	
	// MetricSet
	// MetricGuage
	// MetricCounter
	// 
	// MetricRegistry
	// Metric java-util-metrics 
	// MetricRegistry.add MetricSet("name"); 
	
	// then you can pull the metrics registry and serialize it
	
	// Metrics { 
	//   - Guage -> format - > type 
	// 	 - threshold -> 
	// MetricsRegistry 
	// MetricSet 
	// MetricRegistry 
		// Metric --> getMetaDataModel
		// getMetaModel()
		// getMetrics
				// -- 0 
		// Counter
		// Guage("Sl Sd", 
	
	@Override
	public void init(StreamWorker worker) throws Exception {
		this.worker = worker; 
	}

	@Override
	public void start() throws Exception {
		this.worker.getStream();
		stream.addStreamListener(this);
	}

	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rowInsert(XStreamEntity row) {
		row.addVarListener(this);
		
	}

	@Override
	public void varUpdate(XStreamEntityVar var) {
		// TODO Auto-generated method stub
		
	}
	
	private class Metrics { 
		public AtomicInteger entityCount = new AtomicInteger();
		public AtomicInteger variableCount = new AtomicInteger();
		public AtomicInteger variableInsertCount = new AtomicInteger();
		public AtomicInteger insertQueueSize = new AtomicInteger();
	}
	
	
	
	

	
}
