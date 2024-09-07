package com.dunkware.time.entity.mod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dunkware.time.entity.mod.entity.DBEntity;

@Repository
public interface DBEntityRepository extends JpaRepository<DBEntity, Long> {
    // Additional custom query methods can be defined here if needed
}