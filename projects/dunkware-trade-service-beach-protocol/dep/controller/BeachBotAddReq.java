package com.dunkware.trade.service.beach.protocol.controller;

import comm.dunkware.trade.service.beach.web.bot.WebBot;

public class BeachBotAddReq {
	
	private String name;
	private String account;
	private String broker;
	private WebBot model;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getBroker() {
		return broker;
	}
	public void setBroker(String broker) {
		this.broker = broker;
	}
	public WebBot getModel() {
		return model;
	}
	public void setModel(WebBot model) {
		this.model = model;
	}
	
	
	

	

}
