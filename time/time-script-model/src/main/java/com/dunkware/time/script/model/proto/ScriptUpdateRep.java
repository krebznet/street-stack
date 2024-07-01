package com.dunkware.time.script.model.proto;

import com.dunkware.time.script.model.validate.ScriptErrors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScriptUpdateRep {
	
	private boolean error = false; 
	private double version;
	private ScriptErrors errors;
	

	
	
	
	

}
