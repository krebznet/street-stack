package com.dunkware.trade.service.web.server.controller;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.DunkNetChannel;
import com.dunkware.spring.cluster.core.request.DunkNetChannelRequest;
import com.dunkware.spring.test.model.feed.FeedRequest;
import com.dunkware.spring.test.model.feed.Quote;
import com.dunkware.spring.test.model.time.TimeRequest;
import com.dunkware.spring.test.model.time.TimeResponse;
import com.dunkware.trade.service.web.server.feed.FeedClient;

@RestController
public class FeedController {

	@Autowired
	private DunkNet net;
	
	private FeedClient client = new FeedClient();
	
	@PostConstruct
	private void construct() { 
		System.out.println("fuck");
	}
	
	@GetMapping("/feed/start")
	public void startFeed() { 
		try {
			Thread fucker = new Thread() { 
				public void run() { 
					try {
						FeedRequest dwws = new  FeedRequest();
						dwws.setName("Test");
						DunkNetChannelRequest req = net.channel(dwws);
						DunkNetChannel channel = req.get();
						channel.setHandler(client);
					} catch (Exception e) {
						e.printStackTrace();
						// TODO: handle exception
					}
				}
			};
			fucker.start();
		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@GetMapping(path = "/time/request")
	public @ResponseBody() TimeResponse time() throws Exception { 
		return (TimeResponse)net.serviceBlocking(new TimeRequest());
	}
	
	@GetMapping(path = "/feed/subscribe")
	public void subscribe(@RequestParam() String symbol) { 
		client.subscribe(symbol);
	}
	
	@GetMapping(path = "/feed/quotes")
	public @ResponseBody() Collection<Quote> getQuotes() {
		return client.quotes.values();
	}
	
	@GetMapping(path = "/feed/reset")
	public void resetSubs() { 
		client.reset();
	}
	
}
