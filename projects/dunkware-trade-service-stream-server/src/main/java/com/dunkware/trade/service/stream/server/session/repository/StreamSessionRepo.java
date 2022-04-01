package com.dunkware.trade.service.stream.server.session.repository;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.data.repository.PagingAndSortingRepository;

@Transactional
public interface StreamSessionRepo extends PagingAndSortingRepository<StreamSessionDO, Long> {

	public StreamSessionDO findByDate(LocalDate date);
	
}
