package com.dunkware.spring.cluster.core;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.DunkNetChannel;
import com.dunkware.spring.cluster.DunkNetChannelHandler;
import com.dunkware.spring.cluster.DunkNetChannelListener;
import com.dunkware.spring.cluster.DunkNetChannelState;
import com.dunkware.spring.cluster.DunkNetException;
import com.dunkware.spring.cluster.DunkNetExtensions;
import com.dunkware.spring.cluster.DunkNetNode;
import com.dunkware.spring.cluster.core.request.DunkNetChannelRequest;
import com.dunkware.spring.cluster.core.request.DunkNetServiceRequest;
import com.dunkware.spring.cluster.message.DunkNetMessage;
import com.dunkware.spring.cluster.protocol.descriptors.DunkNetDescriptors;

/**
 * Bug here is that extensions are not getting sent to other party
 * when updated if i believe correctly. 
 */
public class DunkNetChannelImpl implements DunkNetChannel {
	
	private static final int LISTENER_EVENT_START = 1; 
	private static final int LISTENER_EVENT_CLOSE = 2; 
	private static final int LISTENER_EVENT_ERROR = 3; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("DunkNet");

	private Throwable exception = null;
	
	// used to see if we should send a close channel message
	private boolean initialized = false;
	
	private boolean remoteClose = false; 
	
	private DunkNetDescriptors remoteDescriptors;
	
	private DunkNet net;
	private DunkNetNode node;
	private String channelId;
	private List<DunkNetChannelListener> listeners = new ArrayList<DunkNetChannelListener>();
	private Semaphore listenerLock = new Semaphore(1);
	
	private List<DunkNetChannel> subChannels = new ArrayList<DunkNetChannel>();
	
	private DunkNetChannelState state = DunkNetChannelState.CREATING;
	
	private DunkNetExtensions extensions = new DunkNetExtensions();
	
	private DunkNetDescriptors descriptors;
	
	private DunkNetChannelHandler handler;
	
	private ChannelPinger channelPinger; 
	
	
	private boolean client; 
	
	private String label; 
	
	private LocalDateTime lastPing = LocalDateTime.now();
	
	public void init(String channelId, String label, DunkNetNode node, boolean client) throws DunkNetException { 
		this.channelId = channelId;
		this.label = label;
		this.client = client;
		this.node = node;
		this.net = node.getNet();
	}
	
	
	/**
	 * This is called before a server/client handler start() is invoked
	 *  
	 */
	public void startChannel() { 
		channelPinger = new ChannelPinger();
		channelPinger.start();
		
	}
	
	
	@Override
	public void setDescriptors(DunkNetDescriptors descriptors) {
		this.descriptors = descriptors;
	}
	
	
	@Override
	public String getLabel() {
		return label;
	}
	
	@Override
	public void setRemoteDescriptors(DunkNetDescriptors descriptors) {
		this.remoteDescriptors = descriptors;
	}

	@Override
	public DunkNetDescriptors getRemoteDescriptors() {
		return remoteDescriptors;
	}

	@Override
	public void addExtension(Object source) throws DunkNetException {
		extensions.addExtension(source);
		descriptors = extensions.buildDescriptors();
	}
	
	@Override
	public LocalDateTime lastPing() {
		return lastPing;
	}


	public void setLastPing(DunkNetChannelPing ping) { 
		this.remoteDescriptors = ping.getDescriptors();
		this.lastPing = LocalDateTime.now();
		
	}

	@Override
	public void closeChannel() {
		
		if(state == DunkNetChannelState.CLOSED) {
			logger.error("Calling close channel when state is closed fix code");
			return;
		}
		
		channelPinger.interrupt();
		channelPinger = null; 
		
		try {
			
			handler.channelClose();
			// if we are initiating the channel close then send send channel close message
			// if remote then don't send a message 
			if(!isRemoteClose()) { 
				node.message(DunkNetMessage.builder(channelId).channelClose(channelId).buildMessage());	
			}
						
		} catch (Exception e) {
			logger.error(marker, "Can't send channel close message " + e.toString());
		}
		notifyListeners(LISTENER_EVENT_CLOSE);
		state = DunkNetChannelState.CLOSED;
		net.getController().removeChannel(channelId);
		if(logger.isDebugEnabled()) { 
			logger.debug(marker, "Close channel invoked on channel with handler class {} removed from active channel on node {}",
					handler.getClass().getName(),node.getNet().getId());
			
		}
	}

	@Override
	public boolean isOpen() {
		if(state == DunkNetChannelState.OPEN) {
			return true;
		}
		return false;
	}

	@Override
	public Throwable getException() {
		return exception;
	}

	@Override
	public DunkNetChannelState getState() {
		return state;
	}


	@Override
	public DunkNetNode getNode() {
		return node;
	}

