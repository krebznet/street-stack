package com.dunkware.trade.service.stream.worker.session.stats;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.stats.GenericNumber;
import com.dunkware.common.stats.GenericStatistics;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityVar;
import com.dunkware.xstream.api.XStreamSignal;
import com.dunkware.xstream.core.signal.search.XStreamSignalSearchBuilder;
import com.dunkware.xstream.model.signal.type.XStreamSignalType;
import com.dunkware.xstream.model.stats.entity.EntityStat;
import com.dunkware.xstream.model.stats.entity.EntityStatType;

public class StreamEntityStatFactory {
	
	private Marker marker = MarkerFactory.getMarker("EntityStatFactory");
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public static final int generatorCount = 5;
	private List<EntityStat> factoryStats = new ArrayList<EntityStat>();
	
	private BlockingQueue<XStreamEntity> entityQueue = new LinkedBlockingQueue<XStreamEntity>();
	private List<EntityVarStatGenerator> generators = new ArrayList<StreamEntityStatFactory.EntityVarStatGenerator>();
	private AtomicInteger completedGeneratorCount = new AtomicInteger(0);
	
	private XStream stream; 
	private LocalDate date; 
	
	public StreamEntityStatFactory() {
		int i = 0; 
		while(i < 5) { 
			EntityVarStatGenerator gen = new EntityVarStatGenerator();
			generators.add(gen);
			i++;
		}
		
		
	}
	
	public List<EntityStat> buildStreamStats(XStream stream) throws Exception { 
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
				if(loopCount > 100) {
					throw new Exception("25 seconds can't built your fuckin entity stats");
				}
			} catch (Exception e) {
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
					XStreamEntity entity = entityQueue.poll(3, TimeUnit.SECONDS);
					if(entity == null) { 
						completedGeneratorCount.incrementAndGet();
						return; 
					}
					for (XStreamEntityVar var : entity.getVars()) {
						if(var.getSize() > 0 && var.isNumeric()) { 
							List<GenericNumber> values = var.getNumericValues();
							GenericStatistics stats = new GenericStatistics();
							stats.calculateStatistics(values);
							EntityStat stat = new EntityStat();
							stat.setDate(date);
							stat.setEntity(entity.getIdentifier());
							stat.setStat(EntityStatType.VAR_HIGH);
							stat.setValue(stats.getHigh());
							stat.setTime(stats.getHighTime());
							stat.setElement(var.getVarType().getCode());
							factoryStats.add(stat);
							stat = new EntityStat();
							stat.setDate(date);
							stat.setEntity(entity.getIdentifier());
							stat.setElement(var.getVarType().getCode());;
							stat.setStat(EntityStatType.VAR_LOW);
							stat.setValue(stats.getLow());
							stat.setTime(stats.getLowTime());
							factoryStats.add(stat);
							stat = new EntityStat();
							stat.setDate(date);
							stat.setEntity(entity.getIdentifier());
							stat.setElement(var.getVarType().getCode());;
							stat.setStat(EntityStatType.VAR_AVG);
							stat.setValue(stats.getMean());
							factoryStats.add(stat);
							stat = new EntityStat();
							stat.setDate(date);
							stat.setEntity(entity.getIdentifier());
							stat.setElement(var.getVarType().getCode());;
							stat.setStat(EntityStatType.VAR_VARIANCE);
							stat.setValue(stats.getVariance());
							factoryStats.add(stat);
							stat = new EntityStat();
							stat.setDate(date);
							stat.setElement(var.getVarType().getCode());
							stat.setEntity(entity.getIdentifier());
							stat.setStat(EntityStatType.VAR_STD);
							stat.setValue(stats.getStdDev());
							stat.setDate(date);
							factoryStats.add(stat);
							

						}
					}
					
					// get your signal stats here now as well fuck man. 
					Map<Integer,AtomicInteger> signalCounts = new HashMap<Integer,AtomicInteger>();
					for (XStreamSignalType signalType : stream.getSignals().getSiganlTypes()) {
						signalCounts.put((int)signalType.getId(), new AtomicInteger(0));
					}
					List<XStreamSignal> entitySignals = stream.getSignals().search(XStreamSignalSearchBuilder.builder().entityFilter(entity.getIdentifier()).build());
					for (XStreamSignal xStreamSignal : entitySignals) {
						signalCounts.get(xStreamSignal.getSignal().getId()).incrementAndGet();
					}
					for (Integer signalTypeId : signalCounts.keySet()) {
						AtomicInteger atomic = signalCounts.get(signalTypeId);
						EntityStat stat = new EntityStat();
						stat.setDate(date);;
						stat.setEntity(entity.getIdentifier());
						stat.setElement(signalTypeId);
						stat.setStat(EntityStatType.SIGNAL_COUNT);
						stat.setValue(atomic.get());
						factoryStats.add(stat);
					}
				
					
					
					
				} catch (Exception e) {
					logger.error("Exception building entity stats " + e.toString());
					
					// TODO: handle exception
				}

				
				
				
			}
			
			
		}
		
	}

}
