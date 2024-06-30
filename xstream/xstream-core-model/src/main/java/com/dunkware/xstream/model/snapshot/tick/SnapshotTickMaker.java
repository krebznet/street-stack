package com.dunkware.xstream.model.snapshot.tick;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import com.dunkware.utils.tick.proto.TickProto.Tick;
import com.dunkware.xstream.model.snapshot.tick.stats.SnapshotTickMakerSignalStats;
import com.dunkware.xstream.model.snapshot.tick.stats.SnapshotTickMakerStats;
import com.dunkware.xstream.model.snapshot.tick.stats.SnapshotTickMakerStatsWrapper;

/**
 * This is used on the context of a running xstream to generate snapshot ticks for entity snapshots and signals and keeps a state of metrics and stats
 *
 */
public class SnapshotTickMaker {

	public static SnapshotTickMaker newInstance(int streamId, LocalDate date) { 
		return new SnapshotTickMaker(streamId,date);
	}
	
	private int streamId; 
	private LocalDate date;
	private SnapshotTickMakerStatsWrapper statsWrapper; 
	
	/**
	 * Pass in the stream map 
	 * @param streamId
	 */
	private SnapshotTickMaker(int streamId, LocalDate date) { 
		this.streamId = streamId;
		this.date = date; 
		statsWrapper = SnapshotTickMakerStatsWrapper.newInstance();
	}
	
	public SnapshotTickMakerStats getStats() { 
		return statsWrapper.getStats();
	}
	
	public Tick buildSnapshotEntityTick(int entityId, Map<Integer,Number> vars, LocalTime time) { 
		Map<Integer,Number> updates = statsWrapper.entitySecond(entityId, vars, time);
		//TickBuilder tb = TickBuilder.newBuilder(SnapshotTick.TYPE_ENTITY_SNAPSHOT);
		//tb.longField(SnapshotTick.FIELD_TIMESTAMP, DunkTime.toMilliseconds(LocalDateTime.of(date, time)));
		// collision with shit
		//for (Integer varId : updates.keySet()) {
		//	tb.doubleField(varId, updates.get(varId).doubleValue());
		//}
		//Tick tick = tb.build();
		//return tick;
		return null;
	}
	
	public Tick buildSnapshotSignalTick(int entityId, int signalId, Map<Integer,Number> vars, LocalTime time) {
		SnapshotTickMakerSignalStats sigStats = statsWrapper.getStats().getSignalStats().get(signalId);
		if(sigStats == null) { 
			sigStats = new SnapshotTickMakerSignalStats();
			sigStats.setSignalCount(1);
			sigStats.getEntityCounts().put(entityId, 1);
			sigStats.setLastSignalTime(time);
			sigStats.setSignalId(signalId);
			statsWrapper.getStats().getSignalStats().put(signalId, sigStats);
		} else { 
			sigStats.setSignalCount(sigStats.getSignalCount() + 1);
			sigStats.setLastSignalTime(time);
			Integer entityCount = sigStats.getEntityCounts().get(entityId);
			if(entityCount == null) { 
				sigStats.getEntityCounts().put(entityId, 1);
			} else { 
				sigStats.getEntityCounts().put(entityCount, entityCount + 1);
			}
			statsWrapper.getStats().getSignalStats().put(signalId,sigStats);
			
		}
		//TickBuilder tb = TickBuilder.newBuilder(SnapshotTick.TYPE_ENTITY_SIGNAL);
		//tb.intField(SnapshotTick.FIELD_SIGNAL_ID,signalId).intField(SnapshotTick.FIELD_ENTITY_ID, entityId).build();
		
		return null;
	}
	
	
	
}
