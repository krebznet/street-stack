package com.dunkware.trade.sdk.core.model.trade;

/**
 * Initialized: Entry has been created no orders sent yet
 * Opening: Entry has been started and is trying to fill the trade
 * Opened: Entry has completed either a total or partial fill of allocated size
 * Canceling: Entry has received cancel request and is trying to cancel all open orders
 * Cancelled: Entry has cancelled without any order fills
 * Exception: Internal Exception on entry 
 * Rejected: Entry order(s) have been rejected by the broker 
 * 
 * @author dkrebs
 *
 */
public enum EntryStatus {
	Initialized,Opening,Opened,Cancelling,Cancelled,Exception,Rejected
}
