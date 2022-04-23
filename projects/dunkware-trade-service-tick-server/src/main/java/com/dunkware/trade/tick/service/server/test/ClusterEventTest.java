package com.dunkware.trade.tick.service.server.test;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.net.cluster.node.anot.AClusterPojoEventHandler;

@Service
public class ClusterEventTest {

	@Autowired
	private Cluster cluster;
	
	private EventPublisher publisher;
	@PostConstruct
	public void start() { 
		cluster.addComponent(this);
		publisher = new EventPublisher();
		publisher.start();
	}
	
	
	@AClusterPojoEventHandler(pojoClass = ClusterEventBean.class)
	public void onMoodEvent(ClusterEventBean bean) {
		System.out.println("received event!  " + bean.getMood());
	}
	
	public class EventPublisher extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				ClusterEventBean bean = new ClusterEventBean();
				bean.setMood("Yeah" + DUUID.randomUUID(5));
				try {
					cluster.pojoEvent(bean);	
				} catch (Exception e) {
					System.err.println("pojo event fail " + e.toString());;
				}
				try {
					Thread.sleep(20000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		}
	}
}
