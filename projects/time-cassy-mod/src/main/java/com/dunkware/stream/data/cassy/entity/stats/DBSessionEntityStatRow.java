package com.dunkware.stream.data.cassy.entity.stats;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("session_entity_stat")
public class DBSessionEntityStatRow {

    @PrimaryKey
    private DBSessionEntityStatKey key;

    @Column("element")
    private int element;

    @Column("value")
    private double value;
    @CassandraType(type = CassandraType.Name.TIME)
    @Column("time")
    private LocalTime time;

    @Transient
    private int stream; 
    
    @Transient
    private LocalDate date;
    
    
    public DBSessionEntityStatRow() {
    }

    public DBSessionEntityStatRow(DBSessionEntityStatKey key, int element, double value, LocalTime time) {
        this.key = key;
        this.element = element;
        this.value = value;
        this.time = time;
        this.stream = key.getStream();
        this.date = key.getDate();
    }

    public DBSessionEntityStatKey getKey() {
        return key;
    }

    public void setKey(DBSessionEntityStatKey key) {
        this.key = key;
        this.stream = key.getStream();
        this.date = key.getDate();
    }

    @Transient
    public int getStat() {
        return key.getStat();
    }

    @Transient
    public int getEntity() {
        return key.getEntity();
    }

    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
    
    @Transient
    public LocalDate getDate() { 
    	return key.getDate();
    }

	@Override
	public String toString() {
		return new StringBuilder().append(getDate().getYear()).append(":").append(getDate().getMonthValue())
		.append(":").append(getDate().getDayOfMonth()).append("-").append("stream:").append(key.getStream())
		.append("entity:").append(getEntity()).append("element:").append(getElement()).append("stat:")
		.append(getStat()).append(":value").append(getValue()).toString();
	}

	public int getStream() {
		return stream;
	}

	public void setStream(int stream) {
		this.stream = stream;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
    
    
    
  
}
