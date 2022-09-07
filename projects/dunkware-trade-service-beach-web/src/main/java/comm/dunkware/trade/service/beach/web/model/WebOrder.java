package comm.dunkware.trade.service.beach.web.model;

public class WebOrder {

	private String side; // LONG | SHORT 
	private String status; // PENDING | FILLED ETC
	private long orderId; // ORDER ID 
	private long tradeId; // TRADE ID 
	private String system; // System Name
	private String account;  // Account
	private String trade; // trade name
	private int size; // order size
	private int filled;  // shares filled
	private int pending; // shares pending 
	private double commission; 
	private String type; // STOP | TRAILING | MARKET
	private double stopPrice; // if stop order filled
	private double trailingPercent; // if trailing
	private String error; // if status is error error 
	
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public long getTradeId() {
		return tradeId;
	}
	public void setTradeId(long tradeId) {
		this.tradeId = tradeId;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
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
	public int getPending() {
		return pending;
	}
	public void setPending(int pending) {
		this.pending = pending;
	}
	public double getCommission() {
		return commission;
	}
	public void setCommission(double commission) {
		this.commission = commission;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	
	public String getTrade() {
		return trade;
	}
	public void setTrade(String trade) {
		this.trade = trade;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getStopPrice() {
		return stopPrice;
	}
	public void setStopPrice(double stopPrice) {
		this.stopPrice = stopPrice;
	}
	public double getTrailingPercent() {
		return trailingPercent;
	}
	public void setTrailingPercent(double trailingPercent) {
		this.trailingPercent = trailingPercent;
	} 
	
	
	
	 
	
}
