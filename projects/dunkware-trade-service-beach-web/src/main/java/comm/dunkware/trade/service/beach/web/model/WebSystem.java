package comm.dunkware.trade.service.beach.web.model;

public class WebSystem {
	
	private long id; 
	private String name; 
	private String acccount; 
	private String status; 
	private int activeOrders; 
	private int activeTrades; 
	private double activeCapital; 
	private int tradeCount; 
	private double capitalTraded; 
	private double  gainLossPercent; 
	private double gainLossAmount;
	
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
	public String getAcccount() {
		return acccount;
	}
	public void setAcccount(String acccount) {
		this.acccount = acccount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getActiveOrders() {
		return activeOrders;
	}
	public void setActiveOrders(int activeOrders) {
		this.activeOrders = activeOrders;
	}
	public int getActiveTrades() {
		return activeTrades;
	}
	public void setActiveTrades(int activeTrades) {
		this.activeTrades = activeTrades;
	}
	public double getActiveCapital() {
		return activeCapital;
	}
	public void setActiveCapital(double activeCapital) {
		this.activeCapital = activeCapital;
	}
	public int getTradeCount() {
		return tradeCount;
	}
	public void setTradeCount(int tradeCount) {
		this.tradeCount = tradeCount;
	}
	public double getCapitalTraded() {
		return capitalTraded;
	}
	public void setCapitalTraded(double capitalTraded) {
		this.capitalTraded = capitalTraded;
	}
	public double getGainLossPercent() {
		return gainLossPercent;
	}
	public void setGainLossPercent(double gainLossPercent) {
		this.gainLossPercent = gainLossPercent;
	}
	public double getGainLossAmount() {
		return gainLossAmount;
	}
	public void setGainLossAmount(double gainLossAmount) {
		this.gainLossAmount = gainLossAmount;
	}
	
	
	
	
	
	

}
