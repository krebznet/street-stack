package com.dunkware.stream.data.lib.cache.live;

import java.util.Map;

import org.redisson.Redisson;
import org.redisson.api.RLiveObjectService;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import com.dunkware.utils.core.stopwatch.StopWatch;

public class LIveEntityTest {

	public static void main(String[] args) {
		//6379
		try {
			Config config = new Config();
	    	//config.useSingleServer().setAddress("redis://127.0.0.1:6379");
	    	config.useSingleServer().setAddress("redis://testrock1.dunkware.net:32595");
	    	//testrock1.dunkware.net"
	        RedissonClient redisson = Redisson.create(config);

	        StopWatch stopWatch = StopWatch.newInstance();
	        stopWatch.start();
	        RLiveObjectService liveObjectService = redisson.getLiveObjectService();
	        LiveEntity liveTest = liveObjectService.get(LiveEntity.class, 2261L);
	       Thread.sleep(1000);
	        System.out.println(liveTest.getIdent());
	        stopWatch.stop();
	        Map<String,Number> shit = liveTest.getVariables();

	        System.out.println(stopWatch.seconds());
	        for (String key : liveTest.getVariables().keySet()) {
				System.out.println(key + " " + shit.get(key).toString());
			}
	      //  System.out.println(liveTest.getVariables().toString());
	        
	        redisson.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	   	
	}
}
