package com.dunkware.trade.service.data.common.factory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dunkware.xstream.model.entity.EntityVarSnapshot;
import com.google.common.util.concurrent.AtomicDouble;

public class EntityVarSnapshotFactory {
	
	
	
	public static void main(String[] args) {
		EntityVarSnapshotFactoryInput in = new  EntityVarSnapshotFactoryInput();
		in.setStartTime(LocalDateTime.of(2000, 1, 1, 9, 30, 0));
		in.setTimeRange(60);
		in.setVarCount(1);
		List<EntityVarSnapshot> snapshots = createSnapshots(in);
		for (EntityVarSnapshot entityVarSnapshot : snapshots) {
			System.out.println(entityVarSnapshot.toString());
		}
	}
	
	

	public static List<EntityVarSnapshot> createSnapshots(EntityVarSnapshotFactoryInput input) { 
		List<EntityVarSnapshot>	 results = new ArrayList<EntityVarSnapshot>();
		int durationCount = 0;
		AtomicDouble varValue = new AtomicDouble(0);
		while(durationCount < input.getTimeRange()) {
			int varCount = 0;
			varValue.addAndGet(1.5);
			LocalDateTime snapshotDateTime = input.getStartTime().plusSeconds(durationCount);
			while(varCount < input.getVarCount()) { 
				EntityVarSnapshot snapshot = new EntityVarSnapshot();
				snapshot.setDateTime(snapshotDateTime);
				snapshot.setId(varCount);
				snapshot.setIdent("IDENT" + varCount);
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
