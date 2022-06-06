package com.dunkware.net.core.face.components.nav.menu;

import java.util.ArrayList;
import java.util.List;

public class NavSectionNode {

	private String id; 
	private String label; 
	private String icon; 
	
	private List<NavSectionLink> links = new ArrayList<NavSectionLink>();

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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<NavSectionLink> getLinks() {
		return links;
	}

	public void setLinks(List<NavSectionLink> links) {
		this.links = links;
	}
	
	
}
