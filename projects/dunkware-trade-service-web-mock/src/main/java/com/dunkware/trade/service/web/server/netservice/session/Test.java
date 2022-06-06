package com.dunkware.trade.service.web.server.netservice.session;

import java.util.Map;

import javax.annotation.PostConstruct;

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
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeFailureException;
import org.springframework.web.socket.server.HandshakeHandler;

@Service
public class Test {
	
	@Autowired
	IntegrationFlowContext integrationFlowContext;


	@Autowired
	private WebSocketHandlerRegistry handlerReg;
	
	private WebSocketSession session;
	
	@PostConstruct
	public void post() { 
		SockJsServiceOptions s = new SockJsServiceOptions();
		s.setWebSocketEnabled(true);
		ServerWebSocketContainer serverWebSocketContainer =
			       new ServerWebSocketContainer("/dynamic")
			       
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
			
			handlerReg.addHandler(new WebSocketHandler() {
				
				@Override
				public boolean supportsPartialMessages() {
					// TODO Auto-generated method stub
					 return false;
				}
				
				@Override
				public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
					System.out.println("message!" + message.toString());
					session.sendMessage(new TextMessage(message.toString()));
					Test.this.session = session;
				}
				
				@Override
				public void afterConnectionEstablished(WebSocketSession session) throws Exception {
					System.out.println("dynamic session established");
					
				}
				
				@Override
				public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
					// TODO Auto-generated method stub
					
				}
			}, "/dynamic");
			
	}
	
	
	public void fuck() { 
		SockJsServiceOptions s = new SockJsServiceOptions();
		s.setWebSocketEnabled(true);
		ServerWebSocketContainer serverWebSocketContainer =
			       new ServerWebSocketContainer("/fuck")
			       
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
			
			handlerReg.addHandler(new WebSocketHandler() {
				
				@Override
				public boolean supportsPartialMessages() {
					// TODO Auto-generated method stub
					 return false;
				}
				
				@Override
				public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
					System.out.println("message!" + message.toString());
					session.sendMessage(new TextMessage(message.toString()));
					Test.this.session = session;
				}
				
				@Override
				public void afterConnectionEstablished(WebSocketSession session) throws Exception {
					System.out.println("dynamic session established");
					
				}
				
				@Override
				public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
					// TODO Auto-generated method stub
					
				}
			}, "/fuck");
			
	}
	
	
	
}
