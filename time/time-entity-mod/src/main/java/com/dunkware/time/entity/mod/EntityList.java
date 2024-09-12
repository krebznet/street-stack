package com.dunkware.time.entity.mod;

import java.util.List;

import com.dunkware.time.entity.model.data.TimeEntity;

public interface EntityList {
	
	public long getListId();
	
	public String getListName();
	
	public int getListSize();
	
	public List<ServerTimeEntity> getEntityList();
	
	public List<TimeEntity> getDTOList();

}
