package com.dunkware.trade.service.stream.worker.session;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.xstream.api.XStreamEntityQuery;
import com.dunkware.xstream.core.search.row.XStreamEntityQueryImpl;
import com.dunkware.xstream.core.search.row.criteria.XStreamEntityPredicate;
import com.dunkware.xstream.core.search.row.criteria.XStreamEntityValueComparePredicate;
import com.dunkware.xstream.core.search.row.criteria.XStreamEntityValuePredicate;
import com.dunkware.xstream.core.search.row.value.XStreamEntityQueryValue;
import com.dunkware.xstream.core.search.row.value.XStreamEntityVarAggHistValue;
import com.dunkware.xstream.core.search.row.value.XStreamEntityVarCurrentValue;
import com.dunkware.xstream.model.query.XStreamCriteriaModel;
import com.dunkware.xstream.model.query.XStreamEntityCriteriaType;
import com.dunkware.xstream.model.query.XStreamEntityQueryModel;
import com.dunkware.xstream.model.query.XStreamEntityValueModel;
import com.dunkware.xstream.model.query.XStreamEntityValueType;
import com.dunkware.xstream.model.stats.proto.EntityStatBulkReq;
import com.dunkware.xstream.model.stats.proto.EntityStatBulkResp;

import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;

public class AsyncQueryBuilder {

	public AsyncQueryContext context;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("EntityQueryBuilder");

	public Future<XStreamEntityQuery> buildQuery(AsyncQueryContext context, XStreamEntityQueryModel model) {
		DStopWatch watch = DStopWatch.create();
		watch.start();
		this.context = context;

		List<XStreamEntityPredicate> predicates = new ArrayList<XStreamEntityPredicate>();

		Promise<XStreamEntityQuery> queryPromise = Promise.promise();
		List<Future<XStreamEntityPredicate>> predicateFutures = new ArrayList<Future<XStreamEntityPredicate>>();
		for (XStreamCriteriaModel criteria : model.getCriterias()) {
			Future<XStreamEntityPredicate> future = buiildPredicate(criteria);
			predicateFutures.add(future);
		}

		CompositeFuture compFuture = Future.all(predicateFutures);
		compFuture.onSuccess(new Handler<CompositeFuture>() {

			@Override
			public void handle(CompositeFuture event) {
				int size = event.size();
				int i = 0;
				while (i < size) {
					XStreamEntityPredicate predicate = event.resultAt(i);
					predicates.add(predicate);
					i++;
				}
				XStreamEntityQueryImpl query = new XStreamEntityQueryImpl();
				watch.stop();
				query.init(predicates, context.getStream(), watch.getCompletedSeconds());
				queryPromise.complete(query);
				return;

			}
		});
		compFuture.onFailure(new Handler<Throwable>() {

			@Override
			public void handle(Throwable event) {
				queryPromise.fail("Exception in Query Future Return " + event.toString());
				return;

			}
		});

		return queryPromise.future();
	}

	private Future<XStreamEntityPredicate> buiildPredicate(XStreamCriteriaModel model) {
		List<Future<XStreamEntityQueryValue>> valueTasks = new ArrayList<Future<XStreamEntityQueryValue>>();

		Promise<XStreamEntityPredicate> promise = Promise.promise();
		if (model.getType() == XStreamEntityCriteriaType.Value) {
			valueTasks.add(buildEntityValue(model.getValue1()));
		}
		if (model.getType() == XStreamEntityCriteriaType.ValueCompare) {
			valueTasks.add(buildEntityValue(model.getValue2()));
		}
		CompositeFuture compFuture = Future.all(valueTasks);
		compFuture.onSuccess(new Handler<CompositeFuture>() {
			List<XStreamEntityQueryValue> values = new ArrayList<XStreamEntityQueryValue>();

			@Override
			public void handle(CompositeFuture event) {
				int i = 0;
				int size = event.size();
				while (i < size) {
					XStreamEntityQueryValue value = event.resultAt(i);
					values.add(value);
					i++;
				}
				if (model.getType() == XStreamEntityCriteriaType.Value) {
					XStreamEntityValuePredicate predicate = new XStreamEntityValuePredicate();
					predicate.init(model, values.get(0));
					promise.complete(predicate);
					return;
				} else {
					if (values.size() < 2) {
						promise.fail("Value compare only returned 1 value");
						return;
					}
					XStreamEntityValueComparePredicate predicate = new XStreamEntityValueComparePredicate();
					try {
						predicate.init(values.get(0), values.get(1), model);
						promise.complete(predicate);
						return;
					} catch (Exception e) {
						promise.fail("Exception init value compare predicate " + e.toString());
						return;
					}
				}

			}
		});
		compFuture.onFailure(new Handler<Throwable>() {

			@Override
			public void handle(Throwable event) {
				promise.fail(event);
			}
		});

		return promise.future();

	}

