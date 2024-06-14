package com.dunkware.utils.core.json.bytes;

import java.io.IOException;
import java.util.Base64;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;


@JsonDeserialize
public class JsonBytesDeserializer  extends StdDeserializer<JsonBytes> {

    private static final long serialVersionUID = 1514703510863497028L;

    public JsonBytesDeserializer() {
        super(JsonBytes.class);
    }

    @Override
    public JsonBytes deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        String base64 = node.asText();
        return new JsonBytes(Base64.getDecoder().decode(base64));
    }
}
