package com.dunkware.trade.service.stream.serverd.repository;

import java.time.LocalDate;

import org.springframework.data.repository.CrudRepository;

import jakarta.transaction.Transactional;

@Transactional
public interface StreamSessionRepo extends CrudRepository<StreamSessionEntity, Long> {

	public StreamSessionEntity findByDate(LocalDate date);
	
}
