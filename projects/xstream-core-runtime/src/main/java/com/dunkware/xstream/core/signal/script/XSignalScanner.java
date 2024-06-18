package com.dunkware.xstream.core.signal.script;

import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.xScript.SignalScannerType;
import com.dunkware.xstream.xScript.SignalType;

public class XSignalScanner {

	private SignalScannerType scannerType;
	private SignalType signalType; 
	private XStream stream; 
	
	public void start(XStream stream, SignalType signalType, SignalScannerType scannerType)  { 
		this.scannerType = scannerType; 
		this.signalType = signalType;
		this.stream = stream; 
		
	}
	
	
	public void start() { 
		try {
			stream.getRow(3).createExpressoin(scannerType.getExpression());
				
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
}
