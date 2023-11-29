package com.dunkware.trade.tick.api.consumer;

import java.time.LocalTime;

import com.dunkware.common.util.observable.ObservableBean;

public class TickConsumerBean extends ObservableBean  {

	private String name; 
	private int subscriptions; 
	private int tickCount;
	private LocalTime lastTick;
	private String topic;
	
	   
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSubscriptions() {
		return subscriptions;
	}
	public void setSubscriptions(int subscriptions) {
		this.subscriptions = subscriptions;
	}
	public int getTickCount() {
		return tickCount;
	}
	public void setTickCount(int tickCount) {
		this.tickCount = tickCount;
	}
	public LocalTime getLastTick() {
		return lastTick;
	}
	public void setLastTick(LocalTime lastTick) {
		this.lastTick = lastTick;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	} 
	
	
	
	
	
}
