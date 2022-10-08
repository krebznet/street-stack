package comm.dunkware.trade.service.beach.web.model;

import com.dunkware.common.util.json.DJson;

public class BeachWebMain {

	public static void main(String[] args) {
		BeachWebAccount act = new  BeachWebAccount();
		act.setActiveCapital(30.00);// Currency Format
		act.setActiveOrders(2); // Number no formatting 
		act.setActiveSystems(1); // Number no formatting
		act.setActiveTrades(4); // Number no formatting
		act.setCapitalTraded(23323.23); // Currency 
		act.setGainLossAmount(2323); // Currency Format 
		act.setGainLossPercent(23.3); // Precent Format 
		act.setId(3); // Unique ID, not displayed 
		act.setName("DLK Paper"); // Text Formatting 
		act.setStatus("Connected"); // Text Formatting if "Disconnected" make it red? 
		act.setTradeCount(5); // Number formatting. 
		try {
		//	System.out.println(DJson.serializePretty(act));
		} catch (Exception e) {
			e.printStackTrace();
		}
		BeachWebOrder order = new BeachWebOrder(); 
		order.setAccount("DLK Paper");
		order.setCommission(2.23); // Currency Format
		order.setFilled(3); // Number no format 
		order.setOrderId(232);// number no format 
		order.setPending(3); // number no format 
		order.setSide("LONG"); // text 
		order.setSize(32); // number no format 
		order.setStatus("OPEN"); 
		order.setStopPrice(23.23); // currency format 
		
		BeachWebSystem sys = new BeachWebSystem();
		sys.setAcccount("DLK Paper");
		sys.setActiveCapital(2323.23); // Currency Formatting 
		sys.setActiveOrders(3); // NUmber no formatting 
		sys.setActiveTrades(4); //Number no formatting 
		sys.setCapitalTraded(2343.34); // Currency format 
		sys.setGainLossAmount(343.34); // Currency format, red if less than 0, green otherwise. 
		sys.setGainLossPercent(3.23); // Currency format, redit if less than 0, green otherwise 
		sys.setId(4); // Currency format 
		sys.setName("System 1"); // text no formatting 
		sys.setStatus("RUNNING"); // text no formatting 
		sys.setTradeCount(42); // number no formatting
		
		
		try {
			System.out.println(DJson.serializePretty(sys));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
