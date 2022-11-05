package com.dunkware.trade.sdk.lib.model.bot.model;

import com.dunkware.trade.sdk.core.model.system.SystemType;
import com.dunkware.trade.sdk.lib.model.bot.web.WebTradeBot;

public class TradeBotType extends SystemType {

	private WebTradeBot wrapper = new WebTradeBot();

	public WebTradeBot getWrapper() {
		return wrapper;
	}

	public void setWrapper(WebTradeBot wrapper) {
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
