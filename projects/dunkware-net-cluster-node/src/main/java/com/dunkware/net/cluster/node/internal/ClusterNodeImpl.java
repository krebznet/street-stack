package com.dunkware.net.cluster.node.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.helpers.DHttpHelper;
import com.dunkware.common.util.json.DJson;
import com.dunkware.net.cluster.json.node.ClusterNodeState;
import com.dunkware.net.cluster.json.node.ClusterNodeStats;
import com.dunkware.net.cluster.json.node.ClusterNodeType;
import com.dunkware.net.cluster.json.node.ClusterNodeUpdate;
import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.net.cluster.node.ClusterNodeException;

import io.grpc.Channel;
import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ClusterNodeImpl implements ClusterNode {

	private ClusterNodeStats stats;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private ManagedChannel channel;

	private ClusterNodeUpdate lastUpdate;

	private ClusterNodeState state = null;

	private String exception;

	public void start(ClusterNodeUpdate update) {
		lastUpdate = update;
		state = lastUpdate.getState();
		stats = update.getStats();
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

			// test http endpoint ?

		}

	}

	public void update(ClusterNodeUpdate update) {
		this.lastUpdate = update;
		this.stats = update.getStats();
		// todo here if we have exception we try again
		if (channel == null) {
			channel = ManagedChannelBuilder.forTarget(stats.getGrpcEndpoint()).build();
		}
		// Event
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
		return stats.getStart();
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
			respString = DHttpHelper.postJson(stats.getHttpEndpoint(), path, serialized);
			if (respString == null) {
				throw new Exception("node req resp returned null path " + getHttpPathEndPoint(path));
			}
		} catch (Exception e) {
			throw new ClusterNodeException("Exception Invoking Endpoint " + path + " " + e.toString());
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
		// TODO Auto-generated method stub
		return null;
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
