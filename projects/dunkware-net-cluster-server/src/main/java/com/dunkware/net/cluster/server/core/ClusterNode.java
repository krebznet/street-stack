package com.dunkware.net.cluster.server.core;

import com.dunkware.net.cluster.json.node.ClusterNodeState;
import com.dunkware.net.cluster.json.node.ClusterNodeStats;
import com.dunkware.net.cluster.json.node.ClusterNodeUpdate;

import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ClusterNode {
	
	private String id; 
	
	private ClusterNodeStats lastStats;
	
	private ClusterNodeState nodeState;
	
	private ManagedChannel nodeChannel;
	
	public void start(ClusterNodeStats stats) { 
		this.lastStats = stats;
		this.id = stats.getId();
		this.nodeState = ClusterNodeState.Available;
		nodeChannel = ManagedChannelBuilder.forTarget(stats.getGrpcEndpoint()).usePlaintext().build();
		ConnectivityState channelState = nodeChannel.getState(true);
		if(channelState == ConnectivityState.TRANSIENT_FAILURE) { 
			// handle error
		}
		if(channelState == ConnectivityState.CONNECTING) { 
			int i = 1;
			while(channelState == ConnectivityState.CONNECTING) { 
				i++;
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if(i > 30) { 
					nodeState = ClusterNodeState.NodeTimeout;
				}
			}
		}
	}
	
	public String getId() { 
		return id;
	}
	
	public ClusterNodeStats getStats() { 
		return lastStats;
	}
	
	public ClusterNodeState getState() { 
		return nodeState;
	}
	
	
	public void updateNodeState(ClusterNodeStats stats) { 
		// here we should look at like pending task
		// memroy ussage for now return available;
		nodeState = ClusterNodeState.Available;
		lastStats = stats;
	}
	
	public ClusterNodeUpdate createUpdate() { 
		ClusterNodeUpdate update = new ClusterNodeUpdate();
		update.setNode(id);
		update.setState(nodeState);
		update.setStats(lastStats);;
		return update; 
	}
}
