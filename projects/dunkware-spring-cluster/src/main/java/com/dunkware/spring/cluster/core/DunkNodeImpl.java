package com.dunkware.spring.cluster.core;

import java.util.List;
import java.util.Vector;
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
import com.dunkware.spring.cluster.anot.ADunkNetChannel;
import com.dunkware.spring.cluster.core.request.DunkNetChannelRequest;
import com.dunkware.spring.cluster.core.request.DunkNetServiceRequest;
import com.dunkware.spring.cluster.message.DunkNetMessage;
import com.dunkware.spring.cluster.message.DunkNetMessageHelper;
import com.dunkware.spring.cluster.message.DunkNetMessageTransport;
import com.dunkware.spring.cluster.protocol.DunkNetNodeChannel;
import com.dunkware.spring.cluster.protocol.DunkNetNodeDescriptor;
import com.dunkware.spring.cluster.protocol.DunkNetNodeEvent;
import com.dunkware.spring.cluster.protocol.DunkNetNodePing;
import com.dunkware.spring.cluster.protocol.DunkNetNodeService;

public class DunkNodeImpl implements DunkNetNode {
	
	private Marker marker = MarkerFactory.getMarker("DunkNet");
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private DKafkaByteProducer kafkaProducer;
	
	private DunkNetNodePing ping;
	private DunkNetNodeDescriptor descriptor; 
	private Semaphore descriptorLock = new Semaphore(1);
	
	private DunkNet net; 
	
	public DunkNodeImpl(DunkNet net, DunkNetNodePing ping) throws DunkNetException {
		this.net = net;
		this.ping = ping;
		this.descriptor = ping.getDescriptor();
		try {
		
			String nodeTopic = "dunknet." + net.getConfig().getClusterId() + ".node." + ping.getId();
			System.out.println("producer on node " + nodeTopic);
			kafkaProducer = DKafkaByteProducer.newInstance(net.getConfig().getServerBrokers(),nodeTopic,nodeTopic);
		} catch (Exception e) {
			throw new DunkNetException("Exception creating node message producer " + e.toString());
		}
		
	
	}
	
	@Override
	public String getId() {
		return ping.getId();
	}
	
	@Override
	public void ping(DunkNetNodePing ping) {
		this.ping = ping;
		try {
			descriptorLock.acquire();
			this.descriptor = ping.getDescriptor();
		} catch (Exception e) {
		} finally { 
			descriptorLock.release();
		}
	
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
	public DunkNetNodeService getServiceDescriptor(Object input) throws DunkNetException {
		for (DunkNetNodeService desc : descriptor.getServices()) {
			if(input.getClass().getName().equals(desc.getInput())) { 
				return desc;
			}
		}
		throw new DunkNetException("Service Descriptor not found on node " + getId() + " " + input.getClass().getName());
	}

	@Override
	public boolean isEventConsumer(Object input) {
		try {
			descriptorLock.acquire();
			List<DunkNetNodeEvent> events = descriptor.getEvents();
			for (DunkNetNodeEvent nodeMessage : events) {
					if(input.getClass().getName().equals(nodeMessage.getInput())) { 
						return true;
					}
			}
			return false;
		} catch (Exception e) {
			return false;
		} finally { 
			descriptorLock.release();
		}
	}

	@Override
	public boolean isServiceProvider(Object input) {
		try {
			descriptorLock.acquire();
			for (DunkNetNodeService service : descriptor.getServices()) {
				if(input.getClass().getName().equals(service.getInput())) { 
					return true; 
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		} finally { 
			descriptorLock.release();
		}
		
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
	public DunkNetNodeChannel getChannelDescriptor(Object input) throws DunkNetException {
		try {
			descriptorLock.acquire();
			for (DunkNetNodeChannel channel : descriptor.getChannels()) {
				if(input.getClass().getName().equals(channel.getInput())) { 
					return channel;
				}
			}
			throw new DunkNetException("Channel with input " + input.getClass().getName() + " not found on node " + getId());
		} catch (Exception e) {
			throw new DunkNetException("Internenal exception " + e.toString());
		} finally { 
			descriptorLock.release();
		}
	}

	@Override
	public boolean hasProfile(String profile) {
		if(descriptor.getProfiles().contains(profile)) { 
			return true; 
		}
		return false;
	}
	
	

	@Override
	public void message(DunkNetMessage message) throws DunkNetException {
		try {
			kafkaProducer.sendBytes(DJson.serialize(DunkNetMessageHelper.toTransport(message,net.getId())).getBytes());	
		} catch (Exception e) {
			logger.error(marker, "Exception sending event transport to node " + getId() + " " +  e.toString());
		}
		
	}

	@Override
	public void message(DunkNetMessageTransport transport) throws DunkNetException {
		try {
			kafkaProducer.sendBytes(DJson.serialize(transport).getBytes());	
		} catch (Exception e) {
			logger.error(marker, "Exception sending event transport to node " + getId() + " " +  e.toString());
		}
		
	}
	
	

	
}
