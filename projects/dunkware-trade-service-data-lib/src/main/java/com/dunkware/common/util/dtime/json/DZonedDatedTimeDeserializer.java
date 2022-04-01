package com.dunkware.common.util.dtime.json;

import java.io.IOException;

import com.dunkware.common.util.dtime.DZonedDateTime;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class DZonedDatedTimeDeserializer extends JsonDeserializer<DZonedDateTime>  {

	@Override
	public DZonedDateTime deserialize(JsonParser arg0, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		 JsonNode node = arg0.readValueAsTree();
		 JsonNode hour = node.get("datetime");
		 String parsed = hour.asText();
		 return DZonedDateTime.parse(parsed);
	}

}
