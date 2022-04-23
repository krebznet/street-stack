package com.dunkware.trade.service.stream.json.controller.spec;

import com.dunkware.trade.service.stream.json.controller.session.StreamSessionStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class StreamStats {

	private String name;
	private StreamState state;
	@JsonInclude(Include.NON_NULL)
	private StreamSessionStatus session;

	@JsonInclude(Include.NON_NULL)
	private String error;

	public StreamStats() {

	}

	

	public StreamState getState() {
		return state;
	}



	public void setState(StreamState state) {
		this.state = state;
	}



	public StreamSessionStatus getSession() {
		return session;
	}

	public void setSession(StreamSessionStatus session) {
		this.session = session;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
