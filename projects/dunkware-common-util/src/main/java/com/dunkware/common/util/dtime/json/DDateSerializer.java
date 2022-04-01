package com.dunkware.common.util.dtime.json;

import java.io.IOException;

import com.dunkware.common.util.dtime.DDate;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DDateSerializer extends JsonSerializer<DDate> {

	@Override
	public void serialize(DDate arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException {
		arg1.writeStartObject();
		arg1.writeStringField("date",arg0.get().toString());
		arg1.writeEndObject();
	}

}
