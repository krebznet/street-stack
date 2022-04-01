package com.dunkware.trade.tick.provider.atick.impl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.tick.proto.TickProto;
import com.dunkware.common.tick.proto.TickProto.Tick;
import com.dunkware.common.tick.proto.TickProto.Tick.TickFieldType;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.dtime.DZonedClock;
import com.dunkware.common.util.dtime.DZonedClockListener;
import com.dunkware.common.util.dtime.DZonedClockUpdater;
import com.dunkware.common.util.dtime.DZonedDateTime;
import com.dunkware.trade.tick.model.TradeTicks;
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

	private AtomicLong snapshotCount = new AtomicLong();
	private LogThread logThread;
	
	private BlockingQueue<Tick> tickQueue = new LinkedBlockingQueue<TickProto.Tick>();
	private TickDispatcher tickDispatcher;
	
	public ATProviderRequestor(ActiveTickProvider provider, ATProviderSession apiSession, ATProviderStreamer streamer) {
		super(apiSession.GetServerAPI(), apiSession.GetSession(), streamer);
		this.provider = provider;
		this.session = apiSession;
		extendedHoursController = new ExtendedHoursController();
		extendedHoursController.start();
		logThread = new LogThread();
		logThread.start();
		tickDispatcher = new TickDispatcher();
		tickDispatcher.start();
	}
	
	
	public void OnQuoteDbResponse(long origRequest, ATServerAPIDefines.ATQuoteDbResponseType responseType,
			Vector<ATServerAPIDefines.QuoteDbResponseItem> vecData) {
		ActiveTickStatsLogger.get().incrementResponse();
		String strResponseType = "";
		switch (responseType.m_atQuoteDbResponseType) {
		case ATQuoteDbResponseType.QuoteDbResponseSuccess:
			strResponseType = "QuoteDbResponseSuccess";
			break;
		case ATQuoteDbResponseType.QuoteDbResponseInvalidRequest:
			strResponseType = "QuoteDbResponseInvalidRequest";
			logger.error("QuoteFuckInvalidRequest {} ",provider.getQuoteRequests().get(origRequest));
			logger.error("QuoteDbResponseInvalidRequest");
			break;
		case ATQuoteDbResponseType.QuoteDbResponseDenied:
			strResponseType = "QuoteDbResponseDenied";
			logger.error("QuoteDbResponseDenied");
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
				logger.error("SymbolStatusInvalid");
				break;
			case ATSymbolStatus.SymbolStatusUnavailable:
				strSymbolStatus = "SymbolStatusUnavailable";
				logger.error("SymbolStatusUnavailable");
				break;
			case ATSymbolStatus.SymbolStatusNoPermission:
				strSymbolStatus = "SymbolStatusNoPermission";
				logger.error("SymbolStatusNoPermission");
				break;
			}
			String strItemSymbol = new String(responseItem.m_atResponse.symbol.symbol);
			int plainItemSymbolIndex = strItemSymbol.indexOf((byte) 0);
			strItemSymbol = strItemSymbol.substring(0, plainItemSymbolIndex);
			List<Tick.TickField> fields = new ArrayList<Tick.TickField>();
			fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldSymbol).setType(TickFieldType.STRING).setStringValue(strItemSymbol).build());
			fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldSymbolId).setType(TickFieldType.INT).setIntValue(this.provider.getSymbolService().getSymbolId(strItemSymbol)).build());
			
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
							fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldSymbol).setType(TickFieldType.STRING).setStringValue((String)getItemValue(dataItem, dataType)).build());
							break;	
						} catch (Exception e) {
							logger.error("symbol tick exception " + e.toString());
						}
					case ATQuoteFieldType.LastPrice:
						dataType = DOUBLE;
						// if we are in extended trading hours lets not even set this value
					
						fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldLastPrice).setType(TickFieldType.DOUBLE).setDoubleValue((Double)getItemValue(dataItem, dataType)).build());	
					
						break;
					case ATQuoteFieldType.LowPrice:
						dataType = DOUBLE;
						fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldLowPrice).setType(TickFieldType.DOUBLE).setDoubleValue((Double)getItemValue(dataItem, dataType)).build());
						break;
					case ATQuoteFieldType.PreMarketVolume:
						dataType = LONG;
						fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldPreMarketVolume).setType(TickFieldType.LONG).setLongValue((Long)getItemValue(dataItem, dataType)).build());
						
						
						break;
										
					case ATQuoteFieldType.TradeCount:
						dataType = INT;
						fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldTradeCount).setType(TickFieldType.INT).setIntValue((Integer)getItemValue(dataItem, dataType)).build());
						break;
					case ATQuoteFieldType.PreMarketTradeCount:
						dataType = INT;
						fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldPreMarketTradeCount).setType(TickFieldType.INT).setIntValue((Integer)getItemValue(dataItem, dataType)).build());
						break;
					case ATQuoteFieldType.Volume:
						dataType = LONG;
						fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldVolume).setType(TickFieldType.LONG).setLongValue((Long)getItemValue(dataItem, dataType)).build());
						break;
					case ATQuoteFieldType.AfterMarketClosePrice:
						dataType = DOUBLE;
						Double price = (Double)getItemValue(dataItem, dataType);
						System.out.println("After Market Close Price " + price);
						fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldAfterMarketClosePrice).setType(TickFieldType.DOUBLE).setDoubleValue((Double)getItemValue(dataItem, dataType)).build());
						break;
					case ATQuoteFieldType.AfterMarketTradeCount:
						dataType = INT;
						fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldAfterMarketTradeCount).setType(TickFieldType.INT).setDoubleValue((Integer)getItemValue(dataItem, dataType)).build());
						break;
					case ATQuoteFieldType.AfterMarketVolume:
						fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldAfterMarketVolume).setType(TickFieldType.LONG).setLongValue((Long)getItemValue(dataItem, dataType)).build());
						dataType = LONG;
						break;
					case ATQuoteFieldType.AskExchange:
						dataType = STRING;
						break;
					case ATQuoteFieldType.AskPrice:
						dataType = DOUBLE;
						fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldAskPrice).setType(TickFieldType.DOUBLE).setDoubleValue((Double)getItemValue(dataItem, dataType)).build());
						break;
					case ATQuoteFieldType.AskSize:
						dataType = INT;
						fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldAskSize).setType(TickFieldType.INT).setIntValue((Integer)getItemValue(dataItem, dataType)).build());
						break;
					case ATQuoteFieldType.BidExchange:
						dataType = STRING;
						break;
					case ATQuoteFieldType.BidPrice:
						dataType = DOUBLE;
						fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldBidPrice).setType(TickFieldType.DOUBLE).setDoubleValue((Double)getItemValue(dataItem, dataType)).build());
						break;
					case ATQuoteFieldType.BidSize:
						dataType = INT;
						fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldBidSize).setType(TickFieldType.INT).setIntValue((Integer)getItemValue(dataItem, dataType)).build());
						break;
					case ATQuoteFieldType.ClosePrice:
						dataType = DOUBLE;
						fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldClosePrice).setType(TickFieldType.DOUBLE).setDoubleValue((Double)getItemValue(dataItem, dataType)).build());
						break;
					case ATQuoteFieldType.HighPrice:
						dataType = DOUBLE;
						fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldHighPrice).setType(TickFieldType.DOUBLE).setDoubleValue((Double)getItemValue(dataItem, dataType)).build());
						break;
					case ATQuoteFieldType.OpenPrice:
						dataType = DOUBLE;
						fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldOpenPrice).setType(TickFieldType.DOUBLE).setDoubleValue((Double)getItemValue(dataItem, dataType)).build());
						break;
					case ATQuoteFieldType.DayHighDateTime:
						dataType = INT;
						break;
					case ATQuoteFieldType.DayHighPrice:
						fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldDayHighPrice).setType(TickFieldType.DOUBLE).setDoubleValue((Double)getItemValue(dataItem, dataType)).build());
						dataType = DOUBLE;
						break;
					case ATQuoteFieldType.DayLowDateTime:
						dataType = INT;
						break;
					case ATQuoteFieldType.DayLowPrice:
						dataType = DOUBLE;
						fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldDayLowPrice).setType(TickFieldType.DOUBLE).setDoubleValue((Double)getItemValue(dataItem, dataType)).build());
						break;
					case ATQuoteFieldType.ExtendedHoursLastPrice:
						dataType = DOUBLE;
						
						fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldExtendedHoursLastPrice).setType(TickFieldType.DOUBLE).setDoubleValue((Double)getItemValue(dataItem, dataType)).build());
						
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
					System.err.println(e.toString());
					logger.error("Data Type Conversion Exception " + e.toString());
					continue;
				}
			
				
			}
			
			Tick tick = Tick.newBuilder().setType(TradeTicks.TickSnapshot).addAllTickFields(fields).build();
			snapshotCount.incrementAndGet();
			ActiveTickStatsLogger.get().incrementQuoteCount();
			tickQueue.add(tick);
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
	
	
	private class LogThread extends Thread { 
		
		public void run() {
			while(!interrupted()) { 
				try {
					Thread.sleep(5000);
					logger.debug("Snapshot Count {}",snapshotCount.get());
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}
	
	
	private class TickDispatcher extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				try {
					Tick tick = tickQueue.take();
					provider.streamTick(tick);
				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
					logger.error("Error Dispatching Tick " + e.toString(),e);
				}
			}
		}
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
