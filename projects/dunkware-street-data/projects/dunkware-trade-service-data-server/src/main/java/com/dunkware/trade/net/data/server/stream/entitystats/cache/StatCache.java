package com.dunkware.trade.net.data.server.stream.entitystats.cache;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dunkware.xstream.model.stats.entity.EntityStat;

public class StatCache   {

	private HashMap<Long,CacheDate> days = new HashMap<Long,CacheDate>();
	private volatile long size = 0;
	
	
	
	public long getSize() {
		return size; 
	}

	
	public List<EntityStat> getStatsHistoricalRelative(LocalDate date, int daysOrSessionsBack, boolean allOrNull,
			int entity, int stat, int element) {
		List<EntityStat> results = new ArrayList<EntityStat>();
		long dateKey = CacheHelper.dateKey(date);
		long days[] = previousDates(dateKey, daysOrSessionsBack,allOrNull);
		if(allOrNull && days == null) { 
			return null;
		}
		// okay so now - 
		int i = 0;
		while(i < days.length) { 
			long index = days[i];
			CacheDate cacheDate = this.days.get(index);
			if(cacheDate == null) { 
				return null;
			}
			CacheEntity cacheEntity = cacheDate.getEntity(entity);
			if(cacheEntity == null) { 
				return null;
			}
			CacheElement el = cacheEntity.get(element);
			if(el == null) { 
				return null;
			}
			
			EntityStat estat = el.getStatValue(stat);
			if(estat == null) { 
				return null;}
			results.add(estat);
			i++;
		}
		
		return results;

		
		
		
	}


	
	public void cache(EntityStat stat) {
		long dateKey = CacheHelper.dateKey(stat.getDate());
		CacheDate day = getOrCreateDate(dateKey);
		CacheEntity entity = day.getOrCreate(stat.getEntity());
		CacheElement element = entity.getOrCreate(stat.getElement());
		element.add(stat);
		size++;
	}
	
	public CacheDate getOrCreateDate(long key) { 
		CacheDate day = days.get(key);
		if(day == null) { 
			day = new CacheDate(key);
			days.put(key, day);
		}
		return day;
	}

	
	
	private long getPreviousDate(long keyDate) { 
		int count = 0;
		long prev = keyDate - 1;
		while(!days.containsKey(prev)) { 
			count++;
			prev = prev - 1;
			if(count == 100) { 
				return 0; // not found;
			}
		}
		return prev;
	}
	
	/**
	 * Returns an array of date keys for the previous days found 
	 * from the keyDate provided, it does not have to be sequential
	 * keys for the days like 5,4,3,2,1  could be 5,2,1 on days the
	 * market does not run. 
	 * 
	 *  
	 * @param keyDate
	 * @param count
	 * @return
	 */
	public long[] previousDates(long keyDate, int count, boolean nullReturnOnNoResolve) { 
		
		long[] keys = new long[count];
		int resolved = 0; 
		long currentKey = keyDate;
		while(resolved < count) { 
			
			keys[resolved] = getPreviousDate(currentKey); 
			if(keys[resolved] == 0) { 
				// mean we did not find a previous day 
				// check if we should return null 
				if(nullReturnOnNoResolve) { 
					return null;
				}
				return keys;
			}
			resolved++;
			currentKey = currentKey - 1;
		}
		return keys;
	}
	
	
	/**
	 * Tells us if we can resolve a specific number of days or stream sessions
	 * back from a given keyDate. 
	 * @param keyDate
	 * @param count
	 * @return
	 */
	public boolean resolvesPreviousDates(long keyDate, int count) { 
		if(previousDates(keyDate, count, true) == null) { 
			return false; 
		}
		return true;
	}
	
	
	
	

	
	
}
