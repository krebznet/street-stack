package com.dunkware.stream.data.cassy.repository.stats;

import com.dunkware.stream.data.cassy.entity.stats.DBSessionEntityStatKey;
import com.dunkware.stream.data.cassy.entity.stats.DBSessionEntityStatRow;
import com.dunkware.stream.data.cassy.entity.stats.DBSessionEntityStatsKey;
import com.dunkware.stream.data.cassy.entity.stats.DBSessionEntityStatsRow;
import com.dunkware.stream.data.cassy.support.CustomCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionEntityStatsRepo extends CustomCassandraRepository<DBSessionEntityStatsRow, DBSessionEntityStatsKey>, SessionEntityStatsRepoCustom {

	

}
