package com.dunkware.trade.service.stream.server.controller.session.worker.protocol;

public class WorkerMetricsReq {
	
	private String identifier;
	private boolean rows; 
	private boolean vars;
	private String rowIds;
	
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public boolean isRows() {
		return rows;
	}

	public void setRows(boolean rows) {
		this.rows = rows;
	}

	public boolean isVars() {
		return vars;
	}

	public void setVars(boolean vars) {
		this.vars = vars;
	}

	public String getRowIds() {
		return rowIds;
	}

	public void setRowIds(String rowIds) {
		this.rowIds = rowIds;
	}
	
	

	
	
	
	
	
	
	
	

}
