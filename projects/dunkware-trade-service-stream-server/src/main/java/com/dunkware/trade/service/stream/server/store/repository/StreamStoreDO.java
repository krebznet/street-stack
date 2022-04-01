package com.dunkware.trade.service.stream.server.store.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.dunkware.common.util.mysql.model.MySqlConnection;

@Entity(name = "stream_store")
public class StreamStoreDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	private String host; 
	private String dbSchema; 
	private String user;
	private String pass; 
	private String name;
	private int port;
	private boolean active; 
	
	
	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getHost() {
		return host;
	}



	public void setHost(String host) {
		this.host = host;
	}



	
	public String getDbSchema() {
		return dbSchema;
	}



	public void setDbSchema(String dbSchema) {
		this.dbSchema = dbSchema;
	}



	public String getUser() {
		return user;
	}



	public void setUser(String user) {
		this.user = user;
	}



	public String getPass() {
		return pass;
	}



	public void setPass(String pass) {
		this.pass = pass;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getPort() {
		return port;
	}



	public void setPort(int port) {
		this.port = port;
	}


	public static MySqlConnection toConnection(StreamStoreDO obj) { 
		MySqlConnection con = new MySqlConnection();
		con.setFuzz(obj.getPass());
		con.setHost(obj.getHost());
		con.setSchema(obj.getDbSchema());
		con.setName(obj.getName());
		con.setPort(obj.getPort());
		con.setUser(obj.getUser());
		return  con;
	}



	public boolean isActive() {
		return active;
	}



	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
	
	

}
