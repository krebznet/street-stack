package com.dunkware.net.cluster.node.anot;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AClusterExtension {

	// can a cluster extension handler cluster messages? 
	// can a 
	
	// kafka topic bus for all messages --> these are not request/reply 
	// kafka topic for each cluster node -> to consume node specific messages 
	// kafka topic 
	
	
	// cluster service channels 
		// cluster service channels can have input messages 
}
