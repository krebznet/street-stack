package com.dunkware.logger.core;

public class DunkLogSubscriptionBuilder {
	
	public DunkLogSubscriptionBuilder() { 
		
	}
	
	public DunkLogSubscriptionBuilder type(String type) { 
		return this;
	}
	
	public DunkLogSubscriptionBuilder targets(String... targets) { 
		return this; 
	}
	
	public DunkLogSubscriptionBuilder partEquals(String part, Object value) { 
		return this;
	}
	
	public DunkLogSubscriptionBuilder levelGte(int level) { 
		return this;
	}

}
