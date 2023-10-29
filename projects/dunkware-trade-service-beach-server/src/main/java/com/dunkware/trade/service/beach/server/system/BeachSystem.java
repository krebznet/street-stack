package com.dunkware.trade.service.beach.server.system;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.service.beach.model.system.BeachSystemModel;
import com.dunkware.trade.service.beach.server.entity.BeachSystemEntity;
import com.dunkware.trade.service.beach.server.session.BeachSession;
import com.dunkware.trade.service.beach.server.session.BeachSessionException;

public interface BeachSystem {
	
  public BeachSystemBean getBean();
  
  public BeachSystemEntity getEntity();
  
  public void startSession() throws BeachSessionException; 
  
  public void stopSession() throws BeachSessionException; 
  
  public long getId();
  
  public BeachSession getSession() throws BeachSessionException;
  
  public DEventNode getEventNode();
  
  public BeachSystemModel getModel();
	

}
