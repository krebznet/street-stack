package com.dunkware.trade.service.beach.server.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.dunkware.common.util.datagrid.DataGridUpdate;
import com.dunkware.common.util.json.DJson;
import com.dunkware.spring.streaming.StreamingAdapter;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.controller.mock.MockTradeEventList;
import com.dunkware.trade.service.beach.server.controller.stream.mock.MockOrderStream;
import com.dunkware.trade.service.beach.server.runtime.BeachAccountBean;
import com.dunkware.trade.service.beach.server.runtime.BeachBrokerBean;
import com.dunkware.trade.service.beach.server.runtime.BeachOrderBean;
import com.dunkware.trade.service.beach.server.runtime.BeachPlayBean;
import com.dunkware.trade.service.beach.server.runtime.BeachTradeBean;

@CrossOrigin()
@RestController
public class BeachWebMockController {
	
	// /trade/mock/stream/brokers
	
	@Autowired
	private BeachRuntime beachRuntime; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("BeachWebMockController");
	

	@GetMapping(path = "/trade/v1/mock/dash/trades")
	public ResponseEntity<StreamingResponseBody> mockTrades(@RequestParam() int rows) throws IOException{
	    StreamingResponseBody body = new  StreamingResponseBody() {
	        @Override
	        public void writeTo(final OutputStream outputStream) throws IOException{
	        	MockTradeEventList mockList = null;
	        	try {
	        		mockList = MockTradeEventList.newInstance(beachRuntime.getExecutor(), rows);
	        		mockList.start();
	        	} catch (Exception e) {
					throw new ResponseStatusException(
					           HttpStatus.BAD_REQUEST, "Exception starting mock trade list factory " + e.getMessage(), e);				}
	            try {
	            	
	            	while(true) { 
	            		DataGridUpdate update = mockList.nextUpdate(1, TimeUnit.SECONDS);
	            		if(update != null) { 
	            			outputStream.write(DJson.serialize(update).getBytes());
	            			outputStream.flush();
	            		}
	            		
	            	}
	                // Some operations..
	            } catch (Exception e ) {
	            	outputStream.flush();
	            	outputStream.close();
	            	mockList.dispose();
	            	logger.info(marker, " exception in trading mock dash data , dispoing " +e.toString());
	            	
	            	
	               return;
	            }
	        }
	        
	    };
	    return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(body);
	}
	
	@GetMapping(path = "/trade/mock/brokers")
	public @ResponseBody() List<BeachBrokerBean> mockBrokers() {
		List<BeachBrokerBean> beans = new ArrayList<BeachBrokerBean>();
		BeachBrokerBean bean = new BeachBrokerBean();
		bean.setId(1);
		bean.setName("Paper Broker 1");
		bean.setStatus("Connected");
		beans.add(bean);
		bean = new BeachBrokerBean();
		bean.setId(2);
		bean.setName("Paper Broker 2");
		bean.setStatus("Disconnected");
		beans.add(bean);
		return beans;
	}

