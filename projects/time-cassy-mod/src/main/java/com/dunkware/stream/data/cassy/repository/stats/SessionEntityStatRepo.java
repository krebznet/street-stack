package com.dunkware.stream.data.cassy.repository.stats;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dunkware.stream.data.cassy.entity.stats.DBSessionEntityStatKey;
import com.dunkware.stream.data.cassy.entity.stats.DBSessionEntityStatRow;
import com.dunkware.stream.data.cassy.support.CustomCassandraRepository;

@Repository
public interface SessionEntityStatRepo extends CustomCassandraRepository<DBSessionEntityStatRow, DBSessionEntityStatKey>, SessionEntityStatRepoCustom {

	public List<DBSessionEntityStatRow> findByStreamAnLocalDate(int stream, LocalDate date);
	
	

}