	@Override
	public String getChannelId() {
		return channelId;
	}

	@Override
	public List<DunkNetChannel> subChannels() {
		return subChannels;
	}

	@Override
	public DunkNetDescriptors getDescriptors() {
		return descriptors;
	}
	

	@Override
	public DunkNetExtensions getExtensions() {
		return extensions;
	}


	@Override
	public void event(Object payload) throws DunkNetException {
		checkOpen();
		node.message(DunkNetMessage.builder(channelId).event(payload,channelId).buildMessage());
	}
	
	

	@Override
	public DunkNetServiceRequest service(Object payload) throws DunkNetException {
		checkOpen();
		DunkNetServiceRequest sReq = node.getNet().getController().channelServiceRequest(this, payload);
		return sReq;
	}

	@Override
	public Object serviceBlocking(Object payload) throws DunkNetException {
		checkOpen();
		DunkNetServiceRequest sReq = node.getNet().getController().channelServiceRequest(this, payload);
		boolean returned = false;
		try {
			 returned = sReq.await(260, TimeUnit.SECONDS);	
		} catch (Exception e) {
			throw new DunkNetException("Interrupted Exception");
		}
		if(!returned) { 
			throw new DunkNetException("Service Request Timeout 60 Seconds");
		}
		if(sReq.isSuccess()) {
			return sReq.getResponse();
		}
		throw new DunkNetException("Error Returned: " + sReq.getError());
	}

	@Override
	public void addListener(DunkNetChannelListener listener) {
		try {
			listenerLock.acquire();
			listeners.add(listener);
		} catch (Exception e) {
			
		} finally { 
			listenerLock.release();
		}

	}

	@Override
	public void removeListener(DunkNetChannelListener listener) {
		try {
			listenerLock.acquire();
			listeners.remove(listener);
		} catch (Exception e) {
			
		} finally { 
			listenerLock.release();
		}

	}
	
	@Override
	public DunkNetChannelRequest channel(Object payload) throws Exception {
		// TODO Auto-generated method stub
		throw new  IllegalArgumentException("get sub channel not yet implemented");
		
	}
	
	
	
	@Override
	public void setHandler(DunkNetChannelHandler handler) throws DunkNetException {
		this.handler = handler;
		try {
			//
			handler.channelInit(this);
			initialized = true; // is this a client channel
			if(client) { 
				try {
					DunkNetMessage m = DunkNetMessage.builder().channelClientInit(channelId, descriptors).buildMessage();
					getNode().message(m);
					return;
				} catch (Exception e) {
					logger.error(marker, "Exception channel client init message " + e.toString());
					return;
				}
			}
			// if server throw exception
		} catch (Exception e) {
			if(client) { 
				DunkNetMessage m = DunkNetMessage.builder().channelClientInitError(channelId, e.toString()).buildMessage();
				try {
					node.message(m);
				} catch (Exception e2) {
					logger.error(marker, "exception sending client channel init error " + e.toString());
				}
				handler.channelStartError(e.toString());
				handler.channelClose();
			}
		}
		
	}
	
	

	@Override
	public void setState(DunkNetChannelState state) {
		this.state = state;
		
	}

	@Override
	public DunkNetChannelHandler getHandler() {
		return handler;
	}
	
	public void setRemoteClose() {
		this.remoteClose = true; 
	}

	@Override
	public boolean isRemoteClose() {
		return remoteClose;
	}



	private void notifyListeners(int event) { 
		try {
			listenerLock.acquire();
			for (DunkNetChannelListener list : listeners) {
				switch(event) { 
					case LISTENER_EVENT_START:
						list.onChannelStart();
						break;
					case LISTENER_EVENT_CLOSE:
						list.onChannelClose();
						break;
					case LISTENER_EVENT_ERROR:
						list.onChannelError(getException());
						break;
					default:
						break;
				}
			}
		} catch (Exception e) {
		} finally { 
			listenerLock.release();
		}
	}
	
	private void checkOpen() throws DunkNetException { 
		if(!isOpen()) {
			throw new DunkNetException("Channel Is Not Open");
		}
	}
	
	
	private class ChannelPinger extends Thread { 
		
		
		public void run() { 
			setName("DunkNet Channel Pinger " + getChannelId());
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			while(!interrupted()) { 
				try {
					Thread.sleep(1000);
					DunkNetChannelPing ping = new DunkNetChannelPing();
					ping.setDescriptors(descriptors);
					DunkNetMessage m = DunkNetMessage.builder().channelPing(getChannelId(), ping).buildMessage();
					getNode().message(m);
					// we need to get a channel not found message from the ping and come back 
					// to the channel 
				} catch (Exception e) {
					if (e instanceof InterruptedException) { 
						return;
						
					}
					logger.error("Exception sending channel ping " + e.toString());
				}
				
				
			}
			
		}
		
	}

}
