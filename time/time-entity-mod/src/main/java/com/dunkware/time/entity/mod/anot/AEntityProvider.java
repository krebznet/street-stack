package com.dunkware.time.entity.mod.anot;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.dunkware.time.entity.mod.EntityProvider;

	@Target({ElementType.TYPE})
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
public @interface AEntityProvider {

		Class<? extends EntityProvider> provider();
	
		String kind();
}
