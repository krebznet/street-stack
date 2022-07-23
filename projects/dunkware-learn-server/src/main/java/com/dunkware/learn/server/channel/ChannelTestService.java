package com.dunkware.learn.server.channel;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.spring.channel.Channel;
import com.dunkware.spring.channel.ChannelService;

@Service()
public class ChannelTestService {

	@Autowired()
	private ChannelService channelService;

	private Channel clientChannel;
	private Channel serverChannel;

	@PostConstruct
	private void load() { 
		
		String serverTopic = "test-channel-server";
		String clientTopic = "test-channel-client";
		String brokers = "localhost:9092";
		
		
		Map<String,Object> injectables = new HashMap<String,Object>();
		injectables.put("TestInjection", new TestInjection());
		try {
			 clientChannel = channelService.createChannel("ClientChannel", brokers, clientTopic, serverTopic, injectables); 
			 serverChannel = channelService.createChannel("ServerChannel", brokers, serverTopic, clientTopic, injectables);
		} catch (Exception e) {
			e.printStackTrace();
			
			// TODO: handle exception
		}
		
		Thread runner = new Thread() { 
			
			public void run() { 
				int count = 1;
				while(!interrupted()) { 
					try {
						Thread.sleep(1500);
						ClientMessage message = new ClientMessage();
						message.setValue("Client Message " + count);
						ServerMessage serverm = new ServerMessage(); 
						serverm.setValue("Server Message " + count);
						count++;
						try {
							clientChannel.sendReply(serverm);
							return;
						} catch (Exception e2) {
							e2.printStackTrace();
						}

					} catch (Exception e) {
							e.printStackTrace();
						// TODO: handle exception
					}
				}
			}
		};
		
		runner.start();
		
		
		
	}
}
