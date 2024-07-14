package com.dunkware.xstream.model.script.model;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class XScriptProblems {
	
	public static XScriptProblems instance() { 
		return new XScriptProblems();
	}
	
	private List<XScriptProblem> problems = new ArrayList<XScriptProblem>();

	@Transient
	public void addProblem(String problem) { 
		XScriptProblem prop = new XScriptProblem(problem);
		problems.add(prop);
	}
}
