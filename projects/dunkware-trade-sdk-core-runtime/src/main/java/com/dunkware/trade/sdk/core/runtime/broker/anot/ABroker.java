package com.dunkware.trade.sdk.core.runtime.broker.anot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.dunkware.trade.sdk.core.model.broker.BrokerType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ABroker {

	Class<? extends BrokerType> type();
	
}
