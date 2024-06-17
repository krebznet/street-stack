package com.dunkware.stream.data.cassy.repository.stats;

import com.dunkware.stream.data.cassy.entity.stats.DBSessionEntityStatRow;

import java.util.List;

public interface SessionEntityStatRepoCustom {


    void ingest(List<DBSessionEntityStatRow> stats);
    
    
    
}
