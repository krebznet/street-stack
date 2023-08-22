package com.dunkware.xstream.api;

import java.util.List;

import com.dunkware.xstream.model.query.XStreamEntityQueryModel;

public interface XStreamEntityQuery {
	
	public void init(XStreamEntityQueryModel model, XStream stream) throws XStreamQueryException;
	
	public XStreamRowQueryResults query(List<XStreamEntity> rows);
	
}

