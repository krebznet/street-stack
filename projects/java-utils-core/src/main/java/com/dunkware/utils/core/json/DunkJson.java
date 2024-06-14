package com.dunkware.utils.core.json;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.dunkware.utils.core.json.bytes.JsonBytesSerializer;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class DunkJson {
	
private static ObjectMapper objectMapper = null;
	
	private static boolean initialized = false; 
	
	private static void init() { 
		if(!initialized) { 
			objectMapper = new ObjectMapper();
			objectMapper.setSerializationInclusion(Include.NON_NULL);
		
			objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			objectMapper.registerModule(new JavaTimeModule());
			initialized = true;
		}
	}
	public static String serialize(Object object) throws JsonMappingException,JsonGenerationException,IOException { 
		init();
		StringWriter writer = new StringWriter();
		objectMapper.writeValue(writer, object);
		

		return writer.toString();
	}

	public static String serializePretty(Object object) throws JsonMappingException,JsonGenerationException,IOException { 
		init();
		String output = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		return output;
	}

	
	public static ObjectMapper getObjectMapper() { 
		init();
		return objectMapper;
	}
	
	
	public static Map<String,Object> parseMap(String element) throws Exception  { 
		TypeReference<HashMap<String,Object>> typeRef = new   TypeReference<HashMap<String,Object>>() {
		};
		Map<String,Object> map = objectMapper.readValue(element,typeRef);
		return map;
	}
	


}
