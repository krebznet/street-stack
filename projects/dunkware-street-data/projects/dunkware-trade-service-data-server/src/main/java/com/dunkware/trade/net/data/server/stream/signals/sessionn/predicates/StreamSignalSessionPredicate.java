package com.dunkware.trade.net.data.server.stream.signals.sessionn.predicates;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.dunkware.trade.service.data.model.signals.bean.StreamSignalBean;
import com.dunkware.trade.service.data.model.signals.query.StreamSignalSessionQuery;

public class StreamSignalSessionPredicate implements Predicate<StreamSignalBean> {

	private List<Predicate<StreamSignalBean>> predicates = new ArrayList<Predicate<StreamSignalBean>>();
	
	public StreamSignalSessionPredicate(StreamSignalSessionQuery query) throws Exception { 
		if(query.getEntities() != null && query.getEntities().size() > 0) {
			
		}
	}
	
	
	@Override
	public boolean test(StreamSignalBean t) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
