package com.dunkware.time.entity.mod;

import java.util.Map;

import com.dunkware.time.entity.model.data.EntityDTO;
import com.dunkware.time.entity.model.type.EntityTypeDTO;

public interface Entity {

	public String getTypeName();
	
	public String getIdentifier();
	
	public long getId();
	
	public int getVersion();
	
	public Map<String,Object> getProperties();
	
	public EntityTypeDTO getTypeDTO();
	
	public EntityDTO getDTO();
	
	
}
