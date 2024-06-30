package com.dunkware.xstream.api;

import com.dunkware.xstream.model.entity.query.type.XStreamEntityQueryType;

import io.vertx.core.Future;

public interface XStreamEntityQueryBuilder {
	
	public Future<XStreamEntityQuery> build(XStream stream, XStreamEntityQueryType model);

}
