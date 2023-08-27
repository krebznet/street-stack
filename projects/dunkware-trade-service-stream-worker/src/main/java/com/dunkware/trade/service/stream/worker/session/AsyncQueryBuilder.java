package com.dunkware.trade.service.stream.worker.session;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.spring.cluster.DunkNetServiceResponse;
import com.dunkware.xstream.api.XStreamEntityQuery;
import com.dunkware.xstream.core.search.row.criteria.XStreamEntityPredicate;
import com.dunkware.xstream.core.search.row.value.XStreamEntityQueryValue;
import com.dunkware.xstream.core.search.row.value.XStreamEntityVarAggHistValue;
import com.dunkware.xstream.core.search.row.value.XStreamEntityVarCurrentValue;
import com.dunkware.xstream.model.query.XStreamCriteriaModel;
import com.dunkware.xstream.model.query.XStreamEntityCriteriaType;
import com.dunkware.xstream.model.query.XStreamEntityQueryModel;
import com.dunkware.xstream.model.query.XStreamEntityValueModel;
import com.dunkware.xstream.model.query.XStreamEntityValueType;
import com.dunkware.xstream.model.query.XStreamRowValueModel;
import com.dunkware.xstream.model.query.XStreamValueType;
import com.dunkware.xstream.model.stats.EntityStatBulkReq;
import com.dunkware.xstream.model.stats.EntityStatBulkResp;

import io.vertx.core.AsyncResult;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;

public class AsyncQueryBuilder {
	
	public AsyncQueryContext context;
	
	public static void main(String[] args) {
		
	}
	
	public Future<XStreamEntityQuery> buildQuery(XStreamEntityQueryModel model ) {
			List<XStreamEntityPredicate> predicates = new ArrayList<XStreamEntityPredicate>()
			Promise<XStreamEntityQuery> queryPromise = Promise.promise();
			List<Future<XStreamEntityPredicate>> predicateFutures = new ArrayList<Future<XStreamEntityPredicate>>();
			for (XStreamCriteriaModel criteria: model.getCriterias()) {
				Future<XStreamEntityPredicate> future = buiildPredicate(criteria);
				predicateFutures.add(future);
			}
			
			CompositeFuture compFuture = Future.all(predicateFutures);
			compFuture.onSuccess(new Handler<CompositeFuture>() {
				
				@Override
				public void handle(CompositeFuture event) {
					
					System.out.println("stop here figureit out");
				}
			});
			compFuture.onFailure(new Handler<Throwable>() {
				
				@Override
				public void handle(Throwable event) {
					queryPromise.fail(event);
					
				}
			});
			
			
			return queryPromise.future();
	}
	
	private Future<XStreamEntityPredicate> buiildPredicate(XStreamCriteriaModel model) {
		List<Future<XStreamEntityQueryValue>> valueTasks = new ArrayList<Future<XStreamEntityQueryValue>>();
		
		Promise<XStreamEntityPredicate> promise = Promise.promise();
		if(model.getType() == XStreamEntityCriteriaType.Value) {
			valueTasks.add(buildEntityValue(model.getValue1()));
		}
		if(model.getType() == XStreamEntityCriteriaType.ValueCompare) { 
			valueTasks.add(buildEntityValue(model.getValue2()));
		}
		CompositeFuture compFuture = Future.all(valueTasks);
		compFuture.onSuccess(new Handler<CompositeFuture>() {
			
			@Override
			public void handle(CompositeFuture event) {
				System.out.println("also stop here and see what we have");
				// we need 1 or 2 value tasks here to get it. 
			}
		});
		compFuture.onFailure(new Handler<Throwable>() {
			
			@Override
			public void handle(Throwable event) {
				promise.fail(event);
			}
		});
		
	}
	
	private Future<XStreamEntityQueryValue> buildEntityValue(XStreamEntityValueModel model) {
		Promise<XStreamEntityQueryValue> promise = Promise.promise();
		if(model.getType() == XStreamEntityValueType.SignalHistoricalCount) { 
			
		}
		if(model.getType() == XStreamEntityValueType.SignalSessionCount) { 
			
		}
		if(model.getType() == XStreamEntityValueType.VarCurrentValue) { 
			
		}
		
		if(model.getType() == XStreamEntityValueType.VarHistoricalAgg) {
			
		}
		
		if(model.getType() == XStreamEntityValueType.VarSessionAgg) { 
			
		}
		
		
	}
	
	private Future<XStreamEntityQueryValue> buildVarAggHistValue(XStreamEntityValueModel model, XStreamEntityVarAggHistValue value, XStreamEntityVarAggHistValue agg) {
		Promise<XStreamEntityQueryValue> promise = Promise.promise();
			EntityStatBulkReq req = new EntityStatBulkReq();
			req.setAgg(model.getHistoricalAgg().name());
			req.setEntities(context.getStream().getRowIdentifiers());
			req.setTarget(model.getVarIdent());
			req.setRelativeDays(model.getHistoricalTimeRange().getRealtiveTimeRange());
			Future<DunkNetServiceResponse> statReq = entityStatBulkRequest(req);
			statReq.onSuccess(new Handler<DunkNetServiceResponse>() {
				
				@Override
				public void handle(DunkNetServiceResponse event) {
					// TODO Auto-generated method stub
					
				}
			});
			statReq.onFailure(new Handler<Throwable>() {
				
				@Override
				public void handle(Throwable event) {
					// TODO Auto-generated method stub
					
				}
			});	
			return promise.future();
	}
	
	
	private Future<XStreamEntityQueryValue> buildVarCurrentValue(XStreamEntityValueModel model) { 
		Promise<XStreamEntityQueryValue> promise = Promise.promise();
		Future<XStreamEntityQueryValue> future = promise.future();
		model.getVarIdent(); 
		if(!context.getStream().getInput().getScript().varExists(model.getVarIdent()) { 
			promise.fail("Value Type Current Var Value invalid Var reference, not found " + model.getVarIdent());
			return future;
		}
		XStreamEntityVarCurrentValue value = new XStreamEntityVarCurrentValue();
		value.init(model, context.getStream());
		promise.complete(value);
		return future;
	}
	
	private Future<XStreamEntityQueryValue> buildVarAggValue(XStreamEntityValueModel model) { 
		Promise<XStreamEntityQueryValue> promise = Promise.promise();
		Future<XStreamEntityQueryValue> fuure = promise.future();
		
		model.get
		
		
	}
	
	Future<DunkNetServiceResponse> entityStatBulkRequest(EntityStatBulkReq req) {
		Promise<EntityStatBulkResp> promise = Promise.promise();
		dunknetsydunkNet.serviceFuure(EntityStatBulkResp.class);
		dunkNet.serviceFuure(req).onComplete(new Handler<AsyncResult<DunkNetServiceResponse>>() {
			
			@Override
			public void handle(AsyncResult<DunkNetServiceResponse> event) {
				if(event.failed()) { 
					promise.fail("DUnkNet Failed On get servie");
					return;
				}
				if(event.succeeded()) { 
					//EntityStatBulkResp resp = event.res
				}
			}
		});
		
		return promise.future();
		
	}
	
	private Future<XStreamEntityQueryValue>
}
