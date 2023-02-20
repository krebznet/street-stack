package com.dunkware.cluster.server.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.net.cluster.json.cluster.ClusterNodeSpec;

@RestController
public class ClusterWebService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@PostConstruct
	private void load() { 
		logger.info("Starting Celebration Cluster Web Service");
	}
	
	@GetMapping(path = "/cluster/nodes/stats") 
	public ResponseEntity<Nodestat>() {
		List<ClusterNodeSpec> specs = new ArrayList<ClusterNodeSpec>();
		
		return specs; 
				
		
	}
	

}
