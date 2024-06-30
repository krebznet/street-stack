package com.dunkware.stream.data.cassy.entity.stats;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("entity_stats")
public class DBEntityStatsRow {
   
	@PrimaryKey
    private DBEntityStatsKey key;
    
    @Column("vars")
    private Map<Integer, DBEntityVarStats> vars = new HashMap<Integer, DBEntityVarStats>();
    
    @Column("signals")
    private Map<Integer,Integer> signals = new HashMap<Integer,Integer>();
    
    public DBEntityStatsRow() {
    	
    }

	public DBEntityStatsRow(DBEntityStatsKey key, Map<Integer, DBEntityVarStats> vars, Map<Integer, Integer> signals) {
		this.key = key;
		this.vars = vars;
		this.signals = signals;
	}

	public DBEntityStatsKey getKey() {
		return key;
	}

	public void setKey(DBEntityStatsKey key) {
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
