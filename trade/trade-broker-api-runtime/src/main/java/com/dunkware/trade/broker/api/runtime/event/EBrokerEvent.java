package com.dunkware.trade.broker.api.runtime.event;

import com.dunkware.trade.broker.api.runtime.Broker;

//TODO: AVINASHANV-21 Event Object example
/**
 * look in this package these all the events for a broker that uses event nodes
 * we have common pattern of defining base classes like this so listeners can listen
 * to all broker events in one method. on a eventNode you can call node.event(instance of DunkEvent)
 * and aasync notification goes to all registered method handlers that is found by parsing the annotations
 * and matching the event with the class type of the input param on the handler, then it moves the event op 
 * the tree and notifies registered handlers on each parent node. 
 */
public class EBrokerEvent  {

	private Broker broker; 
	
	public EBrokerEvent(Broker broker) { 
		this.broker = broker; 
	}
	
	public Broker getBroker() { 
		return broker; 
	}
}
