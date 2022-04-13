package com.dunkware.trade.service.stream.json.controller.spec;

import com.dunkware.trade.service.stream.json.controller.session.StreamSessionStatsSpec;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class StreamStatsSpec {

	private String stream;
	private StreamStatus status;
	@JsonInclude(Include.NON_NULL)
	private StreamSessionStatsSpec session;

	@JsonInclude(Include.NON_NULL)
	private String error;

	public StreamStatsSpec() {

	}

	public StreamStatus getStatus() {
		return status;
	}

	public void setStatus(StreamStatus status) {
		this.status = status;
	}

	public StreamSessionStatsSpec getSession() {
		return session;
	}

	public void setSession(StreamSessionStatsSpec session) {
		this.session = session;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
