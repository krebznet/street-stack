package com.dunkware.time.entity.mod.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.time.entity.mod.EntityAdminService;
import com.dunkware.time.entity.mod.entity.DBEntityType;
import com.dunkware.time.entity.model.type.EntityTypeDTO;

@RestController
@RequestMapping("/api/entity-types")
public class EntityTypeController {

    @Autowired
    private EntityAdminService entityTypeService;

    @PostMapping("/create")
    public ResponseEntity<DBEntityType> createEntityType(@RequestBody EntityTypeDTO entityTypeDTO) {
        DBEntityType entityType = entityTypeService.createEntityType(entityTypeDTO);
        return ResponseEntity.ok(entityType);
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<DBEntityType> updateEntityType(@PathVariable String name, @RequestBody EntityTypeDTO entityTypeDTO) {
        DBEntityType updatedEntityType = entityTypeService.updateEntityType(name, entityTypeDTO);
        return ResponseEntity.ok(updatedEntityType);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Void> deleteEntityType(@PathVariable String name) {
        entityTypeService.deleteEntityType(name);
        return ResponseEntity.noContent().build();
    }
}