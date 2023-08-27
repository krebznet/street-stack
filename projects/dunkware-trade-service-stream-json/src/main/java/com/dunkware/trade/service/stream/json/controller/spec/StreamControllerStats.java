package com.dunkware.trade.service.stream.json.controller.spec;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.trade.service.stream.json.controller.session.StreamSessionStats;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class StreamControllerStats {

	private String name;
	private StreamState state;
	@JsonInclude(Include.NON_NULL)
	private List<String> errors = new ArrayList<String>();
	@JsonInclude(Include.NON_NULL)
	private String streamException;
	@JsonInclude(Include.NON_NULL)
	private StreamSessionStats session;
	@JsonInclude(Include.NON_NULL)
	private String startException;
	@JsonInclude(Include.NON_NULL)
	private String stopException; 

	public StreamControllerStats() {

	}

	public StreamState getState() {
		return state;
	}

	public void setState(StreamState state) {
		this.state = state;
	}

	public StreamSessionStats getSession() {
		return session;
	}

	public void setSession(StreamSessionStats session) {
		this.session = session;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreamException() {
		return streamException;
	}

	public void setStreamException(String streamException) {
		this.streamException = streamException;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public String getStartException() {
		return startException;
	}

	public void setStartException(String startException) {
		this.startException = startException;
	}

	public String getStopException() {
		return stopException;
	}

	public void setStopException(String stopException) {
		this.stopException = stopException;
	}
	
	
	
	
	
	



}
