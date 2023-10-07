package com.dunkware.trade.tick.provider.atick.impl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.dtime.DZonedClock;
import com.dunkware.common.util.dtime.DZonedClockListener;
import com.dunkware.common.util.dtime.DZonedClockUpdater;
import com.dunkware.common.util.dtime.DZonedDateTime;
import com.dunkware.trade.tick.model.feed.TickFeedSnapshot;
import com.dunkware.trade.tick.provider.atick.ActiveTickProvider;

import at.feedapi.Helpers;
import at.shared.ATServerAPIDefines;
import at.shared.ATServerAPIDefines.ATDataType;
import at.shared.ATServerAPIDefines.ATQuoteDbResponseType;
import at.shared.ATServerAPIDefines.ATQuoteFieldType;
import at.shared.ATServerAPIDefines.ATSymbolStatus;
import at.shared.ATServerAPIDefines.QuoteDbDataItem;
import at.shared.ATServerAPIDefines.QuoteDbResponseItem;
import at.shared.ATServerAPIDefines.SYSTEMTIME;
import at.shared.ActiveTick.DateTime;
import at.shared.ActiveTick.UInt64;

public class ATProviderRequestor extends at.feedapi.ActiveTickServerRequester {


	private static final int STRING = 0;
	private static final int DOUBLE = 1;
	private static final int LONG = 2;
	private static final int INT = 3;

	
	private ATProviderSession session;
	private ActiveTickProvider provider; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private AtomicBoolean extendedHours = new AtomicBoolean(false); 
	private ExtendedHoursController extendedHoursController = null;

