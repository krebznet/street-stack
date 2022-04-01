package com.dunkware.xstream.data.cache.anot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.dunkware.xstream.data.cache.CacheExtensionType;



@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ACacheExtension {
	Class<? extends CacheExtensionType> type();

}
