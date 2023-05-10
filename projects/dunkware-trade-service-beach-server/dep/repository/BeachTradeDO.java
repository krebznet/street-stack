package com.dunkware.trade.service.beach.server.repository;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.dunkware.trade.sdk.core.model.trade.TradeStatus;
import com.dunkware.trade.tick.model.ticker.TradeTickerType;


@Entity(name = "beach_trade")
public class BeachTradeDO  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne()
	private BeachBotDO bot;
	
	private String play; 
	
	private LocalDateTime openingTime;
	private LocalDateTime openTime; 
	
	private LocalDateTime closingTime; 
	private LocalDateTime closedTime; 
	
	private double realizedPL; 
	private double totalCommission; 
	private double avgEntryPrice; 
	private double avgExitPrice; 
	
	private int allocatedSize; 
	private double allocatedCapital; 
	
	private int filledSize; 
	
	
	private String exception; 
	
	@Enumerated(EnumType.STRING)
	private TradeStatus status;
	
	private LocalDateTime lastUpdate;
	
	private String tickerSymbol;
	
	@Enumerated(EnumType.STRING)
	private TradeTickerType tickerType; 
	
	private String entryType; 
	private String exitType;
	
	@OneToOne(cascade = CascadeType.ALL)
	private BeachEntryDO entry; 
	
	@OneToOne(cascade = CascadeType.ALL)
	private BeachExitDO exit; 
	
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
	
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public TradeStatus getStatus() {
		return status;
	}
	public void setStatus(TradeStatus status) {
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
	public String getEntryType() {
		return entryType;
	}
	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}
	public String getExitType() {
		return exitType;
	}
	public void setExitType(String exitType) {
		this.exitType = exitType;
	}
	public BeachEntryDO getEntry() {
		return entry;
	}
	public void setEntry(BeachEntryDO entry) {
		this.entry = entry;
	}
	public BeachExitDO getExit() {
		return exit;
	}
	public void setExit(BeachExitDO exit) {
		this.exit = exit;
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
	public BeachBotDO getBot() {
		return bot;
	}
	public void setBot(BeachBotDO bot) {
		this.bot = bot;
	}
	public String getPlay() {
		return play;
	}
	public void setPlay(String play) {
		this.play = play;
	} 
	
	
	
	
	
	
	
	
	
	
	
	
}
// pool/add