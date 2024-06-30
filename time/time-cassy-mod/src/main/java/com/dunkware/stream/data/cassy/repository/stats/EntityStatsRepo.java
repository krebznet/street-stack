package com.dunkware.stream.data.cassy.repository.stats;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dunkware.stream.data.cassy.entity.stats.DBEntityStatsRow;
import com.dunkware.stream.data.cassy.entity.stats.DBEntityStatsKey;
import com.dunkware.stream.data.cassy.support.CustomCassandraRepository;

@Repository
public interface EntityStatsRepo extends CustomCassandraRepository<DBEntityStatsRow, DBEntityStatsKey>, EntityStatsRepoCustom {


//	public List<DBEntityStatsRow> findByKeyAndStreamAndKeyEntityAndKeyDate(int stream, int entity, LocalDate date);
	
	
	

	
}
