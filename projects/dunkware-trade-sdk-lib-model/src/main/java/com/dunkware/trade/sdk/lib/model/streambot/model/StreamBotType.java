package com.dunkware.trade.sdk.lib.model.streambot.model;

import com.dunkware.trade.sdk.core.model.system.SystemType;
import com.dunkware.trade.sdk.lib.model.streambot.web.WebStreamBot;

public class StreamBotType extends SystemType {

	private WebStreamBot wrapper = new WebStreamBot();

	public WebStreamBot getWrapper() {
		return wrapper;
	}

	public void setWrapper(WebStreamBot wrapper) {
		this.wrapper = wrapper;
	}

	@Override
	public String getIdentifier() {
		return wrapper.getName(); 
	}

	@Override
	public void setIdentifier(String identifier) {
		wrapper.setName(identifier);
	}
	
	
	
}
