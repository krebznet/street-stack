package com.dunkware.stream.data.cassy.helpers;

import com.dunkware.stream.data.cassy.entity.sesion.StreamSessionKey;
import com.dunkware.stream.data.cassy.entity.sesion.StreamSessionRow;
import com.dunkware.stream.data.model.session.StreamSessionModel;

public class StreamSessionRowHelper {
	
	
	
	public static StreamSessionModel toModel(StreamSessionRow row) { 
		StreamSessionModel mod = new StreamSessionModel();
		mod.setDate(row.getKey().getDate());
		mod.setEntities(row.getEntities());
		mod.setVars(row.getVars());
		mod.setStream(row.getKey().getStream());
		mod.setSignals(row.getSignals());
		mod.setStats(row.getStats());
		mod.setStart(row.getStart());
		mod.setStop(row.getStop());
		return mod;
	}
	
	
	public static StreamSessionRow toRow(StreamSessionModel model) { 
		StreamSessionRow row = new StreamSessionRow();
		StreamSessionKey key = new StreamSessionKey(model.getStream(), model.getDate());
		row.setKey(key);
		row.setEntities(model.getEntities());
		row.setSignals(model.getSignals());
		row.setVars(model.getVars());
		row.setStart(model.getStart());
		row.setStop(model.getStop());
		return row;
	}

}
