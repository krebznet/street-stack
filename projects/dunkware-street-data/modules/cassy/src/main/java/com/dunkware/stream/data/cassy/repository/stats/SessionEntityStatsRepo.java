package com.dunkware.stream.data.cassy.repository.stats;

import com.dunkware.stream.data.cassy.entity.stats.SessionEntityStatKey;
import com.dunkware.stream.data.cassy.entity.stats.SessionEntityStatRow;
import com.dunkware.stream.data.cassy.entity.stats.SessionEntityStatsKey;
import com.dunkware.stream.data.cassy.entity.stats.SessionEntityStatsRow;
import com.dunkware.stream.data.cassy.support.CustomCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionEntityStatsRepo extends CustomCassandraRepository<SessionEntityStatsRow, SessionEntityStatsKey>, SessionEntityStatsRepoCustom {

	

}
