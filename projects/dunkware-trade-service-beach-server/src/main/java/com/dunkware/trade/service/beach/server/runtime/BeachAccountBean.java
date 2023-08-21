package com.dunkware.trade.service.beach.server.runtime;

import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.observable.ObservableBean;

public class BeachAccountBean extends ObservableBean {

	private String name; 
	private String broker;
	private int brokerId;
	private double cashBalance; 
	private double buyingPower;
	private double realizedPL; 
	private double unrealizedPL; 
	private double marketValue;
	private int activeTrades;
	
	public static void main(String[] args) {
		BeachAccountBean b = new BeachAccountBean();
		b.setName("Account1");
		b.setBroker("Paper 1");
		b.setCashBalance(43434.34);
		b.setMarketValue(434334.34);
		b.setUnrealizedPL(-323.32);
		b.setRealizedPL(2323.23);
		b.setBuyingPower(500.343);
		b.setBrokerId(1);
		b.setId(4);
		try {
			System.out.println(DJson.serializePretty(b));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	private long id; 
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBroker() {
		return broker;
	}
	public void setBroker(String broker) {
		this.broker = broker;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getCashBalance() {
		return cashBalance;
	}
	
	public int getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(int brokerId) {
		this.brokerId = brokerId;
	}
	public double getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(double marketValue) {
		this.marketValue = marketValue;
	}
	public void setCashBalance(double cashBalance) {
		this.cashBalance = cashBalance;
	}
	public double getBuyingPower() {
		return buyingPower;
	}
	public void setBuyingPower(double buyingPower) {
		this.buyingPower = buyingPower;
	}
	public double getRealizedPL() {
		return realizedPL;
	}
	public void setRealizedPL(double realizedPL) {
		this.realizedPL = realizedPL;
	}
	public double getUnrealizedPL() {
		return unrealizedPL;
	}
	public void setUnrealizedPL(double unrealizedPL) {
		this.unrealizedPL = unrealizedPL;
	}
	public int getActiveTrades() {
		return activeTrades;
	}
	public void setActiveTrades(int activeTrades) {
		this.activeTrades = activeTrades;
	} 
	
	
	
	
	
	
	
	
	
}
