package com.dunkware.utils.core.json.bytes;

import java.io.IOException;
import java.util.Base64;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

@JsonSerialize
public class JsonBytesSerializer extends StdSerializer<JsonBytes> {

    private static final long serialVersionUID = -5510353102817291511L;

    public JsonBytesSerializer() {
      super(JsonBytes.class);
    }

    @Override
    public void serialize(JsonBytes value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(Base64.getEncoder().encodeToString(value.getBytes()));
    }
}