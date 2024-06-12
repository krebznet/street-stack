package com.dunkware.trade.service.stream.worker.session.test;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.trade.service.stream.worker.session.query.AsyncQueryBuilder;
import com.dunkware.trade.service.stream.worker.session.query.AsyncQueryContext;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntityQuery;
import com.dunkware.xstream.core.mock.MockXStream;
import com.dunkware.xstream.core.search.row.criteria.XStreamEntityPredicate;
import com.dunkware.xstream.model.entity.query.type.XStreamEntityQueryModelBuilder;
import com.dunkware.xstream.model.entity.query.type.XStreamEntityQueryType;
import com.dunkware.xstream.model.entity.query.type.XStreamOperator;
import com.dunkware.xstream.model.stats.proto.EntityStatBulkReq;
import com.dunkware.xstream.model.stats.proto.EntityStatReqType;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

@Service()
public class TestQueryService {

	private XStream stream;
	
	@Autowired
	private DunkNet dunkNet;
	
	
	
	@Autowired
	private void load() { 
		try {
			dunkNet.extension(this);	
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}
	
	
	/**
	 * Just saw my first real understanding of futures and promises happen with a query getting biult! 
	 */
	public void test1() { 

		XStreamEntityQueryType model = XStreamEntityQueryModelBuilder.query(XStreamEntityQueryModelBuilder
				.valueCritiera(XStreamOperator.GreaterThan, 2, XStreamEntityQueryModelBuilder.currentVarValue("Last")));
		
		model = XStreamEntityQueryModelBuilder.currentHistoricalCompare("TickLast", 3, "JPM", true, 4);
		
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
				
				System.out.println("So this is where we handle the real exception root, i see the non blockoing " + event.toString());
				
			} 
		});
		
		future.onSuccess(new Handler<XStreamEntityQuery>() {

			@Override
			public void handle(XStreamEntityQuery event) {
				System.out.println("Party! first query built!!");
				for (XStreamEntityPredicate pred : event.getPredicates()) {
					System.out.println(pred.getClass().getName());
				}
			}
		
		});
		
		try {
			builder.buildQuery(context, model);
				
		} catch (Exception e) {
			
		}
		
		
	}
	
	/**
	 * Test bulk stat req
	 */
	public void test2() { 
		try {
			
			
			EntityStatBulkReq req = EntityStatBulkReq.Builder.builder(EntityStatReqType.VarHighRelative).addEntity("AAPL").addEntity("JPM")
				.setDate(LocalDate.now().minusDays(4)).setRelativeDays(5).target("TickLast").stream("us_equity").build();
				
			
		
			
			Future<Object> future = dunkNet.serviceFuure(req);
			future.onComplete(new  Handler<AsyncResult<Object>>() {
				
				@Override
				public void handle(AsyncResult<Object> event) {
					if(event.failed()) { 
						// well then that does it. 
						System.out.println(event.cause());
						event.cause().printStackTrace();
						
					}
					System.out.println("got the query!");
					return;
				}
			});
			
			
			future.onFailure(new Handler<Throwable>() {
				
				@Override
				public void handle(Throwable event) {
					System.out.println("Failed on the close call to get data from chic fliel" + event.toString());
				}
			});
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			// TODO: handle exception
		}
		
	};
}



	


