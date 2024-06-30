package com.dunkware.trade.service.stream.server.repository;

import org.springframework.data.repository.CrudRepository;

public interface StreamVersionRepo extends CrudRepository<StreamVersionEntity, Long> {

	
	public StreamVersionEntity findFirstByStreamOrderByVersion(StreamEntity stream);
}
