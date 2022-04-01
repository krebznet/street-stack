package com.dunkware.xstream.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.dunkware.xstream.xScript.ExpressionType;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)

public @interface AXStreamExpression {
	Class<? extends ExpressionType> type();
	
}