	private Future<XStreamEntityQueryValue> buildEntityValue(XStreamEntityValueModel model) {
		if (model.getType() == XStreamEntityValueType.SignalHistoricalCount) {
			return buildSignalHistCountValue(model);

		}
		if (model.getType() == XStreamEntityValueType.SignalSessionCount) {
			return buildSignalCountValue(model);
		}
		if (model.getType() == XStreamEntityValueType.VarCurrentValue) {
			return buildVarCurrentValue(model);
		}

		if (model.getType() == XStreamEntityValueType.VarHistoricalAgg) {
			return buildVarAggHistValue(model);
		}

		if (model.getType() == XStreamEntityValueType.VarSessionAgg) {
			return buildVarAggValue(model);
		}

		Promise<XStreamEntityQueryValue> promise = Promise.promise();
		Future<XStreamEntityQueryValue> future = promise.future();
		promise.fail("XStreamEntityValue Type " + model.getType().name() + " not handled in query builder");
		return future;

	}

	private Future<XStreamEntityQueryValue> buildVarAggHistValue(XStreamEntityValueModel model) {
		Promise<XStreamEntityQueryValue> promise = Promise.promise();
		EntityStatBulkReq req = new EntityStatBulkReq();
		req.setAgg(model.getHistoricalAgg().name());
		req.setEntities(context.getStream().getRowIdentifiers());
		req.setTarget(model.getVarIdent());
		req.setStream("us_equity");
		req.setRelativeDays(model.getHistoricalTimeRange().getRealtiveTimeRange());
		Future<Object> statReq = context.getDunkNet().serviceFuure(req);
		logger.info(marker, "Invoking BulkEntity Stat Request Entity Count {}",req.getEntities().size());
		statReq.onSuccess(new Handler<Object>() {

			@Override
			public void handle(Object event) {
				System.out.println("wow");
				try {
					EntityStatBulkResp resp = (EntityStatBulkResp)event;
					if(resp.isSuccess() == false) { 
						logger.error(marker, "EntityStatBulkResp returned error {}",resp.getException());
						promise.fail("Entity Stat Bulk Req Reutrned Error " + resp.getException());
						return;
					}
					logger.info(marker, "Invoked Completed BulkEntity Stat Request ");
					XStreamEntityVarAggHistValue value = new XStreamEntityVarAggHistValue();
					value.init(model, context.getStream(), resp);
					promise.complete(value);
					return;
				} catch (Exception e) {
					logger.error(marker, "Outside exception invoking enttiy statu bulkd request");
					promise.fail("Exception invoking net stat request service " + e.toString());
					return;
				}
			}

		});
		statReq.onFailure(new Handler<Throwable>() {

			@Override
			public void handle(Throwable event) {
				promise.fail("stat request failed for var agg hist " + event.toString());
				return;
			}
		});
		return promise.future();
	}

	private Future<XStreamEntityQueryValue> buildVarCurrentValue(XStreamEntityValueModel model) {
		Promise<XStreamEntityQueryValue> promise = Promise.promise();
		Future<XStreamEntityQueryValue> future = promise.future();
		model.getVarIdent();
		// if(!(context.getStream().getInput().getScript().varExists(model.getVarIdent())))
		// {
		// promise.fail("Value Type Current Var Value invalid Var reference, not found "
		// + model.getVarIdent());
		// return future;
		// }
		XStreamEntityVarCurrentValue value = new XStreamEntityVarCurrentValue();
		value.init(model, context.getStream());
		promise.complete(value);
		return future;
	}

	private Future<XStreamEntityQueryValue> buildVarAggValue(XStreamEntityValueModel model) {
		Promise<XStreamEntityQueryValue> promise = Promise.promise();
		Future<XStreamEntityQueryValue> future = promise.future();
		return future;

	}

	private Future<XStreamEntityQueryValue> buildSignalCountValue(XStreamEntityValueModel model) {
		Promise<XStreamEntityQueryValue> promise = Promise.promise();
		Future<XStreamEntityQueryValue> future = promise.future();
		promise.fail("Signal Counts Not Implemented Yet");
		return future;
	}

	private Future<XStreamEntityQueryValue> buildSignalHistCountValue(XStreamEntityValueModel model) {
		Promise<XStreamEntityQueryValue> promise = Promise.promise();
		Future<XStreamEntityQueryValue> future = promise.future();
		promise.fail("Signal Counts Not Implemented Yet");
		return future;
	}

	Future<Object> netService(Object req) {
		return context.getDunkNet().serviceFuure(EntityStatBulkResp.class);

	}

}
