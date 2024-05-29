package com.dunkware.utils.tick.schema;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TickType {
	
	private int id; 
	private String name; 
	private List<TickTypeField> fields = new ArrayList<TickTypeField>();
	private int entityId = -1;
	private int entityIdent = -1;
	
	
	
	
}
