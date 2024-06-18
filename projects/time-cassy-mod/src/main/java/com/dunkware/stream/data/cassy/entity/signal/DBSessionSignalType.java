package com.dunkware.stream.data.cassy.entity.signal;

import java.time.LocalTime;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

//@Table("session_signal_type")
public class DBSessionSignalType {

	@PrimaryKey
    private DBSessionSignalTypeKey key;
	
	@Column("data")
	@CassandraType(type = Name.BLOB)
	private String data;

	@Column("entity")
	private int entity;
	
	@Column("time")
    @CassandraType(type = CassandraType.Name.TIME)
	private LocalTime time;
	
	public DBSessionSignalType() { 
		
	}

	public DBSessionSignalType(DBSessionSignalTypeKey key, String data, int entity, LocalTime time) {
		super();
		this.key = key;
		this.data = data;
		this.entity = entity;
		this.time = time;
	}

	public DBSessionSignalTypeKey getKey() {
		return key;
	}

	public void setKey(DBSessionSignalTypeKey key) {
		this.key = key;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getEntity() {
		return entity;
	}

	public void setEntity(int entity) {
		this.entity = entity;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	} 
	
	

}
