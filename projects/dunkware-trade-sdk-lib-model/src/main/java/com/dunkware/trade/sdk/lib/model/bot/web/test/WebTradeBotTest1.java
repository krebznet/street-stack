package com.dunkware.trade.sdk.lib.model.bot.web.test;

import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.sdk.lib.model.bot.web.WebTradeBot;

public class WebTradeBotTest1 {
	
	private static String json ="{\n"
			+ "  \"name\": \"Test\",\n"
			+ "  \"account\": \"Account 001\",\n"
			+ "  \"allocatedCapital\": 424,\n"
			+ "  \"plays\": [\n"
			+ "    {\n"
			+ "      \"name\": \"tET\",\n"
			+ "      \"tradeSide\": \"Short\",\n"
			+ "      \"capital\": 24,\n"
			+ "      \"activeTradeLimit\": 0,\n"
			+ "      \"activeSymbolLimit\": 0,\n"
			+ "      \"symbolThrottle\": 0,\n"
			+ "      \"tradeThrottle\": 0,\n"
			+ "      \"scanner\": null,\n"
			+ "      \"entryType\": \"Market\",\n"
			+ "      \"entryTimeout\": 2,\n"
			+ "      \"entryLimitPercent\": null,\n"
			+ "      \"entryLimitPriceType\": null,\n"
			+ "      \"entryLimitChaseInterval\": null,\n"
			+ "      \"exitTriggers\": null,\n"
			+ "      \"isComplete\": true\n"
			+ "    }\n"
			+ "  ],\n"
			+ "  \"enabled\": null,\n"
			+ "  \"schedule\": {\n"
			+ "    \"days\": null,\n"
			+ "    \"startTime\": null,\n"
			+ "    \"stopTime\": null\n"
			+ "  },\n"
			+ "  \"activeTradeLimit\": 0,\n"
			+ "  \"activeSymbolLimit\": 0,\n"
			+ "  \"tradeThrottle\": 0,\n"
			+ "  \"symbolThrottle\": 0\n"
			+ "}\n"
			+ "      ";
	public static void main(String[] args) {
		
		try {
			WebTradeBot bot = (WebTradeBot)DJson.getObjectMapper().readValue(json, WebTradeBot.class);
			System.out.println(bot.getName());
			System.out.println("stop[ here");
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}
