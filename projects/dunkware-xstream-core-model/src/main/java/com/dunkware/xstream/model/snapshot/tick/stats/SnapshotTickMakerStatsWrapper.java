package com.dunkware.xstream.model.snapshot.tick.stats;

import java.time.LocalTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.utils.core.helpers.DunkNumber;

/**
 * Wraps SnapshotTickMakerStats POJO for helper and utility methods.
 * 
 * @author Duncan Krebs
 *
 */
public class SnapshotTickMakerStatsWrapper {

	public static SnapshotTickMakerStatsWrapper newInstance() {
		return new SnapshotTickMakerStatsWrapper();
	}

	private SnapshotTickMakerStats stats;

	private SnapshotTickMakerStatsWrapper() {
		this.stats = new SnapshotTickMakerStats();
	}

	public SnapshotTickMakerStats getStats() {
		return stats;
	}
	
	public Map<Integer, Number> entitySecond(int entityId, Map<Integer, Number> values,
			LocalTime time) {
		SnapshotTickMakerEntityStats entityStats = stats.getEntityStats().get(entityId);
		if (entityStats == null) {
			entityStats = new SnapshotTickMakerEntityStats();
			entityStats.setEntityId(entityId);
			entityStats.setFirstTime(time);
			entityStats.setLastTime(time);
			entityStats.setSeconds(1);
			for (Integer varId : values.keySet()) {
				entityStats.getVarSnapshotsFirstTime().put(varId, time);
				entityStats.getVarSnapshotsLastTime().put(varId, time);
				entityStats.getVarSnapshotsLastValue().put(varId, values.get(varId));
				entityStats.getVarSnapshotsCount().put(varId, 1);
			}
			stats.getEntityStats().put(entityId, entityStats);
			return values;
		}
		// else not null; 
		if(entityStats.getLastTime().plusSeconds(1) != time) { 
			entityStats.setSkippedSeconds(entityStats.getSkippedSeconds() + 1);
		}
		entityStats.setLastTime(time);
		entityStats.setSeconds(entityStats.getSeconds() + 1);
		Map<Integer,Number> updatedVars = new ConcurrentHashMap<Integer,Number>();
		for (Integer varId : values.keySet()) {
			Number lastUpdate = entityStats.getVarSnapshotsLastValue().get(varId);
			// this is when a new variable was set post first snapshot with delay 
			if(lastUpdate == null) { 
				entityStats.getVarSnapshotsFirstTime().put(varId, time);
				entityStats.getVarSnapshotsLastTime().put(varId, time);
				entityStats.getVarSnapshotsLastValue().put(varId, values.get(varId));
				entityStats.getVarSnapshotsCount().put(varId, 1);
				updatedVars.put(varId, values.get(varId));
			} else { 
				// check if current value is different than last updated var value 
				if(DunkNumber.compare(lastUpdate, values.get(varId)) != 0) { 
					entityStats.getVarSnapshotsLastTime().put(varId, time);
					entityStats.getVarSnapshotsLastValue().put(varId, values.get(varId));
					entityStats.getVarSnapshotsCount().put(varId, entityStats.getVarSnapshotsCount().get(varId) + 1);
					updatedVars.put(varId, values.get(varId));
				}
			}
			
		}
		return updatedVars;
	}
	
	

}
