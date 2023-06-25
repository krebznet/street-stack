package com.dunkware.trade.service.beach.server.runtime;

import com.dunkware.common.util.databean.DataBean;
import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.uuid.DUUID;

public class BeachBrokerBean extends DataBean {

	private String status; 
	private String name;
	private String summary;
	private int accounts; 
	private long id; 
	
	public static void main(String[] args) {
		BeachTradeBean b = new BeachTradeBean();
		b.setId(1);
		b.setSymbol("AAPL");
		b.setStatus("Open");
		b.setUpl(2.23);
		b.setRpl(3.42);
		b.setAllocatedSize(234);
		b.setFilledSize(200);
		b.setIdent(b.getSymbol() + "_" + b.getId());
		b.setCloseTime("todo");
		b.setClosingTime("todo");
		b.setEntryCommission(DRandom.getRandom(3, 2392));
		b.setExitCommission(23.23);
		b.setOpeningTime("todo");
		b.setOpenTime("todo");
		b.setEntryValue(2323.23);
		b.setPlayId(34);
		b.setAccountId(4);
		b.setAccount("Paper Account1");
		b.setIdent("P1AAPL1");
		b.setActiveOrders(4);
		try {
			System.out.println(DJson.serializePretty(b));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public int getAccounts() {
		return accounts;
	}
	public void setAccounts(int accounts) {
		this.accounts = accounts;
	} 
	
	
	
	
	
	
}
