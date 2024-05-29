package com.dunkware.stream.data.cassy.repository.stats;

import com.dunkware.stream.data.cassy.entity.stats.SessionEntityStatRow;
import com.dunkware.stream.data.cassy.entity.stats.SessionEntityStatsRow;

import java.util.List;

public interface SessionEntityStatsRepoCustom {


    void ingest(List<SessionEntityStatsRow> stats);
}
