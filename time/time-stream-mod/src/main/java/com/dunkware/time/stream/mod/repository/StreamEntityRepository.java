package com.dunkware.time.stream.mod.repository;

import org.springframework.data.repository.CrudRepository;

import com.dunkware.time.stream.mod.entity.DBStream;


public interface StreamEntityRepository extends CrudRepository<DBStream, Long>{

}
