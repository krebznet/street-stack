package com.dunkware.trade.service.beach.server.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dunkware.trade.sdk.core.model.order.OrderAction;
import com.dunkware.trade.sdk.core.model.order.OrderKind;
import com.dunkware.trade.sdk.core.model.order.OrderStatus;
import com.dunkware.trade.tick.model.ticker.TradeTickerType;

@Entity(name = "BeachOrderEnt")
@Table(name = "beach_order")
public class BeachOrderEnt {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	@ManyToOne()
	private BeachAccountEnt account; 
	
	@ManyToOne()
	private BeachBrokerEnt broker;
	
	@ManyToOne
	private BeachTradeEnt trade;
	
	@ManyToOne
	private BeachPlayEnt play; 
	
	private String source; 

	private int orderId; 
	
	private TradeTickerType tickerType; 
	private String symbol; 
	
	private LocalDateTime createTime;
	
	private LocalDateTime openTime; 
	private LocalDateTime fillTime;
	private LocalDateTime cancelTime; 
	private LocalDateTime cancelRequestTime; 
	private LocalDateTime submitTime;  
	private LocalDateTime pendingSubmitTime; 
	private LocalDateTime presubmitTime; 
	
	private boolean open = false;
	
	private OrderStatus lastStatus; 
	private LocalDateTime lastUpdate; 
	
	@Enumerated(EnumType.STRING)
	private OrderKind kind; 
	@Enumerated(EnumType.STRING)
	private OrderAction action; 
	
	private int size; 
	private int filled;
	
	private double commission; 

	private String log; 
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private List<BeachOrderExecEnt> execs = new ArrayList<BeachOrderExecEnt>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	

	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public TradeTickerType getTickerType() {
		return tickerType;
	}
	
	public void setTickerType(TradeTickerType tickerType) {
		this.tickerType = tickerType;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public LocalDateTime getSubmitTime() {
		return submitTime;
	}
	
	public void setSubmitTime(LocalDateTime submitTime) {
		this.submitTime = submitTime;
	}
	
	public LocalDateTime getFillTime() {
		return fillTime;
	}
	
	public void setFillTime(LocalDateTime fillTime) {
		this.fillTime = fillTime;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getFilled() {
		return filled;
	}
	
	public void setFilled(int filled) {
		this.filled = filled;
	}
	

	public LocalDateTime getCancelTime() {
		return cancelTime;
	}
	
	public void setCancelTime(LocalDateTime cancelTime) {
		this.cancelTime = cancelTime;
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public void setOpen(boolean open) {
		this.open = open;
	}
	
	public OrderStatus getLastStatus() {
		return lastStatus;
	}
	
	public LocalDateTime getOpenTime() {
		return openTime;
	}
	public void setOpenTime(LocalDateTime openTime) {
		this.openTime = openTime;
	}
	// 
	public LocalDateTime getCancelRequestTime() {
		return cancelRequestTime;
	}
	public void setCancelRequestTime(LocalDateTime cancelRequestTime) {
		this.cancelRequestTime = cancelRequestTime;
	}
	public LocalDateTime getPendingSubmitTime() {
		return pendingSubmitTime;
	}
	public void setPendingSubmitTime(LocalDateTime pendingSubmitTime) {
		this.pendingSubmitTime = pendingSubmitTime;
	}
	public LocalDateTime getPresubmitTime() {
		return presubmitTime;
	}
	public void setPresubmitTime(LocalDateTime presubmitTime) {
		this.presubmitTime = presubmitTime;
	}
	public void setLastStatus(OrderStatus lastStatus) {
		this.lastStatus = lastStatus;
	}
	
	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}
	
	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	public OrderKind getKind() {
		return kind;
	}
	
	public void setKind(OrderKind kind) {
		this.kind = kind;
	}
	
	public OrderAction getAction() {
		return action;
	}
	
	public void setAction(OrderAction action) {
		this.action = action;
	}
	public double getCommission() {
		return commission;
	}
	public void setCommission(double commission) {
		this.commission = commission;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
	public BeachAccountEnt getAccount() {
		return account;
	}
	public void setAccount(BeachAccountEnt account) {
		this.account = account;
	}
	public BeachBrokerEnt getBroker() {
		return broker;
	}
	public void setBroker(BeachBrokerEnt broker) {
		this.broker = broker;
	}
	
	public BeachTradeEnt getTrade() {
		return trade;
	}
	public void setTrade(BeachTradeEnt trade) {
		this.trade = trade;
	}
	public List<BeachOrderExecEnt> getExecs() {
		return execs;
	}
	public void setExecs(List<BeachOrderExecEnt> execs) {
		this.execs = execs;
	}
	public BeachPlayEnt getPlay() {
		return play;
	}
	public void setPlay(BeachPlayEnt play) {
		this.play = play;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	

	
	
	

	
	
	

	
	
	


}
