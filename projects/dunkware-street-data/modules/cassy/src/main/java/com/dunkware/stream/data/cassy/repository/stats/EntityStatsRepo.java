package com.dunkware.stream.data.cassy.repository.stats;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dunkware.stream.data.cassy.entity.stats.EntityStatsRow;
import com.dunkware.stream.data.cassy.entity.stats.EntityStatsKey;
import com.dunkware.stream.data.cassy.support.CustomCassandraRepository;

@Repository
public interface EntityStatsRepo extends CustomCassandraRepository<EntityStatsRow, EntityStatsKey>, EntityStatsRepoCustom {


	public List<EntityStatsRow> findByKeyStreamAndKeyEntityAndKeyDate(int stream, int entity, LocalDate date);
	
	
	

	
}
