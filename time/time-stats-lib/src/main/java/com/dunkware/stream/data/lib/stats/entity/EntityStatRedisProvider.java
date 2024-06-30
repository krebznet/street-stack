package com.dunkware.stream.data.lib.stats.entity;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.utils.core.concurrent.DunkExecutor;
import com.dunkware.utils.redis.JedisPoolBuilder;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class EntityStatRedisProvider implements EntityStatProvider  {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("EntityStatsRedisProvider");
	
	private JedisPool pool;
	private DunkExecutor executor;

	public void start(String rediHost, int redisPort) throws Exception {
		try {
			executor = DunkExecutor.build(30);
			pool = JedisPoolBuilder.newBuilder().serverAndPort(rediHost, redisPort).build();
		} catch (Exception e) {
			throw new Exception("Jedis Pool Init Failure " + e.toString());
		}
	}
	
	

	@Override
	public Future<EntityStatResp> request(EntityStatReq request) {
		Promise<EntityStatResp> promise = Promise.promise();
		Future<EntityStatResp> future = promise.future();
		EntityStatRequestHandler handler = new EntityStatRequestHandler(promise, request);
		executor.execute(handler);
		return future;
		
	}


	private class EntityStatRequestHandler implements Runnable { 
		
		private Promise<EntityStatResp> promise; 
		private EntityStatReq request; 
		
		public EntityStatRequestHandler(Promise<EntityStatResp> promise, EntityStatReq req) {
			this.promise = promise;
			this.request = req;
		}
		
		public void run() { 
			
			Jedis jedis = pool.getResource();
			EntityStatIterator iterator = EntityStatIterator.build(jedis, request.getStream(), request.getDate(),
					request.getDays(), request.getEntity(), request.getElement(), request.getStat());
			List<Double> values;
			
			try {
				values = iterator.getList(request.getDays());
				//System.out.println("returned values " + values.toString());
				switch (request.getAgg()) {
				case EntityStatReq.AggHigh:
					double high = 0;
					for (int i = 0; i < values.size(); i++) {
						if (i == 0) {
							high = values.get(i);
							continue;
						}
						if (values.get(i) > high) {
							high = values.get(i);
						}
					}
					pool.returnObject(jedis);
					promise.complete(new EntityStatResp(high));
					return;
				case EntityStatReq.AggLow:
					double low = 0;
					for (int i = 0; i < values.size(); i++) {
						if (i == 0) {
							low = values.get(i);
							continue;
						}
						if (values.get(i) < low) {
							high = values.get(i);
						}
					}
					pool.returnObject(jedis);
					promise.complete(new EntityStatResp(low));
					return;
				case EntityStatReq.AggSum:
					double sum = 0;
					for (Double double1 : values) {
						sum = sum + double1;
					}
					pool.returnObject(jedis);
					promise.complete(new EntityStatResp(sum));
				default:
					logger.error(marker, "Stat Type " + request.getAgg() + " not handled ");
					pool.returnObject(jedis);
					promise.fail(new Exception("Agg Type Not Handled"));

				}
			} catch (Exception e) {
				pool.returnObject(jedis);
				EntityStatResp resp = new EntityStatResp();
				resp.setResolved(false);
				logger.error(marker, "Outer Exception " + e.toString(), e);
				promise.fail(e);
			}

		}
	}


	

}
