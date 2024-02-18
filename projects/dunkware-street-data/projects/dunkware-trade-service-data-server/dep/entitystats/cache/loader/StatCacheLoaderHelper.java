package com.dunkware.trade.net.data.server.stream.entitystats.cache.loader;

import java.util.ArrayList;
import java.util.List;

public class StatCacheLoaderHelper {

	public static List<StatCacheLoaderBatch> buildLoaderBatchSet(int ENTITY_ID_HIGH, int ENTITY_BATCH_SIZE) { 
		List<StatCacheLoaderBatch> batchSet = new ArrayList<StatCacheLoaderBatch>();
		int currentEntity = 1; 
		while(currentEntity != ENTITY_ID_HIGH) { 
			int batchSizeCount = 0;
			int batchStart = currentEntity;
			while(batchSizeCount < ENTITY_BATCH_SIZE) { 
				batchSizeCount++;
				
				if(currentEntity == ENTITY_ID_HIGH) { 
					StatCacheLoaderBatch batch = new StatCacheLoaderBatch(batchStart,currentEntity);
					batchSet.add(batch);
					return batchSet;
				}
				currentEntity = currentEntity + 1;
			}
			StatCacheLoaderBatch batch = new StatCacheLoaderBatch(batchStart,currentEntity -1);
			batchSet.add(batch);
		}
		return batchSet;
	}

}
