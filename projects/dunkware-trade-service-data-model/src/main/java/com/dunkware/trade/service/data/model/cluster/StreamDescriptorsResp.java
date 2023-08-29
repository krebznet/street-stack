package com.dunkware.trade.service.data.model.cluster;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.trade.service.stream.descriptor.StreamDescriptor;

public class StreamDescriptorsResp {

	private List<StreamDescriptor> descriptors = new ArrayList<StreamDescriptor>();

	public List<StreamDescriptor> getDescriptors() {
		return descriptors;
	}

	public void setDescriptors(List<StreamDescriptor> descriptors) {
		this.descriptors = descriptors;
	}

	
	
}
