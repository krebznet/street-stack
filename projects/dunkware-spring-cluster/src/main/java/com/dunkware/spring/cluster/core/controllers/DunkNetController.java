package com.dunkware.spring.cluster.core.controllers;

import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.ApplicationContext;

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

	public DunkNetChannelRequest subChannelRequest(DunkNetChannel channel, Object input) throws DunkNetException {
		try {
			channel.getDescriptors().getChannel(input);
		} catch (Exception e) {
			throw e;
		}
		DunkNetMessage m = DunkNetMessage.builder().channelRequest(channel.getChannelId(), input).buildMessage();
		DunkNetChannelRequest req = new DunkNetChannelRequest(m.getMessageId(), channel.getNode());
		pendingChannelRequests.put(m.getMessageId(), req);
		channel.getNode().message(m);
		return req;

	}

	DunkNetChannelRequest nodeChannelRequest(DunkNetNode node, Object input) throws DunkNetException {
		DunkNetChannelDescriptor descript = node.getDescriptor().getDescriptors().getChannel(input);
		if (input.getClass().getName().equals(descript.getInput()) == false) {
			throw new DunkNetException("Channel input " + input.getClass().getName() + " is not matching descriptor "
					+ descript.getInput());
		}

		DunkNetMessage m = DunkNetMessage.builder().channelRequest(input).buildMessage();
		DunkNetChannelRequest req = new DunkNetChannelRequest(m.getMessageId(), node);
		pendingChannelRequests.put(m.getMessageId(), req);
		node.message(m);
		return req;
	}

	public boolean handleInboundMessage(DunkNetMessage message) throws DunkNetException {
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
			return true;
		}
		return true;
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
			channel.init(newChannelId, node, false);
			try {
				channel.setHandler(channelHandler);
			} catch (Exception e) {
				node.message(DunkNetMessage.builder().channelException(null, message.getMessageId(),
						"Exception init channel handler   " + e.toString()).buildMessage());
				channel.getHandler().channelStartError(e.toString());
				channel.getHandler().channelClose();
			}
			channel.setState(DunkNetChannelState.INITIALIZED);
			node.message(DunkNetMessage.builder().channelResponse(newChannelId, message.getMessageId()).buildMessage());
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
			channel.init(newChannelId, node, false);
			channel.setHandler(channelHandler);
			channel.setState(DunkNetChannelState.INITIALIZED);

			node.message(DunkNetMessage.builder()
					.channelResponse(parentChannel.getChannelId(), newChannelId, message.getMessageId())
					.buildMessage());
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
		}
		if (respType != DunkNetMessage.RESPONSE_SUCCESS) {
			logger.error(marker, "Fucked up channel response not error or success is " + respType);
			throw new DunkNetException("channel response coode type invalid " + respType);
		}
		String channelId = message.getChannel();
		DunkNetChannelImpl channel = new DunkNetChannelImpl();
		ac.getAutowireCapableBeanFactory().autowireBean(channel);
		channel.init(channelId, reqNode, true);
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
			channel.getHandler().channelStart();
			node.message(DunkNetMessage.builder().channelServerStart(channel.getChannelId()).buildMessage());
		} catch (Exception e) {
			channel.getHandler().channelStartError(e.toString());
			channel.closeChannel();
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
			channel.getHandler().channelClose();
			activeChannels.remove(channel.getChannelId());
			node.message(DunkNetMessage.builder().channelServerStart(channel.getChannelId()).buildMessage());
		} catch (Exception e) {
			logger.error(marker, "error handling client channel init error " + e.toString());
		}

	}
	

	private void handleChannelClientStartError(DunkNetMessage message) throws DunkNetException {
		DunkNetNode node = getSenderNode(message);
		DunkNetChannel channel = getChannel(message.getChannel());

		String error = message.getHeader(DunkNetMessage.KEY_ERROR).toString();
		channel.getHandler().channelStartError(error);
		channel.closeChannel();
		activeChannels.remove(channel.getChannelId());

	}

	private void handleChannelServerStart(DunkNetMessage message) throws DunkNetException {
		DunkNetNode node = getSenderNode(message);
		DunkNetChannel channel = getChannel(message.getChannel());
		try {
			channel.getHandler().channelStart();
		} catch (Exception e) {
			channel.getHandler().channelStartError(e.toString());
			channel.closeChannel();
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

		String error = message.getHeader(DunkNetMessage.KEY_ERROR).toString();
		channel.getHandler().channelStartError(error);
		channel.closeChannel();
		activeChannels.remove(channel.getChannelId());

	}

	private void handleChannelClose(DunkNetMessage message) {

	}

	private void handleServiceRequest(DunkNetMessage message) {

	}

	private void handleEvent(DunkNetMessage message) {

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
