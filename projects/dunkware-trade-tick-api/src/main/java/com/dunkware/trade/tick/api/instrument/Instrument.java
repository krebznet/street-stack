package com.dunkware.trade.tick.api.instrument;

import java.time.LocalDateTime;

import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public interface Instrument {

	void setLast(double last);

	double getLast();

	void setAskPrice(double askPrice);

	double getAskPrice();

	void setAskSize(int askSize);

	int getAskSize();

	void setBidSize(int bidSize);

	int getBidSize();
	
	double getBidPrice();
	
	void setBidPrice(double bidPrice);
	
	void setOpen(double open);
	
	double getOpen();
	
	void setClose(double close);
	
	double getClose();
	
	void setHigh(double high);
	
	double getHigh();
	
	void setLow(double low);
	
	double getLow();
	
	void setVolume(int volume);
	
	int getVolume();
	
	void setTradeCount(int tradeCount);
	
	int getTradeCount();
	
	TradeTickerSpec getTicker();
	
	LocalDateTime getLastUpdate();
	
	void addListener(InstrumentListener listener);
	
	void removeListener(InstrumentListener listener);
}
