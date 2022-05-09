package com.dunkware.trade.ticker.provider.polygon;

import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.tick.provider.polygon.core.event.PolygonAggEvent;

import junit.framework.TestCase;


public class PolygonParseTest extends TestCase {

	private static final String AggJson = "[{\"ev\":\"A\",\"sym\":\"AAPL\",\"v\":9145,\"av\":20360517,\"op\":154.925,\"vw\":154.0584,\"o\":154.05,\"c\":154.07,\"h\":154.0753,\"l\":154.0203,\"a\":155.1396,\"z\":147,\"s\":1652104002000,\"e\":1652104003000}]";
	
	public void testAggregateParse() throws Exception { 
		assertEquals(1, 1);
		try {
			PolygonAggEvent[] event = DJson.getObjectMapper().readValue(AggJson, PolygonAggEvent[].class);
			assertEquals("AAPL", event[0].getSymbol());
		} catch (Exception e) {
			System.err.println(e.toString());
			throw new Exception("Agg Event Parse Exception " + e.toString());
			// TODO: handle exception
		}
	}
}
