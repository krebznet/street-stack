package com.dunkware.common.util.dtime.json;

import java.io.IOException;

import com.dunkware.common.util.dtime.DTime;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DTimeSerializer  extends JsonSerializer<DTime> {

	public DTimeSerializer() {
		
	}
	
	@Override
	public void serialize(DTime arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException {
		// TODO Auto-generated method stub
		arg1.writeStartObject();
		arg1.writeStringField("time",arg0.get().toString());
		arg1.writeEndObject();
	} 
	
	
}