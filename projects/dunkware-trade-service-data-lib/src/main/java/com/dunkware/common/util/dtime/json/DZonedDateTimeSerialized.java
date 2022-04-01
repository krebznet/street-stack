package com.dunkware.common.util.dtime.json;

import java.io.IOException;

import com.dunkware.common.util.dtime.DZonedDateTime;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DZonedDateTimeSerialized  extends JsonSerializer<DZonedDateTime> {

	@Override
	public void serialize(DZonedDateTime arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException {
		arg1.writeString(arg0.dateTime().toString());
		arg1.writeStartObject();
		arg1.writeStringField("datetime", arg0.dateTime().toString());
	}

	
}
