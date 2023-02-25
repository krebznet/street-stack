package com.dunkware.trade.service.beach.protocol.controller;

import comm.dunkware.trade.service.beach.web.bot.WebBot;

public class BeachBotAddReq {
	
	private String name;
	private int accountId; 
	private WebBot model;
	
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public WebBot getModel() {
		return model;
	}
	public void setModel(WebBot model) {
		this.model = model;
	}
	
	

	

}
