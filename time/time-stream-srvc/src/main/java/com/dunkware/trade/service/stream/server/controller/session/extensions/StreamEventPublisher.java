package com.dunkware.trade.service.stream.server.controller.session.extensions;

import org.springframework.beans.factory.annotation.Value;

import com.dunkware.trade.service.stream.server.controller.session.StreamSession;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionExtension;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionNode;
import com.dunkware.trade.service.stream.server.controller.session.anot.AStreamSessionExt;
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
		

	}

	@Override
	public void sessionStarted(StreamSession session) {
		

	}

	@Override
	public void sessionStopping(StreamSession session) {
		

	}

	@Override
	public void sessionStopped(StreamSession session) {
		

	}

	@Override
	public void nodeStartException(StreamSessionNode node, String exception) {
		

	}

}
