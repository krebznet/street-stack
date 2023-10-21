package com.dunkware.trade.sdk.lib.runtime.exit;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.sdk.core.model.trade.ExitSpec;
import com.dunkware.trade.sdk.core.model.trade.ExitType;
import com.dunkware.trade.sdk.core.runtime.trade.Trade;
import com.dunkware.trade.sdk.core.runtime.trade.TradeExit;
import com.dunkware.trade.sdk.core.runtime.trade.anot.ATradeExit;
import com.dunkware.trade.sdk.core.runtime.trade.event.ETradeExitCompleted;
import com.dunkware.trade.sdk.core.runtime.trade.event.ETradeExitException;
import com.dunkware.trade.sdk.lib.model.exit.SmartExitRuleType;
import com.dunkware.trade.sdk.lib.model.exit.SmartExitType;
import com.dunkware.trade.sdk.lib.model.exit.rules.SmartExitGainLossAmount;
import com.dunkware.trade.sdk.lib.model.exit.rules.SmartExitTimer;
import com.dunkware.trade.sdk.lib.model.exit.rules.SmartExitTrailingStop;
import com.dunkware.trade.sdk.lib.runtime.exit.rules.GainLossRule;
import com.dunkware.trade.sdk.lib.runtime.exit.rules.TimerRule;
import com.dunkware.trade.sdk.lib.runtime.exit.rules.TrailingStopRule;

@ATradeExit(type = SmartExitType.class)
public class SmartExit implements TradeExit {

	private SmartExitType type;

	private List<SmartExitRule> rules = new ArrayList<SmartExitRule>();

	private Queue<SmartExitRule> lockQueue = new LinkedList<SmartExitRule>();
	private SmartExitRule lockHolder = null;
	private boolean lockAcquired = false;
	private boolean lockHolderRequested = false;

	private Trade trade;

	private ExitSpec spec;

	@Override
	public void init(ExitType type) throws Exception {
		this.type = (SmartExitType) type;
		spec = new ExitSpec();
		for (SmartExitRuleType ruleType : this.type.getRules()) {
			SmartExitRule rule = null;
			if (ruleType instanceof SmartExitGainLossAmount) {
				rule = new GainLossRule();
			}
			if (ruleType instanceof SmartExitTrailingStop) {
				rule = new TrailingStopRule();
			}
			if (ruleType instanceof SmartExitTimer) {
				rule = new TimerRule();
			}
			
			if(rule == null) { 
				throw new Exception("Smart Exit Rule Type Not Handled " + ruleType.getClass().getName());
			}
			
			try {
				rule.init(ruleType, this);	
			} catch (Exception e) {
				throw new Exception("Smart Exit Rule Type Init Exception " + e.toString() + " on rule " + rule.getClass().getName());
			}
			rules.add(rule);
			
		}
	}

	@Override
	public void start(Trade trade) {
		this.trade = trade;
		for (SmartExitRule rule : rules) {
			rule.start();
		}
	}

	@Override
	public void cancel() throws Exception {
		//TODO: cancel smart exit?! 
		
	}

	@Override
	public ExitSpec getSpec() {
		return spec;
	}

	@Override
	public Trade getTrade() {
		return trade;
	}
	

	@Override
	public DEventNode getEventNode() {
		return trade.getEventNode();
	}

	public void requestLock(SmartExitRule rule) {
		if (!lockAcquired) {
			lockHolder = rule;
			lockAcquired = true;
			rule.lockAcquired();
		} else {
			lockQueue.add(rule);
			if (!lockHolderRequested) {
				lockHolder.lockRequested();
				lockHolderRequested = true;
			}

		}
	} 

	public void releaseLock(SmartExitRule rule) {
		if (lockHolder == null) {
			// error log
		}
		if (rule != lockHolder) {
			// error log
		}
		// if people are waiting
		// lets get the next person in line
		if (lockQueue.size() > 0) {
			SmartExitRule nextRule = lockQueue.remove();
			lockHolder = nextRule;
			lockHolderRequested = false;
			nextRule.lockAcquired();
		} else {
			// no one wants it
		}
	}
	
	
	/**
	 * Called from rules to indicate that this exit is closed/completed. 
	 */
	public void completed() { 
		for (SmartExitRule rule : rules) {
			rule.dispose();
		}
		ETradeExitCompleted closed = new ETradeExitCompleted(this);
		getEventNode().event(closed);
	}
	
	/**
	 * Called when there is a problem from one of the rules 
	 * @param exception
	 */
	public void exception(String exception) { 
		getSpec().setException(exception);
		ETradeExitException ex = new ETradeExitException(this);
		getEventNode().event(ex);
		for (SmartExitRule rule : rules) {
			rule.dispose();
		}
	}
	

}
