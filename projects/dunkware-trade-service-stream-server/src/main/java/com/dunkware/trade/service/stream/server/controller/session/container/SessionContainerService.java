package com.dunkware.trade.service.stream.server.controller.session.container;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.StreamControllerService;
import com.dunkware.trade.service.stream.server.controller.session.container.connection.SessionContainerConnection;
import com.dunkware.trade.service.stream.server.controller.session.container.connector.GrpcStreamConnector;
import com.dunkware.xstream.net.core.util.GNetProto;

@Service()
public class SessionContainerService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private StreamControllerService streamService;
	
	private Marker marker = SessionContainerMarker.get();
	
	@Autowired
	ApplicationContext ac;
	
	private Map<String,SessionContainer> containers = new ConcurrentHashMap<String,SessionContainer>();
	
	@PostConstruct
	private void load() { 
		
		Collection<StreamController> dataStreams = streamService.getStreams();
		for (StreamController dataStream : dataStreams) {
			
			SessionContainer controller = new SessionContainer();
			ac.getAutowireCapableBeanFactory().autowireBean(controller);
			try {
				controller.start(dataStream);	
				logger.info(marker, "Started Container Controller for " + dataStream.getName());
				containers.put(dataStream.getName(), controller);
			} catch (Exception e) {
				logger.error(marker , "Exception starting data stream controller for " + dataStream.getName() + " " + e.toString(),e);;
			}
			
		}
	}
	
	public SessionContainer getStreamContainer(String stream) { 
		return containers.get(stream);
	}

	public void newConnector(GrpcStreamConnector connector) { 
		if(connector.getHandshake() == null) { 
			logger.error(marker, "Exepecting Handshake on new connector");
			try {
				connector.sendMessage(GNetProto.connectResponse(false, "Expected Client connection handshake but is null"));	
				if(logger.isDebugEnabled()) { 
					logger.debug(marker, "Sending valid Gnet connect response to client " + connector.getHandshake().getClientIdent());
				}
			} catch (Exception e) {
				logger.error(marker, "Could not send back server connect response error no handshake " + e.toString());
			}
			
		}
		String connectorStream = connector.getHandshake().getStream();
		SessionContainer controller = containers.get(connectorStream);
		if(controller == null) { 
			connector.sendMessage(GNetProto.connectResponse(false, "Stream Container " + connectorStream + " not found"));
			connector.close();
			return;
		}
		SessionContainerConnection connection = new SessionContainerConnection();
		try {
			connection.start(connector, controller);	
		} catch (Exception e) {
			logger.error("Exception starting container connnection " + e.toString());
		}
		connector.sendMessage(GNetProto.connectResponse(true, "All Good Connected"));
		System.out.println("stop here need to see if handshake");
		
	}
	
	
	
}
