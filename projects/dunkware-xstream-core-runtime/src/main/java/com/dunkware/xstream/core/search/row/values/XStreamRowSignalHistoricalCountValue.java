package com.dunkware.xstream.core.search.row.values;

import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.api.XStreamResolveException;
import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.core.search.row.XStreamRowValue;
import com.dunkware.xstream.model.query.XStreamRowValueModel;

public class XStreamRowSignalHistoricalCountValue implements XStreamRowValue {

	@Override
	public void init(XStreamRowValueModel model) throws XStreamQueryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canResolve(XStreamRow row) throws XStreamQueryException {
		// TODO Auto-generated method stub
		// XStreamStatsResolver
		return false;
	}

	@Override
	public Number resolve(XStreamRow row) throws XStreamResolveException, XStreamQueryException {
		// TODO Auto-generated method stub
		return null;
	}

}
