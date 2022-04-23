package com.dunkware.trade.service.stream.server.session.repository;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.dunkware.trade.tick.model.ticker.TradeTickerType;

@Entity(name = "stream_session_ticker")
public class StreamSessionTickerDO {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	private String tickerIdentifer;
	private long tickerId; 
	@Enumerated(EnumType.STRING	)
	private TradeTickerType type; 
	private boolean validated = false; 
	
	
	@ManyToOne
	private StreamSessionDO session;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public StreamSessionDO getSession() {
		return session;
	}
	public void setSession(StreamSessionDO session) {
		this.session = session;
	}
	public long getTickerId() {
		return tickerId;
	}
	public void setTickerId(long tickerId) {
		this.tickerId = tickerId;
	}
	public TradeTickerType getType() {
		return type;
	}
	public void setType(TradeTickerType type) {
		this.type = type;
	}
	public boolean isValidated() {
		return validated;
	}
	public void setValidated(boolean validated) {
		this.validated = validated;
	}
	public String getTickerIdentifer() {
		return tickerIdentifer;
	}
	public void setTickerIdentifer(String tickerIdentifer) {
		this.tickerIdentifer = tickerIdentifer;
	}
	
	

}
