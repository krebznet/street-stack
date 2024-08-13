package com.dunkware.trade.service.stream.server.controller;

import com.dunkware.time.stream.model.admin.settings.StreamSettings;
import com.dunkware.utils.core.scheduler.Scheduler;

public interface IStreamController {

	public void updateSettings(StreamSettings settings) throws Exception; 
	
	public Scheduler eventController();
	
	
}
