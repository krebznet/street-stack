package com.dunkware.trade.service.data.service.stream.net.container;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.net.cluster.node.ClusterNodeService;
import com.dunkware.trade.service.data.service.stream.DataStream;

public class DataStreamContainerCluster {
	
	@Autowired
	private ClusterNodeService clusterService;
	
	private List<DataStreamContainerWorker> workers = new ArrayList<DataStreamContainerWorker>();
	private DExecutor executor; 
	public void start(DataStream dataStream, DExecutor executor) throws Exception { 
		this.executor = executor; 
		List<ClusterNode> clusterNodes = new ArrayList<ClusterNode>();
		// split the routing 
		// need to listen to all the fuckin singal / time update and etc. messsages all of them 
		
		
	}
	
	public class WorkerStarter implements Runnable { 
		 
		public void run() { 
			
		}
	}

}
