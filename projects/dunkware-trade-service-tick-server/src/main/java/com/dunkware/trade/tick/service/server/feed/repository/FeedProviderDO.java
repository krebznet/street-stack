package com.dunkware.trade.tick.service.server.feed.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity(name = "feed_provider")
@NamedQueries({
@NamedQuery(name="FeedProviderDO.All",
    query="SELECT e FROM feed_provider e")          
})
public class FeedProviderDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	private String identifier; 
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
