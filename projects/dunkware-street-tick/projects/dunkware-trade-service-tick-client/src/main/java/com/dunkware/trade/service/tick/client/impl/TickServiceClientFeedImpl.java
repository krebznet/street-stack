package com.dunkware.trade.service.tick.client.impl;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler;
import com.dunkware.common.tick.proto.TickProto.Tick;
import com.dunkware.common.tick.stream.TickStream;
import com.dunkware.common.tick.stream.impl.TickStreamImpl;
import com.dunkware.common.util.helpers.DHttpHelper;
import com.dunkware.trade.service.tick.client.TickServiceClient;
import com.dunkware.trade.service.tick.client.TickServiceClientException;
import com.dunkware.trade.service.tick.client.TickServiceClientFeed;
import com.dunkware.trade.tick.api.feed.TickFeed;
import com.dunkware.trade.tick.model.consumer.TickConsumerSession;
import com.dunkware.trade.tick.model.consumer.TickConsumerSpec;
import com.dunkware.trade.tick.service.protocol.feed.TickFeedStartReq;
import com.dunkware.trade.tick.service.protocol.feed.TickFeedStartResp;

public class TickServiceClientFeedImpl implements TickServiceClientFeed, DKafkaByteHandler{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private TickConsumerSpec spec; 
	private TickServiceClient client; 
	private TickFeed feed;
	private TickStream tickStream; 
	private TickConsumerSession session;
	
	private DKafkaByteConsumer kafkaConsumer;
	
	//private Pinger pinger = new Pinger();
	
	private AtomicLong tickCount = new AtomicLong(0);
	
	public void start(TickConsumerSpec spec, TickServiceClient client) throws TickServiceClientException { 
		this.spec = spec; 
		this.client = client; 
		try {
			TickFeedStartReq req = new TickFeedStartReq();
			req.setSpec(spec);
			TickFeedStartResp resp = (TickFeedStartResp)client.postResponseObject("/tick/feed/start",req,TickFeedStartResp.class);
			if(resp.getCode().equals("ERROR")) { 
				throw new TickServiceClientException("Exception Creating Tick Consumer " + resp.getError());
			}
			this.session = resp.getSession();;
		} catch (Exception e) {
			throw new TickServiceClientException("Internal Tick Feed Create Exception " + e.toString(),e);
		}
		try {
			this.tickStream = new TickStreamImpl();
			this.kafkaConsumer = DKafkaByteConsumer.newInstance(session.getKafkaBroker(),session.getKafkaTopic());
			this.kafkaConsumer.addStreamHandler(this);
		} catch (Exception e) {
			throw new TickServiceClientException("Exception Creating Feed KafkaConsumer Borkers " + session.getKafkaBroker() + " Topic " + session.getKafkaTopic() + " Excepion " + e.toString(),e);
		}
		
	//	this.pinger = new Pinger();
	//	pinger.start();
		
		
	}
	
	public static void main(String[] args) {
		int x = 0; 
		while(x < 1000) { 
			x++;
			System.out.println(x);
		}
	}
	
	public TickFeed getFeed() { 
		return feed; 
	}
	
	public void stop() { 
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public void update(TickConsumerSpec spec) throws TickServiceClientException {
		//TODO: send update request 
	}

	@Override
	public void dispose() {
		//TODO: send stop request 
		kafkaConsumer.dispose();
		try {
			DHttpHelper.getURLContent(client.getEndpoint() + "/tick/feed/stop?id=" + session.getSessionId());	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	

	@Override
	public TickConsumerSpec getSpec() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TickStream getTickStream() {
		return tickStream;
	}

	
	@Override
	public long getConsumedTickCount() {
		return tickCount.get();
	}

	@Override
	public void streamBytes(byte[] bytes) {
		try {
			Tick tick = Tick.parseFrom(bytes);
			tickCount.incrementAndGet();
			tickStream.streamTick(tick);
		} catch (Exception e) {
			logger.error("Kafka Byte Consume Exception Line 106 Feed ID  " + this.session.getSessionId() + " " + e.toString(),e);
		}
	}
	
	
	private class Pinger extends Thread { 
		
		public void run() { 
			try {
				while(!interrupted()) { 
					try {
						Thread.sleep(5000); 
						DHttpHelper.getURLContent(client.getEndpoint() + "/tick/feed/ping?id=" + session.getSessionId());
					} catch (Exception e) {
						if (e instanceof InterruptedException) {
							return;
						}
						logger.error("Exception Sending Feed Ping " + e.toString());
					}
				}
			} catch (Exception e) {
				if (e instanceof InterruptedException) {
					return;
				}
				logger.error("Feed Ping Excepiton " + e.toString());
			}
		}
	}
	
	
	
	
	
}
