package com.dunkware.net.core.runtime.chart.registry;

import com.dunkware.net.core.runtime.chart.grid.GridComponent;

public class ChartRegistry {
	
	
	public static class Grid { 
		
		private String name; 
		private Class clazz; 
		
		public GridComponent newInstance() { 
			return null;
		}
	}

}
