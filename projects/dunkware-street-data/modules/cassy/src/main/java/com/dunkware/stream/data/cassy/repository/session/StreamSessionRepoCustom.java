package com.dunkware.stream.data.cassy.repository.session;

import com.dunkware.stream.data.cassy.entity.sesion.StreamSessionRow;
import com.dunkware.stream.data.cassy.entity.stats.EntityStatsRow;

import java.util.List;

public interface StreamSessionRepoCustom {


    void ingest(List<StreamSessionRow> stats);
}
