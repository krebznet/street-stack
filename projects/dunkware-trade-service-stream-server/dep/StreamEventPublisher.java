package com.dunkware.stream.controller.ssession.extensions;

import org.springframework.beans.factory.annotation.Value;

import com.dunkware.stream.controller.ssession.StreamSession;
import com.dunkware.stream.controller.ssession.StreamSessionExtension;
import com.dunkware.stream.controller.ssession.StreamSessionNode;
import com.dunkware.stream.controller.ssession.anot.AStreamSessionExt;
import com.dunkware.xstream.core.extensions.StreamEventPublisherExtType;

//@AStreamSessionExt
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
		String snapshotTopic = "stream_" + node.getStream().getName().toLowerCase() + "_event_snapshot";
		
		String signalTopic = "stream_" + node.getStream().getName().toLowerCase() + "_event_signal";
		String timeTopic = "stream_" + node.getStream().getName().toLowerCase() + "_event_time";
		String eventTopic = "stream_" + node.getStream().getName().toLowerCase() + "_event_all";
		
		StreamEventPublisherExtType extType = new StreamEventPublisherExtType();
		extType.setKafkaBrokers(kafkaBrokers);
		extType.setEventTopic(eventTopic);
		extType.setTimeTopic(timeTopic);
		extType.setSnapshotTopic(snapshotTopic);
		extType.setSignalTopic(signalTopic);
		extType.setKafkaIdentifier(node.getNodeId());
		extType.setNode(node.getNodeId());
		node.getStreamBundle().getExtensions().add(extType);
		
		// 
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