	private Map<String,AtomicInteger> counts = new ConcurrentHashMap<String,AtomicInteger>() ;

	
	public ATProviderRequestor(ActiveTickProvider provider, ATProviderSession apiSession, ATProviderStreamer streamer) {
		super(apiSession.GetServerAPI(), apiSession.GetSession(), streamer);
		this.provider = provider;
		this.session = apiSession;
		extendedHoursController = new ExtendedHoursController();
		extendedHoursController.start();
		
	}
	
	
	public void OnQuoteDbResponse(long origRequest, ATServerAPIDefines.ATQuoteDbResponseType responseType,
			Vector<ATServerAPIDefines.QuoteDbResponseItem> vecData) {
		ActiveTickStatsLogger.get().incrementResponse();
		Integer req = provider.getPendingSnapshotRequests().remove(origRequest);
		String strResponseType = "";
		switch (responseType.m_atQuoteDbResponseType) {
		case ATQuoteDbResponseType.QuoteDbResponseSuccess:
			strResponseType = "QuoteDbResponseSuccess";
			break;
		case ATQuoteDbResponseType.QuoteDbResponseInvalidRequest:
			strResponseType = "QuoteDbResponseInvalidRequest";
			logger.warn("QuoteFuckInvalidRequest {} ", responseType.toString());
			logger.warn("QuoteDB Invalid Reequest, where is the error "+  responseType.toString());
			break;
		case ATQuoteDbResponseType.QuoteDbResponseDenied:
			strResponseType = "QuoteDbResponseDenied";
			logger.warn("QuoteDbResponseDenied");
			ActiveTickStatsLogger.get().incrementResponseError();
			break;
		}

		Iterator<QuoteDbResponseItem> itr = vecData.iterator();
		String strSymbolStatus = "";
		

		for (ATServerAPIDefines.QuoteDbResponseItem responseItem: vecData) {
			//QuoteDbResponseItem responseItem = (QuoteDbResponseItem) itr.next();
			switch (responseItem.m_atResponse.status.m_atSymbolStatus) {
			case ATSymbolStatus.SymbolStatusSuccess:
				strSymbolStatus = "SymbolStatusSuccess";
				break;
			case ATSymbolStatus.SymbolStatusInvalid:
				strSymbolStatus = "SymbolStatusInvalid";
				logger.warn("SymbolStatusInvalid");
				break;
			case ATSymbolStatus.SymbolStatusUnavailable:
				strSymbolStatus = "SymbolStatusUnavailable";
				logger.warn("SymbolStatusUnavailable");
				break;
			case ATSymbolStatus.SymbolStatusNoPermission:
				strSymbolStatus = "SymbolStatusNoPermission";
				logger.warn("SymbolStatusNoPermission");
				break;
			}
			String strItemSymbol = new String(responseItem.m_atResponse.symbol.symbol);
			if(counts.get(strItemSymbol) == null) { 
				counts.put(strItemSymbol, new AtomicInteger(1));
			}  else { 
				counts.get(strItemSymbol).incrementAndGet();
			}
			int plainItemSymbolIndex = strItemSymbol.indexOf((byte) 0);
			strItemSymbol = strItemSymbol.substring(0, plainItemSymbolIndex);
			TickFeedSnapshot snapshot = new TickFeedSnapshot();
			snapshot.setTime(DDateTime.now(DTimeZone.NewYork));
			snapshot.setSymbol(strItemSymbol);
			//TODO: item
			//fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldSymbolId).setType(TickFieldType.INT).setIntValue(this.provider.getSymbolService().getSymbolId(strItemSymbol)).build());
			
			for (QuoteDbDataItem dataItem : responseItem.m_vecDataItems) {
			//while (responseItem.m_atResponse.status.m_atSymbolStatus == ATSymbolStatus.SymbolStatusSuccess
			//		&& itrDataItems.hasNext()) {
			//	QuoteDbDataItem dataItem = (QuoteDbDataItem) itrDataItems.next();
				String strData = "";
				int dataType = 0;
				try {
					switch (dataItem.m_dataItem.fieldType.m_atQuoteFieldType) {
					case ATQuoteFieldType.Symbol:
						dataType = STRING;
						try {
							snapshot.setSymbol((String)(getItemValue(dataItem, STRING)));	
							break;	
						} catch (Exception e) {
							logger.error("symbol tick exception " + e.toString());
						}
					case ATQuoteFieldType.LastPrice:
						dataType = DOUBLE;
						// if we are in extended trading hours lets not even set this value
						Double lastPrice = (Double)getItemValue(dataItem, DOUBLE);
						snapshot.setLast(lastPrice);	
					case ATQuoteFieldType.LowPrice:
						
						break;
					case ATQuoteFieldType.PreMarketVolume:
						dataType = LONG;
						Long preMarketVolume = (Long)getItemValue(dataItem, LONG);
						snapshot.setPremarketVolume(preMarketVolume);
						break;
					case ATQuoteFieldType.TradeCount:
						dataType = INT;
						int tradeCount = (Integer)getItemValue(dataItem, dataType);
						snapshot.setTradeCount(tradeCount);
					case ATQuoteFieldType.PreMarketTradeCount:
						dataType = INT;
						int premarketTradeCount = (Integer)getItemValue(dataItem, dataType);
						snapshot.setPremarketTradeCount(premarketTradeCount);
						break;
					case ATQuoteFieldType.Volume:
						dataType = LONG;
						long volume = (Long)getItemValue(dataItem, dataType);
						snapshot.setVolume(volume);
						break;
					case ATQuoteFieldType.AfterMarketClosePrice:
						dataType = DOUBLE;
						Double price = (Double)getItemValue(dataItem, dataType);
						snapshot.setAfterMarketClosePrice(price);
						break;
					case ATQuoteFieldType.AfterMarketTradeCount:
						dataType = INT;
						int amtc = (Integer)getItemValue(dataItem, dataType);
						snapshot.setAfterMarketTradeCount(amtc);
						break;
					case ATQuoteFieldType.AfterMarketVolume:
						Long amv = (Long)getItemValue(dataItem, dataType);
						snapshot.setAfterMarketVolume(amv);
						break;
					case ATQuoteFieldType.AskExchange:
						dataType = STRING;
						break;
					case ATQuoteFieldType.AskPrice:
						dataType = DOUBLE;
						Double askPrice = (Double)getItemValue(dataItem, dataType);
						snapshot.setAskPrice(askPrice);
						break;
					case ATQuoteFieldType.AskSize:
						dataType = INT;
						int askSize = (Integer)getItemValue(dataItem, dataType);
						snapshot.setAskSize(askSize);
						break;
					case ATQuoteFieldType.BidExchange:
						dataType = STRING;
						break;
					case ATQuoteFieldType.BidPrice:
						dataType = DOUBLE;
						double bp = (Double)getItemValue(dataItem, dataType);
						snapshot.setBidPrice(bp);
						break;
					case ATQuoteFieldType.BidSize:
						dataType = INT;
						int bs = (Integer)getItemValue(dataItem, dataType);
						snapshot.setBidSize(bs);
						break;
					case ATQuoteFieldType.ClosePrice:
						dataType = DOUBLE;	
						double cp = (Double)getItemValue(dataItem, dataType);
						snapshot.setClosePrice(cp);
						break;
					case ATQuoteFieldType.HighPrice:
						dataType = DOUBLE;
						//fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldHighPrice).setType(TickFieldType.DOUBLE).setDoubleValue((Double)getItemValue(dataItem, dataType)).build());
						break;
					case ATQuoteFieldType.OpenPrice:
						dataType = DOUBLE;
						double op = (Double)getItemValue(dataItem, dataType);
						snapshot.setOpenPrice(op);
						break;
					case ATQuoteFieldType.DayHighDateTime:
						dataType = INT;
						break;
					case ATQuoteFieldType.DayHighPrice:
						//fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldDayHighPrice).setType(TickFieldType.DOUBLE).setDoubleValue((Double)getItemValue(dataItem, dataType)).build());
						dataType = DOUBLE;
						break;
					case ATQuoteFieldType.DayLowDateTime:
						dataType = INT;
						break;
					case ATQuoteFieldType.DayLowPrice:
						dataType = DOUBLE;
						//fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldDayLowPrice).setType(TickFieldType.DOUBLE).setDoubleValue((Double)getItemValue(dataItem, dataType)).build());
						break;
					case ATQuoteFieldType.ExtendedHoursLastPrice:
						dataType = DOUBLE;
						Double extendedLast = (Double)getItemValue(dataItem, dataType);
						snapshot.setExtendedHoursLastPrice(extendedLast);
						break;
					
				
					case ATQuoteFieldType.LastTradeDateTime:
						//getItemValue(item, dataType)
						//TODO: Handle LastTradeDateTime
						break;
					default:
						// do not throw error for now
						//logger.error("unknown field type " + dataItem.m_dataItem.fieldType.m_atQuoteFieldType);
						// do nothing for now
						break;
					}
							
				} catch (Exception e) {
				
					logger.error("Data Type Conversion Exception " + e.toString());
					continue;
				}
			
				
			}
			
			// HACKY? DUNKY? IF EXTENDED HOURS UPDATE LAST PRICE WITH EXTENDED LAST PRICE SO WE GET OUR FIELDS UPDATED
			if(extendedHours.get() == true) { 
				snapshot.setLast(snapshot.getExtendedHoursLastPrice());
			}
			provider.onSnapshot(snapshot);	
		}
	}
	
