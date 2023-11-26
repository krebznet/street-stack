package com.dunkware.trade.service.data.model.signals.comparator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.dunkware.trade.service.data.model.signals.bean.StreamSignalBean;

public class StreamSignalDateTimeComparator implements Comparator<StreamSignalBean> {

	private static StreamSignalDateTimeComparator instance = null;
	
	public static void main(String[] args) {
		List<StreamSignalBean> beans = new ArrayList<StreamSignalBean>();
		StreamSignalBean bean = new StreamSignalBean();
		bean.setDateTime(LocalDateTime.now().minusDays(30));
		beans.add(bean);
		bean = new StreamSignalBean();
		bean.setDateTime(LocalDateTime.now().minusDays(45));;
		beans.add(bean);
		Collections.sort(beans,StreamSignalDateTimeComparator.newInstance());
		System.out.println(beans.get(0).getDateTime());
		
	}
	
	public static StreamSignalDateTimeComparator newInstance() { 
		return new StreamSignalDateTimeComparator();
	}
	@Override
	public int compare(StreamSignalBean o1, StreamSignalBean o2) {
		if(o1.getDateTime().equals(o2.getDateTime())) { 
			return 0;
		}
		if(o1.getDateTime().isAfter(o2.getDateTime())) { 
			return -1;
		}
		
		return 1;
	}
	
	

}
