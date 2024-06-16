package com.dunkware.utils.core.stats;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stats {

	private String name; 
	private List<StatGroup> groups = new ArrayList<StatGroup>();
	
}
