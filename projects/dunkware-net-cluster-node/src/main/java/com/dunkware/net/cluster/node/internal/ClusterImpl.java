package com.dunkware.net.cluster.node.internal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.events.DEventTree;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.net.cluster.GClusterEvent;
import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.net.cluster.node.ClusterJob;
import com.dunkware.net.cluster.node.ClusterJobRunner;
import com.dunkware.net.cluster.util.helpers.ClusterEventHelper;

public class ClusterImpl implements Cluster {
	
	@Autowired
	private ClusterConfig clusterConfig; 
	
	@Autowired
	private ApplicationContext ac;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private ClusterRegistry registry; 
	
	private DEventTree eventTree; 
	
	private DExecutor executor;
	
	private DKafkaByteProducer eventProducer; 
	
	private List<ClusterJob> jobs = new ArrayList<ClusterJob>();
	private Semaphore jobLock = new Semaphore(1);
	
	@PostConstruct
	public void load() { 
		registry = new ClusterRegistry();
		executor = new DExecutor(15);
		eventTree = DEventTree.newInstance(executor);
		
		try {
			eventProducer = DKafkaByteProducer.newInstance(clusterConfig.getServerBrokers(), "cluster_core_events", "ClusterNode-" + getNodeId());
		} catch (Exception e) {
			logger.error(MarkerFactory.getMarker("Crash"),"Exception creating cluster event producer " + e.toString());
			System.exit(-1);
		}
	}
	
	public ClusterRegistry getRegistry() { 
		return registry; 
	}
	
	@Override
	public DEventTree getEventTree() {
		return eventTree;
	}

	@Override
	public DExecutor getExecutor() {
		return executor;
	}

	@Override
	public String getNodeId() {
		return clusterConfig.getNodeId();
	}
	
	
	@Override
	public LocalDateTime now() {
		return LocalDateTime.now(DTimeZone.toZoneId(DTimeZone.NewYork));
	}

	@Override
	public ClusterJob startJob(ClusterJobRunner runner, String type, String name) throws Exception {
		try {
			jobLock.acquire();
			ClusterJobImpl job = new ClusterJobImpl();
			ac.getAutowireCapableBeanFactory().autowireBean(job);
			job.start(runner, type, name);
			jobs.add(job);
			return job;
		} catch (Exception e) {
			throw e;
		} finally {
			jobLock.release();
		}
		
	}
	

	@Override
	public void pojoEvent(Object pojo) throws Exception {
		GClusterEvent event = ClusterEventHelper.pojoEvent(pojo, getNodeId());
		eventProducer.sendBytes(event.toByteArray());
		if(logger.isTraceEnabled()) { 
			logger.trace("Pojo Event Published {} node {}",pojo.getClass().getName(),getNodeId());
		}
	}

	@Override
	public void addComponent(Object component) {
		registry.addComponent(component);;
		
	}

	@Override
	public void removeComponent(Object component) {
		registry.removeComponent(component);
		
	}
	
	
	
	
	
	
	

	
	
	

	
	
	
	
	
	
	

}
