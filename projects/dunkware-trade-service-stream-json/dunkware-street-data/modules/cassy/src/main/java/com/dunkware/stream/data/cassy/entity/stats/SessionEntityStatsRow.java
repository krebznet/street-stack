package com.dunkware.stream.data.cassy.entity.stats;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("session_entity_stats_row")
public class SessionEntityStatsRow {

    @PrimaryKey
    private SessionEntityStatsKey key;

    @Column("vars")
    private Map<Integer,EntityVarStats> vars = new HashMap<Integer,EntityVarStats>();


    @Column("signals")
    private Map<Integer,Integer> signals = new HashMap<Integer,Integer>();

    public SessionEntityStatsRow() {
    }

    public SessionEntityStatsRow(SessionEntityStatsKey key, Map<Integer, EntityVarStats> vars, Map<Integer, Integer> signals) {
        this.key = key;
        this.vars = vars;
        this.signals = signals;
    }

    public SessionEntityStatsKey getKey() {
        return key;
    }

    public void setKey(SessionEntityStatsKey key) {
        this.key = key;
    }

    public Map<Integer, EntityVarStats> getVars() {
        return vars;
    }

    public void setVars(Map<Integer, EntityVarStats> vars) {
        this.vars = vars;
    }

    public Map<Integer, Integer> getSignals() {
        return signals;
    }

    public void setSignals(Map<Integer, Integer> signals) {
        this.signals = signals;
    }
}
