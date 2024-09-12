package com.dunkware.trade.service.stream.server.controller;

import com.dunkware.time.stream.model.admin.settings.StreamSettings;

public interface IStreamService {
	
	
	public IStreamController deployStream(StreamSettings settings) throws Exception;

}
