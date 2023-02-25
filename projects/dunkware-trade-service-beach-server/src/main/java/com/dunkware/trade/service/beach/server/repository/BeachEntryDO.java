package com.dunkware.trade.service.beach.server.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.dunkware.trade.sdk.core.model.trade.EntryStatus;

@Entity(name = "BeachEntryDO")
@Table(name = "beach_trade_entry")
public class BeachEntryDO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	@Column(length = 2000)
	private String type;
	
	@OneToOne
	@JoinColumn(name = "trade_id")
	private BeachTradeDO trade;

	@OneToMany()
	@JoinTable(joinColumns=@JoinColumn(name="entry_id"),
			inverseJoinColumns=@JoinColumn(name="order_id"))
	private List<BeachOrderDO> orders = new ArrayList<BeachOrderDO>();

	@Enumerated(EnumType.STRING)
	private EntryStatus status; 
	
	private LocalDateTime openingTime; 
	private LocalDateTime openTime; 
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BeachTradeDO getTrade() {
		return trade;
	}

	public void setTrade(BeachTradeDO trade) {
		this.trade = trade;
	}

	public List<BeachOrderDO> getOrders() {
		return orders;
	}

	public void setOrders(List<BeachOrderDO> orders) {
		this.orders = orders;
	}

	public EntryStatus getStatus() {
		return status;
	}

	public void setStatus(EntryStatus status) {
		this.status = status;
	}

	public LocalDateTime getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(LocalDateTime openingTime) {
		this.openingTime = openingTime;
	}

	public LocalDateTime getOpenTime() {
		return openTime;
	}

	public void setOpenTime(LocalDateTime openTime) {
		this.openTime = openTime;
	}
	
	
	

	

}
