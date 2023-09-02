package com.dunkware.spring.cluster;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.helpers.DAnotHelper;
import com.dunkware.spring.cluster.DunkNetExtension.ComponentMethod;
import com.dunkware.spring.cluster.anot.ADunkNetComponent;
import com.dunkware.spring.cluster.protocol.descriptors.DunkNetChannelDescriptor;
import com.dunkware.spring.cluster.protocol.descriptors.DunkNetDescriptors;
import com.dunkware.spring.cluster.protocol.descriptors.DunkNetEventDescriptor;
import com.dunkware.spring.cluster.protocol.descriptors.DunkNetServiceDescriptor;

import ch.qos.logback.classic.Logger;

public class DunkNetExtensions {

	private List<DunkNetExtension> extensions = new ArrayList<DunkNetExtension>();
	
	

	public static DunkNetExtensions buildComponentExtensions(ApplicationContext ac) throws DunkNetException {

		DunkNetExtensions ext = new DunkNetExtensions();
		Set<Class<?>> classes = DAnotHelper.getClassesAnnotedWith(ADunkNetComponent.class);

		for (Class<?> clazz : classes) {
			try {
				Object source = clazz.newInstance();
				ac.getAutowireCapableBeanFactory().autowireBean(source);
				ext.addExtension(source);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ext;
	}

	public void addExtension(Object source) throws DunkNetException {
		DunkNetExtension ext = new DunkNetExtension();
		ext.initialize(source);
		extensions.add(ext);
	}

	
	public boolean hasServiceMethod(Object input) { 
		try {
			for (DunkNetExtension ext : extensions) {
				for (ComponentMethod method : ext.getServices()) {
					if (method.getParamType().isInstance(input)) {
						return true;
					}
				}
			}	
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	
	
	
	public ComponentMethod getSerivceMethod(Object input) throws DunkNetException {
		for (DunkNetExtension ext : extensions) {
			for (ComponentMethod method : ext.getServices()) {
				if (method.getParamType().isInstance(input)) {
					return method;
				}
			}
		}
		throw new DunkNetException("Service Component Method " + input.getClass() + " not found");
	}

	public ComponentMethod getChannelMethod(Object input) throws DunkNetException {
		for (DunkNetExtension ext : extensions) {
			for (ComponentMethod method : ext.getChannels()) {
				if (method.getParamType().isInstance(input)) {
					return method;
				}
			}
		}
		throw new DunkNetException("Channel Component Method " + input.getClass() + " not found");
	}

	public List<ComponentMethod> getEventMethods(Object input) throws DunkNetException {
		List<ComponentMethod> results = new ArrayList<ComponentMethod>();
		for (DunkNetExtension ext : extensions) {
			for (ComponentMethod method : ext.getEvents()) {
				if (method.getParamType().isInstance(input)) {
					results.add(method);
				}
			}
		}
		return results;

	}

	public DunkNetDescriptors buildDescriptors() {
		DunkNetDescriptors descriptors = new DunkNetDescriptors();
		for (DunkNetExtension ext : extensions) {
			for (ComponentMethod method : ext.getEvents()) {
				DunkNetEventDescriptor eventDesc = new DunkNetEventDescriptor();
				eventDesc.setClazz(ext.getClass().getName());
				eventDesc.setMethod(method.getMethod().getName());
				eventDesc.setInput(method.getParamType().getName());
				descriptors.getEvents().add(eventDesc);
			}
			for (ComponentMethod method : ext.getServices()) {
				DunkNetServiceDescriptor eventDesc = new DunkNetServiceDescriptor();
				eventDesc.setClazz(ext.getClass().getName());
				eventDesc.setMethod(method.getMethod().getName());
				eventDesc.setInput(method.getParamType().getName());
				eventDesc.setLabel(method.getLabel());
				descriptors.getServices().add(eventDesc);
				
			}
			for (ComponentMethod method : ext.getChannels()) {
				DunkNetChannelDescriptor eventDesc = new DunkNetChannelDescriptor();
				eventDesc.setClazz(ext.getClass().getName());
				eventDesc.setMethod(method.getMethod().getName());
				eventDesc.setLabel(method.getLabel());
				eventDesc.setInput(method.getParamType().getName());
				descriptors.getChannels().add(eventDesc);
			}
		}
		return descriptors;
	}
}
