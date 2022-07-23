package com.dunkware.trade.service.stream.server.controller.session.container.core;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.trade.service.stream.container.worker.WorkerContainerInput;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainer;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainerExtension;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainerNode;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainerService;
import com.google.api.client.util.Value;

public class SessionContainerImpl implements SessionContainer {

	private StreamController controller;
	
	@Autowired
	private Cluster cluster;
	
	
	@Autowired
	private SessionContainerService containerService; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ApplicationContext ac; 
	
	@Value("${stream.container.worker.nodes}")
	private String sessionWorkerNodeIds;
	
	@Value("${net.cluster.server.brokers}")
	private String brokers;
	
	
	private List<SessionContainerExtension> extensions = new ArrayList<SessionContainerExtension>();
	
	
	private List<SessionContainerNode> containerNodes = new ArrayList<SessionContainerNode>();
	
	public void start(StreamController controller) throws Exception { 
		this.controller = controller;
		// create extensions 
		for (Class<?> extensionClass : containerService.getContainerExtensions()) {
			try {
				SessionContainerExtension ext = (SessionContainerExtension)extensionClass.newInstance();
				ac.getAutowireCapableBeanFactory().autowireBean(ext);
			} catch (Exception e) {
				throw new Exception("Exception building session container extensions " + e.toString());
			}
		}
		
		
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
					// allow extensions to add worker container extensions right? 
					WorkerContainerInput nodeInput = new WorkerContainerInput(); 
					for (SessionContainerExtension sessionContainerExtension : extensions) {
						sessionContainerExtension.workerInit(nodeInput);
					}
					containerNode.start(node, this, nodeInput);
					
				}
			}
			
			// call start on contianer extensions 
			for (SessionContainerExtension sessionContainerExtension : extensions) {
				sessionContainerExtension.containerStart(this);
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
	
	
	// okay we need to wrap GNET shit into things; 

	
	

	
}
