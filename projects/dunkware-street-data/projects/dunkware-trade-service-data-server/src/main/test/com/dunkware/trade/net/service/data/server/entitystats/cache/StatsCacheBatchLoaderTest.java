package com.dunkware.trade.net.service.data.server.entitystats.cache;

import java.util.List;

import org.junit.Test;

import com.dunkware.trade.net.data.server.stream.entitystats.cache.loader.StatCacheLoaderBatch;
import com.dunkware.trade.net.data.server.stream.entitystats.cache.loader.StatCacheLoaderHelper;

import junit.framework.TestCase;


public class StatsCacheBatchLoaderTest extends TestCase {

	
	public static void main(String[] args) {
		StatsCacheBatchLoaderTest t = new StatsCacheBatchLoaderTest();
		try {
			t.testBatchBuilder();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	@Test
	public void testBatchBuilder() throws Exception { 

	int maxEntityId = 12000;
		int batchSize = 100; 
		List<StatCacheLoaderBatch> cacheLoaderBatchs  = StatCacheLoaderHelper.buildLoaderBatchSet(maxEntityId, batchSize);
		for (StatCacheLoaderBatch statCacheLoaderBatch : cacheLoaderBatchs) {
			System.out.println(statCacheLoaderBatch.getEntityStart() + " " + statCacheLoaderBatch.getEntityStop());
		}
		
	}
}
