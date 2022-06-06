package com.dunkware.net.core.face.components.nav.menu;

import java.util.Map;

public class NavSectionLink {
	
	private String id; 
	private String label; 
	private String routePath; 
	private Map<String,Object> routeData;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getRoutePath() {
		return routePath;
	}
	public void setRoutePath(String routePath) {
		this.routePath = routePath;
	}
	public Map<String, Object> getRouteData() {
		return routeData;
	}
	public void setRouteData(Map<String, Object> routeData) {
		this.routeData = routeData;
	}
	
	

}
