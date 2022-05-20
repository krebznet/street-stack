package com.dunkware.trade.service.data.service.stream.container.connection.handlers;

public class EntityScannerExtension {
	
	// so this will listen for EntityScannerStart 
	
	// it will send out a message to all workers saying here is your new scanner
	// they will send back message updates. 
	
	// we will take them and then relay them back to the client in Updates or Exception

}
