package com.dunkware.utils.mysql.model;

public class MySqlColumn {

	private String name; 
	private MySqlDataType dataType; 
	private boolean indexColumn;
	private int indexOrder;
	private boolean notNull = false; 
	private boolean primaryKey = false; 
	private boolean autoIncrement = false; 
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MySqlDataType getDataType() {
		return dataType;
	}
	public void setDataType(MySqlDataType dataType) {
		this.dataType = dataType;
	}
	public boolean isNotNull() {
		return notNull;
	}
	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}
	public boolean isPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}
	public boolean isIndexColumn() {
		return indexColumn;
	}
	public void setIndexColumn(boolean indexColumn) {
		this.indexColumn = indexColumn;
	}
	public int getIndexOrder() {
		return indexOrder;
	}
	public void setIndexOrder(int indexOrder) {
		this.indexOrder = indexOrder;
	}
	public boolean isAutoIncrement() {
		return autoIncrement;
	}
	public void setAutoIncrement(boolean autoIncrement) {
		this.autoIncrement = autoIncrement;
	}
	
	
	
	
	
	
}
