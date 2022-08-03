package comm.dunkware.trade.service.beach.web.bot;

public class WebBotExitTrigger {
	
	private String name; 
	private WebBotExitTriggerType type; // always set to define what type of exit trigger we defined. 
	private Number stopPercent;  // set if exit trigger type is stop 
	private WebBotPriceType stopPriceType; // set if exit trigger type is stop 
	private Number trailingPercent; // set if exit trigger type is trailing stop 
	private WebBotPriceType trailingPriceType; // set if exit trigger type is trailing stop  
	private Number timer; // set if exit trigger type is timer - defined as seconds 
	private Number unrealizedPL; 
	private WebBotSimpleTime exitTime; // set if exit trigger type is Time 
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public WebBotExitTriggerType getType() {
		return type;
	}
	public void setType(WebBotExitTriggerType type) {
		this.type = type;
	}
	public Number getStopPercent() {
		return stopPercent;
	}
	public void setStopPercent(Number stopPercent) {
		this.stopPercent = stopPercent;
	}
	public WebBotPriceType getStopPriceType() {
		return stopPriceType;
	}
	public void setStopPriceType(WebBotPriceType stopPriceType) {
		this.stopPriceType = stopPriceType;
	}
	public Number getTrailingPercent() {
		return trailingPercent;
	}
	public void setTrailingPercent(Number trailingPercent) {
		this.trailingPercent = trailingPercent;
	}
	public WebBotPriceType getTrailingPriceType() {
		return trailingPriceType;
	}
	public void setTrailingPriceType(WebBotPriceType trailingPriceType) {
		this.trailingPriceType = trailingPriceType;
	}
	public Number getTimer() {
		return timer;
	}
	public void setTimer(Number timer) {
		this.timer = timer;
	}
	public Number getUnrealizedPL() {
		return unrealizedPL;
	}
	public void setUnrealizedPL(Number unrealizedPL) {
		this.unrealizedPL = unrealizedPL;
	}
	public WebBotSimpleTime getExitTime() {
		return exitTime;
	}
	public void setExitTime(WebBotSimpleTime exitTime) {
		this.exitTime = exitTime;
	}
	
	
	
	

}
