package com.dunkware.trade.service.data.model.search;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dunkware.common.util.json.DJson;

//SD21-GIFT-02 We added a REST API where we expect the HTTP request body to deserialize to this, its like what we would in GraphQL except shema's and all that shit is ovekill
public class SignalSearchRequest {
	//SD21-GIFT-03  i we need a stream identifier or stream so we will use it a bit later
	private String stream; 
	
	//SD21-GIFT-04 You will understand the other fields as we go through the next gifts.  
	
	public static void main(String[] args) {
		SignalSearchRequest req = new SignalSearchRequest();
		List<Integer> signalTypes = new ArrayList<Integer>();
		signalTypes.add(9);
		req.setSignalTypes(signalTypes);
		req.setEntities(Arrays.asList(2,3,4));
		req.setStream("us_equity");
		req.setSearchRangeStart(LocalDateTime.now());
		req.setSearchRangeStop(LocalDateTime.now().plusHours(3).truncatedTo(ChronoUnit.SECONDS));
		
		
	
		try {
			System.out.println(DJson.serializePretty(req));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	/**
	 * If not null we restrict or add filter on mongo query where the signal types are wrapped
	 * in a or with the ids in array. 
	 */
	private List<Integer> signalTypes = new ArrayList<Integer>();
	/**
	 * Same as above if nut null we filter so the entity id must be = 
	 * to one of the values in the list below;
	 */
	private List<Integer> entities  = new ArrayList<Integer>();
	private LocalDateTime searchRangeStart = null;
	private LocalDateTime searchRangeStop = null;
	private Integer limit = 10000;
	
	public List<Integer> getSignalTypes() {
		return signalTypes;
	}
	public void setSignalTypes(List<Integer> signalTypes) {
		this.signalTypes = signalTypes;
	}
	public List<Integer> getEntities() {
		return entities;
	}
	public void setEntities(List<Integer> entities) {
		this.entities = entities;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}

	
	
	public LocalDateTime getSearchRangeStart() {
		return searchRangeStart;
	}
	public void setSearchRangeStart(LocalDateTime searchRangeStart) {
		this.searchRangeStart = searchRangeStart;
	}
	public LocalDateTime getSearchRangeStop() {
		return searchRangeStop;
	}
	public void setSearchRangeStop(LocalDateTime searchRangeStop) {
		this.searchRangeStop = searchRangeStop;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	

}
