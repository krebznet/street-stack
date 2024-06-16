package com.dunkware.blocks.runtime.stat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

import com.dunkware.blocks.model.FormatType;
import com.dunkware.utils.core.json.DunkJson;

public class StatRegistry {

	private Map<String, List<Stat>> stats = new ConcurrentHashMap<String, List<Stat>>();
	private List<StatGroup> statGroups = new ArrayList<StatGroup>();
	private Semaphore statLock = new Semaphore(0);

	/**
	 * Serializes a snapshot of the stat values
	 * 
	 * @return
	 */
	public List<StatGroup> statGroups() {
			return statGroups;
	}
	
	public String serailize() { 
		try {
			return DunkJson.serialize(statGroups);
		} catch (Exception e) {
			return e.toString();
			
		}
	}

	public Stat createStat(String group, String name, FormatType formatType) {
		Stat stat = Stat.builder().name(name).group(group).formatType(formatType).value(null).build();
		if (stats.containsKey(group)) {
			stats.get(group).add(stat);
			try {
				statLock.acquire();
				StatGroup statGroup = getGroup(name);
				statGroup.getStats().add(stat);
				statGroup.sort();
				statGroups.add(statGroup);
			} catch (Exception e) {

			} finally { 
				statLock.release();
			}
		} else {
			try {
				statLock.acquire();
				List<Stat> statList = new ArrayList<Stat>();
				statList.add(stat);
				stats.put(stat.getGroup(), statList);
				StatGroup groupObj = new StatGroup();
				groupObj.setName(group);
				groupObj.getStats().add(stat);
				statGroups.add(groupObj);
				Collections.sort(statGroups, new Comparator<StatGroup>() {

					@Override
					public int compare(StatGroup o1, StatGroup o2) {
						o1.sort();
						o2.sort();
						return o1.getName().compareTo(o2.getName());
					}

				});
			} catch (Exception e) {
				
			} finally { 
				statLock.release();
			}
			
			return stat;
		}
		return stat;
	}

	private StatGroup getGroup(String name) {
		for (StatGroup statGroup : statGroups) {
			if(statGroup.getName().equals(name)) { 
				return statGroup;
			}
		}
		StatGroup group = new StatGroup();
		group.setName(name);;
		statGroups.add(group);
		return group;
	}
}
