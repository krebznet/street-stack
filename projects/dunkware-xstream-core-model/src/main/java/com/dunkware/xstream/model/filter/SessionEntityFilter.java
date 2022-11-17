package com.dunkware.xstream.model.filter;

public class SessionEntityFilter {

	private SessionEntityFilterType type; 
	private SessionEntityFilterValue value; 
	private SessionEntityFilterValueCompare valueCompare; 
	private SessionEntityFilterSignalCountSession signalCountSession;
	private SessionEntityFilterSignalCountHistorical signalCountHistorical;
	private String label; 
	private boolean enabled = true;
	private String name; 
	
	
	public SessionEntityFilterType getType() {
		return type;
	}
	public void setType(SessionEntityFilterType type) {
		this.type = type;
	}
	public SessionEntityFilterValue getValue() {
		return value;
	}
	public void setValue(SessionEntityFilterValue value) {
		this.value = value;
	}
	public SessionEntityFilterValueCompare getValueCompare() {
		return valueCompare;
	}
	public void setValueCompare(SessionEntityFilterValueCompare valueCompare) {
		this.valueCompare = valueCompare;
	}
	public SessionEntityFilterSignalCountSession getSignalCountSession() {
		return signalCountSession;
	}
	public void setSignalCountSession(SessionEntityFilterSignalCountSession signalCountSession) {
		this.signalCountSession = signalCountSession;
	}
	public SessionEntityFilterSignalCountHistorical getSignalCountHistorical() {
		return signalCountHistorical;
	}
	public void setSignalCountHistorical(SessionEntityFilterSignalCountHistorical signalCountHistorical) {
		this.signalCountHistorical = signalCountHistorical;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
	
}
