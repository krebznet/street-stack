package com.dunkware.trade.service.beach.protocol.play;

public class AddPlayReq {

	private Play play; 
	private long accountId;
	public Play getPlay() {
		return play;
	}
	public void setPlay(Play play) {
		this.play = play;
	}
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	} 
	
	
}
