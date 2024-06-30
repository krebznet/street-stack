package com.dunkware.utils.mysql.model;

public class MySqlConnection {

	private String host; 
	private String schema; 
	private int port; 
	private String user; 
	private String fuzz;
	private String name;
	
	
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
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getFuzz() {
		return fuzz;
	}
	public void setFuzz(String fuzz) {
		this.fuzz = fuzz;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
	
	
	
}
