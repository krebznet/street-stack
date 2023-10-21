package comm.dunkware.trade.service.beach.web.bot;

public class WebBotEntryStrategy {
	
	private WebBotEntryType type; // defines type to be Market,Limit or LimitChase 
	private Number timeout; // set for all types 
	private Number limitPercent; // set when type is limit or limit chase 
	private WebBotPriceType priceType;  // set when type is limit or limit chase 
	private Number limitChaseInterval; // set when type is limit chase  
	
	public WebBotEntryType getType() {
		return type;
	}
	public void setType(WebBotEntryType type) {
		this.type = type;
	}
	public Number getTimeout() {
		return timeout;
	}
	public void setTimeout(Number timeout) {
		this.timeout = timeout;
	}
	public Number getLimitPercent() {
		return limitPercent;
	}
	public void setLimitPercent(Number limitPercent) {
		this.limitPercent = limitPercent;
	}
	public WebBotPriceType getPriceType() {
		return priceType;
	}
	public void setPriceType(WebBotPriceType priceType) {
		this.priceType = priceType;
	}
	public Number getLimitChaseInterval() {
		return limitChaseInterval;
	}
	public void setLimitChaseInterval(Number limitChaseInterval) {
		this.limitChaseInterval = limitChaseInterval;
	}
	
	

}
