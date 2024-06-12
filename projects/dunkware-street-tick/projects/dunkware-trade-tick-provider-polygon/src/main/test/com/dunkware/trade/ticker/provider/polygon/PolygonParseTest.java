package com.dunkware.trade.ticker.provider.polygon;

import com.dunkware.common.util.json.DunkJson;
import com.dunkware.trade.tick.model.instrument.EquityQuote;
import com.dunkware.trade.tick.provider.polygon.core.event.PolygonAggEvent;
import com.dunkware.trade.tick.provider.polygon.core.event.PolygonQuote;

import junit.framework.TestCase;

public class PolygonParseTest extends TestCase {

	private static final String AggJson = "[{\"ev\":\"A\",\"sym\":\"AAPL\",\"v\":9145,\"av\":20360517,\"op\":154.925,\"vw\":154.0584,\"o\":154.05,\"c\":154.07,\"h\":154.0753,\"l\":154.0203,\"a\":155.1396,\"z\":147,\"s\":1652104002000,\"e\":1652104003000}]";

	private static final String QuoteJson = "{ \"ev\": \"Q\", \"sym\": \"MSFT\", \"bx\": 4, \"bp\": 114.125, \"bs\": 100, \"ax\": 7, \"ap\": 114.128, \"as\": 160, \"c\": 0, \"t\": 1536036818784, \"z\": 3 }";

	public void testAggregateParse() throws Exception {
		assertEquals(1, 1);
		try {
			PolygonAggEvent[] event = DunkJson.getObjectMapper().readValue(AggJson, PolygonAggEvent[].class);
			assertEquals("AAPL", event[0].getSymbol());
		} catch (Exception e) {
			System.err.println(e.toString());
			throw new Exception("Agg Event Parse Exception " + e.toString());
			// TODO: handle exception
		}
	}
	
	public void testQuoteParse() throws Exception { 
		try {
			System.out.println(QuoteJson);
			EquityQuote quote = DunkJson.getObjectMapper().readValue(QuoteJson,EquityQuote.class);
			assertEquals(quote.getBidPrice(), 114.125);
		} catch (Exception e) {
			throw e;
			// TODO: handle exception
		}
	}
}
