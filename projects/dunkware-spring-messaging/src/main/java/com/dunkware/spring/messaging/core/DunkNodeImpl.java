package com.dunkware.spring.messaging.core;

import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.common.util.json.DJson;
import com.dunkware.spring.messaging.DunkNetException;
import com.dunkware.spring.messaging.DunkNet;
import com.dunkware.spring.messaging.DunkNetNode;
import com.dunkware.spring.messaging.anot.ADunkNetChannel;
import com.dunkware.spring.messaging.core.request.DunkNetChannelRequest;
import com.dunkware.spring.messaging.core.request.DunkNetServiceRequest;
import com.dunkware.spring.messaging.message.DunkNetMessage;
import com.dunkware.spring.messaging.message.DunkNetMessageHelper;
import com.dunkware.spring.messaging.message.DunkNetMessageTransport;
import com.dunkware.spring.messaging.protocol.DunkNetNodePing;
import com.dunkware.spring.messaging.protocol.DunkNetNodeEvent;
import com.dunkware.spring.messaging.protocol.DunkNetNodeService;

public class DunkNodeImpl implements DunkNetNode {
	
	
	private Marker marker = MarkerFactory.getMarker("dunknet");
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private DKafkaByteProducer kafkaProducer;
	
	private DunkNetNodePing descriptor;
	
	private DunkNet net; 
	
	private Vector<Class<?>> eventClasses = new Vector<Class<?>>();
	private Vector<Class<?>> serviceClasses = new Vector<Class<?>>();
	private Vector<Class<?>> channelTypes = new Vector<Class<?>>();

	public DunkNodeImpl(DunkNet net, DunkNetNodePing descriptor) throws DunkNetException {
		this.net = net;
		this.descriptor = descriptor;
		try {
			kafkaProducer = DKafkaByteProducer.newInstance(net.getProperties().getBrokers(),descriptor.getNodeTopic(),descriptor.getId());
		} catch (Exception e) {
			throw new DunkNetException("Exception creating node message producer " + e.toString());
		}
		
		try {
			for (DunkNetNodeEvent eventClass : descriptor.getMessages()) {
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void setDescriptor(DunkNetNodePing descriptor) { 
		this.descriptor = descriptor;
	}

	@Override
	public String getId() {
		return descriptor.getId();
	}
	
	@Override
	public void ping(DunkNetNodePing descriptor) {
		this.descriptor = descriptor;
	}


	@Override
	public boolean isChannelProvider(Object input)  {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public DunkNetChannelRequest channel(Object payload) throws DunkNetException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public DunkNet getDunkNet() {
		return net;
	}

	@Override
	public DunkNetNodeService getServiceDescriptor(Object input) {
		for (DunkNetNodeService desc : descriptor.getServices()) {
			if(input.getClass().getName().equals(desc.getInput())) { 
				return desc;
			}
		}
		return null;
	}

	@Override
	public boolean isEventConsumer(Object input) {
		List<DunkNetNodeEvent> events = descriptor.getMessages();
		for (DunkNetNodeEvent nodeMessage : events) {
				if(input.getClass().getName().equals(nodeMessage.getInput())) { 
					return true;
				}
		}
		return false;
	}

	@Override
	public boolean isServiceProvider(Object input) {
		for (DunkNetNodeService service : descriptor.getServices()) {
			if(input.getClass().getName().equals(service.getInput())) { 
				return true; 
			}
		}
		return false;
	}

	
	@Override
	public void event(Object payload) throws DunkNetException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DunkNetServiceRequest service(Object payload) throws DunkNetException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Class<?> getServiceReturnType(Object input) throws DunkNetException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<?> getChannelReturnType(Object intput) throws DunkNetException {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasProfile(String profile) {
		if(descriptor.getProfiles().contains(profile)) { 
			return true; 
		}
		return false;
	}
	
	

	@Override
	public void send(DunkNetMessage message) throws DunkNetException {
		try {
			kafkaProducer.sendBytes(DJson.serialize(DunkNetMessageHelper.toTransport(message,net.getId())).getBytes());	
		} catch (Exception e) {
			logger.error(marker, "Exception sending event transport to node " + getId() + " " +  e.toString());
		}
		
	}

	@Override
	public void send(DunkNetMessageTransport transport) throws DunkNetException {
		try {
			kafkaProducer.sendBytes(DJson.serialize(transport).getBytes());	
		} catch (Exception e) {
			logger.error(marker, "Exception sending event transport to node " + getId() + " " +  e.toString());
		}
		
	}
	
	

	
}
