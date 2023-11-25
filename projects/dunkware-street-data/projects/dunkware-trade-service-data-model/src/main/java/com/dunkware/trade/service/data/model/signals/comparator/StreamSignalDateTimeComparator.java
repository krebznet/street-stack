package com.dunkware.trade.service.data.model.signals.comparator;

import java.util.Comparator;

import com.dunkware.trade.service.data.model.signals.bean.StreamSignalBean;

public class StreamSignalDateTimeComparator implements Comparator<StreamSignalBean> {

	private static StreamSignalDateTimeComparator instance = null;
	
	
	public static StreamSignalDateTimeComparator instance() { 
		if(instance == null) { 
			instance = new StreamSignalDateTimeComparator();
		}
		return instance;
	}
	
	@Override
	public int compare(StreamSignalBean o1, StreamSignalBean o2) {
		if(o1.getDateTime().equals(o2.getDateTime())) { 
			return 0;
		}
		if(o1.getDateTime().isAfter(o2.getDateTime())) { 
			return 1;
		}
		
		return -1;
	}
	
	

}
