package com.dunkware.spring.test.server.time;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.dtime.DTime;
import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.test.model.time.TimeResponse;

@Service
public class TimeService {

	@Autowired
	private DunkNet net;
	
	@PostConstruct
	public void load() { 
		//Sender s = new Sender();
		//s.start();
	}
	
	
	public void sendEvent() { 
		try {
			net.event(new TimeResponse(DTime.now()));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private class Sender extends Thread { 
		
		public void run() {
			while(true) { 
				try {
					net.event(new TimeResponse(DTime.now()));
					Thread.sleep(4000);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}
	
}
