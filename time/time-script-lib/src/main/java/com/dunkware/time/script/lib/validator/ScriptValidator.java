package com.dunkware.time.script.lib.validator;

import com.dunkware.time.script.model.validate.ScriptErrors;
import com.dunkware.xstream.xproject.XScriptProject;

public interface ScriptValidator {

	public void validate(ScriptErrors errors, XScriptProject project); 
}
