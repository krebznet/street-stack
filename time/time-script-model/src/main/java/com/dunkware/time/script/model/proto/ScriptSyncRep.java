package com.dunkware.time.script.model.proto;

import java.util.List;

import com.dunkware.time.script.model.update.ScriptUpdate;
import com.dunkware.time.script.model.update.ScriptUpdates;
import com.dunkware.time.script.model.validate.ScriptErrors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScriptSyncRep {

	private boolean error = false; 
	private ScriptUpdates update;
	private ScriptErrors errors;
	
		
	
}
