package com.dunkware.spring.cluster.core.controllers;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.DunkNetChannel;
import com.dunkware.spring.cluster.DunkNetChannelHandler;
import com.dunkware.spring.cluster.DunkNetChannelState;
import com.dunkware.spring.cluster.DunkNetException;
import com.dunkware.spring.cluster.DunkNetExtension.ComponentMethod;
import com.dunkware.spring.cluster.DunkNetNode;
import com.dunkware.spring.cluster.core.DunkNetChannelImpl;
import com.dunkware.spring.cluster.core.request.DunkNetChannelRequest;
import com.dunkware.spring.cluster.core.request.DunkNetServiceRequest;
import com.dunkware.spring.cluster.message.DunkNetMessage;
import com.dunkware.spring.cluster.protocol.descriptors.DunkNetChannelDescriptor;
import com.dunkware.spring.cluster.protocol.descriptors.DunkNetDescriptors;

public class DunkNetController {

	private DunkNet net;

	@Autowired
	private ApplicationContext ac;

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("DunkNet");

	private ConcurrentHashMap<String, DunkNetServiceRequest> pendingServiceRequests = new ConcurrentHashMap<String, DunkNetServiceRequest>();
	private ConcurrentHashMap<String, DunkNetChannelRequest> pendingChannelRequests = new ConcurrentHashMap<String, DunkNetChannelRequest>();
	private ConcurrentHashMap<String, DunkNetChannel> activeChannels = new ConcurrentHashMap<String, DunkNetChannel>();

	public void init(DunkNet net) {
		this.net = net;
	}

	public DunkNetChannelRequest netChannelRequest(Object input) throws DunkNetException {
		DunkNetNode channelNode = null;
		for (DunkNetNode node : net.getNodes()) {
			if (node.getDescriptor().getDescriptors().hasChannel(input)) {
				channelNode = node;
				break;
			}
		}
		if (channelNode == null) {
			throw new DunkNetException("No channel handler found on any nodes for input " + input.getClass().getName());
		}

		return nodeChannelRequest(channelNode, input);

	}
	
	public void removeChannel(String channelId) { 
		this.activeChannels.remove(channelId);
	}
	
	
	

	public DunkNetChannelRequest subChannelRequest(DunkNetChannel channel, Object input) throws DunkNetException {
		String label = "n/a" ;
		try {
			label = channel.getDescriptors().getChannel(input).getLabel();
			
		} catch (Exception e) {
			throw e;
		}
		DunkNetMessage m = DunkNetMessage.builder().channelRequest(channel.getChannelId(), input).buildMessage();
		DunkNetChannelRequest req = new DunkNetChannelRequest(m.getMessageId(), channel.getNode(),label);
		pendingChannelRequests.put(m.getMessageId(), req);
		channel.getNode().message(m);
		return req;

	}

	public DunkNetChannelRequest nodeChannelRequest(DunkNetNode node, Object input) throws DunkNetException {
		DunkNetChannelDescriptor descript = node.getDescriptor().getDescriptors().getChannel(input);
		if (input.getClass().getName().equals(descript.getInput()) == false) {
			throw new DunkNetException("Channel input " + input.getClass().getName() + " is not matching descriptor "
					+ descript.getInput());
		}

		DunkNetMessage m = DunkNetMessage.builder().channelRequest(input).buildMessage();
		DunkNetChannelRequest req = new DunkNetChannelRequest(m.getMessageId(), node,descript.getLabel());
		pendingChannelRequests.put(m.getMessageId(), req);
		node.message(m);
		return req;
	}
	
	public DunkNetServiceRequest nodeServiceRequest(DunkNetNode node, Object payload) throws DunkNetException { 
		if(!node.getDescriptor().getDescriptors().hasService(payload)) { 
			throw new DunkNetException("NOde " + node.getId() + " does not have service request handler for " + payload.getClass().getName());
		}
		DunkNetMessage message = DunkNetMessage.builder().serviceRequest(payload).buildMessage();
		DunkNetServiceRequest req = new DunkNetServiceRequest(node, payload);
		pendingServiceRequests.put(message.getMessageId(), req);
		if(logger.isDebugEnabled()) { 
			logger.debug(marker, "Sending Noded Service Request From {} to {} with payload {}",
					node.getNet().getId(),node.getId(),payload.getClass().getName());
		}
		node.message(message);
		return req;
	}

