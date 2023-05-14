package com.dunkware.trade.service.beach.server.controller.stream;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.spring.streaming.StreamingAdapter;
import com.dunkware.spring.streaming.StreamingAdapterListener;
import com.dunkware.trade.service.beach.server.runtime.BeachBroker;
import com.dunkware.trade.service.beach.server.runtime.BeachBrokerBean;
import com.dunkware.trade.service.beach.server.runtime.BeachPlay;
import com.dunkware.trade.service.beach.server.runtime.BeachService;
import com.dunkware.trade.service.beach.server.runtime.core.events.EBeachBrokerUpdate;

public class BeachPlayTradesStream implements StreamingAdapterListener {

	@Autowired
	private BeachService beachService; 
	
	StreamingAdapter adapter;
	private BeachPlay play;
	
	public void start(BeachPlay play, StreamingAdapter adapter) { 
		this.adapter = adapter;
		this.play = play;
		this.adapter.addListener(this);
		beachService.getEventNode().addEventHandler(this);
		
		// lets see here its hard. 
	}

	@Override
	public void clientDisconnect(StreamingAdapter adapter) {
		beachService.getEventNode().removeEventHandler(this);
	}

	@Override
	public void serverDisconnect(StreamingAdapter adapter) {
		beachService.getEventNode().removeEventHandler(this);
	}
	
	public void brokerUpdate(EBeachBrokerUpdate update) { 
		sendData();
	}
	
	public void sendData() { 
		List<BeachBrokerBean> beans = new ArrayList<BeachBrokerBean>();
		for (BeachBroker broker : beachService.getBrokers()) {
			beans.add(broker.getBean());
		}
		adapter.send(beans);
	}


}
