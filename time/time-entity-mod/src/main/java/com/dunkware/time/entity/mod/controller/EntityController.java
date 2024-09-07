package com.dunkware.time.entity.mod.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dunkware.time.entity.mod.EntityService;
import com.dunkware.time.entity.model.data.EntityDTO;
import com.dunkware.time.entity.model.search.EntitySearchReq;

@RestController
@RequestMapping("/stream/entities")
public class EntityController {

    @Autowired
    private EntityService entityService;

    // Search entities
    @PostMapping("/search")
    public ResponseEntity<List<EntityDTO>> searchEntities(@RequestBody EntitySearchReq searchReq) {
       // List<EntityDTO> results = entityService.searchEntities(searchReq);
       // return ResponseEntity.ok(results);
    	return null;
    }

    // Import entities from CSV
    @PostMapping("/import")
    public ResponseEntity<String> importEntities(@RequestParam String entityType, @RequestParam("file") MultipartFile file,
                                                 @RequestParam Map<String, String> columnMapping) {
       return null;
    }
}

// time-entity-ser