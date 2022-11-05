package com.dunkware.trade.sdk.lib.model.bot.web.test;

import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.sdk.lib.model.bot.web.WebTradeBot;

public class WebTradeBotTest1 {
	
	private static String json = "       {\n"
			+ "  \"name\": \"Test\",\n"
			+ "  \"account\": \"Account 001\",\n"
			+ "  \"allocatedCapital\": 90000,\n"
			+ "  \"plays\": [\n"
			+ "    {\n"
			+ "      \"name\": \"Play1\",\n"
			+ "      \"tradeSide\": \"Long\",\n"
			+ "      \"capital\": 545454;\n"
			+ "      \"activeTradeLimit\": 9,\n"
			+ "      \"activeSymbolLimit\": 7,\n"
			+ "      \"symbolThrottle\": 9,\n"
			+ "      \"tradeThrottle\": 0,\n"
			+ "      \"scanner\": \"For Dad\",\n"
			+ "      \"entryType\": \"Market\",\n"
			+ "      \"entryTimeout\": 0,\n"
			+ "      \"entryLimitPercent\": null,\n"
			+ "      \"entryLimitPriceType\": null,\n"
			+ "      \"entryLimitChaseInterval\": null,\n"
			+ "      \"exitTriggers\": [\n"
			+ "        {\n"
			+ "          \"name\": \"Trigger 1\",\n"
			+ "          \"type\": \"Timer\",\n"
			+ "          \"stopPercent\": null,\n"
			+ "          \"stopPriceType\": null,\n"
			+ "          \"trailingPercent\": null,\n"
			+ "          \"trailingPriceType\": null,\n"
			+ "          \"timer\": \"30\",\n"
			+ "          \"timeTrigger\": null,\n"
			+ "          \"profitLossAmount\": null,\n"
			+ "          \"profitLossPercent\": null,\n"
			+ "          \"isComplete\": true\n"
			+ "        }\n"
			+ "      ],\n"
			+ "      \"isComplete\": true\n"
			+ "    }\n"
			+ "  ],\n"
			+ "  \"enabled\": true,\n"
			+ "  \"schedule\": {\n"
			+ "    \"days\": [\n"
			+ "      \"Monday\",\n"
			+ "      \"Tuesday\"\n"
			+ "    ],\n"
			+ "    \"startTime\": null,\n"
			+ "    \"stopTime\": null\n"
			+ "  },\n"
			+ "  \"activeTradeLimit\": 9,\n"
			+ "  \"activeSymbolLimit\": 0,\n"
			+ "  \"tradeThrottle\": 9,\n"
			+ "  \"symbolThrottle\": 0\n"
			+ "}";
	public static void main(String[] args) {
		
		try {
			WebTradeBot bot = (WebTradeBot)DJson.getObjectMapper().readValue(json, WebTradeBot.class);
			System.out.println(bot.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}
