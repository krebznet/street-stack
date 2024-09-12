package com.dunkware.trade.service.stream.serverd.controller.extensions;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.trade.service.stream.serverd.controller.StreamController;
import com.dunkware.trade.service.stream.serverd.controller.StreamControllerExt;
import com.dunkware.trade.service.stream.serverd.controller.anot.AStreamControllerExt;
import com.dunkware.trade.service.stream.serverd.controller.session.StreamSession;
import com.dunkware.utils.kafka.admin.DunkKafkaAdmin;
import com.dunkware.utils.kafka.admin.model.KafkaNewTopic;
import com.dunkware.utils.kafka.admin.model.KafkaNewTopicResult;
import com.dunkware.xstream.model.snapshot.SnapshotHelper;

@AStreamControllerExt
public class SnapshotTopicExistsExt implements StreamControllerExt  {

	@Autowired
	DunkNet dunkNet;
	
	@Override
	public void initialize(StreamController controller) throws Exception {
		
		String topicName = SnapshotHelper.kafkaSnapshotTopic(controller.getName());
		
		DunkKafkaAdmin client = dunkNet.createAdminClient();
		if(!client.topicExists(topicName)) { 
			KafkaNewTopicResult result = client.createTopic(KafkaNewTopic.builder().paritions(6).name(topicName).retentionTime(5, TimeUnit.DAYS).replicas((short)1).build());
			if(result.isException()) { 
				throw new Exception("Exception creating Snapshot topic " + controller.getName() + " " + result.getCause());
			}
		}
	}

	@Override
	public void sessionStarted(StreamSession session) {
		
		// STREAM-INGESTOR
		// STREAM-INGESTOR
		
	}

	@Override
	public void sessionStopped(StreamSession session) {
		
		
	}

	@Override
	public void sessionKilled(StreamSession session) {
		
		
	}
	
	

}
