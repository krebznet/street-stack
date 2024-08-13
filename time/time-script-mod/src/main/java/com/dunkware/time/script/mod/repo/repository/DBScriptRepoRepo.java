package com.dunkware.time.script.mod.repo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dunkware.time.script.mod.repo.entity.DBScriptRepo;


public interface DBScriptRepoRepo extends CrudRepository<DBScriptRepo, Long>{

}
