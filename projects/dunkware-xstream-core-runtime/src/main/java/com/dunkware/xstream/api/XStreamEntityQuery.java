package com.dunkware.xstream.api;

import java.util.List;

import com.dunkware.xstream.model.query.XStreamQueryModel;

public interface XStreamEntityQuery {
	
	public void init(XStreamQueryModel model, XStream stream) throws XStreamQueryException;
	
	public XStreamRowQueryResults query(List<XStreamEntity> rows);
	
}

