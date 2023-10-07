package com.dunkware.trade.tick.service.server.ticker.repsoitory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
