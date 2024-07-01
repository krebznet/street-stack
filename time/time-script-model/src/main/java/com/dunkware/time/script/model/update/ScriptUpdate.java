package com.dunkware.time.script.model.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScriptUpdate {

	private String target;
	private String type;
	private String element; 
	
	
	// Delta  |  Type  		Element
	 
	// Insert    Variable     Mtc30sec	 
	// Delete	 Signal 	  Mtco30sec
	// Modify  	 TradeBook
}
