package com.dunkware.trade.broker.tws.connector;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.ib.client.Bar;
import com.ib.client.CommissionReport;
import com.ib.client.Contract;
import com.ib.client.ContractDescription;
import com.ib.client.ContractDetails;
import com.ib.client.Decimal;
import com.ib.client.DeltaNeutralContract;
import com.ib.client.DepthMktDataDescription;
import com.ib.client.EWrapper;
import com.ib.client.Execution;
import com.ib.client.FamilyCode;
import com.ib.client.HistogramEntry;
import com.ib.client.HistoricalSession;
import com.ib.client.HistoricalTick;
import com.ib.client.HistoricalTickBidAsk;
import com.ib.client.HistoricalTickLast;
import com.ib.client.NewsProvider;
import com.ib.client.PriceIncrement;
import com.ib.client.SoftDollarTier;
import com.ib.client.TickAttrib;
import com.ib.client.TickAttribBidAsk;
import com.ib.client.TickAttribLast;
import com.ib.client.TwsOrder;
import com.ib.client.TwsOrderState;

public interface TwsSocketReader extends EWrapper  {

	@Override
	public default void error(Exception e) { }

	@Override
	public default void error(String str) { }


	@Override
	public default void connectionClosed() {}


	@Override
	public default void tickGeneric(int tickerId, int tickType, double value) {	 }

	@Override
	public default void tickString(int tickerId, int tickType, String value) {}

	@Override
	public default void tickEFP(int tickerId, int tickType, double basisPoints,
			String formattedBasisPoints, double impliedFuture, int holdDays,
			String futureExpiry, double dividendImpact, double dividendsToExpiry) { }

	

	@Override
	public default void openOrder(int orderId, Contract contract, TwsOrder order,
			TwsOrderState orderState) {
		
		
	}

	@Override
	public default void openOrderEnd() {
		
		
	}

	@Override
	public default void updateAccountValue(String key, String value, String currency,
			String accountName) {
		
		
	}

	

	@Override
	public default void updateAccountTime(String timeStamp) {
		
		
	}

	@Override
	public default void accountDownloadEnd(String accountName) {
		
		
	}

	@Override
	public default void nextValidId(int orderId) {
		
		
	}

	@Override
	public default void contractDetails(int reqId, ContractDetails contractDetails) {
		
		
	}

	@Override
	public default void bondContractDetails(int reqId, ContractDetails contractDetails) {
		
		
	}

	@Override
	public default void contractDetailsEnd(int reqId) {
		
		
	}

	@Override
	public default void execDetails(int reqId, Contract contract, Execution execution) {
		
		
	}

	@Override
	public default void execDetailsEnd(int reqId) {
		
		
	}

	


	@Override
	public default void updateNewsBulletin(int msgId, int msgType, String message,
			String origExchange) {
		
		
	}

	@Override
	public default void managedAccounts(String accountsList) {
		
		
	}

	@Override
	public default void receiveFA(int faDataType, String xml) {
		
		
	}

	
	@Override
	public default void scannerParameters(String xml) {
		
		
	}

	@Override
	public default void scannerData(int reqId, int rank,
			ContractDetails contractDetails, String distance, String benchmark,
			String projection, String legsStr) {
		
		
	}

	@Override
	public default void scannerDataEnd(int reqId) {
		
		
	}


	@Override
	public default void currentTime(long time) {
		
		
	}

	@Override
	public default void fundamentalData(int reqId, String data) {
		
		
	}

	

	@Override
	default void tickPrice(int tickerId, int field, double price, TickAttrib attrib) {
		
		
	}

	@Override
	default void tickSize(int tickerId, int field, Decimal size) {
		
		
	}

	@Override
	default void tickOptionComputation(int tickerId, int field, int tickAttrib, double impliedVol, double delta,
			double optPrice, double pvDividend, double gamma, double vega, double theta, double undPrice) {
		
		
	}

	@Override
	default void orderStatus(int orderId, String status, Decimal filled, Decimal remaining, double avgFillPrice,
			int permId, int parentId, double lastFillPrice, int clientId, String whyHeld, double mktCapPrice) {
		
		
	}

	@Override
	default void updatePortfolio(Contract contract, Decimal position, double marketPrice, double marketValue,
			double averageCost, double unrealizedPNL, double realizedPNL, String accountName) {
		
		
	}

	@Override
	default void updateMktDepth(int tickerId, int position, int operation, int side, double price, Decimal size) {
		
		
	}

	@Override
	default void updateMktDepthL2(int tickerId, int position, String marketMaker, int operation, int side, double price,
			Decimal size, boolean isSmartDepth) {
		
		
	}

	@Override
	default void historicalData(int reqId, Bar bar) {
		
		
	}

	@Override
	default void realtimeBar(int reqId, long time, double open, double high, double low, double close, Decimal volume,
			Decimal wap, int count) {
		
		
	}

	@Override
	default void deltaNeutralValidation(int reqId, DeltaNeutralContract deltaNeutralContract) {
		
		
	}

	@Override
	default void position(String account, Contract contract, Decimal pos, double avgCost) {
		
		
	}

	@Override
	default void verifyMessageAPI(String apiData) {
		
		
	}

	@Override
	default void verifyCompleted(boolean isSuccessful, String errorText) {
		
		
	}

	@Override
	default void verifyAndAuthMessageAPI(String apiData, String xyzChallenge) {
		
		
	}

	@Override
	default void verifyAndAuthCompleted(boolean isSuccessful, String errorText) {
		
		
	}

	@Override
	default void displayGroupList(int reqId, String groups) {
		
		
	}

