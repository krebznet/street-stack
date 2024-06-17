package com.dunkware.stream.data.cassy.entity.sesion;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("stream_session")
public class DBStreamSessionRow {


    @PrimaryKey()
    private DBStreamSessionKey key;

    @Column("vars")
    private List<Integer> vars = new ArrayList<Integer>();

    @Column("signals")
    private List<Integer> signals = new ArrayList<Integer>();


    @Column("stats")
    private List<Integer> stats = new ArrayList<Integer>();


    @Column("entities")
    private List<Integer> entities = new ArrayList<Integer>();


    @CassandraType(type = CassandraType.Name.TIME)
    @Column("start")
    private LocalTime start;

    @CassandraType(type = CassandraType.Name.TIME)
    @Column("stop")
    private LocalTime stop;

    public DBStreamSessionRow() {
    }

    public DBStreamSessionRow(DBStreamSessionKey key, List<Integer> vars, List<Integer> signals, List<Integer> stats, List<Integer> entities, LocalTime start, LocalTime stop) {
        this.key = key;
        this.vars = vars;
        this.signals = signals;
        this.stats = stats;
        this.entities = entities;
        this.start = start;
        this.stop = stop;
    }

    public DBStreamSessionKey getKey() {
        return key;
    }

    public void setKey(DBStreamSessionKey key) {
        this.key = key;
    }

    public List<Integer> getVars() {
        return vars;
    }

    public void setVars(List<Integer> vars) {
        this.vars = vars;
    }

    public List<Integer> getSignals() {
        return signals;
    }

    public void setSignals(List<Integer> signals) {
        this.signals = signals;
    }

    public List<Integer> getStats() {
        return stats;
    }

    public void setStats(List<Integer> stats) {
        this.stats = stats;
    }

    public List<Integer> getEntities() {
        return entities;
    }

    public void setEntities(List<Integer> entities) {
        this.entities = entities;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getStop() {
        return stop;
    }

    public void setStop(LocalTime stop) {
        this.stop = stop;
    }
    
    
}
