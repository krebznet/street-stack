package com.dunkware.stream.data.cassy.repository.session;

import com.dunkware.stream.data.cassy.entity.sesion.DBStreamSessionRow;
import com.dunkware.stream.data.cassy.entity.stats.DBSessionEntityStatRow;
import com.dunkware.stream.data.cassy.support.CustomCassandraOperations;

import java.util.List;

public class StreamSessionRepoImpl implements  StreamSessionRepoCustom {


    private final CustomCassandraOperations customOperations;

    public StreamSessionRepoImpl(CustomCassandraOperations customOperations) {
        this.customOperations = customOperations;
    }

    @Override
    public void ingest(List<DBStreamSessionRow> row) {
        customOperations.ingest(row);
    }


}
