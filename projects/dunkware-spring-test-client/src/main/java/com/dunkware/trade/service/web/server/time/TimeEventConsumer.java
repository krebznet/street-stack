package com.dunkware.trade.service.web.server.time;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.anot.ADunkNetEvent;
import com.dunkware.spring.test.model.time.TimeResponse;

@Service
public class TimeEventConsumer {

	@Autowired
	private DunkNet net;
	
	
	@PostConstruct
	private void load() { 
		try {
			net.extensions().addExtension(this);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@ADunkNetEvent
	public void timeEvent(TimeResponse resp) { 
		System.out.println("time event " + resp.getTime().toHHmmSS());
	}
}
