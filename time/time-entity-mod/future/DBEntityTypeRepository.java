package com.dunkware.time.entity.mod.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dunkware.time.entity.mod.entity.DBEntityType;

@Repository
public interface DBEntityTypeRepository extends JpaRepository<DBEntityType, Long> {

	Optional<DBEntityType> findByName(String name);

	boolean existsByName(String name);
}