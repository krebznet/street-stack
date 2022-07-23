package com.dunkware.xstream.net.core.container.anot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.dunkware.xstream.container.ContainerExtType;



@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ACacheExtension {
	Class<? extends ContainerExtType> type();

}
