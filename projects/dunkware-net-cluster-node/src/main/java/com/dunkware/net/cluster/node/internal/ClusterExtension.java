package com.dunkware.net.cluster.node.internal;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.helpers.DAnotHelper;
import com.dunkware.net.cluster.node.ClusterNodeException;
import com.dunkware.net.cluster.node.anot.AClusterMessage;
import com.dunkware.net.cluster.node.anot.AClusterService;
import com.dunkware.spring.channel.Channel;
import com.dunkware.spring.channel.ChannelException;
import com.dunkware.spring.channel.core.ChannelHandler;

public class ClusterExtension {
	
	public static ChannelHandler newInstance(Object target, Channel channel) throws ChannelException {
		return new ChannelHandler(target, channel);
	}

	private List<MessageHandler> messageHandlers = new ArrayList<MessageHandler>();
	private List<MessageReply> messageReplies = new ArrayList<MessageReply>();

	private Object target;


	public ClusterExtension(Object target) throws ClusterNodeException {
		this.target = target;
		
		List<Method> messageHandlerMethods = DAnotHelper.getMethodsAnnotatedWith(target.getClass(), AClusterMessage.class);
		for (Method method : messageHandlerMethods) {
			MessageHandler messageHandler = new MessageHandler(); 
			messageHandler.setMethod(method);
			Class<?>[] args =  method.getParameterTypes();
			if(args.length == 0) { 
				// well then annotated method with no arguments 
				throw new ClusterNodeException("Method marked as message handler " + method.getName() + " needs a message param arg");
			}
			messageHandler.setMessageType(args[0]);
			messageHandlers.add(messageHandler);
		}
		
		List<Method> replyMethods = DAnotHelper.getMethodsAnnotatedWith(target.getClass(), AClusterService.class);
		for (Method method : replyMethods) {
			MessageReply reply = new MessageReply(); 
			reply.setMethod(method);
			Class<?>[] args =  method.getParameterTypes();
			
			if(args.length == 0) { 
				// well then annotated method with no arguments 
				throw new ClusterNodeException("Method marked as service " + method.getName() + " needs a message param arg");
			}
			Class<?> returnType = method.getReturnType();
			if(returnType != null) { 
				reply.setResponseType(returnType);
			}
			reply.setMessageType(args[0]);
			messageReplies.add(reply);
		}
		
		
	}
	
	
	public Object getTarget() { 
		return target;
	}
	
	
	public List<MessageReply> getMessageReplies() { 
		return messageReplies;
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
		private Class<?> responseType; 

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

		public Class<?> getResponseType() {
			return responseType;
		}

		public void setResponseType(Class<?> responseType) {
			this.responseType = responseType;
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
	

}
