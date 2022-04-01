package com.dunkware.net.chart.runtime;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.net.chart.grid.GridComponent;

public class Repository {
	
	private Map<String,RepositoryGrid> grids = new ConcurrentHashMap<String,RepositoryGrid>();
	
	public void load() { 
		
	}
	
	public class RepositoryGrid { 
		private String name; 
		private Class clazz;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Class getClazz() {
			return clazz;
		}
		public void setClazz(Class clazz) {
			this.clazz = clazz;
		} 
		
		
	}
	
	
	public GridComponent createGridComponent(String name) throws Exception { 
		return null;
	}

}
