package com.dunkware.stream.data.cassy.repository.stats;

import java.util.List;

import com.dunkware.stream.data.cassy.entity.stats.DBEntityStatsRow;

public interface EntityStatsRepoCustom {


    void ingest(List<DBEntityStatsRow> stats);
}
