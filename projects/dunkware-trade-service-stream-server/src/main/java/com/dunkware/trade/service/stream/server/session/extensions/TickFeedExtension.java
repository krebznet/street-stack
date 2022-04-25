package com.dunkware.trade.service.stream.server.session.extensions;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.trade.service.stream.server.session.StreamSession;
import com.dunkware.trade.service.stream.server.session.StreamSessionExtension;
import com.dunkware.trade.service.stream.server.session.StreamSessionNode;
import com.dunkware.trade.service.stream.server.session.anot.AStreamSessionExt;
import com.dunkware.trade.service.stream.server.session.xstream.StreamSessionTickFeedExtType;
import com.dunkware.trade.service.stream.server.tick.StreamTickService;
import com.dunkware.trade.tick.model.TradeTicks;
import com.dunkware.trade.tick.model.feed.TickFeedSpec;
import com.dunkware.trade.tick.model.feed.TickFeedSpecBuilder;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

@AStreamSessionExt
public class TickFeedExtension implements StreamSessionExtension {

	private StreamSession session; 
	
	@Autowired
	private StreamTickService tickService; 
	
	@Override
	public void sessionStarting(StreamSession session) {
		this.session = session;
	}

	@Override
	public void nodeStarting(StreamSessionNode node) {
		StreamSessionTickFeedExtType type = new StreamSessionTickFeedExtType();
		//type.setServiceEndpoint(null);
		TickFeedSpecBuilder builder = TickFeedSpecBuilder.newInstance(session.getStream().getName() + "_" + node.getNode().getId() + "_" + DUUID.randomUUID(4));
			//	DunkTime.formatHHMMSS(LocalDateTime.now(DTimeZone.toZoneId(node.getStream().getTimeZone())));
		builder.addTickType(TradeTicks.TickSnapshot);
		for (TradeTickerSpec ticker : node.getTickers()) {
			builder.addTicker(ticker);
		}
		TickFeedSpec spec = builder.build();
		type.setFeedSpec(spec);
		type.setServiceEndpoint(tickService.getClient().getEndpoint());
		type.getDataTicks().put(TradeTicks.TickSnapshot, TradeTicks.FieldSymbol);
		node.getStreamBundle().addExtension(type);;
		
	}

	@Override
	public void nodeStarted(StreamSessionNode node) {
		
	}

	@Override
	public void sessionStarted(StreamSession session) {
		
	}

	@Override
	public void sessionStopping(StreamSession session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionStopped(StreamSession session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nodeStartException(StreamSessionNode node, String exception) {
		// TODO Auto-generated method stub
		
	}

	
}
