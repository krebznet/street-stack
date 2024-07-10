package com.dunkware.street.smart.runtime.resources;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.dunkware.street.stream.model.resource.ResourceModel;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)

public @interface AResource {

	Class<? extends ResourceModel> type();
	
}
