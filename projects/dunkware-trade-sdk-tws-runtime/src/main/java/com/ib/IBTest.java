/**
 * @author Duncan Krebs
 * @category Perspective
 * @date Dec 5, 2016
 */
package com.ib;

import com.ib.client.Contract;
import com.ib.client.EClientSocket;
import com.ib.client.TwsOrder;

/**
 * @author Duncan Krebs
 * @category Perspective
 * @date Dec 5, 2016
 */
public class IBTest {

	public static void main(String[] args) {
		try {
		    IBTestWrapper wrapper = new IBTestWrapper();
			EClientSocket socket = new EClientSocket(wrapper);
			socket.eConnect("192.168.23.160", 8123, 15);
			
			
			// make the contract 
			Contract contract = new Contract();
			contract.m_symbol = "SPY";
			contract.m_currency = "USD";
			contract.m_exchange = "SMART";
			contract.m_localSymbol = "SPY";
			contract.m_secId = "NFLX";
			contract.m_secType = "STK";
			TwsOrder _twsOrder = null;
			_twsOrder = new TwsOrder();
			
			// new fields 
			_twsOrder.m_allOrNone = false;
			_twsOrder.m_tif = "DAY";
			
			_twsOrder.m_account = "DU222846";
			_twsOrder.m_action = "SELL";
			_twsOrder.m_orderType = "STP";
			_twsOrder.m_outsideRth = false;
			_twsOrder.m_orderId = wrapper.getNextOrderId();
			
			_twsOrder.m_transmit = true;
			_twsOrder.m_whatIf = false;
			_twsOrder.m_totalQuantity = 15;
			_twsOrder.m_lmtPrice = Double.MAX_VALUE;
			_twsOrder.m_auxPrice = 101.0;
			_twsOrder.m_orderId = 32345;
			
			socket.placeOrder(_twsOrder.m_orderId, contract, _twsOrder);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
