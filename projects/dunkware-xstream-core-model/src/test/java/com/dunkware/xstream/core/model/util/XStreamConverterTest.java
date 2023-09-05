package com.dunkware.xstream.core.model.util;

import com.dunkware.common.util.json.DJson;
import com.dunkware.xstream.model.entity.query.type.StreamEntityCriteriaType;
import com.dunkware.xstream.model.entity.query.type.StreamEntityQueryType;
import com.dunkware.xstream.model.entity.query.type.XStreamEntityQueryType;
import com.dunkware.xstream.model.util.XStreamConverter;

import junit.framework.TestCase;

public class XStreamConverterTest extends TestCase {

	public static String JSON = "{\n"
			+ "    \"type\": \"Value Compare Filter\",\n"
			+ "    \"compareFunction\": \"Percent Change\",\n"
			+ "    \"condition\": \"Greater Than\",\n"
			+ "    \"conditionValue\": 2,\n"
			+ "    \"value\": [\n"
			+ "        {\n"
			+ "            \"valueType\": \"Field Current Value\",\n"
			+ "            \"identifier\": \"SmaRoc1x2min\",\n"
			+ "            \"timeRange\": \"\",\n"
			+ "            \"time\": \"\",\n"
			+ "            \"timeUnit\": \"\",\n"
			+ "            \"startTime\": \"\",\n"
			+ "            \"stopTime\": \"\",\n"
			+ "            \"sessionAggregation\": \"\"\n"
			+ "        },\n"
			+ "        {\n"
			+ "            \"valueType\": \"Field Aggregation (Historical)\",\n"
			+ "            \"identifier\": \"SmaRoc1x2min\",\n"
			+ "            \"timeRange\": \"Relative\",\n"
			+ "            \"time\": 30,\n"
			+ "            \"timeUnit\": \"Days\",\n"
			+ "            \"startTime\": \"\",\n"
			+ "            \"stopTime\": \"\",\n"
			+ "            \"sessionAggregation\": \"High\"\n"
			+ "        }\n"
			+ "    ]\n"
			+ "}";
	
	
	public void testMe()  throws Exception {
		StreamEntityCriteriaType type = DJson.getObjectMapper().readValue(JSON, StreamEntityCriteriaType.class);
		StreamEntityQueryType queryType = new StreamEntityQueryType();
		queryType.getCriterias().add(type);
		XStreamEntityQueryType xQueryType = XStreamConverter.toXStreamEntityQueryType(queryType);
	
		System.out.println(type.getType());
		
		
		assertEquals(false, false);
	}
}
