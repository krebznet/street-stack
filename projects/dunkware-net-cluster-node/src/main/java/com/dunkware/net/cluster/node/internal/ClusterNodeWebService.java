package com.dunkware.net.cluster.node.internal;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.net.cluster.json.node.ClusterNodeUpdate;

@RestController
public class ClusterNodeWebService {

	@PostMapping(path = "/cluster/node/updates")
	public void nodeUpdates(@RequestBody List<ClusterNodeUpdate> updates) {

	}

}