package com.dunkware.xstream.model.snapshot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dunkware.common.util.time.DunkTime;
import com.dunkware.xstream.model.signal.StreamEntitySignal;

public class SnapshotHelper {
	
	
	public static String kafkaSnapshotTopic(String streamIdent) { 
		return new StringBuilder().append("stream_snapshot_").append(streamIdent).toString();
	}
	
	public static SnapshotValue snapshotValue(StreamEntitySignal signal) {
		SnapshotSignal sig = new SnapshotSignal();
		sig.setEntity(signal.getEntity());
		sig.setStreamId(sig.getStreamId());
		sig.setTimestamp(DunkTime.toMilliseconds(signal.getDateTime()));
		SnapshotValue value = new SnapshotValue();
		value.setType(SnapshotType.SIGNAL);
		value.setSignal(sig);
		return value; 
	}
	
	
	public static SnapshotValue snapshotValue(int streamId, int entityId, Map<Integer,Number> numericVars, LocalDateTime dateTime) { 
		SnapshotEntity entity = new SnapshotEntity();
		entity.setStream(streamId);
		entity.setNumericVars(numericVars);
		entity.setEntity(entityId);
		entity.setTimestamp(DunkTime.toMilliseconds(dateTime));
		SnapshotValue value = new SnapshotValue();
		value.setType(SnapshotType.ENTITY);
		value.setEntity(entity);
		return value;
	}
	
	public static List<SnapshotVariable> snapshotVariables(SnapshotEntity entity) { 
		List<SnapshotVariable> vars = new ArrayList<SnapshotVariable>();
		for (Integer key : entity.getNumericVars().keySet()) {
			double value = entity.getNumericVars().get(key).doubleValue();
			SnapshotVariable var = new SnapshotVariable();
			var.setEntity(entity.getEntity());
			var.setStream(entity.getStream());
			var.setTimestamp(entity.getTimestamp());
			var.setValue(value);;
			vars.add(var);
		}
		
		return vars;
	}

}
