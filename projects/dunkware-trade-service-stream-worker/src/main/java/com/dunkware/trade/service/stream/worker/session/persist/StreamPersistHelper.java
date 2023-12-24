package com.dunkware.trade.service.stream.worker.session.persist;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.common.util.helpers.DNumberHelper;

public class StreamPersistHelper {
	
	public static void main(String[] args) {
		testRemoveEqualKeyValues();
	}
	
	private static void testRemoveEqualKeyValues() { 
		Map<Integer,Number> current = new ConcurrentHashMap<Integer, Number>();
		Map<Integer,Number> compare = new ConcurrentHashMap<Integer, Number>();
		current.put(1, 32.5);
		compare.put(1, 32.5);
		current.put(2, 4944);
		current.put(3, 32.3);
		compare.put(3, 21);
		Map<Integer,Number> results = removeEqualKeyValues(current, compare);
		for (Integer key : results.keySet()) {
			System.out.println(key + "=" + results.get(key));
		}
		
	}

	public static Map<Integer,Number> removeEqualKeyValues(Map<Integer,Number> current, Map<Integer,Number> compare) {
		Map<Integer,Number> results = new ConcurrentHashMap<Integer,Number>();
		for (Integer integer : current.keySet()) {
			Number compareValue = compare.get(integer);
			if(compareValue != null) { 
				// if we have a value to compare lets see if its the same value
				// if so we add it to the list of keys to be removed from the current map
				if(DNumberHelper.compare(current.get(integer), compareValue) != 0) { 
					results.put(integer, current.get(integer));
				}
			} else { 
				results.put(integer, current.get(integer));
			}
		}
		return results;
	}
}