	public DunkNetServiceRequest channelServiceRequest(DunkNetChannel channel, Object payload) throws DunkNetException {
		if (!channel.getRemoteDescriptors().hasService(payload)) {
			throw new DunkNetException("Channel service with input " + payload.getClass().getName() + " not found");
		}
		DunkNetMessage message = DunkNetMessage.builder().channelServiceRequest(channel.getChannelId(), payload)
				.buildMessage();
		DunkNetServiceRequest request = new DunkNetServiceRequest(channel.getNode(), payload);
		pendingServiceRequests.put(message.getMessageId(), request);
		if (logger.isDebugEnabled()) {
			logger.debug(marker, "Sending Channel {} Service Request from {} to {} with payload {}",
					channel.getChannelId(), net.getId(), channel.getNode().getId(), payload.getClass().getName());
		}
		channel.getNode().message(message);
		return request;

	}

	public boolean handleInboundMessage(DunkNetMessage message) throws DunkNetException {

		try {
			switch (message.getType()) {
			case DunkNetMessage.TYPE_CHANNEL_CLIENT_INIT:
				
				handleChannelClientInit(message);
				return true;
			case DunkNetMessage.TYPE_CHANNEL_CLOSE:
				handleChannelClose(message);
				return true;
			case DunkNetMessage.TYPE_CHANNEL_REQUEST:
				handleChannelRequest(message);
				return true;
			case DunkNetMessage.TYPE_CHANNEL_RESPONSE:
				
				handleChannelResponse(message);
				return true;
			case DunkNetMessage.TYPE_CHANNEL_CLIENT_INIT_ERROR:
				handleChannelClientInitError(message);
				return true;
			case DunkNetMessage.TYPE_CHANNEL_CLIENT_STARRT_ERROR:
				handleChannelClientStartError(message);
				return true;
			case DunkNetMessage.TYPE_CHANNEL_SERVER_START:
				handleChannelServerStart(message);
				return true;
			case DunkNetMessage.TYPE_CHANNEL_SERVER_START_ERROR:
				handleChannelServerStartError(message);
				return true;
			case DunkNetMessage.TYPE_EVENT:
				handleEvent(message);
				return true;
			case DunkNetMessage.TYPE_SERVICE_REQUEST:
				handleServiceRequest(message);
				return true;
			case DunkNetMessage.TYPE_SERVICE_RESPONSE:
				handleServiceResponse(message);
				return true;
			}
			
			logger.error(marker, "Message not handled type {} on node {} payload {}", message.getType(), net.getId(),DJson.serializePretty(message.getPayload()));
			return false;
		} catch (Exception e) {
			logger.error(marker, "Thrown Exception in message handler switch {}",e.toString(),e);
			return false;

		}
		
	}

	public DunkNetChannel getChannel(String channelId) throws DunkNetException {
		return activeChannels.get(channelId);
	}

