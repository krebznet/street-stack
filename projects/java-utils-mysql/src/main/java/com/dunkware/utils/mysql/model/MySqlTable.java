package com.dunkware.utils.mysql.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MySqlTable {

	private String name; 
	private List<MySqlColumn> columns = new ArrayList<MySqlColumn>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<MySqlColumn> getColumns() {
		return columns;
	}
	public void setColumns(List<MySqlColumn> columns) {
		this.columns = columns;
	}

	public List<MySqlColumn> getPrimaryKeys() { 
		List<MySqlColumn> list = new ArrayList<MySqlColumn>();
		for (MySqlColumn column : columns) {
			if(column.isPrimaryKey()) { 
				list.add(column);
			}
		}
		return list;
	}
	public List<MySqlColumn> getIndexColumns() { 
		List<MySqlColumn> list = new ArrayList<MySqlColumn>();
		for (MySqlColumn column : columns) {
			if(column.isIndexColumn()) { 
				list.add(column);
			}
		}
		// lets go ahead and sort them :) 
		Collections.sort(list, new IndexColumnSorter());
		return list;
	}
	
	
	private class IndexColumnSorter implements Comparator<MySqlColumn> {

		@Override
		public int compare(MySqlColumn o1, MySqlColumn o2) {
			  return o1.getIndexOrder() - o2.getIndexOrder();
		} 
		
		
	}
}
