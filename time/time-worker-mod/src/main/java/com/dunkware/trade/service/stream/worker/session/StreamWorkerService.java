package com.dunkware.trade.service.stream.worker.session;

import javax.annotation.PostConstruct;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.anot.ADunkNetChannel;
import com.dunkware.spring.runtime.services.ExecutorService;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerCreateReq;

@Service()
public class StreamWorkerService  {
	
	@Autowired
	private ApplicationContext ac;
	
	@Autowired
	private DunkNet dunkNet;
	
	@Autowired
	private ExecutorService executorService;
	
	private RedissonClient redissonClient;  
	
	//@Value("${redis.url}") 
	//private String redisUrl; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamWorkerService");
	
	@PostConstruct
	private void init() {
		try {
			try {
			//	Config config = new Config();
			//	config.useSingleServer().setAddress(redisUrl);
			//	redissonClient = Redisson.create(config);
				dunkNet.extensions().addExtension(StreamWorkerService.this);	
			} catch (Exception e) {
				//logger.error("Worker server create to redis server failed " + redisUrl + e.toString()); 
				System.err.println("Worker server create failed redis sever " + e.toString());
				System.exit(-1);
			}
				
		} catch (Exception e) {
			logger.error("Exception adding stream worker service extensions "
		+ e.toString(),e);
			// TODO: handle exception
		}
		
		
		
	
		
	}
	
//	public RedissonClient getRedissonClient() { 
	//	return redissonClient;
	//}
	
	@ADunkNetChannel(label = "Create Stream Session Worker Channel")
	public StreamWorker workerChannel(StreamSessionWorkerCreateReq req) throws Exception { 
		StreamWorker node = new StreamWorker();
		//ac.getAutowireCapableBeanFactory().autowireBean(node);
		node.init(dunkNet,executorService,this);
		return node;
	}
	

	
	

}
