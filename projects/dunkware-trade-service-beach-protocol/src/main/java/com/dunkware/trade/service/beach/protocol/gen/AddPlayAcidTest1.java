package com.dunkware.trade.service.beach.protocol.gen;

import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.beach.protocol.play.AddPlayReq;
import com.dunkware.trade.service.beach.protocol.play.Play;
import com.dunkware.trade.service.beach.protocol.play.PlayExitTrigger;
import com.dunkware.trade.service.beach.protocol.play.PlayExitTriggerType;
import com.dunkware.trade.service.beach.protocol.play.PlayOrderType;

public class AddPlayAcidTest1 {

	public static void main(String[] args) {
		Play play = new Play();
		play.setName("Test1");
		play.setEnableActiveSymbolLimit(false);
		play.setEnableActiveTradeLimit(false);
		play.setEnableStopLoss(false);
		play.setEntryType(PlayOrderType.MARKET);
		play.setEntryTimeout(-1);
		play.setEnableEntryTimeout(false);
		play.setSignal("SPY");
		play.setSide("LONG");
		play.setAllocatedCapital(3000);
		PlayExitTrigger timerTrigger = new PlayExitTrigger();
		timerTrigger.setType(PlayExitTriggerType.TIMER);
		timerTrigger.setTimer(30);
		play.getExitTriggers().add(timerTrigger);
		play.setExitType(PlayOrderType.MARKET);
		play.setTradeAllocation(4000);
		AddPlayReq req = new AddPlayReq();
		req.setAccountId(1);
		req.setPlay(play);
		try {
			System.out.println(DJson.serializePretty(req));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
