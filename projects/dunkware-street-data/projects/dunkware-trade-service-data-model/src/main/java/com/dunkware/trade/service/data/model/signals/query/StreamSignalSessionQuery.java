package com.dunkware.trade.service.data.model.signals.query;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionNodeBean;

/**
 * Okay so this will be used as a query from UI or wherever to get a streaming data grid of session signals it can be in the following form
 * 1. all signals of type a...z for entity type a...z 
 * 2. All signals of type a 
 * 3. all signals of type a for entity a 
 * @author duncankrebs
 *
 */
public class StreamSignalSessionQuery {

	private List<Integer> entities = new ArrayList<Integer>();
	private List<Integer> signalTypes = new ArrayList<Integer>();
	private LocalTime startTime = null;
	private LocalTime stopTime = null;
	
	public static void main(String[] args) {
		try {
			StreamSignalSessionQuery q = DJson.getObjectMapper().readValue("{}",StreamSignalSessionQuery.class);
			System.out.println(q.toString());
			System.out.println(DJson.serialize(new StreamSignalSessionQuery()));	
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}
	
	private String stream;
	public List<Integer> getEntities() {
		return entities;
	}
	public void setEntities(List<Integer> entities) {
		this.entities = entities;
	}
	public List<Integer> getSignalTypes() {
		return signalTypes;
	}
	public void setSignalTypes(List<Integer> signalTypes) {
		this.signalTypes = signalTypes;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getStopTime() {
		return stopTime;
	}
	public void setStopTime(LocalTime stopTime) {
		this.stopTime = stopTime;
	} 
	
	
	
	
}
