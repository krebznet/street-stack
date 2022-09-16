package com.dunkware.common.util.grid.rules;

import java.util.ArrayList;
import java.util.List;

public class GridRowRules {
	
	private GridRowRulesType type;
	private List<GridRowRule> rules = new ArrayList<GridRowRule>();
	
	public GridRowRulesType getType() {
		return type;
	}
	public void setType(GridRowRulesType type) {
		this.type = type;
	}
	public List<GridRowRule> getRules() {
		return rules;
	}
	public void setRules(List<GridRowRule> rules) {
		this.rules = rules;
	}
	
	
	
	
	

}
