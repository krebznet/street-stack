package com.dunkware.trade.tick.service.server.ticker.repsoitory;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.trade.tick.service.protocol.ticker.spec.TradeTickerListSpec;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity(name = "ticker_list")
public class TickerListDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	private String name; 
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
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
