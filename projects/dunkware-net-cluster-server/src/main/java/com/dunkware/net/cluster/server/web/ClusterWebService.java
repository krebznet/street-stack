package com.dunkware.net.cluster.server.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.dunkware.net.cluster.json.cluster.ClusterNodeSpec;
import com.dunkware.net.cluster.server.core.ClusterNode;
import com.dunkware.net.cluster.server.core.ClusterService;
import com.dunkware.spring.streaming.StreamingAdapter;

@RestController
public class ClusterWebService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ClusterService clusterService;
	
	@Autowired
	private ApplicationContext ac;
	
	@PostConstruct
	private void load() { 
		logger.info("Starting Celebration Cluster Web Service");
	}
	
	@GetMapping(path = "/cluster/nodes/stream")
	public ResponseEntity<StreamingResponseBody> nodesStream()
			throws Exception {
	
		
		StreamingAdapter adapter = new StreamingAdapter("NodeStream");
		
		ClusterNodeStream nodeStream = new ClusterNodeStream();
		ac.getAutowireCapableBeanFactory().autowireBean(nodeStream);
		nodeStream.start(adapter);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(adapter);
		// return adapter;

	}
	
	@GetMapping(path = "/cluster/nodes/stats") 
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
