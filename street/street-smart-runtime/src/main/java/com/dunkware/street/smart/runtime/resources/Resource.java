package com.dunkware.street.smart.runtime.resources;

import com.dunkware.street.stream.model.resource.ResourceModel;

public interface Resource {
	
	
	public void create(ResourceModel model);
	
	public void load(ResourceModel model, Resource parent);
	
	

}
