package com.dunkware.xstream.net.core.container;

import java.time.LocalDateTime;

public interface ContainerEntitySignal {
	
	String getIdent();
	
	int getId();
	
	String getEntityIdent();
	
	int getEntityId();
	
	LocalDateTime getTime();
	
	ContainerValueSet getVars();

}
