package com.dunkware.xstream.model.script.descriptor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class XScriptBotDescriptor {

	private int id;
	private String version;
	private String name; 
	private String label;
	private String group;
	private String description;

	 
}
