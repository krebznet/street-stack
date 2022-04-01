package com.dunkware.common.util.dtime.json;

import java.io.IOException;

import com.dunkware.common.util.dtime.DDateTime;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class DDateTimeDeserializer extends JsonDeserializer<DDateTime> {

	@Override
	public DDateTime deserialize(JsonParser arg0, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
