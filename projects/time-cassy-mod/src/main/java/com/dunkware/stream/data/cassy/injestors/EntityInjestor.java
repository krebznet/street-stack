package com.dunkware.stream.data.cassy.injestors;

public interface EntityInjestor<T> {

	public void injest(T entity);
	
	public EntityInjestorMetrics getMetrics();
	
	public void dispose() ; 
	
	
	
}
