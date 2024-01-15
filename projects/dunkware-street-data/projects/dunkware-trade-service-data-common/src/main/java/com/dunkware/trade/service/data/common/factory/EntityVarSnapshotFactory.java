package com.dunkware.trade.service.data.common.factory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dunkware.xstream.model.snapshot.SnapshotVariable;
import com.google.common.util.concurrent.AtomicDouble;

public class EntityVarSnapshotFactory {
	
	
	
	public static void main(String[] args) {
		EntityVarSnapshotFactoryInput in = new  EntityVarSnapshotFactoryInput();
		in.setStartTime(LocalDateTime.of(2000, 1, 1, 9, 30, 0));
		in.setTimeRange(60);
		in.setVarCount(1);
		List<SnapshotVariable> snapshots = createSnapshots(in);
		for (SnapshotVariable entityVarSnapshot : snapshots) {
			System.out.println(entityVarSnapshot.toString());
		}
	}
	
	

	public static List<SnapshotVariable> createSnapshots(EntityVarSnapshotFactoryInput input) { 
		List<SnapshotVariable>	 results = new ArrayList<SnapshotVariable>();
		int durationCount = 0;
		AtomicDouble varValue = new AtomicDouble(0);
		while(durationCount < input.getTimeRange()) {
			int varCount = 0;
			varValue.addAndGet(1.5);
			LocalDateTime snapshotDateTime = input.getStartTime().plusSeconds(durationCount);
			while(varCount < input.getVarCount()) { 
				SnapshotVariable snapshot = new SnapshotVariable();
				snapshot.setTimestamp(durationCount);
				snapshot.setVar(varCount);
				snapshot.setStream(1);
				snapshot.setEntity(durationCount);
				snapshot.setValue(varValue.get());
				Map<String,String> vars = new HashMap<String,String>();
				
				results.add(snapshot);
				varCount++;
			}
			durationCount++;
		}
		return results;
		
	}

}
