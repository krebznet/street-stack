package com.dunkware.trade.service.web.server.netservice.session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.context.IntegrationFlowContext;
import org.springframework.integration.websocket.ServerWebSocketContainer;
import org.springframework.integration.websocket.ServerWebSocketContainer.SockJsServiceOptions;
import org.springframework.integration.websocket.inbound.WebSocketInboundChannelAdapter;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeFailureException;
import org.springframework.web.socket.server.HandshakeHandler;

import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.net.proto.net.GNetCallRequest;
import com.dunkware.net.proto.net.GNetMessage;
import com.dunkware.net.proto.net.GNetMessage.ValueCase;

public class NetSession implements WebSocketHandler {

	@Autowired
	private Cluster cluster;
	
	@Autowired
	IntegrationFlowContext integrationFlowContext;

	@Autowired
	private WebSocketHandlerRegistry handlerReg;
	
	private WebSocketSession socketSession;
	
	private Map<String,NetSessionChannel> channels = new ConcurrentHashMap<String,NetSessionChannel>();

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private String endpoint = null;
	
	public String getSessionId() { 
		return endpoint;
	}
	
	public String start(String token) throws Exception {
		
		 endpoint =  DUUID.randomUUID(5);
		 try {
			 SockJsServiceOptions s = new SockJsServiceOptions();
				s.setWebSocketEnabled(true);
				ServerWebSocketContainer serverWebSocketContainer =
					       new ServerWebSocketContainer("/" + endpoint)
					       
					               .setHandshakeHandler(new HandshakeHandler() {
									
									@Override
									public boolean doHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
											Map<String, Object> attributes) throws HandshakeFailureException {
										// TODO Auto-generated method stub
										return true;
									}
								}).setAllowedOrigins("*").withSockJs(s);

					WebSocketInboundChannelAdapter webSocketInboundChannelAdapter =
					       new WebSocketInboundChannelAdapter(serverWebSocketContainer);

					QueueChannel dynamicRequestsChannel = new QueueChannel();

					IntegrationFlow serverFlow =
					       IntegrationFlows.from(webSocketInboundChannelAdapter)
					               .channel(dynamicRequestsChannel)
					               .get();

					IntegrationFlowContext.IntegrationFlowRegistration dynamicServerFlow =
					       this.integrationFlowContext.registration(serverFlow)
					               .addBean(serverWebSocketContainer)
					               .register();
					
					dynamicServerFlow.destroy();
					
					handlerReg.addHandler(this, endpoint);
		} catch (Exception e) {
			logger.error("Exception creating client net service web socket " + e.toString());
			throw e;
		}
		 return endpoint;
	}
	
	public void sendMessage(GNetMessage message) throws Exception  { 
		TextMessage textMessage = new TextMessage(message.toByteArray());
		this.socketSession.sendMessage(textMessage);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession arg0, CloseStatus arg1) throws Exception {
		
		
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession arg0) throws Exception {
		this.socketSession = arg0;
	}

	@Override
	public void handleMessage(WebSocketSession arg0, WebSocketMessage<?> arg1) throws Exception {
	//	GNetMessage.parseFrom(".")
		GNetMessage m = null;
		if(m.getValueCase() == ValueCase.CALLREQ) {
			GNetCallRequest callReq = m.getCallReq();
			
		}
        if(m.getValueCase() == ValueCase.CHANNELREQ) {
			
		}
		
	}

	@Override
	public void handleTransportError(WebSocketSession arg0, Throwable arg1) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
