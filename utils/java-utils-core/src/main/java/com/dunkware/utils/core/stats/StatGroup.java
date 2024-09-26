package com.dunkware.utils.core.stats;

import java.beans.Transient;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatGroup {
	
	private String name; 
	private List<Stat> stats;
	
	@Transient
	public void sort() { 
		Collections.sort(stats, new Comparator<Stat>() {

			@Override
			public int compare(Stat o1, Stat o2) {
				
				return o1.getName().compareTo(o2.getName());
			}
			
		});
	}
	

}
