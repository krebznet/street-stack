package com.dunkware.xstream.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.dunkware.xstream.xproject.model.XStreamExtensionType;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)

public @interface AXStreamExtension {
	Class<? extends XStreamExtensionType> type();

}
