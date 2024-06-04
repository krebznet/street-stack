package com.dunkware.trade.service.stream.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreamRepo extends CrudRepository<StreamEntity, Long> {
	
	// make this easier? 

}
