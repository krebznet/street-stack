package com.dunkware.time.stream.mod.repository;

import org.springframework.data.repository.CrudRepository;

import com.dunkware.time.stream.mod.persist.DBStreamSession;

public interface DBSessionRepository extends CrudRepository<DBStreamSession, Long>{

}
