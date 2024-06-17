package com.dunkware.stream.data.cassy.entity.stats;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("session_entity_stats_row")
public class DBSessionEntityStatsRow {

    @PrimaryKey
    private DBSessionEntityStatsKey key;

    @Column("vars")
    private Map<Integer,DBEntityVarStats> vars = new HashMap<Integer,DBEntityVarStats>();


    @Column("signals")
    private Map<Integer,Integer> signals = new HashMap<Integer,Integer>();

    public DBSessionEntityStatsRow() {
    }

    public DBSessionEntityStatsRow(DBSessionEntityStatsKey key, Map<Integer, DBEntityVarStats> vars, Map<Integer, Integer> signals) {
        this.key = key;
        this.vars = vars;
        this.signals = signals;
    }

    public DBSessionEntityStatsKey getKey() {
        return key;
    }

    public void setKey(DBSessionEntityStatsKey key) {
        this.key = key;
    }

    public Map<Integer, DBEntityVarStats> getVars() {
        return vars;
    }

    public void setVars(Map<Integer, DBEntityVarStats> vars) {
        this.vars = vars;
    }

    public Map<Integer, Integer> getSignals() {
        return signals;
    }

    public void setSignals(Map<Integer, Integer> signals) {
        this.signals = signals;
    }
}
