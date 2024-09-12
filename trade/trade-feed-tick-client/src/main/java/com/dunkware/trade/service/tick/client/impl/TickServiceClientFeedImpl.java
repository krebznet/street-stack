package com.dunkware.trade.service.tick.client.impl;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.trade.service.tick.client.TickServiceClient;
import com.dunkware.trade.service.tick.client.TickServiceClientException;
import com.dunkware.trade.service.tick.client.TickServiceClientFeed;
import com.dunkware.trade.tick.api.feed.TickFeed;
import com.dunkware.trade.tick.model.consumer.TickConsumerSession;
import com.dunkware.trade.tick.model.consumer.TickConsumerSpec;
import com.dunkware.trade.tick.service.protocol.feed.TickFeedStartReq;
import com.dunkware.trade.tick.service.protocol.feed.TickFeedStartResp;
import com.dunkware.utils.core.http.DunkHttp;
import com.dunkware.utils.kafka.byteconsumer.KafkaByteConsumer;
import com.dunkware.utils.kafka.byteconsumer.KafkaByteHandler;
import com.dunkware.utils.tick.proto.TickProto.Tick;
import com.dunkware.utils.tick.stream.TickStream;
import com.dunkware.utils.tick.stream.impl.TickStreamImpl;

public class TickServiceClientFeedImpl implements TickServiceClientFeed, KafkaByteHandler{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private TickConsumerSpec spec; 
	private TickServiceClient client; 
	private TickFeed feed;
	private TickStream tickStream; 
	private TickConsumerSession session;
	
	private KafkaByteConsumer kafkaConsumer;
	
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
			this.kafkaConsumer = KafkaByteConsumer.newConsumerInstance(session.getKafkaBroker(), session.getSessionId(), session.getKafkaTopic());
			this.kafkaConsumer.addStreamHandler(this);
			this.kafkaConsumer.start();
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

		}
		
	}

	@Override
	public void update(TickConsumerSpec spec) throws TickServiceClientException {

	}

	@Override
	public void dispose() {
 
		kafkaConsumer.dispose();
		try {
			DunkHttp.getURLContent(client.getEndpoint() + "/tick/feed/stop?id=" + session.getSessionId());	
		} catch (Exception e) {
			
		}
		
	}

	

	@Override
	public TickConsumerSpec getSpec() {
		
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
	public void record(ConsumerRecord<String,byte[]> record) {
		try {
			Tick tick = Tick.parseFrom(record.value());
			tickCount.incrementAndGet();
			tickStream.consume(tick);
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
						DunkHttp.getURLContent(client.getEndpoint() + "/tick/feed/ping?id=" + session.getSessionId());
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
