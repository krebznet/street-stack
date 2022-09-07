package comm.dunkware.trade.service.beach.web.model;

public class WebAccount {

	private long id; 
	private String name; 
	private String broker; 
	private String status;
	private int activeOrders;
	private int activeSystems; 
	private int activeTrades;
	private double activeCapital; 
	private double gainLossPercent; 
	private double gainLossAmount;
	private double capitalTraded; 
	private int tradeCount; 
	
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
	public String getBroker() {
		return broker;
	}
	public void setBroker(String broker) {
		this.broker = broker;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Number getActiveOrders() {
		return activeOrders;
	}
	public int getActiveSystems() {
		return activeSystems;
	}
	public void setActiveSystems(int activeSystems) {
		this.activeSystems = activeSystems;
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
	public double getGainLossPercent() {
		return gainLossPercent;
	}
	public void setGainLossPercent(double gainLossPercent) {
		this.gainLossPercent = gainLossPercent;
	}
	public double getCapitalTraded() {
		return capitalTraded;
	}
	public void setCapitalTraded(double capitalTraded) {
		this.capitalTraded = capitalTraded;
	}
	public int getTradeCount() {
		return tradeCount;
	}
	public void setTradeCount(int tradeCount) {
		this.tradeCount = tradeCount;
	}
	public void setActiveOrders(int activeOrders) {
		this.activeOrders = activeOrders;
	}
	public double getGainLossAmount() {
		return gainLossAmount;
	}
	public void setGainLossAmount(double gainLossAmount) {
		this.gainLossAmount = gainLossAmount;
	}
	
	
	
	

	
	

	
}
