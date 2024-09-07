package com.dunkware.time.entity.mod.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.time.entity.mod.entity.DBEntityType;
import com.dunkware.time.entity.mod.entity.DBEntityTypeAttribute;
import com.dunkware.time.entity.mod.repository.DBEntityTypeRepository;
import com.dunkware.time.entity.model.type.EntityTypeDTO;

@Service
public class EntityTypeServiceImpl {

    @Autowired
    private DBEntityTypeRepository entityTypeRepository;

    public DBEntityType createEntityType(EntityTypeDTO entityTypeDTO) {
        if (entityTypeRepository.existsByName(entityTypeDTO.getName())) {
            throw new IllegalArgumentException("Entity type name already exists: " + entityTypeDTO.getName());
        }

        DBEntityType entityType = new DBEntityType();
        entityType.setName(entityTypeDTO.getName());
        entityType.setVersion(1); // Start versioning at 1

        List<DBEntityTypeAttribute> attributes = entityTypeDTO.getAttributes().stream()
                .map(attrDTO -> new DBEntityTypeAttribute(attrDTO.getName(), attrDTO.getLabel(), 
                    attrDTO.getDataType(), attrDTO.getFormatType(), 1))
                .collect(Collectors.toList());

        entityType.setAttributes(attributes);

        return entityTypeRepository.save(entityType);
    }

    public DBEntityType updateEntityType(String name, EntityTypeDTO entityTypeDTO) {
        DBEntityType entityType = entityTypeRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Entity type not found: " + name));

        entityType.incrementVersion(); // Increment version for updates

        List<DBEntityTypeAttribute> attributes = entityTypeDTO.getAttributes().stream()
                .map(attrDTO -> new DBEntityTypeAttribute(attrDTO.getName(), attrDTO.getLabel(), 
                    attrDTO.getDataType(), attrDTO.getFormatType(), entityType.getVersion()))
                .collect(Collectors.toList());

        entityType.setAttributes(attributes);

        return entityTypeRepository.save(entityType);
    }

    public void deleteEntityType(String name) {
        DBEntityType entityType = entityTypeRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Entity type not found: " + name));
        entityTypeRepository.delete(entityType);
    }
}