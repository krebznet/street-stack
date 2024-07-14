package com.dunkware.stream.data.cassy.helpers;

import com.dunkware.stream.data.cassy.entity.StreamSession;
import com.dunkware.stream.data.cassy.entity.StreamSessionKey;
import com.dunkware.time.data.model.session.StreamSessionModel;

public class StreamSessionRowHelper {
	
	
	
	public static StreamSessionModel toModel(StreamSession row) { 
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
	
	
	public static StreamSession toRow(StreamSessionModel model) { 
		StreamSession row = new StreamSession();
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
