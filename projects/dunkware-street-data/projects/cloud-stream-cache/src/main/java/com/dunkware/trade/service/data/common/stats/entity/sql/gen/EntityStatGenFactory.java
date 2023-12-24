package com.dunkware.trade.service.data.common.stats.entity.sql.gen;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.xstream.model.stats.entity.EntityStat;

public class EntityStatGenFactory {

	public static List<EntityStat> build(EntityStatGenBean bean) { 
		List<EntityStat> results = new ArrayList<EntityStat>();
		long days = ChronoUnit.DAYS.between(bean.getStartDate(), bean.getStartDate().plusDays(bean.getDayCount()));
		LocalDate currentDate = bean.getStartDate();
		long count = 0;
		while(count < days) { 
			buildDateStats(currentDate, results, bean);
			count++;
			currentDate = currentDate.plusDays(1);
		}
		return results;
	}
	
	private static void buildDateStats(LocalDate date, List<EntityStat> stats, EntityStatGenBean bean) { 
		int entityCount = 0; 
		int entityId = 1;
		while(entityCount < bean.getEntityCount()) { 
			buildEntityStats(date, stats, entityId, bean);
			entityId++;
			entityCount++;
		}
	}
	
	private static void buildEntityStats(LocalDate date, List<EntityStat> stats, int entityId, EntityStatGenBean bean) { 
		// first vars; 
		int varCount = 0; 
		int varId = 1; 
		while(varCount < bean.getVarCount()) { 
			buildEntityVarStats(date, stats, entityId, varId, bean);
			varId++;
			varCount++;
		}
		buildEntitySignalStats(date, stats, entityId, bean);
	}
	
	private static void buildEntityVarStats(LocalDate date, List<EntityStat> stats, int entityId, int varId,EntityStatGenBean bean) { 
		int statCount = 0; 
		int statId = 1; 
		while(statCount < bean.getVarStatCount()) { 
			EntityStat stat = new EntityStat();
			stat.setDate(date);
			stat.setStat(statId);
			stat.setEntity(entityId);
			stat.setElement(varId);
			stat.setTime(randomTime());
			stat.setValue(randomValue());
			stats.add(stat);
			statCount++;
			statId++;
		}
	}
	
	private static void buildEntitySignalStats(LocalDate date, List<EntityStat> stats, int entityId, EntityStatGenBean bean) { 
		int signalCount = 0;
		int signalId = 1; 
		while(signalCount < bean.getSignalCount()) { 
			EntityStat stat = new EntityStat();
			stat.setDate(date);
			stat.setStat(100);
			stat.setEntity(entityId);
			stat.setElement(signalId);
			stat.setValue(signalCount + 1);
			stats.add(stat);
			signalId++;
			signalCount++;
		}
	}
	
	
	private static LocalTime randomTime() {
		int hour = DRandom.getRandom(2, 22);
		int minute = DRandom.getRandom(3, 59);
		int second = DRandom.getRandom(0, 59);
		return LocalTime.of(hour, minute,second);
		
	}
	
	private static Double randomValue() { 
		double rangeMin = 3.23;
		double rangeMax = 3293443.3;
		Random r = new Random(DRandom.getRandom(3, 100));
		return rangeMin + (rangeMax - rangeMin) * r.nextDouble();
	}
	
	
}
