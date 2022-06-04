package com.dunkware.net.core.anot;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class NetAnnotationRegistry {
	
	private static NetAnnotationRegistry instance = null;
	
	private List<NetCallServiceDescriptor> serviceCalls = new ArrayList<NetCallServiceDescriptor>();
	private List<NetStreamServiceDescriptor> serviceStreams = new ArrayList<NetStreamServiceDescriptor>();
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public static NetAnnotationRegistry get() { 
		if(instance == null) { 
			instance  = new NetAnnotationRegistry();
		}
		return instance; 
	}
	
	public NetAnnotationRegistry() { 
		try {
			Reflections reflections = new Reflections("com.dunkware");
			Set<Class<?>> expClasses = reflections.getTypesAnnotatedWith(ANetCallService.class);
			for (Class<?> expclass : expClasses) {
				ANetCallService[] expAnots = expclass.getAnnotationsByType(ANetCallService.class);
				NetCallServiceDescriptor desc = new NetCallServiceDescriptor();
						desc.setClazz(expclass);
					    desc.setEndpoint(expAnots[0].endpoint());
					    serviceCalls.add(desc);
			}
			
			 expClasses = reflections.getTypesAnnotatedWith(ANetChannelService.class);
			for (Class<?> expclass : expClasses) {
				ANetChannelService[] expAnots = expclass.getAnnotationsByType(ANetChannelService.class);
				NetStreamServiceDescriptor desc = new NetStreamServiceDescriptor();
						desc.setClazz(expclass);
					    desc.setEndpoint(expAnots[0].endpoint());
					    serviceStreams.add(desc);
			}
		} catch (Exception e) {
			logger.error("Exception building net service call registry " + e.toString());
		}
		
	}
	
	
	public List<NetStreamServiceDescriptor> getNetStreamServiceDescriptors() { 
		return serviceStreams;
	}
	
	public List<NetCallServiceDescriptor> getNetCallServiceDescriptors() { 
		return serviceCalls;
	}

}
