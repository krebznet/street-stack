package com.dunkware.common.util.dtime.json;

import java.io.IOException;

import com.dunkware.common.util.dtime.DDateTime;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DDateTimeSerializer extends JsonSerializer<DDateTime> {

	@Override
	public void serialize(DDateTime arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException {
		// TODO Auto-generated method stub
		
		arg1.writeStartObject();
		arg1.writeStringField("datetime",arg0.get().toString());
		arg1.writeEndObject();
	}

}
