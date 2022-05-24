package com.dunkware.trade.service.data.service.stream.container;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.trade.service.data.service.stream.DataStream;
import com.dunkware.trade.service.data.service.stream.DataStreamService;
import com.dunkware.trade.service.data.service.stream.container.connector.GrpcStreamConnector;
import com.dunkware.xstream.net.core.util.GNetProto;

@Service()
public class StreamContainerService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private DataStreamService streamService;
	
	
	@Autowired
	ApplicationContext ac;
	
	private Map<String,StreamContainerController> containers = new ConcurrentHashMap<String,StreamContainerController>();
	
	@PostConstruct
	private void load() { 
		
		Collection<DataStream> dataStreams = streamService.getStreams();
		for (DataStream dataStream : dataStreams) {
			
			StreamContainerController controller = new StreamContainerController();
			ac.getAutowireCapableBeanFactory().autowireBean(controller);
			try {
				controller.start(dataStream);	
				logger.info("Started Container Controller for " + dataStream.getName());
				containers.put(dataStream.getName(), controller);
			} catch (Exception e) {
				logger.error("Exception starting data stream controller for " + dataStream.getName() + " " + e.toString(),e);;
			}
			
		}
	}
	

	public void newConnector(GrpcStreamConnector connector) { 
		if(connector.getHandshake() == null) { 
			logger.error("Exepecting Handshake on new connector");
			try {
				connector.sendMessage(GNetProto.connectResponse(false, "Expected Client connection handshake but is null"));	
			} catch (Exception e) {
				logger.error("Could not send back server connect response error no handshake " + e.toString());
			}
			String connectorStream = connector.getHandshake().getStream();
			StreamContainerController controller = containers.get(connectorStream);
			if(controller == null) { 
				connector.sendMessage(GNetProto.connectResponse(false, "Stream Container " + connectorStream + " not found"));
				connector.close();
			}
		}
		connector.sendMessage(GNetProto.connectResponse(true, "All Good Connected"));
		System.out.println("stop here need to see if handshake");
		
	}
	
	
	
}
