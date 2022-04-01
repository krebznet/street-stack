package com.dunkware.trade.broker.tws.connector.wrapper;

import com.ib.client.EWrapper;

/**
 * 
 * @author Duncan Krebs
 * @date Aug 21, 2015
 * @category Comcast M9
 */
public class WCError implements EWrapperCall {
		
		private int id;
		private int errorCode;
		private String errorMsg;
		
		public WCError(int id, int errorCode, String errorMsg) {
			this.id = id;
			this.errorCode = errorCode;
			this.errorMsg = errorMsg;
		}

		@Override
		public void callMethod(EWrapper wrapper) {
			wrapper.error(id, errorCode, errorMsg);
		}
}
