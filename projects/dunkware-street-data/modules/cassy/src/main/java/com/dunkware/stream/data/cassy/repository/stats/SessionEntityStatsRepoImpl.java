package com.dunkware.stream.data.cassy.repository.stats;

import com.dunkware.stream.data.cassy.entity.stats.DBSessionEntityStatRow;
import com.dunkware.stream.data.cassy.entity.stats.DBSessionEntityStatsRow;
import com.dunkware.stream.data.cassy.support.CustomCassandraOperations;

import java.util.List;

public class SessionEntityStatsRepoImpl implements SessionEntityStatsRepoCustom {


    private final CustomCassandraOperations customOperations;

    public SessionEntityStatsRepoImpl(CustomCassandraOperations customOperations) {
        this.customOperations = customOperations;
    }

    @Override
    public void ingest(List<DBSessionEntityStatsRow> stats) {
        customOperations.ingest(stats);
    }
}
