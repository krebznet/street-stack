package com.dunkware.trade.net.data.server.stream.signals.sessionn.predicates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.trade.service.data.model.signals.bean.StreamSignalBean;

public class EntitySignalIdPredicate implements Predicate<StreamSignalBean> {

	private List<Integer> signalIds;
	
	public EntitySignalIdPredicate(List<Integer> signalIds) { 
		this.signalIds = signalIds;
	}
	
	@Override
	public boolean test(StreamSignalBean t) {
		if(signalIds.size() == 0) { 
			return true; 
		}
		if(signalIds.contains(t.getSignalId())) { 
			return true;
		}
		return false; 
	}
	
	
	public static void main(String[] args) {
		int i = 0;
		List<StreamSignalBean> beans = new ArrayList<StreamSignalBean>(59999999);
		DStopWatch s = DStopWatch.create();
		s.start();
		while(i < 3000000)  {
			StreamSignalBean bean = new StreamSignalBean();
			bean.setEntityId(DRandom.getRandom(3, 34323));
			beans.add(bean);
			i++;
		}
		s.stop();
		System.out.println(s.getCompletedSeconds());;
		List<StreamSignalBean> results  = new ArrayList<StreamSignalBean>();
		s.start();
		EntityIdPredicate p = new EntityIdPredicate(Arrays.asList(3,3,434));
		Collection<StreamSignalBean> poo = beans.stream().filter(p.and(p))
		.collect(Collectors.toSet()); 
	s.stop();
	System.out.println(s.getCompletedSeconds());
	for (StreamSignalBean streamSignalBean : poo) {
		//System.out.println(streamSignalBean.getEntityId());
	}
	}
	
	

}
