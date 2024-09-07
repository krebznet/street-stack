package com.dunkware.time.entity.mod;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.dunkware.time.entity.model.data.EntityDTO;
import com.dunkware.time.entity.model.search.EntitySearchReq;
import com.dunkware.time.entity.model.type.EntityTypeDTO;

public interface EntityProvider {

	public EntityTypeDTO getTypeDTO();
	
	public String getTypeName();

	public List<Entity> getEntities();
	
	public List<EntityDTO> getEntityModels();

	public List<EntityList> getLists();

	public EntityList getList(String name);

	public EntityList getList(long id);

	public void importEntitiesFromCsv(String entityTypeName, MultipartFile file, Map<String, String> columnMapping)
			throws Exception;

	public List<EntityDTO> searchEntities(EntitySearchReq searchReq) throws Exception;
}
