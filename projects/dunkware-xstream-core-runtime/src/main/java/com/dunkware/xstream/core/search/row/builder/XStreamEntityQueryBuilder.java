package com.dunkware.xstream.core.search.row.builder;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.xstream.api.XStreamEntityQuery;
import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.core.search.row.XStreamEntityQueryImpl;
import com.dunkware.xstream.core.search.row.criteria.XStreamEntityPredicate;
import com.dunkware.xstream.core.search.row.criteria.XStreamEntityValuePredicate;
import com.dunkware.xstream.model.query.XStreamCriteriaModel;
import com.dunkware.xstream.model.query.XStreamEntityQueryModel;

import io.vertx.core.AsyncResult;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;


public class XStreamEntityQueryBuilder {

	public static XStreamEntityQueryBuilder newInstance(XStreamEntityQueryModel model) { 
		return new XStreamEntityQueryBuilder(model);
	}
	
	 
	private XStreamEntityQueryModel model; 
	
	private XStreamEntityQueryBuilder(XStreamEntityQueryModel model) { 
		this.model = model;
		 
	}
	
	public XStreamEntityQuery build() throws XStreamQueryException { 
		
		return null;
	}
	
	public  Promise<List<XStreamEntityPredicate>>  buildPredicates()  { 
		Promise<List<XStreamEntityPredicate>> promise  = Promise.promise();  
		final Object lock = new Object();
		List<Future<XStreamEntityPredicate>> predicateFutures = new ArrayList<Future<XStreamEntityPredicate>>();
		for (XStreamCriteriaModel criteriaModel : model.getCriterias()) {
			predicateFutures.add(buildPredicate(criteriaModel));
		}
		final List<Throwable> problems = new ArrayList<Throwable>();
		
		/*
		 * List<XStreamEntityPredicate> predicates = new
		 * ArrayList<XStreamEntityPredicate>(); Handler<AsyncResult<CompositeFuture>>
		 * handler = new Handler<AsyncResult<CompositeFuture>>() {
		 * 
		 * @Override public void handle(AsyncResult<CompositeFuture> event) { for
		 * (Future<XStreamEntityPredicate> future2 : predicateFutures) {
		 * if(future2.failed()) { problems.add(future2.cause());
		 * promise.fail(future2.cause()); } else { XStreamEntityPredicate predicate =
		 * future2.result(); predicates.add(predicate);
		 * 
		 * 
		 * } }
		 * 
		 * 
		 * } };
		 */
		
		
	    CompositeFuture composite = Future.all(predicateFutures);
	    composite.onFailure(new Handler<Throwable>() {
			
			@Override
			public void handle(Throwable event) {
				Object results = composite.result();
				System.out.println("okay");
			}
		}); 
	    composite.onSuccess(new Handler<CompositeFuture>() {
			
			@Override
			public void handle(CompositeFuture event) {
				Object results = event.result();
				System.out.println("here");
			}
		
		
	    });
	    	
	    
	    while(!composite.isComplete() == false) {}
		if(problems.size() > 0) { 
			throw new XStreamQueryException(problems.get(0).toString());
		}
	 
		return predicates;
		
		
	}
	
	
	public Future<XStreamEntityPredicate> buildPredicate(XStreamCriteriaModel input) {
		
		Promise<XStreamEntityPredicate> promise = Promise.promise();
		
		
		Thread tester = new Thread() { 
			
			public void run() { 
				try {
					Thread.sleep(4007);
					XStreamEntityValuePredicate pred = new XStreamEntityValuePredicate();
					pred.init(input);
					promise
					promise.complete(pred);
					return;
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
			}
			
		};
		tester.start();
		return promise.future();
	}
	
	
	
	

}
