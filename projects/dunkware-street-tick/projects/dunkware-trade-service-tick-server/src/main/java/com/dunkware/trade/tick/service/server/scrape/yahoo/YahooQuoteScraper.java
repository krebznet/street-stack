package com.dunkware.trade.tick.service.server.scrape.yahoo;

import java.util.Map;

import com.dunkware.common.util.helpers.DHttpHelper;
import com.dunkware.common.util.helpers.DJsonHelper;

public class YahooQuoteScraper {
	
	public static final String MARKER_AVGVOL = "\"averageVolume\":";
	public static final String MARKER_MARKET_CAP = "\"marketCap\":";
	public static final String MARKER_OPEN_PRICE = "\"regularMarketOpen\":";
	
	public static final String MARKER_TRAILING_EPS = "\"trailingEps\":";
	
	public static YahooQuoteScraper newInstance() { 
		return new YahooQuoteScraper();
	}
	
	
	public YahooQuote scrapeQuote(String ticker) throws Exception { 
		String endpoint = "https://finance.yahoo.com/quote/" + ticker + "?p=" + ticker + "&.tsrc=fin-srch";
		String content = null;
		try {
			content = DHttpHelper.getURLContent(endpoint);
		} catch (Exception e) {
			throw e;
		}
	     
	     Object avgVol = getElement(MARKER_AVGVOL, content);
	     Object marketCap = getElement(MARKER_MARKET_CAP, content);
	     Object trailingEps = getElement(MARKER_TRAILING_EPS, content);
	     Object marketOpen = getElement(MARKER_OPEN_PRICE, content);
	     YahooQuote quote = new YahooQuote();
	     if (trailingEps instanceof Double) {
			Double trailingDouble = (Double) trailingEps;
			quote.setEps(trailingDouble);
		}
	     if (marketOpen instanceof Double) {
			Double openDub = (Double) marketOpen;
			quote.setMarketOpen(openDub);
		}
	     quote.setAverageVolume((Integer)avgVol);
	     if (marketCap instanceof Integer) {
			quote.setMarketCap((Integer)marketCap);
		}
	     if (marketCap instanceof Long) {
			Long longValue = (Long) marketCap;
			quote.setMarketCap(longValue);
		}
	    
		return quote;
	}

	
	public Object getElement(String marker, String content) throws Exception { 
		  
		String element = content.substring(content.indexOf(marker),content.length());
	     element = element.substring(marker.length(),element.length());
	     element = element.substring(0,element.indexOf("}") + 1);		
	     Map<String,Object> json = DJsonHelper.parseJsonToMap(element);
	     return json.get("raw");
	     
	}
}