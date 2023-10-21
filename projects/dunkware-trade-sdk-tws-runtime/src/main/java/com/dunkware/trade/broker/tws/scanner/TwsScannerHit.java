package com.dunkware.trade.broker.tws.scanner;

import com.ib.client.ContractDetails;

public class TwsScannerHit {
	
	private int rank = 0;
	private String symbol = null;
	private ContractDetails contractDetails = null;
	
	public TwsScannerHit(String symbol, int rank, ContractDetails details) { 
		this.rank = rank;
		this.symbol = symbol;
		this.contractDetails = details;
	}
	
	public int getRank() { 
		return rank;
	}
	
	public String getSymbol() { 
		return symbol;
	}

	public void setRank(int rank) { 
		this.rank = rank;
	}

	public ContractDetails getContractDetails() {
		return contractDetails;
	}
	
	
}
