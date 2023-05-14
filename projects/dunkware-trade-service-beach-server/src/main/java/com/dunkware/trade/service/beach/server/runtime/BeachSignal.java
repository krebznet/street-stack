package com.dunkware.trade.service.beach.server.runtime;

import com.dunkware.xstream.model.signal.StreamSignal;

/**
 * Wraps a stream signal, extracts the symbol, last price and volume from the 
 * signal var map. 
 * 
 * @author duncankrebs
 *
 */
public class BeachSignal {

	private String stream; 
	private String signal; 
	private double last; 
	private long volume;
	private StreamSignal streamSignal; 
	private String symbol;
	
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public String getSignal() {
		return signal;
	}
	public void setSignal(String signal) {
		this.signal = signal;
	}
	public double getLast() {
		return last;
	}
	public void setLast(double last) {
		this.last = last;
	}
	public long getVolume() {
		return volume;
	}
	public void setVolume(long volume) {
		this.volume = volume;
	}
	public StreamSignal getStreamSignal() {
		return streamSignal;
	}
	public void setStreamSignal(StreamSignal streamSignal) {
		this.streamSignal = streamSignal;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	} 
	
	
	
	
	
}
