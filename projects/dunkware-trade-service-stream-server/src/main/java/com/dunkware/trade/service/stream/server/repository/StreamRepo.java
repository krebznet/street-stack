package com.dunkware.trade.service.stream.server.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreamRepo extends PagingAndSortingRepository<StreamEntity, Long> {
	
	// make this easier? 

}
