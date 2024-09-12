package com.dunkware.time.entity.mod;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.dunkware.time.entity.model.data.TimeEntity;
import com.dunkware.time.entity.model.search.TimeEntitySearchReq;
import com.dunkware.time.entity.model.type.TimeEntityType;

public interface EntityRepository {

	public TimeEntityType getTypeDTO();
	
	public String getTypeName();

	public List<ServerTimeEntity> getEntities();
	
	public List<TimeEntity> getEntityModels();

	public List<EntityList> getLists();

	public EntityList getList(String name);

	public EntityList getList(long id);

	public void importEntitiesFromCsv(String entityTypeName, MultipartFile file, Map<String, String> columnMapping)
			throws Exception;

	public List<TimeEntity> searchEntities(TimeEntitySearchReq searchReq) throws Exception;
}
