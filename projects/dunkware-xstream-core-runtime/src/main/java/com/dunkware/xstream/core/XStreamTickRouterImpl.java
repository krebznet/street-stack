package com.dunkware.xstream.core;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.tick.TickHandler;
import com.dunkware.common.tick.TickHelper;
import com.dunkware.common.tick.filter.TickFilter;
import com.dunkware.common.tick.proto.TickProto.Tick;
import com.dunkware.common.tick.stream.TickStream;
import com.dunkware.common.tick.time.TimeTick;
import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamTickRouter;

public class XStreamTickRouterImpl implements XStreamTickRouter, TickStream {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker tickBlockTime = MarkerFactory.getMarker("TickBlockTime");
	
	private ConcurrentHashMap<Integer, Integer> dataTickTypes = new ConcurrentHashMap<Integer, Integer>();
	
	private ConcurrentHashMap<TickHandler, TickRouterHandler> tickHandlers = new ConcurrentHashMap<TickHandler, TickRouterHandler>();
	
	private AtomicLong tickCount = new AtomicLong();
	private AtomicLong dataTickCount = new AtomicLong();
	private AtomicLong timeTickCount = new AtomicLong();
	
	private DTime lastDataTickTime = DTime.now();
	private DTimeZone streamTimeZone; 
	private XStreamImpl stream; 
	
	private ConcurrentHashMap<String,Integer> missingIdMap = new ConcurrentHashMap<String,Integer>();
	
	public XStreamTickRouterImpl(XStreamImpl stream) {
		this.stream = stream; 
		this.streamTimeZone = stream.getInput().getTimeZone();
		lastDataTickTime = DTime.now(streamTimeZone);
	}
	
	
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
		try {
			long start = new Date().getTime();
			stream.getExecutor().awaitWhileTasksRunning();
			long end = new Date().getTime();
			if(logger.isTraceEnabled()) { 
				logger.trace(tickBlockTime,"tick block {}", end - start);
			}
		} catch (Exception e) {
			logger.error("Exception blocking on tick " + e.toString());
		
		}
		tickCount.incrementAndGet();
		// first call handlers
		for (TickRouterHandler streamHandler : tickHandlers.values()) {
			if(streamHandler.handle(tick)) { 
				streamHandler.getHandler().onTick(tick);
			}
		}
		// second if time tick send it to the clock
		if(tick.getType() == TimeTick.TYPE) { 
			timeTickCount.incrementAndGet();
			DTime newTime = TimeTick.decode(tick);
			
			
			stream.getClock().setTime(newTime);
		}
		// third if data tick route it to the row
		// create row if does not exist. 
		if(isDataTick(tick)) { 
			lastDataTickTime = DTime.now(streamTimeZone);
			dataTickCount.incrementAndGet();
			String rowId = getDataTickRowId(tick);
			if(stream.hasRow(rowId)) { 
				XStreamEntity row = stream.getRow(rowId);
				row.getTickStream().streamTick(tick);
			} else { 
				// new row create event //
				int identifier = -1;
				try {
					identifier = TickHelper.getInt(tick, 100);	
				} catch (Exception e) {
					if(missingIdMap.containsKey(rowId) == false) { 
						missingIdMap.put(rowId, 1);
						logger.error("Skipping Tick Data Row " + rowId + " missing field 100 symbol id");
					}
					return;
				}
				if(identifier == -1) { 
					if(missingIdMap.containsKey(rowId) == false) { 
						missingIdMap.put(rowId, 1);
						logger.error("Skipping Tick Data Row " + rowId + " field 100 symbol id = -1");
					}
					return;
				}
				
				XStreamEntity row = stream.createRow(rowId,identifier);
				row.getTickStream().streamTick(tick);
			}
		}
	}



	@Override
	public void registerDataTick(int type, int keyField) {
		this.dataTickTypes.put(type, keyField);
	}

	
	@Override
	public long getTickCount() {
		return tickCount.get();
	}

	@Override
	public long getTimeTickCount() {
		return timeTickCount.get();
	}

	@Override
	public long getDataTickCount() {
		return dataTickCount.get();
	}

	
	private String getDataTickRowId(Tick tick) { 
		int keyField = dataTickTypes.get(tick.getType());
		String value = TickHelper.getString(tick, keyField);
		return value;
	}
	
	private boolean isDataTick(Tick tick) { 
		return dataTickTypes.containsKey(tick.getType());
	}
	
	
	@Override
	public DTime getLastDataTickTime() {
		return lastDataTickTime;
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
