package com.dunkware.time.stream.mod.repository;

import org.springframework.data.repository.CrudRepository;

import com.dunkware.time.stream.mod.persist.DBStream;


public interface DBStreamRepository extends CrudRepository<DBStream, Long>{

}
