package com.dunkware.common.util.dtime.json;

import java.io.IOException;
import java.time.LocalTime;

import com.dunkware.common.util.dtime.DTime;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class DTimeDeserializer  extends JsonDeserializer<DTime> {

	public DTimeDeserializer() { 
		
	}
	
	@Override
	public DTime deserialize(JsonParser arg0, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		 JsonNode node = arg0.readValueAsTree();
		 JsonNode hour = node.get("time");
		 String text = hour.asText();
		 LocalTime time = LocalTime.parse(text);
		
		 return DTime.from(time);
		 
		 
		
	} 
	
}


