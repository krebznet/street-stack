package com.dunkware.street.client.trade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.trade.broker.tws.TwsBroker;

public class StreetTrader {

	
	public static StreetTrader create(String server, String username, String password, String twsHost, int twsPort, int twsId) throws Exception { 
		throw new Exception("Fuck Man");
	}
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private TwsBroker twsBroker; 
	private String gatewayUrl;
	private String gatewayUser;
	private String gatewayPass;
	
	private StreetTrader(TwsBroker broker, String server, String username, String password) { 
		this.twsBroker = broker;
		this.gatewayPass = password; 
		this.gatewayUrl = server; 
		this.gatewayUser = username; 

	}
	
	
	
	

		
	
	 
}
