package comm.dunkware.trade.service.beach.web.bot;

import java.util.ArrayList;
import java.util.List;

public class WebBot {
	
	private Number allocatedCapital; 
	private List<WebBotPlay> plays = new ArrayList<WebBotPlay>();
	private boolean enabled; 
	private Number activeTradeLimit; 
	private Number tradeThrottle;
	
	public Number getAllocatedCapital() {
		return allocatedCapital;
	}
	public void setAllocatedCapital(Number allocatedCapital) {
		this.allocatedCapital = allocatedCapital;
	}
	public List<WebBotPlay> getPlays() {
		return plays;
	}
	public void setPlays(List<WebBotPlay> plays) {
		this.plays = plays;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public Number getActiveTradeLimit() {
		return activeTradeLimit;
	}
	public void setActiveTradeLimit(Number activeTradeLimit) {
		this.activeTradeLimit = activeTradeLimit;
	}
	public Number getTradeThrottle() {
		return tradeThrottle;
	}
	public void setTradeThrottle(Number tradeThrottle) {
		this.tradeThrottle = tradeThrottle;
	} 
	
	
	

}
