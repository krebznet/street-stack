package com.dunkware.utils.tick.reactor.blueprint;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ReactorBlueprintParser {
	
	
	public static ReactorBlueprint parse(String string) throws Exception { 
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(string, ReactorBlueprint.class);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
}
