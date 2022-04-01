package com.dunkware.trade.service.stream.protocol.test;

import java.io.File;

import com.dunkware.common.util.helpers.DFileHelper;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.stream.protocol.controller.StreamStatsResp;

public class TestParse {

	public static void main(String[] args) {
		String json = null;
		try {
			File file = new File("/Users/dkrebs/dunkdev/release-major/artifacts/dunkware-trade-service-stream-protocol/src/main/java/com/dunkware/trade/service/stream/protocol/test/Test.json");
			json = DFileHelper.readFileContents(file);
			
			StreamStatsResp resp = DJson.getObjectMapper().readValue(json, StreamStatsResp.class);
			System.out.println(resp.getStats().toString());
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
