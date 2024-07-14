package com.dunkware.xstream.model.snapshot.factories;

import java.time.LocalDateTime;
import java.util.List;

import com.dunkware.xstream.model.snapshot.SnapshotEntity;

public class SnapshotEntityFactoryTest {

	public static void main(String[] args) {
		try {
			List<SnapshotEntity> results = SnapshotEntityFactory.instance().entityCount(5).streamId(1).varCount(5).timeRange(LocalDateTime.now(), LocalDateTime.now().plusSeconds(60)).build();	
			for (SnapshotEntity snapshotEntity : results) {
				System.out.println(snapshotEntity.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
	}
}
