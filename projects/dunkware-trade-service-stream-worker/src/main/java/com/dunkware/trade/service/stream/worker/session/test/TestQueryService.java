package com.dunkware.trade.service.stream.worker.session.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.trade.service.stream.worker.session.AsyncQueryBuilder;
import com.dunkware.trade.service.stream.worker.session.AsyncQueryContext;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntityQuery;
import com.dunkware.xstream.core.mock.MockXStream;
import com.dunkware.xstream.model.query.XStreamEntityQueryModel;
import com.dunkware.xstream.model.query.XStreamEntityQueryModelBuilder;
import com.dunkware.xstream.model.query.XStreamOperator;

import io.vertx.core.Future;
import io.vertx.core.Handler;

@Service()
public class TestQueryService {

	private XStream stream;
	
	@Autowired
	private DunkNet dunkNet;
	
	
	public void test1() { 

		XStreamEntityQueryModel model = XStreamEntityQueryModelBuilder.query(XStreamEntityQueryModelBuilder
				.valueCritiera(XStreamOperator.GreaterThan, 2, XStreamEntityQueryModelBuilder.currentVarValue("Last")));
		
		MockXStream stream = new MockXStream();
		try {
			stream.start(null);			
		} catch (Exception e) {
			// TODO: handle exception
		}
		AsyncQueryContext context = new AsyncQueryContext();
		context.setDunkNet(dunkNet);
		context.setStream(stream);
		
		AsyncQueryBuilder builder = new AsyncQueryBuilder();
		
		Future<XStreamEntityQuery> future = builder.buildQuery(context, model);
		
		future.onFailure(new  Handler<Throwable>() {
			
			@Override
			public void handle(Throwable event) {
				
				System.out.println(event.toString());
			}
		});
		
		try {
			builder.buildQuery(context, model);
				
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
}
