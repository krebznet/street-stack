package com.dunkware.trade.service.data.service.stream.container;

import org.apache.catalina.Container;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.trade.service.data.service.stream.container.connection.StreamContainerConnection;
import com.dunkware.trade.service.data.service.stream.container.connector.KafkaStreamConnector;
import com.dunkware.xstream.net.service.KafkaStreamClientRequest;
import com.dunkware.xstream.net.service.KafkaStreamClientResponse;

@RestController
public class StreamContainerWebService {

	@Autowired
	private StreamContainerService containerService; 
	
	@Autowired
	private Cluster cluster;

	@Value("${kafka.brokers}")
	private String kafkaBrokers;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@PostMapping(path = "/container/client/request")
	public @ResponseBody KafkaStreamClientResponse kafkaStreamClientRequest(@RequestBody() KafkaStreamClientRequest request) { 
		KafkaStreamClientResponse resp = new KafkaStreamClientResponse();
		StreamContainerController controller = containerService.getStreamContainer(request.getStream());
		if(controller == null) { 
			resp.setError(true);
			resp.setException("Stream container " + request.getStream() + " not found");
			return resp;
		}
		// now we want to -- define the topics
		String serverTopic = "stream_container_connection_" + request.getClientIdentifier() + "_server_" + DUUID.randomUUID(5);
		String clientTopic = "stream_container_connection_" + request.getClientIdentifier() + "_client_" + DUUID.randomUUID(5);
		try {
			KafkaStreamConnector connector = new KafkaStreamConnector(request.getClientIdentifier(), kafkaBrokers, serverTopic, clientTopic);
			
			StreamContainerConnection connection = new StreamContainerConnection();
			connection.start(connector, controller);
			controller.getConnections().add(connection);
			// so now that we have done this; 
			resp.setKafkaBrokers(kafkaBrokers);
			resp.setServerMessageTopic(serverTopic);
			resp.setClientMessageTopic(clientTopic);
			resp.setError(false);
			return resp;
		} catch (Exception e) {
			logger.error("Exception creating kafka stream connector " + e.toString());
			resp.setError(true);
			resp.setException("Internal exception creating kafka connector " + e.toString());
			return resp;
		}
		
		
		
	}
}
