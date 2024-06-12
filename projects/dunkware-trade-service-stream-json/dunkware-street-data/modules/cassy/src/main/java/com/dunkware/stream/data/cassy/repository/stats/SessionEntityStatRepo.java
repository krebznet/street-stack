package com.dunkware.stream.data.cassy.repository.stats;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dunkware.stream.data.cassy.entity.stats.SessionEntityStatKey;
import com.dunkware.stream.data.cassy.entity.stats.SessionEntityStatRow;
import com.dunkware.stream.data.cassy.support.CustomCassandraRepository;

@Repository
public interface SessionEntityStatRepo extends CustomCassandraRepository<SessionEntityStatRow, SessionEntityStatKey>, SessionEntityStatRepoCustom {

	public List<SessionEntityStatRow> findByStreamAnLocalDate(int stream, LocalDate date);
	
	

}
