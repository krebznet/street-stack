package com.dunkware.xstream.model.script.descriptor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public class XScriptVariableDescriptor {
		

		private String version; 
		private int id; 
		private String name; 
		private String label; 
		private String group;
		private String format; 
		
	
	}
