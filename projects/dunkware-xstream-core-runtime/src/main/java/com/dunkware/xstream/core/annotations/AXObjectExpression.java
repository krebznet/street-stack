package com.dunkware.xstream.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.dunkware.xstream.xScript.ExpressionType;
import com.dunkware.xstream.xScript.XExpressionType;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)

public @interface AXObjectExpression {
	Class<? extends XExpressionType> type();
	
}
