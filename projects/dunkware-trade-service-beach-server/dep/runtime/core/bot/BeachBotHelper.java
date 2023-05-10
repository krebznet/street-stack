package com.dunkware.trade.service.beach.server.runtime.core.bot;

import com.dunkware.trade.sdk.core.model.trade.EntryType;
import com.dunkware.trade.sdk.core.model.trade.ExitType;
import com.dunkware.trade.sdk.lib.model.constants.PriceType;
import com.dunkware.trade.sdk.lib.model.entry.LimitEntryType;
import com.dunkware.trade.sdk.lib.model.entry.MarketEntryType;
import com.dunkware.trade.sdk.lib.model.exit.SmartExitType;
import com.dunkware.trade.sdk.lib.model.exit.rules.SmartExitTimer;
import com.dunkware.trade.sdk.lib.model.exit.rules.SmartExitTrailingStop;

import comm.dunkware.trade.service.beach.web.bot.WebBotEntryStrategy;
import comm.dunkware.trade.service.beach.web.bot.WebBotEntryType;
import comm.dunkware.trade.service.beach.web.bot.WebBotExitStrategy;
import comm.dunkware.trade.service.beach.web.bot.WebBotExitTrigger;
import comm.dunkware.trade.service.beach.web.bot.WebBotExitTriggerType;
import comm.dunkware.trade.service.beach.web.bot.WebBotPriceType;

public class BeachBotHelper {
	
	
	public static ExitType toExitType(WebBotExitStrategy exit) throws Exception { 
		SmartExitType type = new SmartExitType();
		for (WebBotExitTrigger trigger : exit.getTriggers()) {
			if(trigger.getType() == WebBotExitTriggerType.Timer) { 
				SmartExitTimer timer = new SmartExitTimer();
				timer.setTimer(trigger.getTimer().intValue());
				type.getRules().add(timer);
				continue;
			}
			if(trigger.getType() == WebBotExitTriggerType.Stop) { 
				SmartExitTrailingStop trailing = new SmartExitTrailingStop();
				trailing.setStop(trigger.getStopPercent().doubleValue());
				trailing.setTrail(trigger.getTrailingPercent().doubleValue());
				trailing.setTarget(toPriceType(trigger.getStopPriceType()));
				type.getRules().add(trailing);
				continue;
			}
			throw new Exception("Exit Strategy Trigger " + trigger.getType().toString() + " not handled ");
			
		}
		return type;
	}
	
	public static EntryType toEntryType(WebBotEntryStrategy entryStrategy) throws Exception { 
		if(entryStrategy.getType() == WebBotEntryType.Market) { 
			MarketEntryType entryType = new MarketEntryType();
			if(entryStrategy.getTimeout() != null) { 
				entryType.setTimeout(entryStrategy.getTimeout().intValue());
			}
			return entryType;
		}
		if(entryStrategy.getType() == WebBotEntryType.Limit) { 
			LimitEntryType entryType = new LimitEntryType();
			entryType.setTarget(toPriceType(entryStrategy.getPriceType()));
			if(entryStrategy.getTimeout() != null) { 
				entryType.setTimeout(entryStrategy.getTimeout().intValue());
			}
			entryType.setOffset(entryStrategy.getLimitPercent().doubleValue());
			return entryType;
		}
		// TODO: limit change 
		throw new Exception("entry strategy type not handled " + entryStrategy.getType());
		
	}
	
	
	public static PriceType toPriceType(WebBotPriceType wt) { 
		if(wt == WebBotPriceType.AskPrice) { 
			return PriceType.AskPrice;
		}
		if(wt == WebBotPriceType.BidPrice) { 
			return PriceType.BidPrice;
		}
		if(wt == WebBotPriceType.LastPrice) {
			return PriceType.LastPrice;
		}
		return PriceType.MidPrice;
	}

}