	private Object getItemValue(QuoteDbDataItem item, int dataType) throws Exception {
		try {
			int intValue;
			long longValue;
			String strData = null;
			byte[] intBytes = new byte[4];
			byte[] longBytes = new byte[8];
			switch (dataType) {
			case STRING: {

				switch (item.m_dataItem.dataType.m_atDataType) {
				case ATDataType.Byte:
					strData = new String(item.GetItemData());
					return strData;
				case ATDataType.ByteArray: {
					strData = new String("byte data");
					return strData;
				}
				case ATDataType.UInteger32: {
					System.arraycopy(item.GetItemData(), 0, intBytes, 0, 4);
					int nData = ByteBuffer.wrap(intBytes).order(ByteOrder.LITTLE_ENDIAN).getInt();
					return strData;
				}
				case ATDataType.UInteger64: {
					System.arraycopy(item.GetItemData(), 0, longBytes, 0, 8);
					long nData = ByteBuffer.wrap(longBytes).order(ByteOrder.LITTLE_ENDIAN).getLong();
					strData = new String("" + nData);
					return strData;
				}
				case ATDataType.Integer32: {
					System.arraycopy(item.GetItemData(), 0, intBytes, 0, 4);
					int nData = ByteBuffer.wrap(intBytes).order(ByteOrder.LITTLE_ENDIAN).getInt();
					return new String(String.valueOf(nData));
				}
				case ATDataType.Integer64: {
					System.arraycopy(item.GetItemData(), 0, longBytes, 0, 8);
					long nData = ByteBuffer.wrap(longBytes).order(ByteOrder.LITTLE_ENDIAN).getLong();
					return String.valueOf(nData);
				}
				case ATDataType.Price: {
					ATServerAPIDefines.ATPRICE price = Helpers.BytesToPrice(item.GetItemData());
					return price.price;
				}

				case ATDataType.String: {
					strData = new String(item.GetItemData());
					return strData;
				}

				case ATDataType.UnicodeString: {
					strData = new String(item.GetItemData());
					return strData;
				}
				case ATDataType.DateTime: {
					UInt64 li = new UInt64(item.GetItemData());
					SYSTEMTIME dateTime = DateTime.GetDateTime(li);
					StringBuilder sb = new StringBuilder();
					sb.append(dateTime.month);
					sb.append("/");
					sb.append(dateTime.day);
					sb.append("/");
					sb.append(dateTime.year);
					sb.append(" ");
					sb.append(dateTime.hour);
					sb.append(":");
					sb.append(dateTime.minute);
					sb.append(":");
					sb.append(dateTime.second);
					strData = sb.toString();
					return strData;
				}
				default:
					throw new Exception(
							"DataType " + item.m_dataItem.dataType.m_atDataType + " not handled to convert to string");
				}
			}
			case INT:
				switch (item.m_dataItem.dataType.m_atDataType) {
				case ATDataType.Byte:
					strData = new String(item.GetItemData());
					throw new Exception("cannont convert byte array to int");
				case ATDataType.ByteArray: {
					strData = new String("byte data");
					throw new Exception("AT type byte data does not convert to int");
				}
				case ATDataType.UInteger32: {
					System.arraycopy(item.GetItemData(), 0, intBytes, 0, 4);
					int nData = ByteBuffer.wrap(intBytes).order(ByteOrder.LITTLE_ENDIAN).getInt();
					return nData;
				}
				case ATDataType.UInteger64: {
					System.arraycopy(item.GetItemData(), 0, longBytes, 0, 8);
					long nData = ByteBuffer.wrap(longBytes).order(ByteOrder.LITTLE_ENDIAN).getLong();
					Long nLong = new Long(nData);
					return nLong.intValue();
				}
				case ATDataType.Integer32: {
					System.arraycopy(item.GetItemData(), 0, intBytes, 0, 4);
					int nData = ByteBuffer.wrap(intBytes).order(ByteOrder.LITTLE_ENDIAN).getInt();
					return nData;
				}
				case ATDataType.Integer64: {
					System.arraycopy(item.GetItemData(), 0, longBytes, 0, 8);
					long nData = ByteBuffer.wrap(longBytes).order(ByteOrder.LITTLE_ENDIAN).getLong();
					Long nLong = new Long(nData);
					return nLong.intValue();
				}
				case ATDataType.Price: {
					ATServerAPIDefines.ATPRICE price = Helpers.BytesToPrice(item.GetItemData());
					throw new Exception("cannot convert price to int as asking");
				}

				case ATDataType.String: {
					strData = new String(item.GetItemData());
					try {
						Integer value = Integer.valueOf(strData);
						return value;
					} catch (Exception e) {
						throw new Exception("Exception converting AT String to INT " + e.toString());
					}
				}

				case ATDataType.UnicodeString: {
					strData = new String(item.GetItemData());
					try {
						Integer value = Integer.valueOf(strData);
						return value;
					} catch (Exception e) {
						throw new Exception("Exception converting AT String to INT " + e.toString());
					}
				}
				case ATDataType.DateTime: {
					throw new Exception("Cannot convert DateTime to int");

				}
				default: {
					throw new Exception(
							"DataType " + item.m_dataItem.dataType.m_atDataType + " not handled to convert to string");
				}
				}
			case DOUBLE:
				try {
					switch (item.m_dataItem.dataType.m_atDataType) {
					
					case ATDataType.Byte:
						strData = new String(item.GetItemData());
						throw new Exception("cannont convert byte array to double");
					case ATDataType.ByteArray: {
						strData = new String("byte data");
						throw new Exception("AT type byte data does not convert to double");
					}
					case ATDataType.UInteger32: {
						System.arraycopy(item.GetItemData(), 0, intBytes, 0, 4);
						int nData = ByteBuffer.wrap(intBytes).order(ByteOrder.LITTLE_ENDIAN).getInt();
						return new Double(nData);
					}
					case ATDataType.UInteger64: {
						System.arraycopy(item.GetItemData(), 0, longBytes, 0, 8);
						long nData = ByteBuffer.wrap(longBytes).order(ByteOrder.LITTLE_ENDIAN).getLong();
						Long nLong = new Long(nData);
						return new Double(nLong);
					}
					case ATDataType.Integer32: {
						System.arraycopy(item.GetItemData(), 0, intBytes, 0, 4);
						int nData = ByteBuffer.wrap(intBytes).order(ByteOrder.LITTLE_ENDIAN).getInt();
						return new Double(nData);
					}
					case ATDataType.Integer64: {
						System.arraycopy(item.GetItemData(), 0, longBytes, 0, 8);
						long nData = ByteBuffer.wrap(longBytes).order(ByteOrder.LITTLE_ENDIAN).getLong();
						Long nLong = new Long(nData);
						return new Double(nLong);
					}
					case ATDataType.Price: {
						ATServerAPIDefines.ATPRICE price = Helpers.BytesToPrice(item.GetItemData());
				
						return price.price;
					}

					case ATDataType.String: {
						strData = new String(item.GetItemData());
						try {
							Double value = Double.valueOf(strData);
							return value;
						} catch (Exception e) {
							throw new Exception("Exception converting AT String to DOUBLE " + e.toString());
						}
					}

					case ATDataType.UnicodeString: {
						strData = new String(item.GetItemData());
						try {
							Double value = Double.valueOf(strData);
							return value;
						} catch (Exception e) {
							throw new Exception("Exception converting AT String to Double " + e.toString());
						}
					}
					case ATDataType.DateTime: {
						UInt64 li = new UInt64(item.GetItemData());
						SYSTEMTIME dateTime = DateTime.GetDateTime(li);
						StringBuilder sb = new StringBuilder();
						sb.append(dateTime.month);
						sb.append("/");
						sb.append(dateTime.day);
						sb.append("/");
						sb.append(dateTime.year);
						sb.append(" ");
						sb.append(dateTime.hour);
						sb.append(":");
						sb.append(dateTime.minute);
						sb.append(":");
						sb.append(dateTime.second);
						strData = sb.toString();
						throw new Exception("cannot convert AT DateTime to Double");
					}
					default:
						throw new Exception(
								"DataType " + item.m_dataItem.dataType.m_atDataType + " not handled to convert to string");
					}	
				} catch (Exception e) {
					logger.error("error fuck shit conversion " + e.toString());
				}
				
			case LONG:
				switch (item.m_dataItem.dataType.m_atDataType) {
				case ATDataType.Byte:
					strData = new String(item.GetItemData());
					throw new Exception("cannot convert byte to long");
				case ATDataType.ByteArray: {
					strData = new String("byte data");
					throw new Exception("cannot convert byte array to long");
				}
				case ATDataType.UInteger32: {
					System.arraycopy(item.GetItemData(), 0, intBytes, 0, 4);
					int nData = ByteBuffer.wrap(intBytes).order(ByteOrder.LITTLE_ENDIAN).getInt();
					return new Long(nData);
				}
				case ATDataType.UInteger64: {
					System.arraycopy(item.GetItemData(), 0, longBytes, 0, 8);
					long nData = ByteBuffer.wrap(longBytes).order(ByteOrder.LITTLE_ENDIAN).getLong();
					Long nLong = new Long(nData);
					return nLong;
				}
				case ATDataType.Integer32: {
					System.arraycopy(item.GetItemData(), 0, intBytes, 0, 4);
					int nData = ByteBuffer.wrap(intBytes).order(ByteOrder.LITTLE_ENDIAN).getInt();
					return new Long(nData);
				}
				case ATDataType.Integer64: {
					System.arraycopy(item.GetItemData(), 0, longBytes, 0, 8);
					long nData = ByteBuffer.wrap(longBytes).order(ByteOrder.LITTLE_ENDIAN).getLong();
					Long nLong = new Long(nData);
					return new Long(nLong);
				}
				case ATDataType.Price: {
					ATServerAPIDefines.ATPRICE price = Helpers.BytesToPrice(item.GetItemData());
					strData = "" + price.price;
					throw new Exception("cannot convert price double to long");
				}

				case ATDataType.String: {
					strData = new String(item.GetItemData());
					try {
						Long value = Long.valueOf(strData);
						return value;
					} catch (Exception e) {
						throw new Exception("Exception converting AT String to LONG " + e.toString());
					}
				}

				case ATDataType.UnicodeString: {
					strData = new String(item.GetItemData());
					try {
						Long value = Long.valueOf(strData);
						return value;
					} catch (Exception e) {
						throw new Exception("Exception converting AT String to Long " + e.toString());
					}
				}
				case ATDataType.DateTime: {
					UInt64 li = new UInt64(item.GetItemData());
					SYSTEMTIME dateTime = DateTime.GetDateTime(li);
					StringBuilder sb = new StringBuilder();
					sb.append(dateTime.month);
					sb.append("/");
					sb.append(dateTime.day);
					sb.append("/");
					sb.append(dateTime.year);
					sb.append(" ");
					sb.append(dateTime.hour);
					sb.append(":");
					sb.append(dateTime.minute);
					sb.append(":");
					sb.append(dateTime.second);
					strData = sb.toString();
					throw new Exception("cannot convert AT DateTime to Long");
				}
				default:
					throw new Exception(
							"DataType " + item.m_dataItem.dataType.m_atDataType + " not handled to convert to string");
				}
			}
		} catch (Exception e) {
			System.err.println("fucker here it is!! " + e.toString());
		}
	

		throw new Exception("unhalded switch in get vlaue data value");

	}
	
	

