package com.dunkware.trade.service.beach.server.controller.mock;

import com.dunkware.common.util.executor.DExecutor;

public class MockTradeEventListTest {
	
	public static void main(String[] args) {
		MockTradeEventList.newInstance(new DExecutor(4), 15).start();
		
	}

}
