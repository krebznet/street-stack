package com.dunkware.trade.engine.api.registry;

import com.dunkware.trade.engine.api.TradeEntryExecutor;
import com.dunkware.trade.engine.api.TradeEntryTrigger;
import com.dunkware.trade.engine.api.TradeExitExecutor;
import com.dunkware.trade.engine.api.TradeExitTrigger;
import com.dunkware.trade.engine.api.TradeValidator;
import com.dunkware.trade.engine.model.api.extend.TradeEntryExecutorType;
import com.dunkware.trade.engine.model.api.extend.TradeEntryTriggerType;
import com.dunkware.trade.engine.model.api.extend.TradeExitExecutorType;
import com.dunkware.trade.engine.model.api.extend.TradeExitTriggerType;
import com.dunkware.trade.engine.model.api.extend.TradeValidatorType;

public interface TradeRegistry {
	
	public TradeEntryExecutor entryExecutor(TradeEntryExecutorType type) throws Exception; 
	
	public TradeExitExecutor exitExecutor(TradeExitExecutorType type) throws Exception;

	public TradeEntryTrigger entryTrigger(TradeEntryTriggerType type) throws Exception;
	
	public TradeExitTrigger exitTrigger(TradeExitTriggerType type) throws Exception;

	public TradeValidator tradeValidator(TradeValidatorType type) throws Exception;
}
