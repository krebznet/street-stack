package com.dunkware.time.data.model.stats.entity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class EntityStatsModel {

    private int stream;
    private int entity;
    private LocalDate date;

    private Map<Integer,Integer> sigcounts = new HashMap<Integer,Integer>();
    private Map<Integer, EntityVarStatsModel> varstats = new HashMap<Integer, EntityVarStatsModel>();
    
    public EntityStatsModel() {

    }
    
    
	public EntityStatsModel(int stream, int entity, LocalDate date, Map<Integer, Integer> sigcounts,
                            Map<Integer, EntityVarStatsModel> varstats) {
		this.stream = stream;
		this.entity = entity;
		this.date = date;
		this.sigcounts = sigcounts;
		this.varstats = varstats;
	}


	public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getEntity() {
        return entity;
    }

    public void setEntity(int entity) {
        this.entity = entity;
    }

    public int getStream() {
        return stream;
    }

    public void setStream(int stream) {
        this.stream = stream;
    }



	public Map<Integer, Integer> getSigcounts() {
		return sigcounts;
	}



	public void setSigcounts(Map<Integer, Integer> sigcounts) {
		this.sigcounts = sigcounts;
	}



	public Map<Integer, EntityVarStatsModel> getVarstats() {
		return varstats;
	}



	public void setVarstats(Map<Integer, EntityVarStatsModel> varstats) {
		this.varstats = varstats;
	}
    
    


    


  
}


