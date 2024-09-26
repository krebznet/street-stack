package com.dunkware.trade.tick.service.server.feed.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "ticker_feed_provider")
public class FeedProviderDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	private String identifier; 
	@Column()
	private String json; 
	private int subscriptionLimit;
	
	public Long getId() {
		return id;
	} 
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public int getSubscriptionLimit() {
		return subscriptionLimit;
	}
	public void setSubscriptionLimit(int subscriptionLimit) {
		this.subscriptionLimit = subscriptionLimit;
	} 
	
	
	
	
}
