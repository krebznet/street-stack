package com.dunkware.xstream.core.search.row.builder;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntityQuery;
import com.dunkware.xstream.core.search.row.criteria.XStreamEntityPredicate;
import com.dunkware.xstream.core.search.row.value.XStreamEntityQueryValue;
import com.dunkware.xstream.model.query.XStreamCriteriaModel;
import com.dunkware.xstream.model.query.XStreamCriteriaType;
import com.dunkware.xstream.model.query.XStreamEntityQueryModel;
import com.dunkware.xstream.model.query.XStreamRowValueModel;
import com.dunkware.xstream.model.stats.EntityStatBulkReq;

import io.vertx.core.Future;
import io.vertx.core.Promise;

public class AsyncQueryBuilder {

	private XStream stream;
	
	private DunkNet dunkNet;
	
	public Future<XStreamEntityQuery> buildQuery(XStreamEntityQueryModel model) { 
			Promise<XStreamEntityQuery> queryPromise = Promise.promise();
			List<Future<XStreamEntityPredicate>> predicateFutures = new ArrayList<Future<XStreamEntityPredicate>>();
			for (XStreamCriteriaModel criteria: model.getCriterias()) {
				
			}
			
			return queryPromise.future();
	}
	
	private Future<XStreamEntityPredicate> buildPredicate(XStreamCriteriaModel model) { 
		List<Future<XStreamEntityQueryValue>> valueTasks = new ArrayList<Future<XStreamEntityQueryValue>>();
		Promise<XStreamEntityPredicate> promse = Promise.promise();
		if(model.getType() == XStreamCriteriaType.Value) { 
			valueTasks
		}
	}
	
	private Future<XStreamEntityQueryValue> buildEntityValue(XStreamRowValueModel model) {
		Promise<XStreamEntityQueryValue> = Promise.promise();
		
		
	}
	
	private Future<XStreamEntityQueryValue> buildVarAggHisValue(XStreamRowValueModel model) {
		Promise<XStreamEntityQueryValue> promise = Promise.promise();
			EntityStatBulkReq req = new EntityStatBulkReq();
			req.setAgg(model.getHistoricalAgg().name());
			req.setEntities(stream.getRowIdentifiers());
				
	}
	
	private Future<XStreamEntityQueryValue>
}
