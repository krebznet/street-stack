package com.dunkware.spring.channel.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.helpers.DAnotHelper;
import com.dunkware.spring.channel.Channel;
import com.dunkware.spring.channel.ChannelException;
import com.dunkware.spring.channel.anot.AChannelSetter;
import com.dunkware.spring.channel.anot.AMessageHandler;
import com.dunkware.spring.channel.anot.AMessageReply;

public class ChannelHandler {

	public static ChannelHandler newInstance(Object target, Channel channel) throws ChannelException {
		return new ChannelHandler(target, channel);
	}

	private List<MessageHandler> messageHandlers = new ArrayList<MessageHandler>();
	private List<MessageReply> messageReplies = new ArrayList<MessageReply>();
	private List<ChannelSetter> channelSetters = new ArrayList<ChannelSetter>();
	

	private Object target;
	private Channel channel;

	public ChannelHandler(Object target, Channel channel) throws ChannelException {
		this.channel = channel;
		this.target = target;
		
		List<Method> messageHandlerMethods = DAnotHelper.getMethodsAnnotatedWith(target.getClass(), AMessageHandler.class);
		for (Method method : messageHandlerMethods) {
			MessageHandler messageHandler = new MessageHandler(); 
			messageHandler.setMethod(method);
			Class<?>[] args =  method.getParameterTypes();
			if(args.length == 0) { 
				// well then annotated method with no arguments 
				throw new ChannelException("Method marked as message handler " + method.getName() + " needs a message param arg");
			}
			messageHandler.setMessageType(args[0]);
			messageHandlers.add(messageHandler);
		}
		
		List<Method> replyMethods = DAnotHelper.getMethodsAnnotatedWith(target.getClass(), AMessageReply.class);
		for (Method method : replyMethods) {
			MessageReply reply = new MessageReply(); 
			reply.setMethod(method);
			Class<?>[] args =  method.getParameterTypes();
			if(args.length == 0) { 
				// well then annotated method with no arguments 
				throw new ChannelException("Method marked as message reply " + method.getName() + " needs a message param arg");
			}
			reply.setMessageType(args[0]);
			messageReplies.add(reply);
		}
		
		List<Method> setterMethods = DAnotHelper.getMethodsAnnotatedWith(target.getClass(), AChannelSetter.class);
		for (Method method : setterMethods) {
			ChannelSetter setter = new ChannelSetter();
			setter.setMethod(method);
			Class<?>[] args =  method.getParameterTypes();
			if(args.length == 0) { 
				// well then annotated method with no arguments 
				throw new ChannelException("Method marked as channel setter " + method.getName() + " needs a message param arg");
			}
			setter.setSetterType(args[0]);
			channelSetters.add(setter);
		}
	}
	
	
	public Object getTarget() { 
		return target;
	}
	
	public List<ChannelSetter> getChannelSetters() { 
		return channelSetters;
	}
	
	public boolean hasMessageReply(Object payload) { 
		for (MessageReply messageReply : messageReplies) {
			if(messageReply.getMessageType().isInstance(payload)) { 
				return true; 
			}
		}
		return false; 
	}
	
	public MessageReply getMessageReply(Object payload) { 
		for (MessageReply messageReply : messageReplies) {
			if(messageReply.getMessageType().isInstance(payload)) { 
				return messageReply; 
			}
		}
		return null;

	}
	
	public List<MessageReply> getMessageReplies(Object messageType) { 
		List<MessageReply> results = new ArrayList<>();
		for (MessageReply messageReply : messageReplies) {
			if(messageReply.getMessageType().isInstance(messageType)) { 
				results.add(messageReply);
			}
		}
		return results;
	}
	
	
	public List<MessageHandler> getMessageHandlers(Object messageType) { 
		List<MessageHandler> results = new ArrayList<MessageHandler>();
		for (MessageHandler messageHandler : messageHandlers) {
			if(messageHandler.getMessageType().isInstance(messageType)) { 
				results.add(messageHandler);
			}
		}
		return results; 
	}
	

	public static class MessageReply {

		private Method method;
		private Class<?> messageType;

		public Method getMethod() {
			return method;
		}

		public void setMethod(Method method) {
			this.method = method;
		}

		public Class getMessageType() {
			return messageType;
		}

		public void setMessageType(Class messageType) {
			this.messageType = messageType;
		}

	}

	public static class MessageHandler {

		private Method method;
		private Class<?> messageType;

		public Method getMethod() {
			return method;
		}

		public void setMethod(Method method) {
			this.method = method;
		}

		public Class getMessageType() {
			return messageType;
		}

		public void setMessageType(Class messageType) {
			this.messageType = messageType;
		}

	}
	
	
	public static class ChannelSetter { 
		
		private Method method; 
		private Class<?> setterType;
		public Method getMethod() {
			return method;
		}
		
		public void setMethod(Method method) {
			this.method = method;
		}
		public Class<?> getSetterType() {
			return setterType;
		}
		
		public void setSetterType(Class<?> setterType) {
			this.setterType = setterType;
		} 
		
		
		
	}
}
