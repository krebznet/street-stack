package com.dunkware.trade.broker.tws.instrument;

import com.dunkware.trade.broker.tws.connector.TwsSocketReader;

/**
 * Put the instrument and quote shit in its own class instead of stuffing it in
 * TwsBroker
 * 
 * @author dkrebs
 *
 */
public class TwsInstruments implements TwsSocketReader {

	/*
	 * private Logger logger = LoggerFactory.getLogger(getClass());
	 * 
	 * private ConcurrentHashMap<TradeTickerSpec, Instrument> instruments = new
	 * ConcurrentHashMap<TradeTickerSpec, Instrument>(); private
	 * ConcurrentHashMap<Integer, TradeTickerSpec> instrumentIds = new
	 * ConcurrentHashMap<Integer, TradeTickerSpec>(); private
	 * ConcurrentHashMap<TradeTickerSpec, Integer> instrumentInverseIds = new
	 * ConcurrentHashMap<TradeTickerSpec, Integer>(); private AtomicInteger
	 * instrumentIdFactory = new AtomicInteger(0);
	 * 
	 * private TwsBroker broker;
	 * 
	 * private ConcurrentHashMap<Integer, ReusableCountDownLatch>
	 * pendingSubscriptions = new ConcurrentHashMap<Integer,
	 * ReusableCountDownLatch>();;
	 * 
	 * public TwsInstruments(TwsBroker broker) { this.broker = broker;
	 * broker.addSocketReader(this); }
	 * 
	 * public Instrument subscribe(TradeTickerSpec ticker) throws Exception {
	 * if(ticker.getType() != TradeTickerType.Equity) { throw new
	 * Exception("Trade Ticker Type " + ticker.getType().toString() +
	 * " not handled in tws"); } StkContract contract = new
	 * StkContract(ticker.getSymbol()); int id =
	 * instrumentIdFactory.incrementAndGet(); instrumentIds.put(id, ticker);
	 * instrumentInverseIds.put(ticker, id); ReusableCountDownLatch latch = new
	 * ReusableCountDownLatch(); latch.increment(); pendingSubscriptions.put(id,
	 * latch); Instrument instrument = new
	 * InstrumentImpl(ticker,broker.getExecutor()); instruments.put(ticker,
	 * instrument); //broker.getClientSocket().reqMktData(id, contract,
	 * "293",false); if(!latch.waitTillZero(5, TimeUnit.SECONDS)) { throw new
	 * Exception("Tws Market Data 5 Second Timeout On Subscription Create  Ticker "
	 * + ticker.toString() ); } // remove pending subscription latch
	 * pendingSubscriptions.remove(id); return instrument; }
	 * 
	 * public void unsubsribe(Instrument instrument) {
	 * if(instruments.containsKey(instrument.getTicker())) {
	 * broker.getClientSocket().cancelMktData(instrumentInverseIds.get(instrument.
	 * getTicker())); instruments.remove(instrument.getTicker());
	 * instrumentIds.remove(instrumentInverseIds.get(instrument.getTicker()));
	 * instrumentInverseIds.remove(instrument.getTicker()); }
	 * 
	 * }
	 * 
	 * 
	 * 
	 * @Override public void tickPrice(int tickerId, int field, double price,
	 * TickAttrib attrib) { if(!instrumentIds.containsKey(tickerId)) { logger.
	 * warn("Received TickPrice For TickerID that is not found in instrument id map"
	 * ); return; }
	 *
	 * 
	 * 
	 * Instrument instrument = instruments.get(instrumentIds.get(tickerId));
	 * if(TickType.valueOf(null)) switch (field) {
	 * 
	 * case TickType. instrument.setAskPrice(price); break; case TickType.BID:
	 * instrument.setBidPrice(price); break; case TickType.OPEN:
	 * instrument.setOpen(price); break; case TickType.CLOSE:
	 * instrument.setClose(price); break; case TickType.HIGH:
	 * instrument.setHigh(price); break; case TickType.LOW:
	 * instrument.setLow(price); break; case TickType.LAST:
	 * instrument.setLast(price); break; }
	 * 
	 * processPending(tickerId, instrument);
	 * 
	 * }
	 * 
	 * 
	 * @Override public void tickSize(int tickerId, int field, int size) {
	 * if(!instrumentIds.containsKey(tickerId)) { logger.
	 * warn("Received TickSize For TickerID that is not found in instrument id map"
	 * ); return; }
	 * 
	 * Instrument instrument = instruments.get(instrumentIds.get(tickerId));
	 * 
	 * 
	 * switch (field) { case TickType.VOLUME: instrument.setVolume(size); break;
	 * case TickType.ASK_SIZE: instrument.setAskSize(size); break; case
	 * TickType.BID_SIZE: instrument.setBidSize(size); break; case
	 * TickType.TRADE_COUNT: instrument.setTradeCount(size); break; }
	 * 
	 * processPending(tickerId, instrument); }
	 * 
	 * 
	 * @Override public void tickString(int tickerId, int tickType, String value) {
	 * 
	 * }
	 * 
	 * 
	 *//**
		 * Called on every tick update so we can return any threads blocking while new
		 * subscriptions are creating.
		 * 
		 * @param equity
		 *//*
			 * private void processPending(int instrumentId, Instrument instrument) {
			 * if(pendingSubscriptions.containsKey(instrumentId)) {
			 * if(isProcessed(instrument)) { ReusableCountDownLatch latch =
			 * pendingSubscriptions.get(instrumentId); latch.decrement(); } } }
			 * 
			 * private boolean isProcessed(Instrument instrement) {
			 * if(instrement.getAskPrice() != Double.MIN_VALUE) {
			 * if(instrement.getBidPrice() != Double.MIN_VALUE) { if(instrement.getAskSize()
			 * != Integer.MIN_VALUE) { if(instrement.getBidSize() != Integer.MIN_VALUE) {
			 * if(instrement.getLast() != Double.MIN_VALUE) { return true; } } } } } return
			 * false; }
			 */

}
