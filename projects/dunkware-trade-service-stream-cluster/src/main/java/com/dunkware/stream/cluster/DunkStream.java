package com.dunkware.stream.cluster;

import java.util.List;

public interface DunkStream {
	
	
	public int getId();
	
	public String getIdent();
	
	public Object getBlueprint();
	
	public List<DunkStreamEntity> getEntities();
	
	public boolean entityExists(String identifier);
	
	// EnttiyStats --> 

	// what entittis we hvae 
}
