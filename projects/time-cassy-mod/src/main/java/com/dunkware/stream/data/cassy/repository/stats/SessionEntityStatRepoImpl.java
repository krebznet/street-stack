package com.dunkware.stream.data.cassy.repository.stats;

import com.dunkware.stream.data.cassy.entity.stats.DBSessionEntityStatRow;
import com.dunkware.stream.data.cassy.support.CustomCassandraOperations;

import java.util.List;

public class SessionEntityStatRepoImpl implements SessionEntityStatRepoCustom {


    private final CustomCassandraOperations customOperations;

    public SessionEntityStatRepoImpl(CustomCassandraOperations customOperations) {
        this.customOperations = customOperations;
    }

    @Override
    public void ingest(List<DBSessionEntityStatRow> stats) {
        customOperations.ingest(stats);
    }
}