	@Override
	default void displayGroupUpdated(int reqId, String contractInfo) {
		
		
	}

	@Override
	default void error(int id, int errorCode, String errorMsg, String advancedOrderRejectJson) {
		
		
	}

	@Override
	default void connectAck() {
		
		
	}

	@Override
	default void positionMulti(int reqId, String account, String modelCode, Contract contract, Decimal pos,
			double avgCost) {
		
		
	}

	@Override
	default void positionMultiEnd(int reqId) {
		
		
	}

	@Override
	default void accountUpdateMulti(int reqId, String account, String modelCode, String key, String value,
			String currency) {
		
		
	}

	@Override
	default void accountUpdateMultiEnd(int reqId) {
		
		
	}

	@Override
	default void securityDefinitionOptionalParameter(int reqId, String exchange, int underlyingConId,
			String tradingClass, String multiplier, Set<String> expirations, Set<Double> strikes) {
		
		
	}

	@Override
	default void securityDefinitionOptionalParameterEnd(int reqId) {
		
		
	}

	@Override
	default void softDollarTiers(int reqId, SoftDollarTier[] tiers) {
		
		
	}

	@Override
	default void familyCodes(FamilyCode[] familyCodes) {
		
		
	}

	@Override
	default void symbolSamples(int reqId, ContractDescription[] contractDescriptions) {
		
		
	}

	@Override
	default void historicalDataEnd(int reqId, String startDateStr, String endDateStr) {
		
		
	}

	@Override
	default void mktDepthExchanges(DepthMktDataDescription[] depthMktDataDescriptions) {
		
		
	}

	@Override
	default void tickNews(int tickerId, long timeStamp, String providerCode, String articleId, String headline,
			String extraData) {
		
		
	}

	@Override
	default void smartComponents(int reqId, Map<Integer, Entry<String, Character>> theMap) {
		
		
	}

	@Override
	default void tickReqParams(int tickerId, double minTick, String bboExchange, int snapshotPermissions) {
		
		
	}

	@Override
	default void newsProviders(NewsProvider[] newsProviders) {
		
		
	}

	@Override
	default void newsArticle(int requestId, int articleType, String articleText) {
		
		
	}

	@Override
	default void historicalNews(int requestId, String time, String providerCode, String articleId, String headline) {
		
		
	}

	@Override
	default void historicalNewsEnd(int requestId, boolean hasMore) {
		
		
	}

	@Override
	default void headTimestamp(int reqId, String headTimestamp) {
		
		
	}

	@Override
	default void histogramData(int reqId, List<HistogramEntry> items) {
		
		
	}

	@Override
	default void historicalDataUpdate(int reqId, Bar bar) {
		
		
	}

	@Override
	default void rerouteMktDataReq(int reqId, int conId, String exchange) {
		
		
	}

	@Override
	default void rerouteMktDepthReq(int reqId, int conId, String exchange) {
		
		
	}

	@Override
	default void marketRule(int marketRuleId, PriceIncrement[] priceIncrements) {
		
		
	}

	@Override
	default void pnl(int reqId, double dailyPnL, double unrealizedPnL, double realizedPnL) {
		
		
	}

	@Override
	default void pnlSingle(int reqId, Decimal pos, double dailyPnL, double unrealizedPnL, double realizedPnL,
			double value) {
		
		
	}

	@Override
	default void historicalTicks(int reqId, List<HistoricalTick> ticks, boolean done) {
		
		
	}

	@Override
	default void historicalTicksBidAsk(int reqId, List<HistoricalTickBidAsk> ticks, boolean done) {
		
		
	}

	@Override
	default void historicalTicksLast(int reqId, List<HistoricalTickLast> ticks, boolean done) {
		
		
	}

	@Override
	default void tickByTickAllLast(int reqId, int tickType, long time, double price, Decimal size,
			TickAttribLast tickAttribLast, String exchange, String specialConditions) {
		
		
	}

	@Override
	default void tickByTickBidAsk(int reqId, long time, double bidPrice, double askPrice, Decimal bidSize,
			Decimal askSize, TickAttribBidAsk tickAttribBidAsk) {
		
		
	}

	@Override
	default void tickByTickMidPoint(int reqId, long time, double midPoint) {
		
		
	}

	@Override
	default void orderBound(long orderId, int apiClientId, int apiOrderId) {
		
		
	}

	@Override
	default void completedOrder(Contract contract, TwsOrder order, TwsOrderState orderState) {
		
		
	}

	@Override
	default void completedOrdersEnd() {
		
		
	}

	@Override
	default void replaceFAEnd(int reqId, String text) {
		
		
	}

	@Override
	default void wshMetaData(int reqId, String dataJson) {
		
		
	}

	@Override
	default void wshEventData(int reqId, String dataJson) {
		
		
	}

	@Override
	default void historicalSchedule(int reqId, String startDateTime, String endDateTime, String timeZone,
			List<HistoricalSession> sessions) {
		
		
	}

	@Override
	default void userInfo(int reqId, String whiteBrandingId) {
		
		
	}

	@Override
	public default void tickSnapshotEnd(int reqId) {
		
		
	}

	@Override
	public default void marketDataType(int reqId, int marketDataType) {
		
		
	}

	@Override
	public default void commissionReport(CommissionReport commissionReport) {
		
	}

	

	@Override
	public  default void positionEnd() {
	}

	@Override
	public default void accountSummary(int reqId, String account, String tag,
			String value, String currency) {
			
	}

	@Override
	public default void accountSummaryEnd(int reqId) {
		
	}
	
	
	
	

}
