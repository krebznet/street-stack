package com.dunkware.utils.tick.schema;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TickSchema {

	private String name;
	private List<TickType> types = new ArrayList<TickType>();
	
	
}
