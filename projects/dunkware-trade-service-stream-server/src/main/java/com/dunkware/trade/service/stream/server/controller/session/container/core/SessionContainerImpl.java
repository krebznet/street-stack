package com.dunkware.trade.service.stream.server.controller.session.container.core;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;

import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.trade.service.stream.container.worker.WorkerContainerInput;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainer;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainerException;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainerExtension;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainerNode;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainerService;

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

	private Marker marker;
	
	// ses

	private List<SessionContainerExtension> extensions = new ArrayList<SessionContainerExtension>();

	private List<SessionContainerNode> containerNodes = new ArrayList<SessionContainerNode>();

	public void start(StreamController controller) throws Exception {
		this.controller = controller;
		marker = MarkerFactory.getMarker("sc-" + controller.getName());
		logger.info(marker, "Starting Session Container {}", controller.getName());
		// create extensions
		for (Class<?> extensionClass : containerService.getContainerExtensions()) {
			try {
				SessionContainerExtension ext = (SessionContainerExtension) extensionClass.newInstance();
				logger.debug(marker, "created session container extension " + ext.getClass().getName());
				ac.getAutowireCapableBeanFactory().autowireBean(ext);
				extensions.add(ext);
			} catch (Exception e) {
				throw new Exception("Exception building session container extensions " + e.toString());
			}
		}

		String[] configuredWorkers = sessionWorkerNodeIds.split(",");
		Thread.sleep(5000);
		for (String nodeId : configuredWorkers) {
			ClusterNode node = cluster.getNode(nodeId);
			if (node == null) {
				logger.error("Configured worker node " + nodeId + " not found");
				throw new Exception("Worker ndoe " + nodeId + " not found");
			}
			// create and configure node input
			WorkerContainerInput nodeInput = new WorkerContainerInput();
			nodeInput.setKafkaBroker(brokers);
			nodeInput.setServerTopic("stream_" + controller.getName() + "_container_" + node.getId() + "_server");
			nodeInput.setWorkerTopic("stream_" + controller.getName() + "_container_" + node.getId() + "_worker");
			nodeInput.setTimeZone(controller.getTimeZone());
			nodeInput.setStreamIdentifier(controller.getName());
			for (SessionContainerExtension sessionContainerExtension : extensions) {
				sessionContainerExtension.workerInit(nodeInput);
			}
			try {
				SessionContainerNodeImpl containerNode = new SessionContainerNodeImpl();
				ac.getAutowireCapableBeanFactory().autowireBean(containerNode);
				containerNode.start(node, this, nodeInput);
				containerNodes.add(containerNode);
				for (SessionContainerExtension sessionContainerExtension : extensions) {
					sessionContainerExtension.workerStart(containerNode);
				}
			} catch (Exception e) {
				logger.error(marker, "Exception starting worker node " + e.toString());
				throw new Exception("Worker node start failed " + e.toString());
			}
			logger.info(marker, "started session node " + node.getId());
		}

		try {
			for (SessionContainerExtension sessionContainerExtension : extensions) {
				sessionContainerExtension.containerStart(this);
			}

		} catch (Exception e) {
			throw new Exception("Exception calling extension container start " + e.toString());
		}

	}

	@Override
	public void dispose() {
		// TODO: dipose me
	}

	@Override
	public StreamController getStream() {
		return controller;
	}

	@Override
	public List<SessionContainerNode> getNodes() {
		return containerNodes;

	}

	@Override
	public SessionContainerNode getEntityNode(String identifier) throws SessionContainerException {
		for (SessionContainerNode sessionContainerNode : containerNodes) {
			if(sessionContainerNode.hasEntity(identifier)) { 
				return sessionContainerNode;
			}
		}
		throw new SessionContainerException("Identify " + identifier + " does not have a worker node");
	}
	
	

	// okay we need to wrap GNET shit into things;

}
