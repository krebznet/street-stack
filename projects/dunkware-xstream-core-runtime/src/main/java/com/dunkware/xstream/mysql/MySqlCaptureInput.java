package com.dunkware.xstream.mysql;

/**
 * MySqlCaptureInput 
 * @author dkrebs
 *
 */
public class MySqlCaptureInput {
	
	private boolean dropTable = false; 
	private String host; 
	private String password;
	private int port; 
	private String username; 
	private String database; 
	private String tablePrefix;
	private int captureInterval;
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public String getTablePrefix() {
		return tablePrefix;
	}
	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}
	public int getCaptureInterval() {
		return captureInterval;
	}
	public void setCaptureInterval(int captureInterval) {
		this.captureInterval = captureInterval;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isDropTable() {
		return dropTable;
	}
	public void setDropTable(boolean dropTable) {
		this.dropTable = dropTable;
	}
	
	
	
	
	

}
