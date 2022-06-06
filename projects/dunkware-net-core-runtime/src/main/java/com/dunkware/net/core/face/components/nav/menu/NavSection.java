package com.dunkware.net.core.face.components.nav.menu;

import java.util.ArrayList;
import java.util.List;

public class NavSection {
	
	private String id; 
	private String label;
	private List<NavSectionNode> nodes = new ArrayList<NavSectionNode>();
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
	public List<NavSectionNode> getNodes() {
		return nodes;
	}
	public void setNodes(List<NavSectionNode> nodes) {
		this.nodes = nodes;
	}
	
	
	

}
