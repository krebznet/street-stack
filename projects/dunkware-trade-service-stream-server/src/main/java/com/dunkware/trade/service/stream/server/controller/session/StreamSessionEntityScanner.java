package com.dunkware.trade.service.stream.server.controller.session;

import com.dunkware.common.util.data.NetScanner;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamRuntimeException;
import com.dunkware.xstream.model.scanner.SessionEntityScanner;

public interface StreamSessionEntityScanner {
	
	void dispose(); 
	
	void init(XStream stream, SessionEntityScanner model) throws XStreamRuntimeException; 
	
	NetScanner getNetScanner(); 
	
	//void setScannerVars(List<String> varIdentifiers) throws XSte

}
