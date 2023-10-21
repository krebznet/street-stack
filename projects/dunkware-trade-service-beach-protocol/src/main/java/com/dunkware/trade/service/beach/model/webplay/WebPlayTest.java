package com.dunkware.trade.service.beach.model.webplay;

import com.dunkware.common.util.json.DJson;

public class WebPlayTest {
	
	 public static String JSON = "{\n"
			+ "    \"summaryData\": {\n"
			+ "        \"name\": \"Test\",\n"
			+ "        \"signal\": \"US Equity\",\n"
			+ "        \"stream\": \"US Equity\",\n"
			+ "        \"allocatedCapital\": 4,\n"
			+ "        \"tradeCapital\": 445,\n"
			+ "        \"side\": \"Long\"\n"
			+ "    },\n"
			+ "    \"limitsData\": {\n"
			+ "        \"enableActiveTradeLimit\": false,\n"
			+ "        \"enableActiveSymbolLimit\": false,\n"
			+ "        \"enableStopLoss\": true,\n"
			+ "        \"activeTradeLimit\": 0,\n"
			+ "        \"activeSymbolLimit\": 0,\n"
			+ "        \"stopLoss\": 4\n"
			+ "    },\n"
			+ "    \"entryOrderData\": {\n"
			+ "        \"entryType\": \"Market\",\n"
			+ "        \"enableEntryTimeout\": true,\n"
			+ "        \"entryTimeout\": 30,\n"
			+ "        \"entryTargetPrice\": \"\",\n"
			+ "        \"entryChaseInterval\": 0,\n"
			+ "        \"entryChaseOffSet\": 0\n"
			+ "    },\n"
			+ "    \"exitOrderData\": {\n"
			+ "        \"exitType\": \"Market\",\n"
			+ "        \"exitTargetPrice\": \"\",\n"
			+ "        \"exitChaseInterval\": 0,\n"
			+ "        \"exitChaseOffSet\": 0\n"
			+ "    },\n"
			+ "    \"exitTriggerData\": [\n"
			+ "        {\n"
			+ "            \"name\": \"Test\",\n"
			+ "            \"type\": \"Timer\",\n"
			+ "            \"timer\": \"30\",\n"
			+ "            \"unrealizedPL\": 0,\n"
			+ "            \"isNew\": true\n"
			+ "        }\n"
			+ "    ]\n"
			+ "}";
	public static void main(String[] args) {
		
		try {
			WebPlay play = DJson.getObjectMapper().readValue(JSON, WebPlay.class);
			System.out.println(play.getSummaryData().getName());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}

}
