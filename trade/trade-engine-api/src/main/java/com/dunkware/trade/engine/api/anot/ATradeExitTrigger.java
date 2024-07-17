package com.dunkware.trade.engine.api.anot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.dunkware.trade.engine.model.api.TradeValidatorType;
import com.dunkware.trade.engine.model.api.extend.TradeExitTriggerType;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)

public @interface ATradeExitTrigger {
	Class<? extends TradeExitTriggerType> type();
	
}
 