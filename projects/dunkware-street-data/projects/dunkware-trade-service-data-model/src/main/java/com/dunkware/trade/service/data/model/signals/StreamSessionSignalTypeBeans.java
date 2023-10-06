package com.dunkware.trade.service.data.model.signals;

import java.util.ArrayList;
import java.util.List;

public class StreamSessionSignalTypeBeans {
	
	private String stream; 
	private List<StreamSessionSignalTypeBean> beans = new ArrayList<StreamSessionSignalTypeBean>();
	
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public List<StreamSessionSignalTypeBean> getBeans() {
		return beans;
	}
	public void setBeans(List<StreamSessionSignalTypeBean> beans) {
		this.beans = beans;
	}
	
	

}
