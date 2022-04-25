package com.dunkware.net.cluster.server.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.net.cluster.json.cluster.ClusterNodeSpec;
import com.dunkware.net.cluster.json.node.ClusterNodeStats;
import com.dunkware.net.cluster.server.core.ClusterNode;
import com.dunkware.net.cluster.server.core.ClusterService;

@RestController
public class ClusterWebService {
	
	@Autowired
	private ClusterService clusterService;
	
	@GetMapping(path = "/info/nodes/stats") 
	public @ResponseBody()List<ClusterNodeSpec> nodesSPecs() {
		List<ClusterNodeSpec> specs = new ArrayList<ClusterNodeSpec>();
		Collection<ClusterNode> nodes = clusterService.getNodeService().getNodes();
		for (ClusterNode clusterNode : nodes) {
			ClusterNodeSpec spec = new ClusterNodeSpec();
			spec.setId(clusterNode.getStats().getId());
			spec.setState(clusterNode.getState());
			spec.setStatus(clusterNode.getStats());
			spec.setType(clusterNode.getStats().getType());
			specs.add(spec);
		}
		return specs; 
				
		
	}
	

}
