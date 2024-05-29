package com.dunkware.stream.data.cassy.repository.stats;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.stream.data.cassy.entity.stats.EntityStatsRow;
import com.dunkware.stream.data.cassy.support.CustomCassandraOperations;

public class EntityStatsRepoImpl implements EntityStatsRepoCustom {

    @Autowired
    private CustomCassandraOperations customOperations;

    @Override
    public void ingest(List<EntityStatsRow> stats) {
        customOperations.ingest(stats);
    }
}
