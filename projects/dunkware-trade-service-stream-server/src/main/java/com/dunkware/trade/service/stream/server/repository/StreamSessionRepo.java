package com.dunkware.trade.service.stream.server.repository;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.data.repository.PagingAndSortingRepository;

@Transactional
public interface StreamSessionRepo extends PagingAndSortingRepository<StreamSessionEntity, Long> {

	public StreamSessionEntity findByDate(LocalDate date);
	
}
