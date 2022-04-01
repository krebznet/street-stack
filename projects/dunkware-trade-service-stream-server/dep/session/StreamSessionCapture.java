package com.dunkware.trade.service.stream.server.controller.session;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.events.anot.ADEventMethod;
import com.dunkware.trade.service.stream.server.controller.event.EStreamSessionSignal;
import com.dunkware.trade.service.stream.server.controller.event.EStreamSessionStopped;
import com.dunkware.trade.service.stream.server.controller.event.EStreamSessionTicker;
import com.dunkware.trade.service.stream.server.controller.repository.StreamSessionDO;
import com.dunkware.trade.service.stream.server.controller.repository.StreamSessionRepo;
import com.dunkware.trade.service.stream.server.controller.repository.StreamSessionSignalDO;
import com.dunkware.trade.service.stream.server.controller.repository.StreamSessionSignalRepo;
import com.dunkware.trade.service.stream.server.controller.repository.StreamSessionTickerDO;
import com.dunkware.trade.service.stream.server.controller.repository.StreamSessionTickerRepo;
import com.dunkware.trade.tick.api.ticker.Ticker;

public class StreamSessionCapture {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private StreamSessionRepo sessionRepo; 
	
	@Autowired
	private StreamSessionSignalRepo signalRepo; 
	
	@Autowired
	private StreamSessionTickerRepo tickerRepo;
	
	private StreamSession session;
	
	private StreamSessionDO entity; 
	
	private int signalCount; 
	private int tickerCount;
	
	public void start(StreamSession session) throws Exception { 
		this.session = session;
		
		entity = new StreamSessionDO();
		
		entity.setDate(LocalDate.now());
		entity.setStartDateTime(LocalDateTime.now(DTimeZone.toZoneId(session.getStream().getSpec().getTimeZone())));
		entity.setStream(session.getStream().getEntity());
		entity.setStreamStore(session.getStreamStore().getEntity());
		entity.setTickerListSize(session.getTickerList().getSize());
		
		
		try {
			sessionRepo.save(entity);
		} catch (Exception e) {
			logger.error("Exception saving StreamSessionDO " + e.toString());
			throw e; 
		}
		
		List<Ticker> activeTickers = session.getActiveTickers();
		session.getEventNode().addEventHandler(this);
		
		for (Ticker ticker : activeTickers) {
			StreamSessionTickerDO ent = new StreamSessionTickerDO();
			ent.setSession(entity);
			ent.setSymbol(ticker.getSymbol());
			ent.setTickerId(ticker.getId());
			ent.setTime(LocalTime.now(DTimeZone.toZoneId(session.getStream().getSpec().getTimeZone())));	
			tickerRepo.save(ent);
		}
		
	}
	
	
	
	@ADEventMethod()
	public void streamStopped(EStreamSessionStopped event) {
		if(logger.isDebugEnabled()) { 
			logger.debug("Stream Session {} Capture Stop ",session.getStream().getSpec().getName());
		}
		if(event.getStream().getSession() == session) { 
			entity.setStopDateTime(LocalDateTime.now());
			entity.setTickerCount(tickerCount);
			entity.setSignalCount(signalCount);
			sessionRepo.save(entity);
		}
		session.getEventNode().removeEventHandler(this);
		
		
	}
	
	
	@ADEventMethod()
	public void sessionSignal(EStreamSessionSignal signal) { 
		if(signal.getStream().getSession() == session) { 
			StreamSessionSignalDO ent = new StreamSessionSignalDO();
			ent.setDate(LocalDate.now());
			ent.setTime(signal.getTimestamp().toLocalTime().get());
			ent.setSymbol(signal.getSignal().getSignal().getRow());
			ent.setType(signal.getSignal().getSignal().getSignal());

			ent.setSession(entity);
			signalCount++;
			try {
				signalRepo.save(ent);
			} catch (Exception e) {
				logger.error("Exception saving stream session signal " + e.toString());
			}			
		}

		
	}
	
	@ADEventMethod()
	public void sessionTicker(EStreamSessionTicker ticker) { 
		if(ticker.getSession() == session) { 
			StreamSessionTickerDO ent = new StreamSessionTickerDO();
			ent.setSession(entity);
			ent.setSymbol(ticker.getTicker().getSymbol());
			ent.setTickerId(ticker.getTicker().getId());
			ent.setTime(ticker.getTime().get());
			try {
				tickerRepo.save(ent);
				tickerCount++;
			} catch (Exception e) {
				logger.error("Exception saving session ticker " + e.toString());
			}	
		}
		
	}
	
	
	
	
}