	private void handleChannelRequest(DunkNetMessage message) throws DunkNetException {
		if (message.isChannelMessage()) {
			handleSubChannelRequest(message);
			return;
		}
		DunkNetNode node = getSenderNode(message);

		ComponentMethod method = null;
		try {
			method = net.extensions().getChannelMethod(message.getPayload());
		} catch (Exception e2) {
			node.message(DunkNetMessage.builder().channelException(null, message.getMessageId(),
					"Exception getting channel compoennt method " + e2.toString()).buildMessage());
			return;
		}
		Object response = null;
		try {
			response = invokeComponentMethod(method, message.getPayload());
		} catch (Exception e2) {
			node.message(DunkNetMessage.builder().channelException(null, message.getMessageId(),
					"Exception invoking channel method " + e2.toString()).buildMessage());
			return;
		}
		DunkNetChannelHandler channelHandler = null;
		try {
			channelHandler = (DunkNetChannelHandler) response;
		} catch (Exception e) {
			node.message(DunkNetMessage.builder().channelException(null, message.getMessageId(),
					"Exception invoking method response type not channel handler ").buildMessage());
			return;
		}
		try {
			String newChannelId = DUUID.randomUUID(5);
			DunkNetChannelImpl channel = new DunkNetChannelImpl();
			ac.getAutowireCapableBeanFactory().autowireBean(channel);
			channel.init(newChannelId, method.getLabel(), node, false);
			activeChannels.put(channel.getChannelId(), channel);
			try {
				channel.setHandler(channelHandler);
			} catch (Exception e) {
				node.message(DunkNetMessage.builder().channelException(null, message.getMessageId(),
						"Exception init channel handler   " + e.toString()).buildMessage());
				channel.getHandler().channelStartError(e.toString());
				channel.getHandler().channelClose();
				return;

			}
			channel.setState(DunkNetChannelState.INITIALIZED);
			
			node.message(DunkNetMessage.builder()
					.channelResponse(newChannelId, message.getMessageId(), channel.getDescriptors()).buildMessage());
		} catch (Exception e) {
			node.message(DunkNetMessage.builder().channelException(null, message.getMessageId(),
					"Exception invoking channel handler init  " + e.toString()).buildMessage());
			return;
		}

	}

	private void handleSubChannelRequest(DunkNetMessage message) throws DunkNetException {
		DunkNetNode node = getSenderNode(message);
		DunkNetChannel parentChannel = null;

		try {
			parentChannel = getChannel(message.getChannel());
		} catch (Exception e) {
			DunkNetMessage.builder().channelException(message.getChannel(), null, message.getMessageId(),
					"Cannot find parente channel");
		}
		ComponentMethod method = null;
		try {
			method = parentChannel.getExtensions().getChannelMethod(message.getPayload());
		} catch (Exception e2) {
			DunkNetMessage.builder().channelException(message.getChannel(), null, message.getMessageId(),
					"Cannot find component method on parent channel on request for "
							+ message.getPayload().getClass().getName());
			return;
		}
		Object response = null;
		try {
			response = invokeComponentMethod(method, message.getPayload());
		} catch (Exception e2) {
			node.message(DunkNetMessage.builder().channelException(parentChannel.getChannelId(), null,
					message.getMessageId(), "Exception invoking channel method " + e2.toString()).buildMessage());
			return;
		}
		DunkNetChannelHandler channelHandler = null;
		try {
			channelHandler = (DunkNetChannelHandler) response;
		} catch (Exception e) {
			node.message(DunkNetMessage.builder().channelException(parentChannel.getChannelId(), null,
					message.getMessageId(), "Exception invoking method response type not channel handler ")
					.buildMessage());
			return;
		}
		try {
			String newChannelId = DUUID.randomUUID(5);
			DunkNetChannelImpl channel = new DunkNetChannelImpl();
			ac.getAutowireCapableBeanFactory().autowireBean(channel);
			channel.init(newChannelId, method.getLabel(), node, false);
			channel.setHandler(channelHandler);
			channel.setState(DunkNetChannelState.INITIALIZED);

			node.message(DunkNetMessage.builder().channelResponseWithParent(parentChannel.getChannelId(), newChannelId,
					message.getMessageId(), channel.getDescriptors()).buildMessage());
		} catch (Exception e) {
			node.message(DunkNetMessage.builder().channelException(parentChannel.getChannelId(), null,
					message.getMessageId(), "Exception invoking channel handler init  " + e.toString()).buildMessage());
			return;
		}

	}

