package com.dunkware.common.tick.stream.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import com.dunkware.common.tick.TickHandler;
import com.dunkware.common.tick.filter.TickFilter;
import com.dunkware.common.tick.proto.TickProto.Tick;
import com.dunkware.common.tick.stream.TickStream;

public class TickStreamImpl implements TickStream {

	private ConcurrentHashMap<TickHandler, TickRouterHandler> tickHandlers = new ConcurrentHashMap<TickHandler, TickStreamImpl.TickRouterHandler>();
	private AtomicLong tickCounter = new AtomicLong(0);
	

	@Override
	public void addTickHandler(TickHandler handler) {
		TickRouterHandler streamHandler = new TickRouterHandler(handler);
		this.tickHandlers.put(handler, streamHandler);	
	}
	
	@Override
	public void addTickHandler(TickHandler handler, TickFilter tickFilter) {
		TickRouterHandler streamHandler = new TickRouterHandler(handler,tickFilter);
		this.tickHandlers.put(handler, streamHandler);
		
	}
	@Override
	public void removeTickHandler(TickHandler handler) {
		tickHandlers.remove(handler);
	}
	@Override
	public void streamTick(Tick tick) {
		tickCounter.incrementAndGet();
		for (TickRouterHandler streamHandler : tickHandlers.values()) {
			if(streamHandler.handle(tick)) { 
				streamHandler.getHandler().onTick(tick);
			}
		}
	}
	
	@Override
	public long getTickCount() {
		return tickCounter.get();
	}


	private class TickRouterHandler { 
		
		private TickFilter tickFilter = null;
		private TickHandler handler;
		
		public TickRouterHandler(TickHandler handler) { 
			this.handler = handler;
		}
		
		public TickRouterHandler(TickHandler handler, TickFilter filter) { 
			this.tickFilter = filter;
			this.handler = handler;
		}
		
		public boolean handle(Tick tick) { 
			if(tickFilter == null) { 
				return true;
			}
			return tickFilter.match(tick);
		}
		
		public TickHandler getHandler() { 
			return handler;
		}
	}
	
	
	
	

}
