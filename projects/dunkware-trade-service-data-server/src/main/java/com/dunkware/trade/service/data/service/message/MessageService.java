package com.dunkware.trade.service.data.service.message;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import com.dunkware.trade.service.data.json.controller.message.StreamMessage;
import com.dunkware.trade.service.data.json.controller.message.StreamSessionPing;
import com.dunkware.trade.service.data.json.controller.message.StreamSessionStart;
import com.dunkware.trade.service.data.json.controller.message.StreamSessionStop;

@Service
public class MessageService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private List<MessageHandler> handlers = new ArrayList<MessageHandler>();
	private Semaphore handlerLock = new Semaphore(1);
	
	@StreamListener(MessageProcessor.MESSAGES_IN)
	
	public void streamMessage(StreamMessage message) {
		if (message instanceof StreamSessionStart) {
			StreamSessionStart m = (StreamSessionStart)message;	
			try {
				handlerLock.acquire();
				for (MessageHandler messageHandler : handlers) {
					messageHandler.sessionStart(m);
				}
			} catch (Exception e) {
				logger.error("Excepton notifying message handler stream start");
			} finally { 
				handlerLock.release();
			}
			
		}
		if (message instanceof StreamSessionStop) {
			StreamSessionStop m = (StreamSessionStop)message;
			//TODO: log info stream stop 
			try {
				handlerLock.acquire();
				for (MessageHandler messageHandler : handlers) {
					messageHandler.sessionStop(m);
				}
			} catch (Exception e) {
				logger.error("Excepton notifying message handler stream stop");
			} finally { 
				handlerLock.release();
			}
			
		}
		if (message instanceof StreamSessionPing) {
			StreamSessionPing m = (StreamSessionPing)message;
			try {
				handlerLock.acquire();
				for (MessageHandler messageHandler : handlers) {
					messageHandler.sessionPing(m);
				}
			} catch (Exception e) {
				logger.error("Excepton notifying message handler stream status");
			} finally { 
				handlerLock.release();
			}
			
		}
	}

	
	public void addHandler(MessageHandler handler) { 
		try {
			handlerLock.acquire();
			handlers.add(handler);
		} catch (Exception e) {
			logger.error("Exception adding message handler " + e.toString());
		} finally { 
			handlerLock.release();
		}
		
	}
	
	public void removeHandler(MessageHandler handler) { 
		try {
			handlerLock.acquire();
			handlers.remove(handler);
		} catch (Exception e) {
			logger.error("Exception adding message handler " + e.toString());
		} finally { 
			handlerLock.release();
		}
	}
}