	private class ExtendedHoursController extends Thread implements DZonedClockListener { 
		
		private DZonedClock clock;
		private DZonedClockUpdater clockUpdater; 
		private DZonedDateTime lastDateTime;
		
		private LocalTime marketOpen = LocalTime.of(9,30,0);
		private LocalTime marketClose = LocalTime.of(16,0,0);
		
		private boolean pendingOpen = false; 
		private boolean pendingClose = false; 
	
		
		public void run() { 
			clockUpdater = DZonedClockUpdater.now(DTimeZone.NewYork, 1, TimeUnit.SECONDS);
			clock = clockUpdater.getClock();
			lastDateTime = clockUpdater.getClock().getDateTime();
			initialize();
			clock.addListener(this);
		}
		
		public void dispose() { 
			clockUpdater.dispose();
			clock.removeListener(this);
		}
		
		private void initialize() { 
			if(lastDateTime.toLocalTime().get().isBefore(marketClose) && lastDateTime.toLocalTime().get().isAfter(marketOpen)) { 
				pendingClose = true;
				logger.info("Starting Extended Hours To False, Market is Open");
				return;
			}
			if(lastDateTime.toLocalTime().get().isAfter(marketClose)) { 
				extendedHours.set(true);
				logger.info("Starting Extended Hours to True, Market is Closed");
				pendingOpen = true;
				return;
			}
			
		}

		@Override
		public void clockUpdate(DZonedClock clock) {
			this.lastDateTime = clock.getDateTime();
			if(pendingOpen) {
				// if we are after market open time then lets set
				// extended hours to false 
				if(lastDateTime.toLocalTime().get().isAfter(marketClose) == false && lastDateTime.toLocalTime().get().isAfter(marketOpen)) { 
					logger.info("Setting Extended Hours To False, Market Has Openeed");
					extendedHours.set(false);
					pendingClose = true;
					pendingOpen = false; 
				}
				return;
			}
			if(pendingClose) { 
				if(lastDateTime.toLocalTime().get().isAfter(marketClose)) { 
					extendedHours.set(true);
					logger.info("Setting Extended Hours to True, Market Has Closed");
					pendingClose = false;
					pendingOpen = true;
				}
				return;
			}
		}
		
		
	}
	
}
