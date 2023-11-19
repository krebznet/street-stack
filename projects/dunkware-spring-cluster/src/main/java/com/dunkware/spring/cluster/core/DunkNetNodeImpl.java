package com.dunkware.spring.cluster.core;

import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.common.util.json.DJson;
import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.DunkNetException;
import com.dunkware.spring.cluster.DunkNetNode;
import com.dunkware.spring.cluster.core.request.DunkNetChannelRequest;
import com.dunkware.spring.cluster.core.request.DunkNetServiceRequest;
import com.dunkware.spring.cluster.message.DunkNetMessage;
import com.dunkware.spring.cluster.message.DunkNetMessageHelper;
import com.dunkware.spring.cluster.message.DunkNetMessageTransport;
import com.dunkware.spring.cluster.protocol.descriptors.DunkNetNodeDescriptor;

public class DunkNetNodeImpl implements DunkNetNode {
	
	private Marker marker = MarkerFactory.getMarker("DunkNet");
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private DKafkaByteProducer kafkaProducer;
	

	private DunkNetNodeDescriptor descriptor; 
	private Semaphore descriptorLock = new Semaphore(1);
	
	private DunkNet net; 
	
	public DunkNetNodeImpl(DunkNet net, DunkNetNodeDescriptor descriptor) throws DunkNetException {
		this.net = net;
		this.descriptor = descriptor;
		try {
		
			String nodeTopic = "dunknet." + net.getConfig().getClusterId() + ".node." + descriptor.getId();
			System.out.println("producer on node " + nodeTopic);
			kafkaProducer = DKafkaByteProducer.newInstance(net.getConfig().getServerBrokers(),nodeTopic,nodeTopic);
		} catch (Exception e) {
			throw new DunkNetException("Exception creating node message producer " + e.toString());
		}
		
	
	}
	
	@Override
	public String getId() {
		return descriptor.getId();
	}
	
	
	
	@Override
	public DunkNet getNet() {
		return net;
	}

	@Override
	public void nodeUpdate(DunkNetNodeDescriptor descriptor) {
		this.descriptor = descriptor;
	}

	@Override
	public DunkNetNodeDescriptor descriptor() {
		return descriptor;
	}

	@Override
	public boolean isOnline() {
		return true;
	}

	
	@Override
	public DunkNetNodeDescriptor getDescriptor() {
		try {
			descriptorLock.acquire();
			return descriptor;
		} catch (Exception e) {
			// TODO: handle exception
		} finally { 
			descriptorLock.release();
		}
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public DunkNetChannelRequest channel(Object payload) throws DunkNetException {
		return net.getController().nodeChannelRequest(this, payload);
	}


	
	@Override
	public void event(Object payload) throws DunkNetException {
		
	}

	@Override
	public DunkNetServiceRequest service(Object payload) throws DunkNetException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public void message(DunkNetMessage message) throws DunkNetException {
		message.getHeaders().put(DunkNetMessage.KEY_SOURCE_NODE, net.getId());
		try {
			if(logger.isDebugEnabled()) { 
				if(message.getPayload() != null) {
					logger.debug("Sending message id {} of type {} on node {} to node {} payload {} headers {}",
							message.getMessageId(), message.getType(), net.getId(), getId(), message.getPayload().getClass().getName(),message.getHeaders().toString());
				} else { 
					logger.debug("Sending message id {} of type {} on node {} to node {} with node payload headers {} size {}",
							message.getMessageId(), message.getType(), net.getId(), getId(), message.getHeaders().toString(),message.getHeaders().size());
				}
			}
			kafkaProducer.sendBytes(DJson.serialize(DunkNetMessageHelper.toTransport(message,net.getId())).getBytes());	
		} catch (Exception e) {
			logger.error(marker, "Exception sending event transport to node " + getId() + " " +  e.toString());
		}
		
	}

	@Override
	public void message(DunkNetMessageTransport transport) throws DunkNetException {
		try {
			transport.getHeaders().put(DunkNetMessage.KEY_SOURCE_NODE, net.getId());
			if(logger.isDebugEnabled()) { 
				logger.debug(marker, "Sending ransport on node {} to node {} serialized {} ",
						net.getId(),getId(),DJson.serializePretty(transport));
			}
			kafkaProducer.sendBytes(DJson.serialize(transport).getBytes());	
		} catch (Exception e) {
			logger.error(marker, "Exception sending event transport to node " + getId() + " " +  e.toString());
		}
		
	}
	
	

	
}
