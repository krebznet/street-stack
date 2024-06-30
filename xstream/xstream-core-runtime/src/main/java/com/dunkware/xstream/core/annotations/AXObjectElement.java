package com.dunkware.xstream.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.dunkware.xstream.xScript.XClassElementType;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)

public @interface AXObjectElement {
	Class<? extends XClassElementType> type();
	
}
