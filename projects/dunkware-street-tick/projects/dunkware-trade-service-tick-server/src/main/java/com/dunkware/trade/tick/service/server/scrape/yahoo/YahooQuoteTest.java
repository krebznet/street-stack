package com.dunkware.trade.tick.service.server.scrape.yahoo;

public class YahooQuoteTest {
	
	
	public static void main(String[] args) {
		try {
			String tickers = "MSFT";
			String[] tickerList = tickers.split(",");
			for (String ticker : tickerList) {
				YahooQuote q = YahooQuoteScraper.newInstance().scrapeQuote(ticker);
				System.out.println("Data For " + ticker);
				System.out.println(q.getAverageVolume());
				System.out.println(q.getMarketCap());
				System.out.println(q.getEps());
				System.out.println(q.getMarketOpen());	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