	@GetMapping(path = "/trade/mock/accounts")
	public @ResponseBody() List<BeachAccountBean> mockAccounts() {
		List<BeachAccountBean> beans = new ArrayList<BeachAccountBean>();
		BeachAccountBean b = new BeachAccountBean();
		b.setBroker("Paper Broker 1");
		b.setId(1);
		b.setName("Account 1");
	//	b.setBuyingPower(2300.23);
	//	b.setTotalCashValue(233.23);
	//	b.setGrossPositionValue(2345.23);
		beans.add(b);
		b = new BeachAccountBean();
		b.setBroker("Paper Broker 2");
		b.setId(2);
		b.setName("Accunt 2");
		;
	//	b.setTotalCashValue(55445.45);
		//b.setBuyingPower(23232.23);
	//	b.setTotalCashValue(2323.4);
		beans.add(b);
		return beans;

	}
	@GetMapping(path = "/trade/mock/trades")
	public @ResponseBody() List<BeachTradeBean> mockTrades() {
		List<BeachTradeBean> beans = new ArrayList<BeachTradeBean>();
		BeachTradeBean b = new BeachTradeBean();
		b.setAccount("Account 1");
		b.setAccountId(1);
		b.setAllocatedSize(3000);
		b.setEntryCommission(3.23);
		b.setOpeningTime("9:23:42");
		b.setOpenTime("10:23:32");
		b.setEntryValue(3.23);
		b.setFilledSize(3000);
		b.setIdent("TRADE1");
		b.setMarketValue(234.2332);
		b.setPlayId(1);
		b.setPlay("TestPlay1");
		b.setSize(3000);
		b.setStatus("Open");
		b.setSymbol("SPY");
		b.setId(1);
		b.setUpl(-23.3);
		b.setUplp(-1.3);
		beans.add(b);
		b = new BeachTradeBean();
		b.setAccount("Account 1");
		b.setAccountId(1);
		b.setAllocatedSize(4000);
		b.setEntryCommission(2.23);
		b.setOpeningTime("10:23:42");
		b.setOpenTime("10:23:32");
		b.setEntryValue(3.23);
		b.setFilledSize(3000);
		b.setIdent("TRADE2");
		b.setMarketValue(2394.2332);
		b.setPlayId(1);
		b.setPlay("TestPlay1");
		b.setSize(3000);
		b.setStatus("Closed");
		b.setSymbol("FSLR");
	
		b.setUpl(-23.3);
		b.setUplp(-1.3);
		b.setCloseTime("11:13:33");
		b.setClosingTime("11:11:34");
		b.setExitCommission(3.23);
		b.setTradeComission(5.23);
		beans.add(b);
		return beans;
	}

	@GetMapping(path = "/trade/mock/orders")
	public @ResponseBody() List<BeachOrderBean> mockOrders() {
		List<BeachOrderBean> beans = new ArrayList<BeachOrderBean>();
		BeachOrderBean b = new BeachOrderBean();
		b.setAccount("Account 1");
		b.setCommission(5.32);
		b.setFilled(5);
		b.setSize(5);
		b.setRemaining(0);
		b.setStatus("Filled");
		b.setAccount("Buy");
		b.setKind("MKT");
		b.setTrade("TRADE1");
		b.setAvgFillPrice(32.42);
		b.setSymbol("AAPL");
		b.setOpenTimeStamp("9:23:23AM");
		b.setAccountId(1);
		b.setFillTimeStamp("932:AM");
		beans.add(b);
		return beans;
	}
	
	@GetMapping(path = "/trade/mock/plays")
	public @ResponseBody() List<BeachPlayBean> mockPlays() {
		List<BeachPlayBean> beans = new ArrayList<BeachPlayBean>();
		BeachPlayBean b = new BeachPlayBean();
		b.setAccount("Account 1");
		b.setActiveCapital(3434.34);
		b.setActiveTrades(4);
		b.setClosedTrades(5);
		b.setName("TestPlay1");
		b.setStatus("Running");
		b.setUpl(235.23);
		b.setRpl(23.5);
		b.setAccountId(1);
		b.setTradeCount(9);
		//b.setStartTime("9:30:23");
		beans.add(b);
		b = new BeachPlayBean();
		 b = new BeachPlayBean();
		b.setAccount("Account 1");
		b.setActiveCapital(3434.34);
		b.setActiveTrades(4);
		b.setClosedTrades(5);
		b.setName("TestPla21");
		b.setStatus("Stopped");
		b.setUpl(235.23);
		b.setRpl(23.5);
		b.setAccountId(1);
		b.setTradeCount(9);
		//b.setStartTime("9:30:23");
		beans.add(b);
		return beans;
	}
	
	@CrossOrigin()
	@GetMapping(path = "/trade/stream/orders")
	public ResponseEntity<StreamingResponseBody> nodesStream()
			throws Exception {
	
		StreamingAdapter adapter = new StreamingAdapter("BeachOrders");
		MockOrderStream mock = new MockOrderStream(adapter);
	
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(adapter);
		// return adapter;

	}

}
