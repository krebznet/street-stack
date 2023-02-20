package com.dunkware.net.cluster.node.core;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.dunkware.net.cluster.node.DunkCluster;
import com.dunkware.net.cluster.node.DunkNode;

@Service
public class DunkClusterImpl implements DunkCluster {
	
	// so we need to consume messages from cluster & node topic 
	// need to figure that out. 
	
	@PostConstruct
	private void init() throws Exception { 
		// okay here is magic. 
		
	}

	@Override
	public List<DunkNode> getNodes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DunkNode getNode(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
