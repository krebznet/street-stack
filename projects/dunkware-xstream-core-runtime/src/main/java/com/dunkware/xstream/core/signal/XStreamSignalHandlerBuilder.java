package com.dunkware.xstream.core.signal;

import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntityQuery;
import com.dunkware.xstream.model.signal.type.XStreamSignalType;

import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;

public class XStreamSignalHandlerBuilder {

	
	public Future<XStreamSignalHandler> build(XStreamSignalsImpl signals, XStream stream, XStreamSignalType type) { 
		Promise<XStreamSignalHandler> promise = Promise.promise();
		Future<XStreamEntityQuery> queryFuture = stream.buildEntityQuery(type.getQuery());
		queryFuture.onSuccess(new Handler<XStreamEntityQuery>() {
			
			@Override
			public void handle(XStreamEntityQuery event) {
				XStreamSignalHandler handler = new XStreamSignalHandler();
				XStreamEntityQuery query = queryFuture.result();
				try {
					handler.start(signals, stream, query, type);
					promise.complete(handler);
				} catch (Exception e) {
					promise.fail("Exception Starting Signal Handler " + type.getIdentifier() + " error " + e.toString());
				}
				
			}
		});
		queryFuture.onFailure(new Handler<Throwable>() {
			
			@Override
			public void handle(Throwable event) {
				promise.fail("Exception creating signal handler " + type.getIdentifier() + " " + event.getMessage());
			}
		});
		return promise.future();
		
		
	}
}
