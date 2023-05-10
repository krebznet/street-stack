package com.dunkware.trade.service.beach.server.runtime.core.util;

import com.dunkware.trade.service.beach.server.runtime.BeachTrade;

public class TradeSet {
	
	public void addTrade(BeachTrade trade) { 
		
	}
	
	public void removeTrade(BeachTrade trade) { 
		
	}
	
	public int activeSymbolTrade(String symbol) { 
		return 0;
	}
	
	public int lastActiveSymbolTrade(String symbol) { 
		return 1;
	}
	
	
	
	// we need standard 
	private class Metrics { 
		
		private Number upl; 
		private Number uplp; 
		private Number activeCapital; 
		private Number tradedCapital;
		private Number openTrades; 
		private Number closeTrades; 
		private Number rpl; 
		private Number rplp;
		
		public Number getUpl() {
			return upl;
		}
		public void setUpl(Number upl) {
			this.upl = upl;
		}
		public Number getUplp() {
			return uplp;
		}
		public void setUplp(Number uplp) {
			this.uplp = uplp;
		}
		public Number getActiveCapital() {
			return activeCapital;
		}
		public void setActiveCapital(Number activeCapital) {
			this.activeCapital = activeCapital;
		}
		public Number getOpenTrades() {
			return openTrades;
		}
		public void setOpenTrades(Number openTrades) {
			this.openTrades = openTrades;
		}
		public Number getCloseTrades() {
			return closeTrades;
		}
		public void setCloseTrades(Number closeTrades) {
			this.closeTrades = closeTrades;
		}
		public Number getRpl() {
			return rpl;
		}
		public void setRpl(Number rpl) {
			this.rpl = rpl;
		}
		public Number getRplp() {
			return rplp;
		}
		public void setRplp(Number rplp) {
			this.rplp = rplp;
		}
		
		
		
	}

}
