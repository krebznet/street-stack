package com.dunkware.trade.tick.service.server.ticker.repsoitory;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "ticker_list_ticker")
public class TickerListTickerDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	
	@ManyToOne
	@JoinColumn(name = "ticker_id")
	private TickerDO ticker;
	
	@ManyToOne
	private TickerListDO list;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TickerDO getTicker() {
		return ticker;
	}

	public void setTicker(TickerDO ticker) {
		this.ticker = ticker;
	}
	
	
	
}
