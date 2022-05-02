package com.dunkware.common.util.json.bytes;

import java.io.IOException;
import java.util.Base64;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class DBytesSerializer extends StdSerializer<DBytes> {

    private static final long serialVersionUID = -5510353102817291511L;

    public DBytesSerializer() {
      super(DBytes.class);
    }

    @Override
    public void serialize(DBytes value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(Base64.getEncoder().encodeToString(value.getBytes()));
    }
}
