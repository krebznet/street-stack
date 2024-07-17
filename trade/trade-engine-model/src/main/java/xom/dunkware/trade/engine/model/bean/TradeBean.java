package com.dunkware.trade.engine.model.bean;
import java.beans.PropertyChangeSupport;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import com.dunkware.utils.core.eventlogger.EventLog;
import com.dunkware.utils.core.observable.ObservableBean;

public class TradeBean extends ObservableBean {
    private String source;
    private String key;
    private String ticker;
    private String tickerType;
    private String status;
    private LocalDateTime openingTimestamp;
    private LocalDateTime openTimestamp;
    private LocalDateTime closingTimestamp;
    private LocalDateTime closedTimestamp;
    private Duration tradeDuration;
    private Duration openingDuration;
    private Duration closingDuration;
    private int allocatedShares;
    private double allocatedCapital;
    private double tradeCapital;
    private int tradeSize;
    private double avgEntryPrice;
    private double avgExitPrice;
    private double exitCommision;
    private double entryCommision;
    private String tradeSide;
    private double tradeCommission;
    private double lastPrice;
    private List<TradeOrderBean> orders;
    private List<EventLog> eventLogs;
    private List<TradeErrorBean> errors;
    private PropertyChangeSupport support;

    // No-arg constructor
    public TradeBean() {
    }

    // All-args constructor
    public TradeBean(String source, String key, String ticker, String tickerType, String status,
                     LocalDateTime openingTimestamp, LocalDateTime openTimestamp, LocalDateTime closingTimestamp,
                     LocalDateTime closedTimestamp, Duration tradeDuration, Duration openingDuration, Duration closingDuration,
                     int allocatedShares, double allocatedCapital, double tradeCapital, int tradeSize, double avgEntryPrice,
                     double avgExitPrice, double exitCommision, double entryCommision, String tradeSide, double tradeCommission,
                     double lastPrice, List<TradeOrderBean> orders, List<EventLog> eventLogs, List<TradeErrorBean> errors,
                     PropertyChangeSupport support) {
        this.source = source;
        this.key = key;
        this.ticker = ticker;
        this.tickerType = tickerType;
        this.status = status;
        this.openingTimestamp = openingTimestamp;
        this.openTimestamp = openTimestamp;
        this.closingTimestamp = closingTimestamp;
        this.closedTimestamp = closedTimestamp;
        this.tradeDuration = tradeDuration;
        this.openingDuration = openingDuration;
        this.closingDuration = closingDuration;
        this.allocatedShares = allocatedShares;
        this.allocatedCapital = allocatedCapital;
        this.tradeCapital = tradeCapital;
        this.tradeSize = tradeSize;
        this.avgEntryPrice = avgEntryPrice;
        this.avgExitPrice = avgExitPrice;
        this.exitCommision = exitCommision;
        this.entryCommision = entryCommision;
        this.tradeSide = tradeSide;
        this.tradeCommission = tradeCommission;
        this.lastPrice = lastPrice;
        this.orders = orders;
        this.eventLogs = eventLogs;
        this.errors = errors;
        this.support = support;
    }

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public String getTickerType() {
		return tickerType;
	}

	public void setTickerType(String tickerType) {
		this.tickerType = tickerType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getOpeningTimestamp() {
		return openingTimestamp;
	}

	public void setOpeningTimestamp(LocalDateTime openingTimestamp) {
		this.openingTimestamp = openingTimestamp;
	}

	public LocalDateTime getOpenTimestamp() {
		return openTimestamp;
	}

	public void setOpenTimestamp(LocalDateTime openTimestamp) {
		this.openTimestamp = openTimestamp;
	}

	public LocalDateTime getClosingTimestamp() {
		return closingTimestamp;
	}

	public void setClosingTimestamp(LocalDateTime closingTimestamp) {
		this.closingTimestamp = closingTimestamp;
	}

	public LocalDateTime getClosedTimestamp() {
		return closedTimestamp;
	}

	public void setClosedTimestamp(LocalDateTime closedTimestamp) {
		this.closedTimestamp = closedTimestamp;
	}

	public Duration getTradeDuration() {
		return tradeDuration;
	}

	public void setTradeDuration(Duration tradeDuration) {
		this.tradeDuration = tradeDuration;
	}

	public Duration getOpeningDuration() {
		return openingDuration;
	}

	public void setOpeningDuration(Duration openingDuration) {
		this.openingDuration = openingDuration;
	}

	public Duration getClosingDuration() {
		return closingDuration;
	}

	public void setClosingDuration(Duration closingDuration) {
		this.closingDuration = closingDuration;
	}

	public int getAllocatedShares() {
		return allocatedShares;
	}

	public void setAllocatedShares(int allocatedShares) {
		this.allocatedShares = allocatedShares;
	}

	public double getAllocatedCapital() {
		return allocatedCapital;
	}

	public void setAllocatedCapital(double allocatedCapital) {
		this.allocatedCapital = allocatedCapital;
	}

	public double getTradeCapital() {
		return tradeCapital;
	}

	public void setTradeCapital(double tradeCapital) {
		this.tradeCapital = tradeCapital;
	}

	public int getTradeSize() {
		return tradeSize;
	}

	public void setTradeSize(int tradeSize) {
		this.tradeSize = tradeSize;
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

	public double getExitCommision() {
		return exitCommision;
	}

	public void setExitCommision(double exitCommision) {
		this.exitCommision = exitCommision;
	}

	public double getEntryCommision() {
		return entryCommision;
	}

	public void setEntryCommision(double entryCommision) {
		this.entryCommision = entryCommision;
	}

	public String getTradeSide() {
		return tradeSide;
	}

	public void setTradeSide(String tradeSide) {
		this.tradeSide = tradeSide;
	}

	public double getTradeCommission() {
		return tradeCommission;
	}

	public void setTradeCommission(double tradeCommission) {
		this.tradeCommission = tradeCommission;
	}

	public double getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public List<TradeOrderBean> getOrders() {
		return orders;
	}

	public void setOrders(List<TradeOrderBean> orders) {
		this.orders = orders;
	}

	public List<EventLog> getEventLogs() {
		return eventLogs;
	}

	public void setEventLogs(List<EventLog> eventLogs) {
		this.eventLogs = eventLogs;
	}

	public List<TradeErrorBean> getErrors() {
		return errors;
	}

	public void setErrors(List<TradeErrorBean> errors) {
		this.errors = errors;
	}

	public PropertyChangeSupport getSupport() {
		return support;
	}

	public void setSupport(PropertyChangeSupport support) {
		this.support = support;
	}
    
    

    // Getters and setters for all fields
    // (omitted for brevity)
}
