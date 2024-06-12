package com.dunkware.trade.service.stream.worker.session.query;

import com.dunkware.trade.service.stream.worker.session.StreamWorker;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntityQuery;
import com.dunkware.xstream.api.XStreamEntityQueryBuilder;
import com.dunkware.xstream.model.entity.query.type.XStreamEntityQueryType;

import io.vertx.core.Future;

public class StreamWorkerEntityQueryBuilder implements XStreamEntityQueryBuilder  {
	
	private StreamWorker streamWorker; 
	
	public void init(StreamWorker worker) { 
		this.streamWorker = worker;
	}

	@Override
	public Future<XStreamEntityQuery> build(XStream stream, XStreamEntityQueryType model) {
		AsyncQueryBuilder builder = new AsyncQueryBuilder();
		AsyncQueryContext context = new AsyncQueryContext();
		context.setDunkNet(streamWorker.getDunkNet());
		context.setStream(stream);
		return builder.buildQuery(context, model);
	}

	
	
}
