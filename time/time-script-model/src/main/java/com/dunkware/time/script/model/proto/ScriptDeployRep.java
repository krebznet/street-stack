package com.dunkware.time.script.model.proto;

import com.dunkware.time.script.model.validate.ScriptErrors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScriptDeployRep {

	private boolean error = false; 
	private ScriptErrors errors; 
}
