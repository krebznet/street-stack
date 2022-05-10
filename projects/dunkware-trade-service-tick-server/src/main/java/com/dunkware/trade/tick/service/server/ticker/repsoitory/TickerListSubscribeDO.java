package com.dunkware.trade.tick.service.server.ticker.repsoitory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "ticker_list_subscribe")
public class TickerListSubscribeDO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	@ManyToOne
	private TickerListDO list;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TickerListDO getList() {
		return list;
	}

	public void setList(TickerListDO list) {
		this.list = list;
	}
	
	

}
