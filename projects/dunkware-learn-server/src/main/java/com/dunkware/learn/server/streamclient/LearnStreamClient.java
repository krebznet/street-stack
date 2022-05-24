package com.dunkware.learn.server.streamclient;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.dunkware.xstream.net.client.test.StreamClientTest1;

@Service
public class LearnStreamClient {

	@PostConstruct
	public void start() { 
		StreamClientTest1 test1 = new StreamClientTest1();
		
	}
}
