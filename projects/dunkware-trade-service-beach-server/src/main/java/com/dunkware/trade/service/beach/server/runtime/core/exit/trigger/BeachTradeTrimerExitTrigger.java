package com.dunkware.trade.service.beach.server.runtime.core.exit.trigger;

import com.dunkware.trade.service.beach.protocol.play.PlayExitTrigger;
import com.dunkware.trade.service.beach.server.runtime.BeachTrade;
import com.dunkware.trade.service.beach.server.runtime.core.BeachTradeExitTrigger;

public class BeachTradeTrimerExitTrigger implements BeachTradeExitTrigger {

	private BeachTrade trade;
	private PlayExitTrigger model;
	
	private Timer timer; 
	
	@Override
	public void init(BeachTrade trade, PlayExitTrigger model) throws Exception {
		this.model = model;
		this.trade = trade;
		if(model.getTimer() == 0 || model.getTimer() < 0) { 
			throw new Exception("Timer Exit Trigger cannot be " + model.getTimer());
		}	
	}

	@Override
	public void dispose() {
		timer.interrupt();
	}

	@Override
	public void start() {
		timer = new Timer();
		timer.start();
	}
	
	private class Timer extends Thread { 
		
		public void run() { 
			try {
				int seconds = 0;
				while(!interrupted()) { 
					Thread.sleep(1000);
					seconds++;
					if(seconds == model.getTimer()) { 
						trade.close("Timer " + model.getTimer() + " Seconds");
						return;
					}
				}
			} catch (Exception e) {
				if (e instanceof InterruptedException) {
					return;
				}
				// else what 
			} 
		}
		
	}

}
