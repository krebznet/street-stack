package com.dunkware.trade.service.stream.worker.session.publish;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.time.data.model.stats.entity.EntityStatTypes;
import com.dunkware.time.data.model.stats.entity.EntityStatsModel;
import com.dunkware.time.data.model.stats.entity.EntityVarStatsModel;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityVar;

public class EntityDateStatsCollector {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("VarStatKafkaPublisher");

	public static final int CollectorCount = 3;

	public static EntityDateStatsCollector newInstance(Collection<XStreamEntity> entities, LocalDate date, int stream) {
		return new EntityDateStatsCollector(entities, stream, date);
	}

	private final BlockingQueue<XStreamEntity> entities = new LinkedBlockingQueue<XStreamEntity>();

	private EntityDateStatsCollector(Collection<XStreamEntity> input, int stream, LocalDate date) {
		this.entities.addAll(input);
		this.stream = stream;
		this.date = date;
	}

	private int stream;

	private AtomicInteger collectorCreation = new AtomicInteger(0);
	private LocalDate date;
	public List<EntityStatsModel> collected = new ArrayList<EntityStatsModel>();

	private AtomicInteger returnedCollectors = new AtomicInteger(0);

	public List<EntityStatsModel> collectStats() throws Exception {
		int i = 0;
		while (i < CollectorCount) {
			collectorCreation.incrementAndGet();
			Collector c = new Collector();
			c.start();
			i++;
		}
		int count = 0;
		while (returnedCollectors.get() != collectorCreation.get()) {
			try {
				Thread.sleep(250);
				count++;
				if (count > 25) {
					logger.error(marker,
							"Okay collected size is " + collected.size() + " queue size is " + entities.size());
					return collected;
				}
			} catch (Exception e) {
				throw e;
			}

		}

		return collected;
	}

	private class Collector extends Thread {

		public void run() {
			int counter = 0;
			while (true) {
				try {
					XStreamEntity entity = entities.poll(1, TimeUnit.SECONDS);
					if (entity == null) {
						returnedCollectors.incrementAndGet();
						return;
					}

					EntityStatsModel stats = new EntityStatsModel();
					stats.setStream(stream);
					stats.setEntity(entity.getIdentifier());
					stats.setDate(date);
					for (XStreamEntityVar entityVar : entity.getVars()) {
						if (!entityVar.isNumeric() || entityVar.getSize() == 0) {
							continue;
						}
						EntityVarStatsModel statsVar = new EntityVarStatsModel();
						statsVar.setVar(entityVar.getVarType().getCode());
						statsVar.getStats().put(EntityStatTypes.VarHigh, entityVar.getHigh().doubleValue());
						statsVar.getTimes().put(EntityStatTypes.VarHigh, entityVar.getHighTime());
						statsVar.getStats().put(EntityStatTypes.VarLow, entityVar.getLow().doubleValue());
						statsVar.getTimes().put(EntityStatTypes.VarLow, entityVar.getLowTime());
						stats.getVarstats().put(entityVar.getVarType().getCode(),statsVar);
					
					}
					
					collected.add(stats);

				} catch (InterruptedException e) {

					return;
				}
			}
		}

	}
}