	// channel id a bit fucked up
	private void handleChannelResponse(DunkNetMessage message) throws DunkNetException {
		DunkNetChannelRequest req = pendingChannelRequests.get(message.getRequestId());
		DunkNetNode reqNode = getSenderNode(message);
		if (req == null) {
			logger.error("Exception getting pending channel request from response ");
			throw new DunkNetException("channel response not mattching pending channel request");
		}
		int respType = (Integer) message.getHeader(DunkNetMessage.KEY_RESPONSE_CODE);
		if (respType == DunkNetMessage.RESPONSE_ERROR) {
			req.setError(message.getHeader(DunkNetMessage.KEY_RESPONSE_ERROR).toString());
			return;
		
		}
		if (respType != DunkNetMessage.RESPONSE_SUCCESS) {
			logger.error(marker, "Fucked up channel response not error or success is " + respType);
			throw new DunkNetException("channel response coode type invalid " + respType);
		}
		String channelId = message.getChannel();
		DunkNetChannelImpl channel = new DunkNetChannelImpl();

		ac.getAutowireCapableBeanFactory().autowireBean(channel);
		channel.setRemoteDescriptors((DunkNetDescriptors) message.getPayload());
		channel.init(channelId,req.getChannelLabel(),reqNode,true);
		req.setChannel(channel);
		pendingChannelRequests.remove(message.getRequestId());
		activeChannels.put(channel.getChannelId(), channel);
		if (message.hasParentChannel()) {
			try {
				DunkNetChannel parentChannel = getChannel(message.getParentChannel());
				parentChannel.subChannels().add(channel);

			} catch (Exception e) {
				logger.error(marker, "Exception getting active channel when channel response has parent channel on it");
			}
		}
	}

	private void handleChannelClientInit(DunkNetMessage message) throws DunkNetException {
		DunkNetChannel channel = getChannel(message.getChannel());
		DunkNetNode node = getSenderNode(message);
		try {
			channel.setDescriptors((DunkNetDescriptors) message.getPayload());
			channel.setState(DunkNetChannelState.OPEN);
			channel.getHandler().channelStart();
			node.message(DunkNetMessage.builder().channelServerStart(channel.getChannelId()).buildMessage());
		} catch (Exception e) {
			channel.getHandler().channelStartError(e.toString());
			channel.closeChannel();
			activeChannels.remove(channel.getChannelId());
			try {
				node.message(DunkNetMessage.builder().channelServerStartError(channel.getChannelId(), e.toString())
						.buildMessage());
			} catch (Exception e2) {
				logger.error(marker, "error sending channel server start exception " + e.toString());
			}

		}
	}

	private void handleChannelClientInitError(DunkNetMessage message) throws DunkNetException {
		DunkNetChannel channel = getChannel(message.getChannel());

		DunkNetNode node = getSenderNode(message);
		try {
			channel.getHandler().channelStartError(message.getHeader(DunkNetMessage.KEY_ERROR).toString());
			//channel.getHandler().channelClose();
			activeChannels.remove(channel.getChannelId());
			node.message(DunkNetMessage.builder().channelServerStart(channel.getChannelId()).buildMessage());
		} catch (Exception e) {
			logger.error(marker, "error handling client channel init error " + e.toString());
		}

	}

	private void handleChannelClientStartError(DunkNetMessage message) throws DunkNetException {
		DunkNetNode node = getSenderNode(message);
		DunkNetChannel channel = getChannel(message.getChannel());
		if(channel == null) { 
			logger.error(marker, "client start error server channel should be in here?");
			return;
		}
		String error = message.getHeader(DunkNetMessage.KEY_ERROR).toString();
		if(logger.isDebugEnabled()) { 
			logger.debug(marker, "Channel start error notifying error and closing server channel");
		}
		channel.getHandler().channelStartError(error);
		//channel.closeChannel();
		activeChannels.remove(channel.getChannelId());

	}

	private void handleChannelServerStart(DunkNetMessage message) throws DunkNetException {
		DunkNetNode node = getSenderNode(message);
		DunkNetChannel channel = getChannel(message.getChannel());
		channel.setState(DunkNetChannelState.OPEN);
		try {
			channel.getHandler().channelStart();
		} catch (Exception e) {
			channel.getHandler().channelStartError(e.toString());
		//	channel.closeChannel();
			activeChannels.remove(channel.getChannelId());
			try {
				node.message(DunkNetMessage.builder().channelClientStartError(channel.getChannelId(), e.toString())
						.buildMessage());
			} catch (Exception e2) {
				logger.error(marker, "Can't send channel client start error message " + e.toString());
			}
		} 

	}

