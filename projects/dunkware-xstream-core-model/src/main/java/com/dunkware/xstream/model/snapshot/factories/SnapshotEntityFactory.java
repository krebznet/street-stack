package com.dunkware.xstream.model.snapshot.factories;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.time.DunkTime;
import com.dunkware.xstream.model.snapshot.SnapshotEntity;

public class SnapshotEntityFactory {
	
	
	public static SnapshotEntityFactory instance() { 
		return new SnapshotEntityFactory();
	}
	
	
	private int entityCount; 
	private int varCount; 
	private LocalDateTime startDuration; 
	private LocalDateTime stopDuration; 
	private int streamId; 
	
	public SnapshotEntityFactory streamId(int id) { 
		this.streamId = id; 
		return this;
	}
	
	public SnapshotEntityFactory entityCount(int entities) { 
		this.entityCount = entities; 
		return this;
	}
	
	public SnapshotEntityFactory varCount(int vars) { 
		this.varCount = vars;
		return this;
	}
	
	public SnapshotEntityFactory timeRange(LocalDateTime start, LocalDateTime stop) { 
		this.startDuration = start;
		this.stopDuration = stop;
		return this; 
	}
	
	public List<SnapshotEntity> build() throws Exception { 
		List<SnapshotEntity> results = new ArrayList<SnapshotEntity>();
		LocalDateTime currentTime = startDuration;
		while(currentTime.isAfter(stopDuration) == false) { 
			int i = 0;
			while(i < entityCount) { 
				SnapshotEntity entity = new SnapshotEntity();
				entity.setTimestamp(DunkTime.toMilliseconds(currentTime));
				entity.setEntity(i);
				i++;
				entity.setStream(streamId);
				int x = 0;
				while(x < varCount) { 
					entity.getNumericVars().put(x, Integer.valueOf(x));
					x++;
				}
				
				results.add(entity);
			}
			currentTime = currentTime.plusSeconds(1);
			
		}
		return results;
		
		
	}

}
