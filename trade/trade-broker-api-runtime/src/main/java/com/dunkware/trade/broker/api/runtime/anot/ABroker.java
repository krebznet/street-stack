package com.dunkware.trade.broker.api.runtime.anot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.dunkware.trade.broker.api.model.broker.BrokerType;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)

public @interface ABroker {
	Class<? extends BrokerType> type();
	
}
