package com.dunkware.trade.service.stream.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dunkware.common.util.json.DJson;

public class WebSignalQuery {

	private String stream; 
	private boolean enableDateRange; 
	private String startDateTime; 
	private String endDateTime; 
	private boolean enableEntityFilters;
	private List<Integer> entitites = new ArrayList<Integer>();
	private boolean enableSignalTypeFilters; 
	private List<Integer> signalTypeFilters = new ArrayList<Integer>();
	
	
	
	
	
	public String getStream() {
		return stream;
	}





	public void setStream(String stream) {
		this.stream = stream;
	}





	public boolean isEnableDateRange() {
		return enableDateRange;
	}





	public void setEnableDateRange(boolean enableDateRange) {
		this.enableDateRange = enableDateRange;
	}





	public String getStartDateTime() {
		return startDateTime;
	}





	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}





	public String getEndDateTime() {
		return endDateTime;
	}





	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}





	public boolean isEnableEntityFilters() {
		return enableEntityFilters;
	}





	public void setEnableEntityFilters(boolean enableEntityFilters) {
		this.enableEntityFilters = enableEntityFilters;
	}





	public List<Integer> getEntitites() {
		return entitites;
	}





	public void setEntitites(List<Integer> entitites) {
		this.entitites = entitites;
	}





	public boolean isEnableSignalTypeFilters() {
		return enableSignalTypeFilters;
	}





	public void setEnableSignalTypeFilters(boolean enableSignalTypeFilters) {
		this.enableSignalTypeFilters = enableSignalTypeFilters;
	}





	public List<Integer> getSignalTypeFilters() {
		return signalTypeFilters;
	}





	public void setSignalTypeFilters(List<Integer> signalTypeFilters) {
		this.signalTypeFilters = signalTypeFilters;
	}





	public static void main(String[] args) {
		WebSignalQuery q = new WebSignalQuery();
		q.setEnableDateRange(true);
		q.setStream("us_equity");
		q.setStartDateTime("23-09-32:9:30:00");
		q.setEndDateTime("23-09-36:9:30:00");
		q.setEnableSignalTypeFilters(true);
		q.setSignalTypeFilters(Arrays.asList(1,2,43));
		q.setEnableEntityFilters(false);;
		try {
			System.out.println(DJson.serializePretty(q));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}
	
	
}
