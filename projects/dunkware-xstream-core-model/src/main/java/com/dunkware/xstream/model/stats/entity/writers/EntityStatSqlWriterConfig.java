package com.dunkware.xstream.model.stats.entity.writers;

public class EntityStatSqlWriterConfig {

	private String host; 
	private String schema; 
	private String table; 
	private int port;
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getSchema() {
		return schema;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	} 
	
	
}
