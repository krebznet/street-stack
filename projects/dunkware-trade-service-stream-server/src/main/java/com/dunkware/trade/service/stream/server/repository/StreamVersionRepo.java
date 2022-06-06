package com.dunkware.trade.service.stream.server.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface StreamVersionRepo extends PagingAndSortingRepository<StreamVersionEntity, Long> {

	
	public StreamVersionEntity findFirstByStreamOrderByVersion(StreamEntity stream);
}
