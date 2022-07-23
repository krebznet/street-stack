package com.dunkware.trade.service.stream.server.controller.session.container.core;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainer;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainerNode;
import com.google.api.client.util.Value;

public class SessionContainerImpl implements SessionContainer {

	private StreamController controller;
	
	@Autowired
	private Cluster cluster;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ApplicationContext ac; 
	
	@Value("${stream.container.worker.nodes}")
	private String sessionWorkerNodeIds;
	
	
	private List<SessionContainerNode> containerNodes = new ArrayList<SessionContainerNode>();
	
	public void start(StreamController controller) throws Exception { 
		this.controller = controller;
		try {
			String[] configuredWorkers = sessionWorkerNodeIds.split(",");
			// sometimes this starts too soon before the node receives other node updates
			Thread.sleep(5000);
			for (String nodeId : configuredWorkers) {
				ClusterNode node = cluster.getNode(nodeId);
				if(node == null) { 
					logger.error("Configured worker node " + nodeId + " not found");
					
				} else {
					SessionContainerNodeImpl containerNode = new SessionContainerNodeImpl(); 
					containerNode.start(node, this);
				}
			}
			
		} catch (Exception e) {
			logger.error("Exception Getting Worker Nodes " + e.toString());
		}
		
		// create 
		
	}
	@Override
	public void dispose() {
		//TODO: dipose me 
	}
	
	

	@Override
	public StreamController getStream() {
		return controller;
	}
	@Override
	public List<SessionContainerNode> getNodes() {
		return containerNodes; 
	
	}

	
	

	
}
