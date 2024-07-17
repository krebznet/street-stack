package com.dunkware.trade.engine.api.anot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.dunkware.trade.engine.model.api.extend.TradeEntryExecutorType;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)

public @interface ATradeValidator {
	Class<? extends TradeEntryExecutorType> type();
	
}
 