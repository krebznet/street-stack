package com.dunkware.trade.tick.service.server.ticker.repsoitory;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.dunkware.trade.tick.service.protocol.ticker.spec.TradeTickerListSpec;

@Entity(name = "ticker_list")
public class TickerListDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	private String name; 
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "list_id")
	private List<TickerListTickerDO> tickers = new ArrayList<TickerListTickerDO>();
	
	private int size;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<TickerListTickerDO> getTickers() {
		return tickers;
	}
	
	public void setTickers(List<TickerListTickerDO> tickers) {
		this.tickers = tickers;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	} 
	
	
	public static TradeTickerListSpec toTickerList(TickerListDO obj) { 
		TradeTickerListSpec list = new TradeTickerListSpec();
		list.setName(obj.getName());
		for (TickerListTickerDO tickerDo : obj.getTickers()) {
			list.getTickers().add(TickerDO.toTicker(tickerDo.getTicker()));
		}
		return list;
	}
	
	
	
}
