package com.dunkware.spring.cluster.core.controllers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2SpecBuilder;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.OffsetType;
import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.json.DJson;
import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.DunkNetNode;
import com.dunkware.spring.cluster.core.DunkNetController;
import com.dunkware.spring.cluster.core.DunkNetImpl;
import com.dunkware.spring.cluster.message.DunkNetMessage;
import com.dunkware.spring.cluster.protocol.DunkNetNodePing;


public class DunkNetPingController implements DunkNetController, DKafkaByteHandler2 {

	private DunkNetImpl dunkNet;
	
	

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("DunkNet");

	@Autowired
	private ApplicationContext ac;
	
	private PingSender pingSender; 
	
	private DKafkaByteConsumer2 pingConsumer; 

	public void init(DunkNet net) {
		this.dunkNet =(DunkNetImpl) net;
		pingSender = new PingSender(); 
		pingSender.start();
		
		try {
			String pingTopic = "dunknet." + dunkNet.getConfig().getClusterId() + ".node.ping";
			pingConsumer = DKafkaByteConsumer2.newInstance(DKafkaByteConsumer2SpecBuilder.newBuilder(ConsumerType.Auto, OffsetType.Latest).setClientAndGroup(dunkNet.getConfig().getNodeId(), dunkNet.getConfig().getNodeId()).addBroker(dunkNet.getConfig().getServerBrokers()).addTopic(pingTopic).build());
			pingConsumer.addStreamHandler(this);
			pingConsumer.start();
			
		} catch (Exception e) {
			logger.error(marker, "Exception creating ping consumer");
		}
	}
	

	@Override
	public void record(ConsumerRecord<String, byte[]> record) {
		try {
			DunkNetNodePing ping = DJson.getObjectMapper().readValue(record.value(), DunkNetNodePing.class);
			dunkNet.consumePing(ping);
		} catch (Exception e) {
			logger.error(marker, "Exception consuming ping " + e.toString());
		}
	}



	@Override
	public boolean handleMessage(DunkNetMessage message) {
		return false;
	}
	
	
	private class PingSender extends Thread { 
		
		public void run() {
			setName("DunkNet-PingSender");
			while(!interrupted()) { 
				DunkNetNodePing ping = new DunkNetNodePing();
				ping.setDescriptor(dunkNet.getDescriptor());
				ping.setId(dunkNet.getId());
				ping.setProfiles(dunkNet.getConfig().getProfiles());
				ping.setPingTime(DDateTime.now());
				dunkNet.sendPing(ping);
				try {
					Thread.sleep(3000);	
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		}
	}


	

}
