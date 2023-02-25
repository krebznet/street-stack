package comm.dunkware.trade.service.beach.web.bot;

public class WebBotPlay {
	
	private String name; 
	private WebBotTradeSide tradeSide; 
	private Number capital;
	private Number activeTradeLimit; 
	private Number activeSymbolLimit; 
	private Number symbolThrottle;
	private Number tradeThrottle; 
	private String signal;
	private String stream; // us_equity
	private WebBotEntryStrategy entryStrategy; 
	private WebBotExitStrategy exitStrategy;
	private Number rank; 
	private boolean rankEnabled;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public WebBotTradeSide getTradeSide() {
		return tradeSide;
	}
	public void setTradeSide(WebBotTradeSide tradeSide) {
		this.tradeSide = tradeSide;
	}
	public Number getCapital() {
		return capital;
	}
	public void setCapital(Number capital) {
		this.capital = capital;
	}
	public Number getActiveTradeLimit() {
		return activeTradeLimit;
	}
	public void setActiveTradeLimit(Number activeTradeLimit) {
		this.activeTradeLimit = activeTradeLimit;
	}
	public Number getActiveSymbolLimit() {
		return activeSymbolLimit;
	}
	public void setActiveSymbolLimit(Number activeSymbolLimit) {
		this.activeSymbolLimit = activeSymbolLimit;
	}
	public Number getSymbolThrottle() {
		return symbolThrottle;
	}
	public void setSymbolThrottle(Number symbolThrottle) {
		this.symbolThrottle = symbolThrottle;
	}
	public Number getTradeThrottle() {
		return tradeThrottle;
	}
	public void setTradeThrottle(Number tradeThrottle) {
		this.tradeThrottle = tradeThrottle;
	}
	
	public WebBotEntryStrategy getEntryStrategy() {
		return entryStrategy;
	}
	public void setEntryStrategy(WebBotEntryStrategy entryStrategy) {
		this.entryStrategy = entryStrategy;
	}
	public WebBotExitStrategy getExitStrategy() {
		return exitStrategy;
	}
	public void setExitStrategy(WebBotExitStrategy exitStrategy) {
		this.exitStrategy = exitStrategy;
	}
	public Number getRank() {
		return rank;
	}
	public void setRank(Number rank) {
		this.rank = rank;
	}
	public boolean isRankEnabled() {
		return rankEnabled;
	}
	public void setRankEnabled(boolean rankEnabled) {
		this.rankEnabled = rankEnabled;
	}
	public String getSignal() {
		return signal;
	}
	public void setSignal(String signal) {
		this.signal = signal;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	} 
	
	
	
	
	

}
