package com.dunkware.common.util.json.bytes;

import java.io.IOException;
import java.util.Base64;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class DBytesDeserializer extends StdDeserializer<DBytes> {

    private static final long serialVersionUID = 1514703510863497028L;

    public DBytesDeserializer() {
        super(DBytes.class);
    }

    @Override
    public DBytes deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        String base64 = node.asText();
        return new DBytes(Base64.getDecoder().decode(base64));
    }
}
