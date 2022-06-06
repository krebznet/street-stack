package com.dunkware.net.core.face.components.nav.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NavMenuBuilder {
	
	public static NavMenuBuilder newBuilder() {
		return new NavMenuBuilder();
	}
	public List<Section> sections = new ArrayList<Section>();
	
	public NavMenuBuilder() { 
		
	}
	
	public Section section(String id, String label) { 
		Section section = new Section(id, label, this);
		sections.add(section);
		return section;
	}
	
	public NavMenu build() { 
		NavMenu menu = new NavMenu();
		for (Section section : sections) {
			NavSection navSec = new NavSection();
			navSec.setId(section.id);
			navSec.setLabel(section.label);
			for (Node node : section.nodes) {
				NavSectionNode navNode = new NavSectionNode();
				navNode.setIcon(node.icon);
				navNode.setId(node.id);
				navNode.setLabel(node.label);
				navNode.setIcon(node.icon);
				for (Link link : node.links) {
					NavSectionLink navLink = new NavSectionLink();
					navLink.setId(link.id);
					navLink.setRoutePath(link.routePath);
					navLink.setRouteData(link.params);
					navNode.getLinks().add(navLink);
				}
				navSec.getNodes().add(navNode);
			}
			menu.getSections().add(navSec);
		}
		return menu;
	}

	public class Section { 
		public String id; 
		public String label; 
		public List<Node> nodes = new ArrayList<Node>();
		public NavMenuBuilder parent;
		
		public Section(String id, String label, NavMenuBuilder parent) { 
			this.id = id;
			this.label = label;
			this.parent = parent;
			
		}
		public Node node(String id, String label, String icon) { 
			Node node = new Node(id, label, icon, this);
			nodes.add(node);
			return node;
		}
		
		public NavMenuBuilder build() { 
			return parent;
		}
		
	}
	
	public class Node { 
		
		public String id; 
		public String label; 
		public String icon; 
		public Section section;
		
		public List<Link> links = new ArrayList<Link>();
		
		public Node(String id, String label,String icon, Section section) {
			this.icon = icon;
			this.id = id;
			this.label = label;
			this.section = section;
			
		}
		
		public Link link(String id, String label,String routePath) {
			Link link = new Link(id, label, routePath, this);
			links.add(link);
			return link;
		}
		
		public Section build() { 
			return section;
		}
		
		
		
	}
	
	public class Link { 
		
		public String id; 
		public String label; 
		public String routePath; 
		public Node parent;
		
		public Map<String,Object> params = new HashMap<String,Object>();
		
		public Link(String id, String label, String routePath, Node parent) {
			this.id = id;
			this.label = label;
			this.routePath = routePath;
			this.parent = parent;
		}
		
		public Link dataParam(String name, Object value) { 
			params.put(name, value);
			return this;
		}
		
		public Link dataParams(Map<String,Object> params) { 
			this.params = params;
			return this;
		}
		
		public Node build() { 
			return parent;
		}
	}
}
