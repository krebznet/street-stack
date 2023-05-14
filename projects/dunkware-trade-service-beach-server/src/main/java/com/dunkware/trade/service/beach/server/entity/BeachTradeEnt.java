package com.dunkware.trade.service.beach.server.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dunkware.trade.service.beach.server.runtime.BeachTradeStatus;
import com.dunkware.trade.tick.model.ticker.TradeTickerType;

@Entity(name = "BeachTradeEnt")
@Table(name = "beach_trade")
public class BeachTradeEnt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne()
	private BeachPlayEnt play;
	
	@ManyToOne()
	private BeachAccountEnt account; 
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "trade_id")
	private List<BeachTradeLogEnt> logs = new ArrayList<BeachTradeLogEnt>();
	
	@ManyToOne()
	private BeachBrokerEnt broker; 
	
	private LocalDateTime openingTime;
	private LocalDateTime openTime; 
	
	private LocalDateTime closingTime; 
	private LocalDateTime closedTime; 
	
	// halted status?
	
	private double realizedPL; 
	private double totalCommission; 
	private double avgEntryPrice; 
	private double avgExitPrice; 
	
	private int allocatedSize; 
	private double allocatedCapital; 
	
	private int filledSize; 
	
	private double entryCommission; 
	private double exitCommission; 
	private double commission; 
	
	private String entryException; 
	private String exitException;
	
	@Enumerated(EnumType.STRING)
	private BeachTradeStatus status;
	
	private LocalDateTime lastUpdate;
	
	private String tickerSymbol;
	private String exitTrigger;
	
	private String identifier; 
	
	
	@Enumerated(EnumType.STRING)
	private TradeTickerType tickerType; 
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public LocalDateTime getClosingTime() {
		return closingTime;
	}
	public void setClosingTime(LocalDateTime closingTime) {
		this.closingTime = closingTime;
	}
	public LocalDateTime getClosedTime() {
		return closedTime;
	}
	public void setClosedTime(LocalDateTime closedTime) {
		this.closedTime = closedTime;
	}
	public double getRealizedPL() {
		return realizedPL;
	}
	public void setRealizedPL(double realizedPL) {
		this.realizedPL = realizedPL;
	}
	public double getTotalCommission() {
		return totalCommission;
	}
	public void setTotalCommission(double totalCommission) {
		this.totalCommission = totalCommission;
	}
	public double getAvgEntryPrice() {
		return avgEntryPrice;
	}
	public void setAvgEntryPrice(double avgEntryPrice) {
		this.avgEntryPrice = avgEntryPrice;
	}
	public double getAvgExitPrice() {
		return avgExitPrice;
	}
	public void setAvgExitPrice(double avgExitPrice) {
		this.avgExitPrice = avgExitPrice;
	}
	
	public String getEntryException() {
		return entryException;
	}
	public void setEntryException(String entryException) {
		this.entryException = entryException;
	}
	public String getExitException() {
		return exitException;
	}
	public void setExitException(String exitException) {
		this.exitException = exitException;
	}
	
	public BeachTradeStatus getStatus() {
		return status;
	}
	public void setStatus(BeachTradeStatus status) {
		this.status = status;
	}
	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getTickerSymbol() {
		return tickerSymbol;
	}
	public void setTickerSymbol(String tickerSymbol) {
		this.tickerSymbol = tickerSymbol;
	}
	public TradeTickerType getTickerType() {
		return tickerType;
	}
	public void setTickerType(TradeTickerType tickerType) {
		this.tickerType = tickerType;
	}
	
	public int getAllocatedSize() {
		return allocatedSize;
	}
	public void setAllocatedSize(int allocatedSize) {
		this.allocatedSize = allocatedSize;
	}
	public double getAllocatedCapital() {
		return allocatedCapital;
	}
	public void setAllocatedCapital(double allocatedCapital) {
		this.allocatedCapital = allocatedCapital;
	}
	public int getFilledSize() {
		return filledSize;
	}
	public void setFilledSize(int filledSize) {
		this.filledSize = filledSize;
	}
	public BeachPlayEnt getPlay() {
		return play;
	}
	public void setPlay(BeachPlayEnt play) {
		this.play = play;
	}
	public double getEntryCommission() {
		return entryCommission;
	}
	public void setEntryCommission(double entryCommission) {
		this.entryCommission = entryCommission;
	}
	public double getExitCommission() {
		return exitCommission;
	}
	public void setExitCommission(double exitCommission) {
		this.exitCommission = exitCommission;
	}
	public double getCommission() {
		return commission;
	}
	public void setCommission(double commission) {
		this.commission = commission;
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
	public String getExitTrigger() {
		return exitTrigger;
	}
	public void setExitTrigger(String exitTrigger) {
		this.exitTrigger = exitTrigger;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
