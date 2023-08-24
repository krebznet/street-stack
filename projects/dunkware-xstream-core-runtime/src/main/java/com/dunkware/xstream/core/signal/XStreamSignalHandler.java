package com.dunkware.xstream.core.signal;

import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntityQuery;
import com.dunkware.xstream.model.signal.XStreamSignalModel;

public class XStreamSignalHandler {

	private XStream stream; 
	private XStreamSignalModel model; 
	private XStreamEntityQuery query; 
	
	public void init(XStream stream, XStreamSignalModel model) throws Exception { 
		this.stream = stream;
		//this.type = type;
		// this will happen in the start thread of xsream 
		// okay so this is created is is it initialized? 
		//stream.createRowQuery(type.getQuery());
		//stream.getExecutor().
		// so this thing needs a runnable
		// so it needs to be scheduled basd on the type settings
		// how do we schedule recurring things 
		//stream.getClock().scheduleRunnable(new Runner(), type.getRunInterva());
		
	}
	
	
	private class Runner implements Runnable {

		@Override
		public void run() {
			
			// TODO Auto-generated method stub
			// goign to execute query against all shit 
			// for results look at symbol limit; 
			//stream.getClock().getLocalTime()
			
		} 
		
		
		
		
	}
}
