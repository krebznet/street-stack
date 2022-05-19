package com.dunkware.xstream.net.core.container;

import java.time.LocalDateTime;

public interface ContainerEntitySnapshot {
	
	LocalDateTime getTime();
	
	ContainerValueSet getVars(); 
	
	int getId();
	
	String getIdent();

}
