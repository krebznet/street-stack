package com.dunkware.xstream.net.core.container.core;

import java.time.LocalDateTime;

import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerEntitySignal;
import com.dunkware.xstream.net.core.container.ContainerValueSet;

public class ContainerEntitySignalImpl implements ContainerEntitySignal {
	
	private int id; 
	private String identifier; 
	private LocalDateTime time; 
	
	
	
	private String entityIdentifier; 
	private int entityId; 
	
	private ContainerValueSet vars;
	private Container container; 
	
	public ContainerEntitySignalImpl(Container container, int id, String ident, int entityId, String entityIdent, LocalDateTime time, ContainerValueSet vars) { 
		this.id = id; 
		this.identifier = ident;
		this.time = time; 
		this.vars = vars; 
		this.container = container;
		this.entityIdentifier = entityIdent;
	}
	
	

	@Override
	public String getIdent() {
		return identifier;
	}

	@Override
	public String getEntityIdent() {
		return entityIdentifier;
	}


	@Override
	public int getId() {
		return id;
	}
	
	@Override
	public int getEntityId() {
		return entityId;
	}
	
	@Override
	public LocalDateTime getTime() {
		return time;
	}
	
	@Override
	public ContainerValueSet getVars() {
		return vars;
	} 

	


	

}
