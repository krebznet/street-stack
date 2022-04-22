package com.dunkware.net.cluster.node.internal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.events.DEventTree;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.common.util.logging.Markers;
import com.dunkware.net.cluster.json.job.ClusterJobState;
import com.dunkware.net.cluster.json.node.ClusterNodeState;
import com.dunkware.net.cluster.json.node.ClusterNodeStats;
import com.dunkware.net.cluster.json.node.ClusterNodeType;
import com.dunkware.net.cluster.json.node.ClusterNodeUpdate;
import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.net.cluster.node.ClusterJob;
import com.dunkware.net.cluster.node.ClusterJobRunner;
import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.net.cluster.node.ClusterNodeException;
import com.dunkware.net.cluster.util.helpers.ClusterEventHelper;
import com.dunkware.net.proto.cluster.GClusterEvent;

@Service
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

	private DDateTime startTime;
	
	private ClusterEventService eventService;

	private List<ClusterJob> jobs = new ArrayList<ClusterJob>();

	private Map<String, ClusterNode> nodes = new ConcurrentHashMap<String, ClusterNode>();

	private Semaphore jobLock = new Semaphore(1);
	
	private ClusterStatsPublisher statsPublisher;

	@PostConstruct
	public void load() {
		startTime = DDateTime.now(DTimeZone.NewYork);
		registry = new ClusterRegistry();
		executor = new DExecutor(15);
		eventTree = DEventTree.newInstance(executor);
		eventService = new ClusterEventService();
		ac.getAutowireCapableBeanFactory().autowireBean(eventService);
		try {
			eventService.start(this);
		} catch (Exception e) {
			logger.error(MarkerFactory.getMarker("Crash"), "Event Service Failed to start " + e.toString());
			System.exit(-1);
		}
		
		try {
			eventProducer = DKafkaByteProducer.newInstance(clusterConfig.getServerBrokers(), "cluster_core_events",
					"ClusterNode-" + getNodeId());
		} catch (Exception e) {
			logger.error(MarkerFactory.getMarker("Crash"), "Exception creating cluster event producer " + e.toString());
			System.exit(-1);
		}
		
		try {
			statsPublisher = new ClusterStatsPublisher();
			ac.getAutowireCapableBeanFactory().autowireBean(statsPublisher);
			statsPublisher.start();
		} catch (Exception e) {
			logger.error(Markers.systemCrash(),"Exception starting cluster node stats publisher " + e.toString());
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
	public ClusterJob startJob(ClusterJobRunner runner, String type, String name) throws ClusterNodeException {
		try {
			jobLock.acquire();
			ClusterJobImpl job = new ClusterJobImpl();
			ac.getAutowireCapableBeanFactory().autowireBean(job);
			job.start(runner, type, name);
			jobs.add(job);
			return job;
		} catch (Exception e) {
			throw new ClusterNodeException("Start Job rinner " + runner.getClass().getName() + e.toString(), e);
		} finally {
			jobLock.release();
		}

	}

	@Override
	public void pojoEvent(Object pojo) throws ClusterNodeException {
		try {
			GClusterEvent event = ClusterEventHelper.pojoEvent(pojo, getNodeId());
			eventProducer.sendBytes(event.toByteArray());
		} catch (Exception e) {
			throw new ClusterNodeException("Exception sending pojo event " + e.toString(), e);
		}

		if (logger.isTraceEnabled()) {
			logger.trace("Pojo Event Published {} node {}", pojo.getClass().getName(), getNodeId());
		}
	}

	@Override
	public void addComponent(Object component) {
		registry.addComponent(component);
		;

	}

	@Override
	public void removeComponent(Object component) {
		registry.removeComponent(component);

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
	public List<ClusterNode> getAvailableWorkerNodes() {
		List<ClusterNode> results = new ArrayList<ClusterNode>();
		for (String key : nodes.keySet()) {
			ClusterNode node = nodes.get(key);
			if (node.getState() == ClusterNodeState.Available) {
				results.add(node);
			}
		}
		return results;
	}
	
	@Override
	public boolean nodeExists(String id) {
		if(nodes.get(id) == null)
			return false;
		return true;
	}

	@Override
	public ClusterNode getNode(String id) throws ClusterNodeException {
		ClusterNode node = nodes.get(id);
		if(node == null) {
			throw new ClusterNodeException("Node " + id + " does not exist");
		}
		return node;
	}

	@Override
	public List<ClusterNode> getAvailablWorkereNodes(int count) throws ClusterNodeException {
		List<ClusterNode> results = new ArrayList<ClusterNode>();
		int resultCount = 0;
		for (String key : nodes.keySet()) {
			ClusterNode node = nodes.get(key);
			if (node.getState() == ClusterNodeState.Available) {
				if (node.getType() == ClusterNodeType.Worker) {
					results.add(node);
					resultCount++;
					if (resultCount == count) {
						return results;
					}
				}
			}
		}
		throw new ClusterNodeException(
				"Requested " + count + " avaiable nodes is bigger than nodes avaiable which is " + results.size());
	}

	@Override
	public int getAvailablWorkereNodeCount() {
		return getAvailableWorkerNodes().size();
	}

	@Override
	public ClusterNodeStats getStats() {
		ClusterNodeStats stats = new ClusterNodeStats();
		stats.setGrpcEndpoint(clusterConfig.getServerGrpc());
		stats.setHttpEndpoint(clusterConfig.getServerHttp());
		stats.setId(getNodeId());
		stats.setStart(startTime.toString());
		stats.setExecutorStats(executor.getStats());
		int activeJobs = 0;
		for (ClusterJob clusterJob : jobs) {
			if (clusterJob.getState() == ClusterJobState.Running) {
				activeJobs++;
			}
		}
		stats.setRunningJobCount(activeJobs);
		stats.setType(clusterConfig.getNodeType());
		return stats;
	}

	void nodeUpdates(List<ClusterNodeUpdate> updates) {
		Runnable nodeUpdater = new Runnable() {
			
			@Override
			public void run() {
				for (ClusterNodeUpdate update : updates) {
					ClusterNode node = nodes.get(update.getNode());
					if(node == null) { 
						// log new node
						node = new ClusterNodeImpl();
						ClusterNodeImpl impl = (ClusterNodeImpl)node;
						impl.start(update);;
						nodes.put(update.getNode(), node);
						
					} else {
						ClusterNodeImpl impl = (ClusterNodeImpl)node;
						impl.update(update);
					}
					
				}
			}
		};
		getExecutor().execute(nodeUpdater);
	}

	

}
