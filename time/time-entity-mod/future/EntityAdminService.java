package com.dunkware.time.entity.mod;

import org.springframework.stereotype.Service;

import com.dunkware.time.entity.mod.entity.DBEntityType;
import com.dunkware.time.entity.model.type.EntityTypeDTO;

@Service
public interface EntityAdminService {

	public DBEntityType createEntityType(EntityTypeDTO entityTypeDTO);

	public void deleteEntityType(String name);

	public DBEntityType updateEntityType(String name, EntityTypeDTO entityTypeDTO);

}
