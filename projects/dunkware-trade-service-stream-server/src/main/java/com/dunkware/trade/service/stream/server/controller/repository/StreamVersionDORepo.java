package com.dunkware.trade.service.stream.server.controller.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface StreamVersionDORepo extends PagingAndSortingRepository<StreamVersionDO, Long> {

	public List<StreamVersionDO> findByStreamOrderByVersion(StreamDO stream);
	
	public StreamVersionDO findFirstByStreamOrderByVersion(StreamDO stream);
}