	private void handleChannelServerStartError(DunkNetMessage message) throws DunkNetException {
		DunkNetNode node = getSenderNode(message);
		DunkNetChannel channel = getChannel(message.getChannel());
		if(channel == null) {
			logger.error(marker, "Server Start Error handling channel should be here ");
			return;
		}
		String error = message.getHeader(DunkNetMessage.KEY_ERROR).toString();
		channel.getHandler().channelStartError(error);
		channel.closeChannel();
		activeChannels.remove(channel.getChannelId());

	}

	private void handleChannelClose(DunkNetMessage message) throws DunkNetException {
		DunkNetNode senderNode = getSenderNode(message);
		DunkNetChannel channel = null;
		try {
			channel = getChannel(message.getChannel());
			if(channel == null) { 
				// not going to worry about it
				// this message gets triggered 
				// when a node is closed on this node and already closed
				return;
			}
		} catch (Exception e) {
			logger.error(marker, "Exception getting channel {} on node {} for close event ",
					message.getChannel(),net.getId());
			return;
		}
		channel.closeChannel();
		if(logger.isDebugEnabled()) { 
			logger.debug(marker, "Handled close channel message on node {} for channel id {} from node {}",
					net.getId(),message.getChannel(),senderNode.getId());
			activeChannels.remove(channel.getChannelId());
		}
	}

	private void handleServiceRequest(DunkNetMessage message) throws DunkNetException {
		if (message.getChannel() != null) {
			handleChannelServiceRequest(message);
			return;
		}
		DunkNetNode node = getSenderNode(message);
		if(logger.isDebugEnabled()) { 
			logger.debug(marker, "Handling on-channel service request {} on node {} from node {} with input class type {}",
					message.getRequestId(),net.getId(),node.getId(),message.getPayload().getClass().getName());
		}
		ComponentMethod method = null;
		try {
			method = net.extensions().getSerivceMethod(message.getPayload());
		} catch (Exception e) {
			DunkNetMessage m = DunkNetMessage.builder()
					.serviceResponseError(message.getMessageId(), "Service method not found " + e.toString())
					.buildMessage();
			node.message(m);
			return;
		}
		try {
			Object result = method.getMethod().invoke(method.getObject(), message.getPayload());
			node.message(DunkNetMessage.builder().serviceResponse(result, message.getMessageId()).buildMessage());
		} catch (Exception e) {
			try {
				DunkNetMessage m = DunkNetMessage.builder()
						.serviceResponseError(message.getMessageId(), "Service invocation exception " + e.toString())
						.buildMessage();
				node.message(m);
			} catch (Exception e2) {
				throw new DunkNetException("Exception sending service response error to node " + e.toString());
			}
		}

	}

	private void handleChannelServiceRequest(DunkNetMessage message) {
		ComponentMethod method = null;
		DunkNetChannel channel = null;

		try {

			channel = getChannel(message.getChannel());
		} catch (Exception e) {
			logger.error(marker, "Internal exception channel service request but not found");
			return;
		}
		if (logger.isDebugEnabled()) {
			logger.debug(marker, "Handling channel {} service request on {} from {} with payload {}",
					channel.getChannelId(), net.getId(), channel.getNode().getId(),
					message.getPayload().getClass().getName());
		}
		try {
			method = channel.getExtensions().getSerivceMethod(message.getPayload());
		} catch (Exception e) {
			DunkNetMessage m = DunkNetMessage.builder()
					.channelServiceResponseError(channel.getChannelId(), message.getMessageId(), e.toString())
					.buildMessage();
			try {
				channel.getNode().message(m);
			} catch (Exception e2) {
				logger.error(marker, "Exceptions sending channel service request error " + e.toString());
				return;
			}
		}
		DunkNetMessage responseMessage = null;
		try {
			Object results = method.getMethod().invoke(method.getObject(), message.getPayload());
			responseMessage = DunkNetMessage.builder()
					.channelServiceResponse(channel.getChannelId(), message.getMessageId(), results).buildMessage();
			if (logger.isDebugEnabled()) {
				logger.debug(marker,
						"Invoked channel {} service request on class {} method {} witn input {} and returned {} on node {}",
						channel.getChannelId(), method.getObject().getClass().getName(), method.getMethod().getName(),
						message.getPayload().getClass().getName(), results.getClass().getName(), net.getId());
			}

		} catch (Exception e) {
			responseMessage = DunkNetMessage.builder()
					.channelServiceResponseError(channel.getChannelId(), message.getMessageId(), e.toString())
					.buildMessage();
		}
		try {
			if (logger.isDebugEnabled()) {
				logger.debug(marker,
						"Sending Channel service response on channel {} requestId {} to node {} with return tyep {}",
						channel.getChannelId(), message.getMessageId(), channel.getNode().getId(), "n/a");
			}
			channel.getNode().message(responseMessage);
		} catch (Exception e) {
			logger.error(marker, "internal exception sending message channel service response " + e.toString());
		}

	}

