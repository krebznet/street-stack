package com.dunkware.trade.service.stream.server.cluster.impl;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler;
import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.stream.json.cluster.spec.ClusterNodeStatsSpec;
import com.dunkware.trade.service.stream.json.cluster.spec.ClusterNodeStatus;
import com.dunkware.trade.service.stream.server.cluster.ClusterException;
import com.dunkware.trade.service.stream.server.cluster.ClusterNode;
import com.dunkware.trade.service.stream.server.cluster.ClusterService;

/**
 * Implementation of Cluster Service, I hope you make this component reusable
 * Duncan. I don't have time to make its own component etc. But spring boot
 * component-cluster would be nice.
 * 
 * @author dkrebs
 *
 */
@Component()
@Profile("Cluster")
public class ClusterServiceImpl implements ClusterService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${kafka.brokers}")
	private String clusterBrokers;

	@Value("${cluster.topic.ping}")
	private String pingTopic;

	@Value("${cluster.id}")
	private String myNodeId;

	@Value("${cluster.endpoint}")
	private String myEndpoint;

	@Value("${cluster.port}")
	private int myPort;

	@Autowired
	private Environment environment;

	private PingSender pingSender;
	private PingConsumer pingConsumer;

	private List<ClusterNode> nodeList = new ArrayList<ClusterNode>();
	private Semaphore nodeLock = new Semaphore(1);

	@PostConstruct
	public void init() {
		logger.info("Starting Cluster Service");
		pingSender = new PingSender();
		pingSender.start();

		pingConsumer = new PingConsumer();
		pingConsumer.start();

	}
	
	

	@Override
	public ClusterNode[] getNodes() {
		List<ClusterNode> results = new ArrayList<ClusterNode>();
		try {
			nodeLock.acquire();
			for (ClusterNode clusterNode : nodeList) {
				results.add(clusterNode);
			}
			return results.toArray(new ClusterNode[results.size()]);
		} catch (Exception e) {
			return results.toArray(new ClusterNode[results.size()]);
		} finally {
			nodeLock.release();
		}	
	}



	@Override
	public ClusterNode[] getGreeNodes() {
		List<ClusterNode> results = new ArrayList<ClusterNode>();
		try {
			nodeLock.acquire();
			for (ClusterNode clusterNode : nodeList) {
				if (clusterNode.getStatus() == ClusterNodeStatus.Green) {
					results.add(clusterNode);
				}
			}
			return results.toArray(new ClusterNode[results.size()]);
		} catch (Exception e) {
			return results.toArray(new ClusterNode[results.size()]);
		} finally {
			nodeLock.release();
		}
	}

	@Override
	public ClusterNode[] getGreeNodes(String... profiles) {
		List<ClusterNode> results = new ArrayList<ClusterNode>();
		ClusterNode[] healthyNodes = getGreeNodes();
		for (ClusterNode node : healthyNodes) {
			if (node.hasProfiles(profiles)) {
				results.add(node);
			}
		}
		return results.toArray(new ClusterNode[results.size()]);
	}

	@Override
	public ClusterNode[] getYellowNodes() {
		List<ClusterNode> results = new ArrayList<ClusterNode>();
		try {
			nodeLock.acquire();
			for (ClusterNode clusterNode : nodeList) {
				if (clusterNode.getStatus() == ClusterNodeStatus.Yellow) {
					results.add(clusterNode);
				}
			}
			return results.toArray(new ClusterNode[results.size()]);
		} catch (Exception e) {
			return results.toArray(new ClusterNode[results.size()]);
		} finally {
			nodeLock.release();
		}
	}

	@Override
	public ClusterNode[] getRedNodes() {
		List<ClusterNode> results = new ArrayList<ClusterNode>();
		try {
			nodeLock.acquire();
			for (ClusterNode clusterNode : nodeList) {
				if (clusterNode.getStatus() == ClusterNodeStatus.Red) {
					results.add(clusterNode);
				}
			}
			return results.toArray(new ClusterNode[results.size()]);
		} catch (Exception e) {
			return results.toArray(new ClusterNode[results.size()]);
		} finally {
			nodeLock.release();
		}
	}

	@Override
	public int getNodeCount() {
		return nodeList.size();
	}

	@Override
	public ClusterNode getNode(String id) throws ClusterException {
		try {
			nodeLock.acquire();
			for (ClusterNode node : nodeList) {
				if (node.getId().equals(id)) {
					return node;
				}
			}
			throw new ClusterException("Node " + id + " not found");
		} catch (Exception e) {
			throw new ClusterException("Interrupt get node? " + e.toString());
		} finally {
			nodeLock.release();
		}
	}

	public String getMyNodeId() {
		return myNodeId;
	}

	public String getMyEndpoint() {
		return myEndpoint;
	}

	public int getMyPort() {
		return myPort;
	}

	
	public static String humanReadableByteCountSI(long bytes) {
	    if (-1000 < bytes && bytes < 1000) {
	        return bytes + " B";
	    }
	    CharacterIterator ci = new StringCharacterIterator("kMGTPE");
	    while (bytes <= -999_950 || bytes >= 999_950) {
	        bytes /= 1000;
	        ci.next();
	    }
	    return String.format("%.1f %cB", bytes / 1000.0, ci.current());
	}
	
	
	public boolean nodeExists(String id) {
		try {
			nodeLock.acquire();
			for (ClusterNode clusterNode : nodeList) {
				if (clusterNode.getId().equals(id)) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		} finally {
			nodeLock.release();
		}
	}

	private class PingSender extends Thread {

		private DKafkaByteProducer producer;

		public void run() {
			try {
				producer = DKafkaByteProducer.newInstance(clusterBrokers, pingTopic, myNodeId);

				while (!interrupted()) {
					Thread.sleep(1000);
					ClusterNodeStatsSpec stats = new ClusterNodeStatsSpec();
					stats.setEndpoint(myEndpoint);
					stats.setId(myNodeId);
					stats.setProfiles(environment.getActiveProfiles());
					stats.setLastPing(DTime.now());
					stats.setStatus(ClusterNodeStatus.Green);
					stats.setTotalMem(humanReadableByteCountSI(Runtime.getRuntime().totalMemory()));
					stats.setFreeMem(humanReadableByteCountSI(Runtime.getRuntime().freeMemory()));
					stats.setUsedMem(humanReadableByteCountSI(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
					String serialized = DJson.serialize(stats);
					producer.sendBytes(serialized.getBytes());
				}

			} catch (Exception e) {
				logger.error("Exception Sending Cluster Node Ping " + e.toString());
			}

		}
	}

	private class PingConsumer extends Thread implements DKafkaByteHandler {

		private DKafkaByteConsumer consumer;

		public void run() {
			try {
				logger.debug("Cluster Ping Consumer Starting ");
				consumer = DKafkaByteConsumer.newInstance(clusterBrokers, pingTopic);
				consumer.addStreamHandler(this);
			} catch (Exception e) {
				logger.error("Exception Creating Cluster Ping Topic {}", e.toString(), e);
				return;
			}
		}

		@Override
		public void streamBytes(byte[] bytes) {
			ClusterNodeStatsSpec ping = null;
			try {
				ping = DJson.getObjectMapper().readValue(bytes, ClusterNodeStatsSpec.class);
			} catch (Exception e) {
				logger.error("Exception deserializing cluster node ping " + e.toString());
			}
			if (nodeExists(ping.getId())) {
				try {
					ClusterNodeImpl node = (ClusterNodeImpl) getNode(ping.getId());
					node.setStats(ping);
				} catch (Exception e) {
					logger.error("Exception processing node ping wtf " + e.toString(), e);
				}
			} else {
				ClusterNodeImpl node = new ClusterNodeImpl(ping);
				try {
					nodeLock.acquire();
					nodeList.add(node);
				} catch (Exception e) {
					logger.error("Exception adding node on discovyer " + e.toString());
				} finally {
					nodeLock.release();
				}
				if (logger.isDebugEnabled()) {
					logger.debug("Cluster Node Discovery {}", ping.getId());
				}
			}
		}

	}
}
