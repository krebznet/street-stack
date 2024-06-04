package com.dunkware.stream.controller.stream.extensions;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.kafka.admin.DKafkaAdminClient;
import com.dunkware.common.kafka.admin.model.DKafkaNewTopic;
import com.dunkware.common.kafka.admin.model.DKafkaNewTopicResult;
import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.stream.controller.stream.StreamController;
import com.dunkware.stream.controller.stream.StreamControllerExt;
import com.dunkware.stream.controller.stream.anot.AStreamControllerExt;
import com.dunkware.stream.controller.stream.session.StreamSession;
import com.dunkware.xstream.model.snapshot.SnapshotHelper;

@AStreamControllerExt
public class SnapshotTopicExistsExt implements StreamControllerExt  {

	@Autowired
	DunkNet dunkNet;
	
	@Override
	public void initialize(StreamController controller) throws Exception {
		
		String topicName = SnapshotHelper.kafkaSnapshotTopic(controller.getName());
		
		DKafkaAdminClient client = dunkNet.createAdminClient();
		if(!client.topicExists(topicName)) { 
			DKafkaNewTopicResult result = client.createTopic(DKafkaNewTopic.builder().paritions(6).name(topicName).retentionTime(5, TimeUnit.DAYS).replicas((short)1).build());
			if(result.isException()) { 
				throw new Exception("Exception creating Snapshot topic " + controller.getName() + " " + result.getCause());
			}
		}
	}

	@Override
	public void sessionStarted(StreamSession session) {
		// TODO Auto-generated method stub
		// STREAM-INGESTOR
		// STREAM-INGESTOR
		
	}

	@Override
	public void sessionStopped(StreamSession session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionKilled(StreamSession session) {
		// TODO Auto-generated method stub
		
	}
	
	

}
