package com.dunkware.trade.service.beach.server.session.ext.strategies;

import com.dunkware.trade.service.beach.server.session.BeachSessionException;
import com.dunkware.trade.service.beach.server.session.BeachSessionPlayContainer;
import com.dunkware.trade.service.beach.server.session.BeachSessionStrategy;
import com.dunkware.trade.service.beach.server.stream.BeachSignalListener;
import com.dunkware.xstream.model.signal.StreamEntitySignal;

public class SignalTradeStrategy implements BeachSessionStrategy, BeachSignalListener {

	private BeachSessionPlayContainer container; 
	
	@Override
	public void init(Object model, BeachSessionPlayContainer container) throws BeachSessionException {
		// TODO Auto-generated method stub
		// cast to my model; 
	}

	@Override
	public void onStreamSignal(StreamEntitySignal signal) {
		// now we are getting closer here. 
		// have to figure out the model. 
	}
	
	
	
	
	

	
	
}
