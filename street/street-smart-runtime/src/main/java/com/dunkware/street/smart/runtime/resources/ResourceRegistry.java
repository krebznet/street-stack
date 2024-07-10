package com.dunkware.street.smart.runtime.resources;

import java.util.Set;

import com.dunkware.street.stream.model.resource.ResourceModel;
import com.dunkware.utils.core.helpers.DunkAnot;


public class ResourceRegistry {

	private static ResourceRegistry instance = null;
	private Set<Class<?>> resources;
	

	public static ResourceRegistry get() { 
			if(instance == null) { 
				instance = new ResourceRegistry();
			}
			return instance; 
	}
	
	private ResourceRegistry() { 
		resources = DunkAnot.getClassesAnnotedWith(AResource.class);		
	}
	
	public Resource createResource(ResourceModel model) throws Exception {

		throw new Exception("Impement me");
	}
}
