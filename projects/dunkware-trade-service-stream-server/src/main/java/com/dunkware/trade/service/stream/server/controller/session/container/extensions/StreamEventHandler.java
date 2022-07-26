package com.dunkware.trade.service.stream.server.controller.session.container.extensions;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.graalvm.compiler.asm.sparc.SPARCAssembler.ContinousBitSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2SpecBuilder;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GStreamEvent;
import com.dunkware.net.proto.stream.GStreamEventType;
import com.dunkware.net.proto.stream.GStreamTimeUpdate;
import com.dunkware.trade.service.stream.container.worker.WorkerContainerInput;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainer;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainerException;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainerExtension;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainerNode;
import com.dunkware.trade.service.stream.server.controller.session.container.anot.ASessionContainerExtension;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.xstream.container.proto.GEntitySignalWrapper;
import com.dunkware.xstream.container.proto.GEntitySnapshotWrapper;
import com.dunkware.xstream.container.proto.GStreamTimeUpdateWrapper;

/**
 * Okay so this extension is going to listen to stream events, wrap them into
 * JSON serializable and round robin across the avaialble worker nodes right?
 * 
 * @author duncankrebs
 *
 */
@ASessionContainerExtension()
public class StreamEventHandler implements SessionContainerExtension, DKafkaByteHandler2 {

	@Value("${kafka.brokers}")
	private String kafkaBrokers;

	private SessionContainer sessionContainer;

	private DKafkaByteConsumer2 eventConsumer;

	private List<SessionContainerNode> nodes;

	private BlockingQueue<GStreamEvent> streamEventQueue = new LinkedBlockingQueue<GStreamEvent>();

	private Logger logger = LoggerFactory.getLogger(getClass());

	private StreamEventRouter eventRouter;
	
	private Marker marker = MarkerFactory.getMarker("StreamEventHandler");

	@Override
	public void workerInit(WorkerContainerInput input) {
		// TODO Auto-generated method stub

	}
	
	
	@Override
	public void workerStart(SessionContainerNode node) throws SessionContainerException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void containerStart(SessionContainer container) throws SessionContainerException {
		// assign the entities round robin to the worker nodes.
		logger.info(marker, "Container Start Invoked");
		if(container == null) { 
			logger.error(marker, "Container is null on containerStart");
			return;
		}
		int nextIndex = 0;
		List<SessionContainerNode> nodes = null;
		try {
			 nodes = container.getNodes();	
		} catch (Exception e) {
			logger.error(marker, "Exception getting container nodes " + e.toString());
			return;
		}
		List<TradeTickerSpec> tickers = null;
		if(container.getStream() == null) { 
			logger.error(marker, "Container Stream is null cannot get tickers");
			throw new SessionContainerException("Exception getting stream controller from session container in stream event handler");
		}
		try {
			tickers = container.getStream().getTickers(); 
		} catch (Exception e) {
			logger.error(marker, "Exception getting Tickers from stream controller " + e.toString());
			try {
				Thread.sleep(5000);
			} catch (Exception e2) {
				try {
					tickers = container.getStream().getTickers();
				} catch (Exception e3) {
					logger.error(marker, "Exception getting Tickers again after pausing 5 seconds " + e.toString());;
					throw new SessionContainerException("Exception getting tickers after pasuing for 5 seconds in StreamEventHandler " + e.toString());
					
				}
			}
		}
		logger.info(marker, "Received " + tickers.size() + " tickers for stream " + container.getStream().getName());
		for (TradeTickerSpec ticker : container.getStream().getTickers()) {
			if(nextIndex == nodes.size()) { 
				nextIndex = 0; 
			}
			try {
				nodes.get(nextIndex).addEntity(ticker.getSymbol());
			} catch (Exception e) {
				logger.error(marker, "Exception either getting node or ticker symbol fucked up"); 
				throw new SessionContainerException("fucked up on entity assignment index out of bounds "  + e.toString());
			}
			nextIndex++;
		}
		
		
		this.sessionContainer = container;
		this.nodes = container.getNodes();

		String eventTopic = "stream_" + container.getStream().getName().toLowerCase() + "_event_all";
		try {
			DKafkaByteConsumer2Spec spec2 = DKafkaByteConsumer2SpecBuilder
					.newBuilder(ConsumerType.Auto, OffsetType.Latest).addBroker(kafkaBrokers).addTopic(eventTopic)
					.setClientAndGroup("DataContainerCluster_" + DUUID.randomUUID(4),
							"DataContainerCluster_" + DUUID.randomUUID(4))
					.build();
			eventConsumer = DKafkaByteConsumer2.newInstance(spec2);
			eventConsumer.start();
			eventConsumer.addStreamHandler(this);
			eventRouter = new StreamEventRouter();
			eventRouter.start();
		} catch (Exception e) {
			logger.error(marker,"Exception connecting to stream kafka event consumer " + e.toString());
			throw new SessionContainerException("Exception starting session event extension " + e.toString());
		}

	}

	@Override
	public void sessionStop(SessionContainer container) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionStart(SessionContainer container) {
		// okay fine

	}

	@Override
	public void record(ConsumerRecord<String, byte[]> record) {
		// okay should be a GStreamEvent
		try {
			GStreamEvent event = GStreamEvent.parseFrom(record.value());
			streamEventQueue.add(event);
		} catch (Exception e) {
			logger.error("Exception parsing GSTreamEvent in session container event extension " + e.toString());
		}
	}


	private  class StreamEventRouter extends Thread {

		public void run() {
			while (!interrupted()) {
				GStreamEvent event = null;
				try {
					event = streamEventQueue.take();
				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
					logger.error("Event Publisher Remove From Q Exception " + e.toString());
				}

				if (event.getType() == GStreamEventType.EntitySnapshot) {
					GEntitySnapshot snapshot = event.getEntitySnapshot();
					GEntitySnapshotWrapper snapshotWrapper = new GEntitySnapshotWrapper();
					snapshotWrapper.setBytes(snapshot.toByteArray());
					;

					SessionContainerNode worker = null;
					try {
						worker = sessionContainer.getEntityNode(snapshot.getIdentifier());
					} catch (Exception e) {
						logger.error("Exception consuming snapshot without a node with entity assigned " + snapshot.getIdentifier());
						continue;
					}
					// send the snapshot to the worker
					try {
						worker.getChannel().send(snapshotWrapper);
					} catch (Exception e) {
						logger.error("Exception sending snapshot to worker node " + e.toString());
					}

					// so we need to wrap a snapshot message
					// worker.sendMessage(GContainerProto.snapshotMessage(snapshot));
				}
				if (event.getType() == GStreamEventType.EntitySignal) {
					GEntitySignal signal = event.getEntitySignal();
					GEntitySignalWrapper signalWrapper = new GEntitySignalWrapper();
					signalWrapper.setBytes(signal.toByteArray());
					;
					for (SessionContainerNode sessionContainerNode : nodes) {
						try {
							sessionContainerNode.getChannel().send(signalWrapper);
						} catch (Exception e) {
							logger.error("Exception sending signal event to worker " + e.toString());
						}
					}

				}
				if (event.getType() == GStreamEventType.TimeUpdate) {
					GStreamTimeUpdate update = event.getTimeUpdate();
					GStreamTimeUpdateWrapper wrapper = new GStreamTimeUpdateWrapper();
					wrapper.setBytes(update.toByteArray());
					;
					for (SessionContainerNode sessionContainerNode : nodes) {
						try {
							sessionContainerNode.getChannel().send(wrapper);
						} catch (Exception e) {
							logger.error("Exception sending time update event to worker " + e.toString());
						}
					}

				}

			}
		}
	}

}
