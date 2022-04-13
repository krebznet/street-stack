package com.dunkware.trade.service.stream.server.session.extensions;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import com.dunkware.common.kafka.admin.DKafkaAdmin;
import com.dunkware.trade.service.stream.server.session.StreamSession;
import com.dunkware.trade.service.stream.server.session.StreamSessionExtension;
import com.dunkware.trade.service.stream.server.session.StreamSessionNode;
import com.dunkware.trade.service.stream.server.session.anot.AStreamSessionExt;
import com.dunkware.xstream.core.extensions.StreamEventPublisherExtType;

@AStreamSessionExt
public class StreamEventPublisher implements StreamSessionExtension {

	private StreamSession session;

	@Value("${kafka.brokers}")
	private String kafkaBrokers;

	@Override
	public void sessionStarting(StreamSession session) {
		this.session = session;

	}

	@Override
	public void nodeStarting(StreamSessionNode node) {
		// create a topic for the day if it does not exist
		Date myDate = new Date();
		SimpleDateFormat sm = new SimpleDateFormat("yyMMdd");
		String strDate = sm.format(myDate);
		String topicName = node.getSession().getKafkaSnapshotTopic();
		
		String signalTopics = node.getSession().getKafkaSignalTopic();

		
		try {
			DKafkaAdmin admin = DKafkaAdmin.newInstance(kafkaBrokers);

			if (!admin.topicExists(topicName)) {
				admin.createTopic(topicName, 4, (short) 2, null);
			}
			if(!admin.topicExists(signalTopics)) { 
				admin.createTopic(signalTopics, 2, (short) 2, null);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		StreamEventPublisherExtType extType = new StreamEventPublisherExtType();
		extType.setKafkaBrokers(kafkaBrokers);
		
		extType.setSnapshotTopic("stream_" + node.getSession().getStream().getName().toLowerCase() + "_snapshots");
		extType.setSignalTopic("stream_" + node.getSession().getStream().getName().toLowerCase() + "_signals");
		extType.setKafkaIdentifier(node.getNodeId());
		node.getStreamBundle().addExtension(extType);
	}

	@Override
	public void nodeStarted(StreamSessionNode node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionStarted(StreamSession session) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionStopping(StreamSession session) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionStopped(StreamSession session) {
		// TODO Auto-generated method stub

	}

	@Override
	public void nodeStartException(StreamSessionNode node, String exception) {
		// TODO Auto-generated method stub

	}

}
