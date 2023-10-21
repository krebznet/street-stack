package com.dunkware.trade.sdk.core.runtime.trade.anot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.dunkware.trade.sdk.core.model.trade.EntryType;
import com.dunkware.trade.sdk.core.model.trade.ExitType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ATradeExit {

	Class<? extends ExitType> type();
	
}