	private void handleServiceResponse(DunkNetMessage message) throws DunkNetException {
		if (message.getChannel() != null) {
			handleChannelServiceResponse(message);
			return;
		}

		DunkNetNode senderNode = getSenderNode(message);
		if (logger.isDebugEnabled()) {
			logger.debug(marker,
					"Handling non-channel service response on node {} from node {} requestId {} service return type {}",
					net.getId(), senderNode.getId(), message.getRequestId(), message.getPayload().getClass().getName());

		}
		DunkNetServiceRequest request = null;
		try {
			request = pendingServiceRequests.get(message.getRequestId());
		} catch (Exception e) {
			logger.error(marker,
					"Handling non-channel service response did not find pending request for request id {} on node {}",
					message.getRequestId(), net.getId());
			return;
		}
		if (request == null) {

			logger.error(marker,
					"Handling non-channel service response did not find pending request for request id {} on node {}",
					message.getRequestId(), net.getId());
			return;
		}
		int respType = (Integer) message.getHeader(DunkNetMessage.KEY_RESPONSE_CODE);
		if (respType == DunkNetMessage.RESPONSE_ERROR) {
			if (logger.isDebugEnabled()) {
				logger.debug(marker,
						"Setting service request error on request object for request id {} with error {} on node id {}",
						message.getRequestId(), message.getHeader(DunkNetMessage.KEY_RESPONSE_ERROR).toString(),
						net.getId());
			}
			request.setError(message.getHeader(DunkNetMessage.KEY_RESPONSE_ERROR).toString());
			pendingServiceRequests.remove(message.getRequestId());
			return;
		}
		if (logger.isDebugEnabled()) {
			logger.debug(marker, "Setting success on non-channel service request on node {} with response type {}",
					net.getId(), message.getPayload().getClass().getName());
		}
		request.setSuccess(message.getPayload());
		pendingServiceRequests.remove(message.getRequestId());

	}

	private void handleChannelServiceResponse(DunkNetMessage message) {
		// don't think it matters if its a channel or not
		// it should be a pending service request.
		DunkNetServiceRequest request = null;
		try {
			request = pendingServiceRequests.get(message.getRequestId());
			if(logger.isDebugEnabled()) { 
				logger.debug(marker, "Handing channel service response on node {} from node {} request id {}",
						net.getId(),"n/a", message.getRequestId());
			}
		} catch (Exception e) {
			logger.error(marker,
					"Exception handling channel service response, no pending service request found for request id {} on node {} with response type {}",
					message.getRequestId(), net.getId(), message.getPayload().getClass().getName());
			return;

		}
		if (request == null) {
			logger.error(marker, "Exception internal looking for pending service request not in the map ");
			return;
		}
		
		int respType = (Integer) message.getHeader(DunkNetMessage.KEY_RESPONSE_CODE);
		if (respType == DunkNetMessage.RESPONSE_ERROR) {
			if (logger.isDebugEnabled()) {
				logger.debug(marker, "Setting service error on service request {} with error {} on node {}",
						message.getRequestId(), message.getHeader(DunkNetMessage.KEY_RESPONSE_ERROR).toString(),
						net.getId());
			}
			request.setError(message.getHeader(DunkNetMessage.KEY_RESPONSE_ERROR).toString());
			pendingServiceRequests.remove(message.getRequestId());
			return;
		}
		if (logger.isDebugEnabled()) {
			logger.debug(marker, "Setting service response on service request {} with response type {} on node {}",
					message.getRequestId(), message.getPayload().getClass().getName(), net.getId());
		}
		if(logger.isDebugEnabled()) { 
			logger.debug(marker, "setting response on channel request id {} response type {} on node {}",
					message.getRequestId(), message.getPayload().getClass().getName(),net.getId());
		}
		request.setSuccess(message.getPayload());
		pendingServiceRequests.remove(message.getRequestId());
	}

