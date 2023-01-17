package com.dunkware.trade.service.stream.server.controller.session.container.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.spring.channel.Channel;
import com.dunkware.spring.channel.ChannelService;
import com.dunkware.trade.service.stream.container.worker.WorkerContainerInput;
import com.dunkware.trade.service.stream.container.worker.WorkerContainerStartReq;
import com.dunkware.trade.service.stream.container.worker.WorkerContainerStartResp;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainer;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainerNode;

public class SessionContainerNodeImpl implements SessionContainerNode {

	@Autowired
	private ChannelService channelService; 
	
	@Autowired
	private Cluster cluster; 
	
	private Channel channel; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private ClusterNode node; 
	
	private SessionContainer sessionContainer; 
	
	private Marker streamInfo = MarkerFactory.getMarker("StreamInfo");
	
	private List<String> entities = new ArrayList<String>();
	
	public void start(ClusterNode node, SessionContainer container, WorkerContainerInput input) throws Exception {
		this.node = node; 
		this.sessionContainer = container; 
		
		WorkerContainerStartReq req = new WorkerContainerStartReq(); 
		req.setInput(input);
		
		WorkerContainerStartResp resp = null;
		try {
			resp = (WorkerContainerStartResp)node.jsonPost("/worker/stream/container/start", req, WorkerContainerStartResp.class);
			if(resp.isSuccessfull() == false) { 
				throw new Exception("Exception starting container worker on start request " + resp.getException());
			}
		} catch (Exception e) {
			throw new Exception("Exception calling start container worker node " + e.toString());
		}
		
		Map<String,Object> injectables = new HashMap<String,Object>();
		injectables.put("sessionContainer", sessionContainer);
		injectables.put("workerContainer", this);
		injectables.put("stream", container.getStream());
		
		try {
			
			channel = channelService.createChannel("SessionContainer", input.getKafkaBroker(), input.getServerTopic(), input.getWorkerTopic(), injectables);
	
		} catch (Exception e) {
			throw new Exception("Exception creating session worker node channel " + e.toString());
		}
	}
	
	
	
	
	@Override
	public boolean hasEntity(String identifier) {
		if(entities.contains(identifier))
			return true; 
		return false; 
	}




	@Override
	public void addEntity(String identifier) {
		entities.add(identifier);
	}




	@Override
	public SessionContainer getSessionContainer() {
		return sessionContainer;
	}




	@Override
	public StreamController getStream() {
		return sessionContainer.getStream();
	}




	@Override
	public Channel getChannel() {
		return channel;
	}

	@Override
	public ClusterNode getCluserNode() {
		return  node; 
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	
}
