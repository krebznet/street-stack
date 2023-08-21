package com.dunkware.spring.messaging.core.component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.helpers.DAnotHelper;
import com.dunkware.spring.messaging.DunkNetException;
import com.dunkware.spring.messaging.anot.ADunkNetChannel;
import com.dunkware.spring.messaging.anot.ADunkNetEvent;
import com.dunkware.spring.messaging.anot.ADunkNetService;
import com.dunkware.spring.messaging.core.response.DunkNetChannelResponse;
import com.dunkware.spring.messaging.core.response.DunkNetServiceResponse;

public class DunkNetComponent {

	private List<ComponentMethod> channels = new ArrayList<ComponentMethod>();
	private List<ComponentMethod> events = new ArrayList<ComponentMethod>();
	private List<ComponentMethod> services = new ArrayList<ComponentMethod>();

	private Object target;

	public DunkNetComponent(Object target) throws DunkNetException {
		List<Method> methods = DAnotHelper.getMethodsAnnotatedWith(target.getClass(), ADunkNetEvent.class);
		for (Method method : methods) {
			ComponentMethod handler = new ComponentMethod();
			handler.setMethod(method);
			Class<?>[] args = method.getParameterTypes();
			if (args.length == 0) {
				throw new DunkNetException("Message Handler method " + method.getName() + " must have 1 input param");
			}
			handler.setParamType(args[0]);
			events.add(handler);
		}
		methods = DAnotHelper.getMethodsAnnotatedWith(target.getClass(), ADunkNetService.class);
		for (Method method : methods) {
			ComponentMethod handler = new ComponentMethod();
			handler.setMethod(method);
			Class<?>[] args = method.getParameterTypes();
			if (args.length == 0) {
				throw new DunkNetException("Service Handler method " + method.getName() + " must return ServiceReply");
			}
			if (DunkNetServiceResponse.class.equals(method.getReturnType()) == false) {
				throw new DunkNetException(
						"Service method must return ServiceResponse but returns " + method.getReturnType().getName());
			}
			handler.setParamType(args[0]);
			handler.setReturnType(method.getReturnType());
			services.add(handler);
		}
		methods = DAnotHelper.getMethodsAnnotatedWith(target.getClass(), ADunkNetChannel.class);
		for (Method method : methods) {
			ComponentMethod handler = new ComponentMethod();
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

	public Object getTarget() {
		return target;
	}

	public static class ComponentMethod {

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

	}

}
