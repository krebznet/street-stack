package com.dunkware.trade.service.beach.protocol.broker;

import com.dunkware.common.util.json.DJson;

public class AddBrokerResp {
	
	private boolean ok = true; 
	private String error = null;
	
	public static void main(String[] args) {
		AddBrokerResp resp = new AddBrokerResp();
		resp.setError("Connection Exception Host 13.23.132 Port 23 - Connection Timeout");
		resp.setOk(false);
		try {
			System.out.println(DJson.serializePretty(resp));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	} 
	
	

}
