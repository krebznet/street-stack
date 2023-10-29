package com.dunkware.trade.service.beach.server.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dunkware.trade.service.beach.model.trade.BeachTradeStatus;

@Entity(name = "BeachSessionTradeEntity")
@Table(name = "trade_session_trade")
public class BeachSessionTradeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	
	@ManyToOne()
	private BeachSystemEntity system; 
	
	@Column(columnDefinition = "text")
	private String model; 
	
	@Enumerated(EnumType.ORDINAL)
	private BeachTradeStatus status;
	
	
	private String identifier; 
	
	@Column(name = "time_created")	
	private LocalDateTime created; 
	
	@Column( name = "time_submittied")
	private LocalDateTime submitted; 
	
	@Column(name = "broker_exception")
	private String brokerException; 

	@Column(name = "internal_exception")
	private String exception; 
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BeachSystemEntity getSystem() {
		return system;
	}

	public void setSystem(BeachSystemEntity system) {
		this.system = system;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public BeachTradeStatus getStatus() {
		return status;
	}

	public void setStatus(BeachTradeStatus status) {
		this.status = status;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getSubmitted() {
		return submitted;
	}

	public void setSubmitted(LocalDateTime submitted) {
		this.submitted = submitted;
	}

	public String getBrokerException() {
		return brokerException;
	}

	public void setBrokerException(String brokerException) {
		this.brokerException = brokerException;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	} 
	
	
	
	
	

}
