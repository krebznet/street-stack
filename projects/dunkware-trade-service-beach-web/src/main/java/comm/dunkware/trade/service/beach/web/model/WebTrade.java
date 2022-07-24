package comm.dunkware.trade.service.beach.web.model;

public class WebTrade {

	private String status; 
	private String symbol; 
	private Number marketValue; 
	private String bot; 
	private long id; 
	private Number realizedPL; 
	private Number unrealizedPL; 
	private Number commission;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Number getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(Number marketValue) {
		this.marketValue = marketValue;
	}
	public String getBot() {
		return bot;
	}
	public void setBot(String bot) {
		this.bot = bot;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Number getRealizedPL() {
		return realizedPL;
	}
	public void setRealizedPL(Number realizedPL) {
		this.realizedPL = realizedPL;
	}
	public Number getUnrealizedPL() {
		return unrealizedPL;
	}
	public void setUnrealizedPL(Number unrealizedPL) {
		this.unrealizedPL = unrealizedPL;
	}
	public Number getCommission() {
		return commission;
	}
	public void setCommission(Number commission) {
		this.commission = commission;
	} 
	
	
	
}
