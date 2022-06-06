package com.dunkware.net.cluster.node.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.common.util.bitch.BitchLogger;
import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.helpers.DHttpHelper;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.net.cluster.json.node.ClusterNodeState;
import com.dunkware.net.cluster.json.node.ClusterNodeStats;
import com.dunkware.net.cluster.json.node.ClusterNodeType;
import com.dunkware.net.cluster.json.node.ClusterNodeUpdate;
import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.net.cluster.node.ClusterNodeException;
import com.dunkware.net.proto.net.GNetMessage;

import io.grpc.Channel;
import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ClusterNodeImpl implements ClusterNode {

	@Autowired
	private ClusterConfig clusterConfig;
	
	private ClusterNodeStats stats;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private ManagedChannel channel;

	private ClusterNodeUpdate lastUpdate;

	private DKafkaByteProducer netMessageProducer; 
	
	private ClusterNodeState state = null;

	private String exception;

	public void start(ClusterNodeUpdate update) {
		logger.info("Starting Cluster Node {} " + update.getNode());;
		lastUpdate = update;
		state = lastUpdate.getState();
		stats = update.getStats();
		try {
			String topic = "cluster_node_" + update.getNode() + "_net_messages";
			BitchLogger.log("Creating producer for node " + update.getNode() + " topic " + topic);
			netMessageProducer = DKafkaByteProducer.newInstance(clusterConfig.getServerBrokers(), topic, "peer_node_" + DUUID.randomUUID(5));
		} catch (Exception e) {
			logger.error("Exception creating kafka net message producer to diiscovered node " + update.getNode() + " exception " + e.toString());
		}
		if (update.getState() == ClusterNodeState.Available) {
			try {
				channel = ManagedChannelBuilder.forTarget(stats.getGrpcEndpoint()).usePlaintext().build();
				ConnectivityState state = channel.getState(true);
				if (state == ConnectivityState.TRANSIENT_FAILURE) {
					exception = "Grpc Channel Transient Failure on node " + stats.getId();
					logger.error("Available node {} transient failure", stats.getId());
					this.state = ClusterNodeState.NodeException;
					// Event
					return;
				}
			} catch (Exception e) {
				exception = "Exception creating grpc channel node " + stats.getId() + " " + e.toString();
				logger.error("Exception creating Available cluster node grpc channel " + e.toString());
				state = ClusterNodeState.NodeException;
			}
			if(logger.isDebugEnabled()) { 
				logger.debug("New Node Cluster Added ID {} Type {} State {}",lastUpdate.getNode(), lastUpdate.getStats().getType().name(), state.name());
			}
		}

	}
	

	@Override
	public boolean hasNetCallService(String endpoint) {
		//for (stats.getServices() d : iterable) {
			return false;
		//
	}



	@Override
	public boolean hasNetChannelService(String endpoint) {
		// TODO Auto-generated method stub
		return false;
	}



	public void update(ClusterNodeUpdate update) {
		this.lastUpdate = update;
		this.stats = update.getStats();
		if(state == ClusterNodeState.Available && update.getState() == ClusterNodeState.Busy) { 
			// event
			state = update.getState();
		}
	}

	
	@Override
	public ClusterNodeState getState() {
		return state;
	}

	@Override
	public boolean isAvailable() {
		if (state == ClusterNodeState.Available) {
			return true;
		}
		return false;
	}

	@Override
	public String getException() {
		return exception;
	}

	@Override
	public String getId() {
		return stats.getId();
	}

	@Override
	public DDateTime getStartTime() {
		return null;
	}

	@Override
	public ClusterNodeType getType() {
		return stats.getType();
	}

	@Override
	public int runningJobCount() {
		return stats.getRunningJobCount();
	}

	@Override
	public ClusterNodeStats getStats() {
		return stats;
	}

	@Override
	public Object jsonPost(String path, Object request, Class response) throws ClusterNodeException {
		String serialized = null;
		try {
			serialized = DJson.serialize(request);
			
		} catch (Exception e) {
			throw new ClusterNodeException(
					"Exception Serializing " + request.getClass().getName() + " " + e.toString());
		}

		String respString = null;
		try {
			respString = DHttpHelper.postJson(stats.getHttpEndpoint(), path, request);
			if (respString == null) {
				throw new Exception("node req resp returned null path " + getHttpPathEndPoint(path));
			}
		} catch (Exception e) {
			throw new ClusterNodeException("Exception Invoking Endpoint " + path + " " + e.toString() + " Serialized Post Body is " + serialized,e);
		}
		try {
			Object resp = DJson.getObjectMapper().readValue(respString, response);
			return resp;
		} catch (Exception e) {
			throw new ClusterNodeException("Exception parsing json response " + e.toString());
		}
	}
	
	

	@Override
	public Object jsonPostSerializedRequest(String path, Object request, Class response) throws ClusterNodeException {
		String serialized = null;
		try {
			serialized = DJson.serialize(request);
		} catch (Exception e) {
			throw new ClusterNodeException(
					"Exception Serializing " + request.getClass().getName() + " " + e.toString());
		}

		String respString = null;
		try {
			respString = DHttpHelper.postJson(stats.getHttpEndpoint(), path, serialized);
			if (respString == null) {
				throw new Exception("node req resp returned null path " + getHttpPathEndPoint(path));
			}
		} catch (Exception e) {
			throw new ClusterNodeException("Exception Invoking Endpoint " + path + " " + e.toString() + " Serialized Post Body is " + serialized,e);
		}
		try {
			Object resp = DJson.getObjectMapper().readValue(respString, response);
			return resp;
		} catch (Exception e) {
			throw new ClusterNodeException("Exception parsing json response " + e.toString());
		}
	}

	@Override
	public void jsonPostVoid(String path, Object request) throws ClusterNodeException {
		// TODO Auto-generated method stub

	}

	@Override
	public Object jsonPost(String path) throws ClusterNodeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Channel grpcChannel() throws ClusterNodeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object jsonGet(String path, Class response) throws ClusterNodeException {
		String endpoint = getHttpPathEndPoint(path);
		try {
			String respString = DHttpHelper.getJson(endpoint);
			return DJson.getObjectMapper().readValue(respString, response);
		} catch (Exception e) {
			throw new ClusterNodeException("Exception invoking endpoint " + endpoint + " " + e.toString(),e);
		}
	
	}
	

	@Override
	public void sendNetMessage(GNetMessage message) throws ClusterNodeException {
		try {
			byte[] bytes = message.toByteArray();
			netMessageProducer.sendBytes(bytes);
		} catch (Exception e) {
			logger.error("Exception sending GnetMessage to node " + lastUpdate.getNode() + " " + e.toString());
			throw new ClusterNodeException("Net Message Send Exception " + e.toString());
		}
	}

	@Override
	public void jsonGetVoid(String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public String httpGet(String path) throws ClusterNodeException {
		try {
			return DHttpHelper.getURLContent(getHttpPathEndPoint(path));
		} catch (Exception e) {
			throw new ClusterNodeException(
					"Exception invoking http endpoint " + getHttpPathEndPoint(path) + " " + e.toString());
		}

	}

	@Override
	public String httpPost(String path) throws ClusterNodeException {
		
		return null;
	}

	public String getHttpPathEndPoint(String path) {
		String endpoint = stats.getHttpEndpoint();
		if (endpoint.endsWith("/")) {
			endpoint = endpoint.substring(0, endpoint.length() - 1);
		}
		if (path.startsWith("/") == false) {
			path = "/" + path;
		}
		endpoint = endpoint + path;
		return endpoint;

	}
	
	public boolean hasChannelService(String endpoint) { 
		// TODO: convert
		return false; 
	}
	
	public boolean hasRequestService(String endpoint) { 
		return false; 
	}

	/**
	 * This will check and monitor grpc channel is not terminated
	 * 
	 * @author duncankrebs
	 *
	 */
	private class ChannelMonitor extends Thread {
		//TODO;
		public void run() {
			while(!interrupted()) {
				if(ClusterNodeImpl.this.channel != null) { 
					ConnectivityState state = channel.getState(true);
					if(state == ConnectivityState.IDLE || state == ConnectivityState.SHUTDOWN) {
						try {
							
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				}
			}
		}
	}

}