	private void handleEvent(DunkNetMessage message) {
		System.out.println("handle event " + message.getPayload().getClass().getName());;
		if(message.getChannel() != null) { 
			handleChannelEvent(message);
			return;
		}
		DunkNetNode node = null; 
		try {
			node = getSenderNode(message);
		} catch (Exception e) {
			logger.error(marker, "exception getting sender node in handle event " + e.toString());
			return;
		}
		
		if(logger.isDebugEnabled()) { 
			logger.debug(marker, "handling event {} from node {} on node {}",
					message.getPayload().getClass().getName(), node.getId(),net.getId());
		}
		try {
			List<ComponentMethod> methods = net.extensions().getEventMethods(message.getPayload());
			for (ComponentMethod componentMethod : methods) {
				try {
					componentMethod.getMethod().invoke(componentMethod.getObject(), message.getPayload());
					if(logger.isDebugEnabled()) { 
						logger.debug(marker, "Invoked event method {} on class {} on node {} from node {} with payload {}",
								componentMethod.getMethod().getName(),componentMethod.getClass().getName(),net.getId(),node.getId(),message.getPayload().getClass().getName());
					}
				} catch (Exception e) {
					logger.error(marker, "Exception invoking method event on class {} method {} exeption {}",
							componentMethod.getObject().getClass().getName(),componentMethod.getMethod().getName(),e.toString());
				}
				
				
			}
		} catch (Exception e) {
			System.out.println("event not bueno");
		}

	}
	
	private void handleChannelEvent(DunkNetMessage message) { 
		DunkNetChannel channel = null;
		try {
			channel = getChannel(message.getChannel());
			if(channel == null) { 
				logger.error(marker, "Handling Channel Event for channel {} on node {} but channel is not found",
						message.getChannel(),net.getId());
				return;
			}
		} catch (Exception e) {
			logger.error(marker, "Handling Channel Event for channel {} on node {} but channel is not found",
					message.getChannel(),net.getId());
			return;
		}
		List<ComponentMethod> methods = null;
		try {
			methods = channel.getExtensions().getEventMethods(message.getPayload());
		} catch (Exception e) {
			logger.error(marker, "Exception getting event methods on handleChannel event {}",e.toString());
			return;
		}
		for (ComponentMethod componentMethod : methods) {
			try {
				componentMethod.getMethod().invoke(componentMethod.getObject(), message.getPayload());
				if(logger.isDebugEnabled()) { 
					logger.debug(marker, "Invoked channel event method {} on class {} with input class {}",
							componentMethod.getMethod().getName(),componentMethod.getObject().getClass().getName(),message.getPayload().getClass().getName());
				}
			} catch (Exception e) {
				logger.error(marker, "Exception invoking event method on channel class {} method {} exception {}",
						componentMethod.getObject().getClass().getName(),componentMethod.getMethod().getName(),e.toString());
				
			}
		}
		
	}

	private Object invokeComponentMethod(ComponentMethod method, Object input) throws DunkNetException {
		try {
			Object results = method.getMethod().invoke(method.getObject(), input);
			return results;
		} catch (Exception e) {
			throw new DunkNetException("Component Method Exception " + e.toString());
		}
	}

	private DunkNetNode getSenderNode(DunkNetMessage message) throws DunkNetException {
		return net.getNode(message.getSenderId());
	}

}
