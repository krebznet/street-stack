package com.dunkware.trade.service.data.common.factory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.trade.service.data.common.StreamData;
import com.dunkware.trade.service.data.common.reddis.RedisSnapshotWriter;
import com.dunkware.xstream.model.entity.EntitySnapshot;

import redis.clients.jedis.Jedis;

public class EntitySnapshotFactory {
	
	public static void main(String[] args) {
		EntitySnapshotFactoryInput i = new EntitySnapshotFactoryInput();
		i.setDurationSeconds(180);
		i.setStartDateTime(LocalDateTime.of(2023, 3, 3, 3, 3));
		i.setEntityCount(500);
		i.setVarCount(78);
		i.setStream(1);
		List<EntitySnapshot> snapshots = createSnapshots(i);
		System.out.println(snapshots.size());
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			RedisSnapshotWriter writer = new RedisSnapshotWriter();
			writer.open("testrock1.dunkware.net",30379,"TaSnanbP5WQirwrw",8);
			DStopWatch watch = DStopWatch.create();
			watch.start();
			//writer.write(snapshots);
		//	writer.close();
			watch.stop();
		//	System.out.println("wrote " + snapshots.size() + "snapshots in " + watch.getCompletedSeconds() + " seconds");
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		try {
			Jedis jedis = new Jedis("testrock1.dunkware.net",30379);
			jedis.auth("TaSnanbP5WQirwrw");
			jedis.connect();
			
			DStopWatch s = DStopWatch.create();
					s.start();
			for (EntitySnapshot snapshot : snapshots) {
				String key = StreamData.snapshotKey(snapshot);
			//	System.out.println(key);
				Map<?,?> fuck = jedis.hgetAll(key);
				
				//Map<String,String> varMap = jedis.hgetAll(key);
				//String varString = "";
				//for (Object keySet: fuck.keySet()) {
				//	varString = varString + keySet.toString() + "=" + fuck.get(keySet).toString();
				//}
			//	System.out.println("Entity snapshot time " + snapshot.getDateTime().toString() + " entity " + snapshot.getEntity() + varString); 
				
				
			}
			s.stop();
			System.out.println("Read + " +  snapshots.size() + " snapshots in " + s.getCompletedSeconds());
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	
	}
	
	
	public static List<EntitySnapshot> createSnapshots(EntitySnapshotFactoryInput input) { 
		List<EntitySnapshot> results = new ArrayList<EntitySnapshot>();
		int durationCount = 0;
		while(durationCount < input.getDurationSeconds()) {
			int entityCount  = 0;
			LocalDateTime snapshotDateTime = input.getStartDateTime().plusSeconds(durationCount);
			while(entityCount < input.getEntityCount()) { 
				EntitySnapshot snapshot = new EntitySnapshot();
				snapshot.setDateTime(snapshotDateTime);
				snapshot.setEntity(entityCount);
				snapshot.setStream(input.getStream());
				Map<String,String> vars = new HashMap<String,String>();
				int varCount = 0;
				while(varCount < input.getVarCount()) { 
					vars.put(String.valueOf(varCount), String.valueOf(durationCount));
					varCount++;
				}
				snapshot.setVars(vars);
				results.add(snapshot);
				entityCount++;
			}
			durationCount++;
		}
		
		
		return results;
		
	}

}
