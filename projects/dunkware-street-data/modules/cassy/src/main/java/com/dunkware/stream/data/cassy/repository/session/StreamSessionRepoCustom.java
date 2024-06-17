package com.dunkware.stream.data.cassy.repository.session;

import com.dunkware.stream.data.cassy.entity.sesion.DBStreamSessionRow;
import com.dunkware.stream.data.cassy.entity.stats.DBEntityStatsRow;

import java.util.List;

public interface StreamSessionRepoCustom {


    void ingest(List<DBStreamSessionRow> stats);
}
