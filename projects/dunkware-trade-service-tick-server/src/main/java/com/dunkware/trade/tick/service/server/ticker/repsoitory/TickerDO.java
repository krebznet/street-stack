package com.dunkware.trade.tick.service.server.ticker.repsoitory;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
@Entity(name = "ticker_ticker")

@NamedQuery(name="TickerDO.All",
query="SELECT e FROM ticker_ticker e")          
public class TickerDO {
	
	public static final int STATUS_PENDING = 0;
	public static final int STATUS_GOOD = 1; 
	public static final int STATUS_BAD = 2;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	private String symbol;
	@Column(name = "SecurityName")
	private String securityName;
	private String marketCategory;
	@Transient()
	private String testIssue; 
	@Transient()
	private String financialStatus;
	@Transient()
	private String roundLotSize;
	@Transient()
	private String etf;
	@Transient()
	private String nextShares;
	@Column(name = "AvgVol")
	private Long averageVolume;
	@Column(name = "MarketCap" )
	private Long marketCap;
	@Column(name = "LastPrice" )
	private Double price;
	@Column(name = "Eps")
	private Double eps;
	@Column(name= "Status")
	private int status;
	@Column(name = "LastUpdate")
	private Date lastUpdate;
	@Column(name = "UpdateError")
	private String updateError; 
	
	
	public TickerDO() { 
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	@Column(name = "Symbol")
	public String getSymbol() {
		return symbol;
	}


	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}


	public String getSecurityName() {
		return securityName;
	}


	public void setSecurityName(String securityName) {
		this.securityName = securityName;
	}


	public String getMarketCategory() {
		return marketCategory;
	}


	public void setMarketCategory(String marketCategory) {
		this.marketCategory = marketCategory;
	}


	public String getTestIssue() {
		return testIssue;
	}


	public void setTestIssue(String testIssue) {
		this.testIssue = testIssue;
	}


	public String getFinancialStatus() {
		return financialStatus;
	}


	public void setFinancialStatus(String financialStatus) {
		this.financialStatus = financialStatus;
	}


	public String getRoundLotSize() {
		return roundLotSize;
	}


	public void setRoundLotSize(String roundLotSize) {
		this.roundLotSize = roundLotSize;
	}


	public String getEtf() {
		return etf;
	}


	public void setEtf(String etf) {
		this.etf = etf;
	}


	public String getNextShares() {
		return nextShares;
	}


	public void setNextShares(String nextShares) {
		this.nextShares = nextShares;
	}


	public Long getAverageVolume() {
		return averageVolume;
	}


	public void setAverageVolume(Long averageVolume) {
		this.averageVolume = averageVolume;
	}


	public Long getMarketCap() {
		return marketCap;
	}


	public void setMarketCap(Long marketCap) {
		this.marketCap = marketCap;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public Date getLastUpdate() {
		return lastUpdate;
	}


	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


	public String getUpdateError() {
		return updateError;
	}


	public void setUpdateError(String updateError) {
		this.updateError = updateError;
	}


	public Double getEps() {
		return eps;
	}


	public void setEps(Double eps) {
		this.eps = eps;
	}


	public static TradeTickerSpec toTicker(TickerDO obj) { 
		TradeTickerSpec ticker = new TradeTickerSpec();
		ticker.setAvgVolume(obj.getAverageVolume());
		ticker.setSymbol(obj.getSymbol());
		Long fuck = new Long(obj.getId());
		ticker.setId(fuck.intValue());
		return ticker;
		
	}
	
	
	
	
	
	
	
	
	
	

}
