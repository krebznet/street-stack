package com.dunkware.trade.service.stream.server.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface StreamVersionRepo extends PagingAndSortingRepository<StreamVersionEntity, Long> {

	public List<StreamVersionEntity> findByStreamOrderByVersion(StreamEntity stream);
	
	public StreamVersionEntity findFirstByStreamOrderByVersion(StreamEntity stream);
}
