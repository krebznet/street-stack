package com.dunkware.trade.service.beach.server.trade.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "trade_pool")
public class BeachPoolDO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	

	private String identifier; 
	
	@ManyToOne()
	private BeachAccountDO account;
	
	private LocalDateTime created;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "pool_id")
	private List<BeachTradeDO> trades = new ArrayList<BeachTradeDO>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}


	public BeachAccountDO getAccount() {
		return account;
	}

	public void setAccount(BeachAccountDO account) {
		this.account = account;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public List<BeachTradeDO> getTrades() {
		return trades;
	}

	public void setTrades(List<BeachTradeDO> trades) {
		this.trades = trades;
	}

	
	

}
