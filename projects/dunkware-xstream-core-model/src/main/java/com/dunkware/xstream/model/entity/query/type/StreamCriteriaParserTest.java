package com.dunkware.xstream.model.entity.query.type;

import com.dunkware.common.util.json.DJson;

public class StreamCriteriaParserTest {
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
	public static void main(String[] args) {
		try {
			StreamEntityCriteriaType criteria = null;
			try {
				criteria = DJson.getObjectMapper().readValue(JSON, StreamEntityCriteriaType.class);
				System.out.println(criteria.getType());
				System.out.println(criteria.getValue().get(0).getValueType());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
