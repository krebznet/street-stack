package com.dunkware.trade.service.stream.server.cluster.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.service.stream.protocol.cluster.ClusterStatusResp;
import com.dunkware.trade.service.stream.protocol.cluster.spec.ClusterNodeStatus;
import com.dunkware.trade.service.stream.server.cluster.ClusterNode;
import com.dunkware.trade.service.stream.server.cluster.ClusterService;

@RestController
@Profile("Cluster")
@CrossOrigin(origins = "*") 
public class ClusterWebService {

	@Autowired
	private ClusterService clusterService;
	
	@PostConstruct
	public void load() { 
		System.out.println("load me mother fucker");
	}
	
	@GetMapping(path = "/cluster/stats")
	public @ResponseBody()ClusterStatusResp clusterStatus() { 
		ClusterStatusResp resp = new ClusterStatusResp();
		ClusterNode[] nodes = clusterService.getNodes();
		resp.setNodeCount(nodes.length);
		int greenCount = 0;
		int yellowCount = 0; 
		int redCount = 0; 
		for (ClusterNode clusterNode : nodes) {
			resp.getNodes().add(clusterNode.getStats());
			ClusterNodeStatus nodeStatus = clusterNode.getStatus();
			if(nodeStatus == ClusterNodeStatus.Green) { 
				greenCount++;
			}
			if(nodeStatus == ClusterNodeStatus.Yellow) { 
				yellowCount++;
			}
			if(nodeStatus == ClusterNodeStatus.Red) { 
				redCount++;
			}
		}
		resp.setGreeNodeCount(greenCount);
		resp.setYellowNodeCount(yellowCount);
		resp.setRedNodeCount(redCount);
		if(redCount > 0) { 
			resp.setStatus("Red");
		} else { 
			if(yellowCount > 0) { 
				resp.setStatus("Yellow");
			} else { 
				resp.setStatus("Green");
			}
			
		}
		return resp;
	}
	
	
}
