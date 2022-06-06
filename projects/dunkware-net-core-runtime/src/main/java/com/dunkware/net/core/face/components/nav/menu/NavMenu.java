package com.dunkware.net.core.face.components.nav.menu;

import java.util.ArrayList;
import java.util.List;

public class NavMenu {
	
	private String id; 
	
	private List<NavSection> sections = new ArrayList<NavSection>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<NavSection> getSections() {
		return sections;
	}

	public void setSections(List<NavSection> sections) {
		this.sections = sections;
	}
	
	

}
