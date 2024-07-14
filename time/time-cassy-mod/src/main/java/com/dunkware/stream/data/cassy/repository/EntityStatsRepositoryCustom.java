package com.dunkware.stream.data.cassy.repository;

import java.util.List;

import com.dunkware.stream.data.cassy.entity.EntityStats;

public interface EntityStatsRepositoryCustom {

	void ingest(List<EntityStats> stats);
}
