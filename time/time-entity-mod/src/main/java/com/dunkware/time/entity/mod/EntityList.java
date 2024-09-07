package com.dunkware.time.entity.mod;

import java.util.List;

import com.dunkware.time.entity.model.data.EntityDTO;

public interface EntityList {
	
	public long getListId();
	
	public String getListName();
	
	public int getListSize();
	
	public List<Entity> getEntityList();
	
	public List<EntityDTO> getDTOList();

}
