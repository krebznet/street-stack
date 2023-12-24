package com.dunkware.trade.service.stream.worker.session.stats;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityVar;
import com.dunkware.xstream.model.stats.entity.EntityStat;
import com.dunkware.xstream.model.stats.entity.EntityStatType;

public class StreamEntityStatFactory {
	
	private Marker marker = MarkerFactory.getMarker("EntityStatFactory");
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public static final int generatorCount = 10;
	private List<EntityStat> factoryStats = new ArrayList<EntityStat>();
	private Semaphore factoryStatsLock = new Semaphore(1);
	
	private BlockingQueue<XStreamEntity> entityQueue = new LinkedBlockingQueue<XStreamEntity>();
	private List<EntityVarStatGenerator> generators = new ArrayList<StreamEntityStatFactory.EntityVarStatGenerator>();
	private AtomicInteger completedGeneratorCount = new AtomicInteger(0);
	
	private XStream stream; 
	private LocalDate date; 
	
	public StreamEntityStatFactory() {
		int i = 0; 
		while(i < generatorCount) { 
			EntityVarStatGenerator gen = new EntityVarStatGenerator();
			generators.add(gen);
			i++;
		}
		
		
	}
	
	public List<EntityStat> buildEntityStats(XStream stream) throws Exception { 
		this.stream = stream;
		date = stream.getClock().getLocalDateTime().toLocalDate();
		for (XStreamEntity xStreamEntity : stream.getRows()) {
			entityQueue.add(xStreamEntity);
		}
		for (EntityVarStatGenerator gen : generators) {
			gen.start();
		}
		int loopCount = 0;
		
		DStopWatch watch = DStopWatch.create();
		watch.start();
		while(completedGeneratorCount.get() != generatorCount) { 
			try {
				Thread.sleep(250);
				loopCount++;
				if(loopCount > 300) {
					throw new Exception("75 seconds can't built your fuckin entity stats");
				}
			} catch (Exception e) {
				logger.error(e.toString(),e);
				
				throw new Exception("fuck outer exception " + e.toString());
			}
		}
		
		watch.stop();
		logger.info(marker, "Created {} Entity Stats",factoryStats.size());
		return factoryStats;
	}
	
	
	
	
	
	
	private class EntityVarStatGenerator extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				try {
					XStreamEntity entity = entityQueue.poll(5, TimeUnit.SECONDS);
					if(entity == null) { 
						completedGeneratorCount.incrementAndGet();
						return; 
					}
					try {
						for (XStreamEntityVar var : entity.getVars()) {
							
							if(var.getSize() > 0 && var.isNumeric()) { 
								EntityStat stat = new EntityStat();
								boolean exception = false; 
								try {
									stat.setDate(stream.getInput().getDate().get());
									stat.setEntity(entity.getIdentifier());
									stat.setElement(var.getVarType().getCode());
									stat.setStat(EntityStatType.VAR_HIGH);
									stat.setValue(var.getHigh());
									stat.setTime(var.getHighTime());
											
								} catch (Exception e) {
									logger.error("Exception creating high stat " + e.toString(),e);
									exception = true;
								}
								if(!exception)
									factoryStats.add(stat);
								EntityStat low = new EntityStat();
								 exception = false;
								 try {
									 
										low.setDate(stream.getInput().getDate().get());
										low.setEntity(entity.getIdentifier());
										low.setElement(var.getVarType().getCode());
										low.setStat(EntityStatType.VAR_LOW);
										low.setValue(var.getLow());
										low.setTime(var.getLowTime());	
								} catch (Exception e) {
									logger.error("Exception creating low stat " + e.toString(), e);
									exception = true;
								}
								if(!exception)
									factoryStats.add(low);
							}
						}
						
					} catch (Exception e) {
						logger.error("Exception in iterate entity vars " + e.toString());;
					}
				
					try {
						Map<XStreamEntity,Map<Integer,AtomicInteger>> entitySignals = stream.getSignals().getEntitySignalCounts();
						for (XStreamEntity xStreamEntity : entitySignals.keySet()) {
							Map<Integer,AtomicInteger> counts = entitySignals.get(xStreamEntity);
							for (Integer signalid : counts.keySet()) {
								EntityStat stat = new EntityStat();
								stat.setDate(date);;
								stat.setEntity(entity.getIdentifier());
								stat.setElement(signalid);
								stat.setStat(EntityStatType.SIGNAL_COUNT);
								stat.setValue(counts.get(signalid).get());
								factoryStats.add(stat);
								
							}
						}
						
					} catch (Exception e) {
						logger.error("exception collecting signal stats " + e.toString(),e);;
					}
					
										
					} catch (Exception e) {
						logger.error("Exception in signal stats creation " + e.toString());	
					}
				
					
				

				
				
				
			}
			
			
		}
		
	}

}
