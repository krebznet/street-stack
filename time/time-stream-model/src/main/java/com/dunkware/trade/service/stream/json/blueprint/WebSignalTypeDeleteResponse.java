package com.dunkware.trade.service.stream.json.blueprint;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.utils.core.json.DunkJson;

public class WebSignalTypeDeleteResponse {
	
	private boolean hasConflicts = false;
	private List<String> conflicts = new ArrayList<String>();
	
	public static void main(String[] args) {
		WebSignalTypeDeleteResponse resp = new WebSignalTypeDeleteResponse();
		resp.setHasConflicts(true);;
		resp.getConflicts().add("Signal Type is being used by Trading System Breakout1");
		resp.getConflicts().add("Signal type is refernced by Singnal 3");
		try {
			System.out.println(DunkJson.serializePretty(resp));
		} catch (Exception e) {
			e.getLocalizedMessage();
			
		}
	}
	
	

	public boolean isHasConflicts() {
		return hasConflicts;
	}



	public void setHasConflicts(boolean hasConflicts) {
		this.hasConflicts = hasConflicts;
	}



	public List<String> getConflicts() {
		return conflicts;
	}
	public void setConflicts(List<String> conflicts) {
		this.conflicts = conflicts;
	}
	
	

}
