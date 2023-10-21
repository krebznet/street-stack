package com.dunkware.trade.sdk.core.runtime.system.anot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.dunkware.trade.sdk.core.model.system.SystemType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ASystem {

	Class<? extends SystemType> type();
	
	
}
