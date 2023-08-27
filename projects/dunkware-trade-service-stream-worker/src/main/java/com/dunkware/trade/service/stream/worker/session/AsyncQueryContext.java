package com.dunkware.trade.service.stream.worker.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.xstream.api.XStream;

public class AsyncQueryContext {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("EntityQueryBuilder");

	private XStream stream;
	
	private DunkNet dunkNet;
	
	private StreamWorkerChannel channe;

	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	public XStream getStream() {
		return stream;
	}

	public void setStream(XStream stream) {
		this.stream = stream;
	}

	public DunkNet getDunkNet() {
		return dunkNet;
	}

	public void setDunkNet(DunkNet dunkNet) {
		this.dunkNet = dunkNet;
	}

	public StreamWorkerChannel getChanne() {
		return channe;
	}

	public void setChanne(StreamWorkerChannel channe) {
		this.channe = channe;
	}

	public Logger getLogger() {
		return logger;
	}
	
	
}
