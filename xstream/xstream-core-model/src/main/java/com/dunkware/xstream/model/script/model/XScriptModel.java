package com.dunkware.xstream.model.script.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class XScriptModel {
	
	private String name;
	private String version;
	private List<XScriptModelVariable> variables = new ArrayList<XScriptModelVariable>();
	private List<XScriptModelSignal> signals = new ArrayList<XScriptModelSignal>();
	private List<XScriptModelBot> bots = new ArrayList<XScriptModelBot>();

}
