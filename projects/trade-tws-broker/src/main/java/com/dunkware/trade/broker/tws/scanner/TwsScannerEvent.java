package com.dunkware.trade.broker.tws.scanner;

public class TwsScannerEvent {

	public static int HIT_ADDED = 1;
	public static int HIT_UPDATED = 2;
	public static int HIT_DROPPED = 3;
	
	private int type;
	private TwsScannerHit hit;
	private TwsScanner scanner;
	
	public TwsScannerEvent(int type, TwsScannerHit hit, TwsScanner scanner) { 
		this.type = type;
		this.hit = hit;
		this.scanner = scanner;
	}
	
	public int getType() { 
		return type;
	}
	
	public TwsScannerHit getHit() { 
		return hit;
	}
	
	public TwsScanner getScanner() { 
		return scanner;
	}
}
