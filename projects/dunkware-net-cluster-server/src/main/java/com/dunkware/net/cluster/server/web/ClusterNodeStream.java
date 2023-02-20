package com.dunkware.net.cluster.server.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.net.cluster.json.node.ClusterNodeStatsList;
import com.dunkware.net.cluster.server.core.ClusterService;
import com.dunkware.spring.streaming.StreamingAdapter;
import com.dunkware.spring.streaming.StreamingAdapterListener;

public class ClusterNodeStream implements StreamingAdapterListener {
	
	
	@Autowired
	private ClusterService clusterService;

	private Logger logger = LoggerFactory.getLogger(getClass());
	private StreamingAdapter adapter;
	private Streamer streamer;

	public void start(StreamingAdapter adapter) {
		this.adapter = adapter;
		this.adapter.addListener(this);
		streamer = new Streamer();
		streamer.start();
	}
	
	
	@Override
	public void clientDisconnect(StreamingAdapter adapter) {
		streamer.interrupt();
		this.adapter.removeListener(this);
	}



	@Override
	public void serverDisconnect(StreamingAdapter adapter) {
		this.adapter.removeListener(this);
		streamer.interrupt();
	}



	private class Streamer extends Thread {

		public void run() {
			while (!interrupted()) {
				try {
					Thread.sleep(1000);
					ClusterNodeStatsList list = clusterService.getNodeService().getNodeStatsList();
					adapter.send(list);
					
				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
					logger.error("Exception Streaming Cluster Nodes " + e.toString());
				}

			}

		}
	}

}
