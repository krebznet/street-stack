package com.dunkware.cluster.proto.node;

import java.util.ArrayList;
import java.util.List;

/**|
 * NodeStats Collection Wrapper For Streaming REST Data 
 * @author duncankrebs
 *
 */
public class NodeStatsCollection {


	private List<NodeStats> stats = new ArrayList<NodeStats>();

	public List<NodeStats> getStats() {
		return stats;
	}

	public void setStats(List<NodeStats> stats) {
		this.stats = stats;
	} 
	
	
	
}
