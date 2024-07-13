package com.dunkware.xstream.model.script.update;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class XScriptUpdate {

	private double versioj;
	private List<XScriptChange> changes = new ArrayList<XScriptChange>();

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public class XScriptChange {

		private String elementName; 
		private String elementLabel;
		private int elementId;
		private XScriptElementType elementType;
		private XScriptChangeType changeType;
		
		
	}

	public enum XScriptChangeType {
		Update,Insert,Delete
			
		}

	public enum XScriptElementType {
		Variable,Signal,Bot
			
		}

}

