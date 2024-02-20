package com.dunkware.trade.service.stream.worker.session.publish;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.stream.data.session.meta.SessionMetaEntity;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntity;

public class SessionMetaEntityCollector {
	
	
	
	public static List<SessionMetaEntity> collect(XStream stream, List<Integer> stats) { 
		List<SessionMetaEntity> results = new ArrayList<SessionMetaEntity>();
		for (XStreamEntity ent : stream.getRows()) {
			SessionMetaEntity metaEnt = new SessionMetaEntity();
			metaEnt.setDate(stream.getInput().getDate().get());
			metaEnt.setStart(stream.getClock().getStartTime());
			metaEnt.setStop(stream.getClock().getLocalTime());
			metaEnt.setEntity(ent.getIdentifier());
			metaEnt.setSignals(stream.getInput().getScript().getStreamSignalIds());
			metaEnt.setVars(stream.getInput().getScript().getStreamVarIds());
			metaEnt.setStats(stats);
			metaEnt.setStream((int)stream.getInput().getId());
			results.add(metaEnt);
		}
		
		return results;
	}
	

}
