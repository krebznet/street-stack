package com.dunkware.trade.sdk.lib.runtime.exit.rules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.events.anot.ADEventMethod;
import com.dunkware.trade.sdk.core.model.order.OrderType;
import com.dunkware.trade.sdk.core.runtime.order.Order;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderException;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderFilled;
import com.dunkware.trade.sdk.core.runtime.trade.Trade;
import com.dunkware.trade.sdk.lib.model.exit.SmartExitRuleType;
import com.dunkware.trade.sdk.lib.model.exit.rules.SmartExitTimer;
import com.dunkware.trade.sdk.lib.runtime.exit.SmartExit;
import com.dunkware.trade.sdk.lib.runtime.exit.SmartExitRule;

public class TimerRule extends SmartExitRule {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private SmartExitTimer type;
	private SmartExit exit; 
	
	private TimerThread timerThread; 
	private boolean timerRunning = true; 
	
	private Order exitOrder;
	
	@Override
	public void init(SmartExitRuleType type, SmartExit exit) throws Exception {
		this.type = (SmartExitTimer)type; 	
		this.exit = exit;
	}

	@Override
	public void start() {
		timerThread = new TimerThread();
		timerThread.start();
	}

	@Override
	public void dispose() {
		if(timerRunning) { 
			timerThread.interrupt();
		}
	}

	@Override
	public void lockRequested() {
		// well what do we think here?
	}

	@Override
	public void lockAcquired() {
		// here we go
		// Market
		try {
			if(logger.isDebugEnabled()) { 
				logger.debug("Timer Lock Acquired Initiating Market Exit Order");
			}
			OrderType exitType = createExitOrder();			
			exitOrder = exit.getTrade().createExitOrder(exitType);
			exitOrder.getEventNode().addEventHandler(this);
			exitOrder.send();
		} catch (Exception e) {
			exit.exception("Exception Creating Timer Exit Rule " + e.toString());
		}
	}

	@Override
	public Trade getTrade() {
		return exit.getTrade();
	}
	
	@ADEventMethod()
	public void orderFilled(EOrderFilled filled) { 
		if(logger.isDebugEnabled()) { 
			logger.debug("Timer Exit Order Filled Invoking Complete Method On Exit");
		}
		exit.completed();
	}
	
	@ADEventMethod()
	public void orderException(EOrderException exception) { 
		exit.exception("Timer Exit Order Exception Handler " + exception.getOrder().getSpec().getException());
	}
	
	private class TimerThread extends Thread { 
		
		public void run() { 
			int timer = type.getTimer();
			int i = 0;
			if(logger.isDebugEnabled()) { 
				logger.debug("Starting Smart Exit Time For {} Seconds",timer);
			}
			while(i < timer) { 
				try {
					Thread.sleep(1000); 
					i++;
				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
					logger.error("Timer Rule Thread Exception Not Interrupted Type ? " + e.toString());
				}
			}
			if(logger.isDebugEnabled()) { 
				logger.debug("Timer Smart Exit Activated After {} Seconds, Requesting Order Lock",timer);
			}
			timerRunning = false;
			exit.requestLock(TimerRule.this);
			
		}
	}

	
}
