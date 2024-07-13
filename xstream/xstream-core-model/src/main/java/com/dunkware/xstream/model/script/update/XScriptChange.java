package com.dunkware.xstream.model.script.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class XScriptChange {

	private String element;
	private XScriptChangeType type;
	private String label;
	private String name; 
	
	
	
}
