package com.dunkware.trade.service.data.service.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.trade.service.data.json.controller.message.StreamSessionPing;
import com.dunkware.trade.service.data.json.controller.message.StreamSessionStart;
import com.dunkware.trade.service.data.json.controller.message.StreamSessionStop;
import com.dunkware.trade.service.data.json.data.stream.StreamStats;
import com.dunkware.trade.service.data.json.enums.StreamSessionState;
import com.dunkware.trade.service.data.service.config.ConfigService;
import com.dunkware.trade.service.data.service.domain.StreamDO;
import com.dunkware.trade.service.data.service.message.MessageHandler;
import com.dunkware.trade.service.data.service.message.MessageService;
import com.dunkware.trade.service.data.service.repository.StreamSessionRepository;
import com.dunkware.trade.service.data.service.stream.session.StreamSession;

@Service
public class Stream implements MessageHandler {
	
	@Autowired
	private ConfigService configService; 

	@Autowired
	private ApplicationContext ac;
	
	@Autowired
	private MessageService messageService;

	@Autowired
	private StreamSessionRepository sessionRepo;
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	private StreamSession session; 
	
	private StreamDO entity; 
	
	
	public void init(StreamDO entity) { 
		this.entity = entity;
		this.messageService.addHandler(this);
		
	}
	
	public StreamStats getStats() { 
		StreamStats stats = new StreamStats();
		stats.setName(entity.getStream());
		stats.setRunning(isSessionRunning());
		if(session != null) { 
			stats.setSessionStats(session.getStats());
			
		}
		return stats;
	}
	
	public void disposeSession() { 
		//TODO: dispose stream session 
	}
	
	public StreamDO getEntity() {
		return entity;
	}
	
	public boolean isSessionRunning() { 
		if(session == null) { 
			return false; 
		}
		return true;
		
	}
	
	public StreamSession getSession() { 
		return session; 
	}


	@Override
	public void sessionPing(final StreamSessionPing ping) {
		if(ping.getSpec().getStreamIdentifier().equals(entity.getStream()) == false) { 
			return;
		}
		if(session == null || session.getState() == StreamSessionState.Stopped) { 
			logger.info(MarkerFactory.getMarker("Data"), "Starting stream " + getEntity().getStream() + " from snapshot session ");
		}
		Thread runner = new Thread() { 
			
			public void run() { 
				StreamSession session = new StreamSession();
				ac.getAutowireCapableBeanFactory().autowireBean(session);
				try {
					session.start(Stream.this,ping.getSpec());
					Stream.this.session = session;
				} catch (Exception e) {
					logger.error(MarkerFactory.getMarker("Data"), "Exception starting stream on ping " + e.toString());
					
				}
				
			}
		};
		
		runner.start();
		
		
	}


	@Override
	public void sessionStart(StreamSessionStart start) {
		if(start.getSpec().getStreamIdentifier().equals(entity.getStream()) == false) { 
			return;
		}
		if(session == null || session.getState() == StreamSessionState.Stopped) { 
			logger.info(MarkerFactory.getMarker("Data"), "Starting stream " + getEntity().getStream() + " from snapshot session ");
		}
		 session = new StreamSession();
		 
		ac.getAutowireCapableBeanFactory().autowireBean(session);
			try {
				session.start(this,start.getSpec());
					
			} catch (Exception e) {
				System.err.println(e.toString());
				e.printStackTrace();
				// TODO: handle exception
			}
			
		
		
		
	}


	@Override
	public void sessionStop(StreamSessionStop stop) {
		if(stop.getSpec().getStreamIdentifier().equals(entity.getStream()) == false) { 
			return;
		}
		
		if(session != null) { 
			logger.info(MarkerFactory.getMarker("Data"), "Stopping stream session from messsage " + stop.getSpec().getSessionId());
			Thread runner = new Thread() { 
				public void run() { {
					session.stop();		
				}
				}};
			runner.start();
			
		}
	}
	
	
	
	
}
