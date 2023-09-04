package com.dunkware.xstream.stats.builders;

import java.util.List;

import com.dunkware.xstream.api.XStreamEntityVar;
import com.dunkware.xstream.model.stats.entity.EntityStat;

public interface EntityVarStatBuilder {

	public void start(XStreamEntityVar var); 
	
	public void stop();
	
	public void collect(List<EntityStat> stats);
	
}
