package com.dunkware.trade.service.stream.server.controller.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreamRepo extends PagingAndSortingRepository<StreamDO, Long> {
	
	// make this easier? 

}
