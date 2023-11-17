package com.dunkware.common.stats;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.helpers.DRandom;

public class GenericNumberFactory {
	
	
	public static List<GenericNumber> create(int size) { 
		List<GenericNumber> results = new ArrayList<GenericNumber>();
		int i = 0;
		while(i < size) { 
			results.add(new GenericNumber(DRandom.getRandom(3, 3999), LocalTime.now().plusHours(DRandom.getRandom(0, 588))));
			i++;
		}
		return results;
	}

}
