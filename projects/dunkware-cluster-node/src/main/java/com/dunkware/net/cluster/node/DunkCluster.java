package com.dunkware.net.cluster.node;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface DunkCluster {
	
	List<DunkNode> getNodes();
	
	DunkNode getNode(String id);
	

	
	
}


	
	
	
	
