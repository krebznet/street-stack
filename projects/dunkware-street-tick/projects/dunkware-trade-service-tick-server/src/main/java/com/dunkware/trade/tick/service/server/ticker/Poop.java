package com.dunkware.trade.tick.service.server.ticker;

public class Poop {
	
	public static void main(String[] args) {
		new Poop();
	}
	
	public Poop() { 
		me();
	}
	
	public void me() { 
		int callersLineNumber = new Exception().getStackTrace()[2].getLineNumber();
		System.out.println(callersLineNumber);
	}

}
