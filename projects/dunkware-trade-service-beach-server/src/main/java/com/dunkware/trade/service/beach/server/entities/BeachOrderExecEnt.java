package com.dunkware.trade.service.beach.server.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "BeachOrderExecEnt")
@Table(name = "beach_orderexec")
public class BeachOrderExecEnt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	private int brokerOrderId; 
	private double avgPrice; 
	private String execId; 
	private String side; 
	private double price; 
	private String time; 
	private String exchange; 
	
	
	@ManyToOne()
	private BeachOrderEnt order; 
	
	


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public int getBrokerOrderId() {
		return brokerOrderId;
	}


	public void setBrokerOrderId(int brokerOrderId) {
		this.brokerOrderId = brokerOrderId;
	}


	public double getAvgPrice() {
		return avgPrice;
	}


	public void setAvgPrice(double avgPrice) {
		this.avgPrice = avgPrice;
	}


	public String getExecId() {
		return execId;
	}


	public void setExecId(String execId) {
		this.execId = execId;
	}


	public String getSide() {
		return side;
	}


	public void setSide(String side) {
		this.side = side;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getExchange() {
		return exchange;
	}


	public void setExchange(String exchange) {
		this.exchange = exchange;
	}


	public BeachOrderEnt getOrder() {
		return order;
	}


	public void setOrder(BeachOrderEnt order) {
		this.order = order;
	}


	
	
	
	
}
