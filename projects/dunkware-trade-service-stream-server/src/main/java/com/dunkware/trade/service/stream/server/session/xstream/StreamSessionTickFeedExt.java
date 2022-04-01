package com.dunkware.trade.service.stream.server.session.xstream;

import com.dunkware.common.tick.TickHandler;
import com.dunkware.common.tick.proto.TickProto.Tick;
import com.dunkware.trade.service.tick.client.TickServiceClient;
import com.dunkware.trade.service.tick.client.TickServiceClientFactory;
import com.dunkware.trade.service.tick.client.TickServiceClientFeed;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamException;
import com.dunkware.xstream.api.XStreamExtension;
import com.dunkware.xstream.core.annotations.AXStreamExtension;
import com.dunkware.xstream.xproject.model.XStreamExtensionType;

@AXStreamExtension(type = StreamSessionTickFeedExtType.class)

public class StreamSessionTickFeedExt implements XStreamExtension, TickHandler {

	private StreamSessionTickFeedExtType myType;
	private XStream stream;
	
	private TickServiceClientFeed tickFeed;
	
	private TickServiceClient tickService; 
	
	@Override
	public void init(XStream stream, XStreamExtensionType type) throws XStreamException {
		this.stream = stream;
		myType = (StreamSessionTickFeedExtType)type;
		try {
			tickService = TickServiceClientFactory.connect(myType.getServiceEndpoint());
		} catch (Exception e) {
			throw new XStreamException("Exception Creating TickServiceClient for StreamSessionTickFeedExt " + e.toString());
		}
		if(myType.getDataTicks() == null) { 
			throw new XStreamException("TickFeedExt has null data tick type array ");
		}
	}

	@Override
	public void preStart() throws XStreamException {
		
	}

	@Override
	public void start() throws XStreamException {
		try {
			for (Integer key : myType.getDataTicks().keySet()) {
				stream.getTickRouter().registerDataTick(key, myType.getDataTicks().get(key));
			}
			tickFeed = tickService.createFeed(myType.getFeedSpec());
			tickFeed.getTickStream().addTickHandler(this);
		} catch (Exception e) {
			throw new XStreamException("Exception Creating TickService Client TickFeed " + e.toString());
		}
		
	}

	@Override
	public void preDispose() {
		tickFeed.dispose();
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void onTick(Tick tick) {
		stream.getTickRouter().streamTick(tick);
	}
	
	

	
	
}
