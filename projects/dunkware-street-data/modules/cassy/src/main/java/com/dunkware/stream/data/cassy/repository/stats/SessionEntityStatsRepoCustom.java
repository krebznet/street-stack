package com.dunkware.stream.data.cassy.repository.stats;

import com.dunkware.stream.data.cassy.entity.stats.DBSessionEntityStatRow;
import com.dunkware.stream.data.cassy.entity.stats.DBSessionEntityStatsRow;

import java.util.List;

public interface SessionEntityStatsRepoCustom {


    void ingest(List<DBSessionEntityStatsRow> stats);
}
