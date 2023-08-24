package com.dunkware.spring.cluster;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.helpers.DAnotHelper;
import com.dunkware.spring.cluster.anot.ADunkNetChannel;
import com.dunkware.spring.cluster.anot.ADunkNetEvent;
import com.dunkware.spring.cluster.anot.ADunkNetService;
import com.dunkware.spring.cluster.core.response.DunkNetChannelResponse;

public class DunkNetExtension {
	
	
	private List<ComponentMethod> channels = new ArrayList<ComponentMethod>();
	private List<ComponentMethod> events = new ArrayList<ComponentMethod>();
	private List<ComponentMethod> services = new ArrayList<ComponentMethod>();
	private boolean initialized = false; 
	private Object source;

	public void initialize(Object source) throws DunkNetException {
		this.source = source;
		List<Method> methods = DAnotHelper.getMethodsAnnotatedWith(source.getClass(), ADunkNetEvent.class);
		for (Method method : methods) {
			ComponentMethod handler = new ComponentMethod();
			handler.setMethod(method);
			handler.setObject(source);;
			Class<?>[] args = method.getParameterTypes();
			if (args.length == 0) {
				throw new DunkNetException("Message Handler method " + method.getName() + " must have 1 input param");
			}
			handler.setParamType(args[0]);
			events.add(handler);
		}
		methods = DAnotHelper.getMethodsAnnotatedWith(source.getClass(), ADunkNetService.class);
		for (Method method : methods) {
			ComponentMethod handler = new ComponentMethod();
			handler.setMethod(method);
			handler.setObject(source);
			Class<?>[] args = method.getParameterTypes();
			if (args.length == 0) {
				throw new DunkNetException("Service Handler method " + method.getName() + " must return ServiceReply");
			}
			
			handler.setParamType(args[0]);
			handler.setReturnType(method.getReturnType());
			services.add(handler);
		}
		methods = DAnotHelper.getMethodsAnnotatedWith(source.getClass(), ADunkNetChannel.class);
		for (Method method : methods) {
			ComponentMethod handler = new ComponentMethod();
			handler.setObject(source);
			handler.setMethod(method);
			ADunkNetChannel anot = method.getAnnotationsByType(ADunkNetChannel.class)[0];
			
			Class<?>[] args = method.getParameterTypes();

			if (args.length == 0) {
				throw new DunkNetException("Channel Handler method " + method.getName() + " must return ChannelReply");
			}
			if (DunkNetChannelResponse.class.equals(method.getReturnType()) == false) {
				throw new DunkNetException(
						"Channel method must return ChannelReply but returns " + method.getReturnType().getName());
			}
			handler.setParamType(args[0]);
			handler.setReturnType(anot.getClass());
			channels.add(handler);
		}
		initialized = true;
	}
	
	public Object getSource() { 
		return source;
	}
	
	
	public List<ComponentMethod> getEventMethods(Object input) throws DunkNetException { 
		List<ComponentMethod> results = new ArrayList<ComponentMethod>();
		for (ComponentMethod componentMethod : events) {
			if(componentMethod.getParamType().isInstance(input)) { 
				results.add(componentMethod);
			}
		}
		return results;
	}
	
	public ComponentMethod getServiceMethod(Object input) throws DunkNetException { 
		for (ComponentMethod componentMethod : services) {
			if(componentMethod.getParamType().isInstance(input)) { 
				return componentMethod;
			}
		}
		throw new DunkNetException("Component service method not found for " + input.getClass().getName() + " on class " + getClass().getName());
	}
	
	public ComponentMethod getChannelMethod(Object input) throws DunkNetException { 
		for (ComponentMethod componentMethod : channels) {
			if(componentMethod.getParamType().isInstance(input)) { 
				return componentMethod;
			}
		}
		throw new DunkNetException("Component channel method not found for " + input.getClass().getName() + " on class " + getClass().getName());
	}

	public List<ComponentMethod> getChannels() {
		return channels;
	}

	public List<ComponentMethod> getEvents() {

		return events;
	}

	public List<ComponentMethod> getServices() {
		return services;
	}

	
	public static class ComponentMethod {

		private Object object;
		private Class paramType;
		private Method method;
		private Class returnType = null;

		public Class getParamType() {
			return paramType;
		}

		public void setParamType(Class paramType) {
			this.paramType = paramType;
		}

		public Class getReturnType() {
			return returnType;
		}

		public void setReturnType(Class returnType) {
			this.returnType = returnType;
		}

		public Method getMethod() {
			return method;
		}

		public void setMethod(Method method) {
			this.method = method;
		}

		public Object getObject() {
			return object;
		}

		public void setObject(Object object) {
			this.object = object;
		}
		
		

	}


}
