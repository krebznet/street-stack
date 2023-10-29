package com.dunkware.trade.service.beach.server.system;

import java.util.Collection;

import com.dunkware.trade.service.beach.model.system.BeachSystemModel;

import ca.odell.glazedlists.ObservableElementList;

public interface BeachSystemService {
	
	public Collection<BeachSystem> getSystems();
	
	public ObservableElementList<BeachSystemBean> getBeans();
	
	public void addSystem(BeachSystemModel model) throws Exception;
	
	public void deleteSystem(long systemId) throws Exception; 
	
	public void startSystem(long systemId) throws Exception; 
	
	public void stopSystem(long systemId) throws Exception; 
	
	public void saveSystem(BeachSystemModel model, long systemId) throws Exception; 

}
