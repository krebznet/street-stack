package com.dunkware.trade.broker.tws.connector;

import com.ib.client.CommissionReport;
import com.ib.client.Contract;
import com.ib.client.ContractDetails;
import com.ib.client.EWrapper;
import com.ib.client.Execution;
import com.ib.client.TwsOrder;
import com.ib.client.TwsOrderState;
import com.ib.client.UnderComp;

public interface TwsSocketReader extends EWrapper  {

	@Override
	public default void error(Exception e) {
		
		
	}

	@Override
	public default void error(String str) {
		
		
	}

	@Override
	public default void error(int id, int errorCode, String errorMsg) {
	
	}

	@Override
	public default void connectionClosed() {
		
		
	}

	@Override
	public default void tickPrice(int tickerId, int field, double price,
			int canAutoExecute) {
		
		
	}

	@Override
	public default void tickSize(int tickerId, int field, int size) {
		
		
	}

	@Override
	public default void tickOptionComputation(int tickerId, int field,
			double impliedVol, double delta, double optPrice,
			double pvDividend, double gamma, double vega, double theta,
			double undPrice) {
		
		
	}

	@Override
	public default void tickGeneric(int tickerId, int tickType, double value) {
		
		
	}

	@Override
	public default void tickString(int tickerId, int tickType, String value) {
		
		
	}

	@Override
	public default void tickEFP(int tickerId, int tickType, double basisPoints,
			String formattedBasisPoints, double impliedFuture, int holdDays,
			String futureExpiry, double dividendImpact, double dividendsToExpiry) {
		
		
	}

	@Override
	public default void orderStatus(int orderId, String status, int filled,
			int remaining, double avgFillPrice, int permId, int parentId,
			double lastFillPrice, int clientId, String whyHeld) {
		
		
	}

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
	public default void updatePortfolio(Contract contract, int position,
			double marketPrice, double marketValue, double averageCost,
			double unrealizedPNL, double realizedPNL, String accountName) {
		
		
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
	public default void updateMktDepth(int tickerId, int position, int operation,
			int side, double price, int size) {
		
		
	}

	@Override
	public default void updateMktDepthL2(int tickerId, int position,
			String marketMaker, int operation, int side, double price, int size) {
		
		
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
	public default void historicalData(int reqId, String date, double open,
			double high, double low, double close, int volume, int count,
			double WAP, boolean hasGaps) {
		
		
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
	public default void realtimeBar(int reqId, long time, double open, double high,
			double low, double close, long volume, double wap, int count) {
		
		
	}

	@Override
	public default void currentTime(long time) {
		
		
	}

	@Override
	public default void fundamentalData(int reqId, String data) {
		
		
	}

	@Override
	public default void deltaNeutralValidation(int reqId, UnderComp underComp) {
		
		
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
	public default void position(String account, Contract contract, int pos,
			double avgCost) {

		
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
