package comm.dunkware.trade.service.beach.web.examples;

import com.dunkware.common.util.json.DJson;

import comm.dunkware.trade.service.beach.web.bot.WebBot;
import comm.dunkware.trade.service.beach.web.bot.WebBotEntryStrategy;
import comm.dunkware.trade.service.beach.web.bot.WebBotEntryType;
import comm.dunkware.trade.service.beach.web.bot.WebBotExitStrategy;
import comm.dunkware.trade.service.beach.web.bot.WebBotExitTrigger;
import comm.dunkware.trade.service.beach.web.bot.WebBotExitTriggerType;
import comm.dunkware.trade.service.beach.web.bot.WebBotPlay;
import comm.dunkware.trade.service.beach.web.bot.WebBotPriceType;
import comm.dunkware.trade.service.beach.web.bot.WebBotTradeSide;

public class BotGenerator1 {

	public static void main(String[] args) {
		
		WebBot bot = new WebBot();
		bot.setAccount("Account1");
		bot.setActiveTradeLimit(1);
		bot.setAllocatedCapital(25000.0);
		bot.setEnabled(true);
		bot.setName("Trade Bot 1");
		bot.setTradeThrottle(30);
		
		// create some plays 
		
		WebBotPlay play1 = new WebBotPlay(); 
		play1.setName("Breakout Play");
		play1.setActiveSymbolLimit(5);
		play1.setCapital(3500.0);
		play1.setRank(1);
		play1.setRankEnabled(true);
		play1.setSymbolThrottle(10);
		play1.setActiveSymbolLimit(1);
		play1.setTradeSide(WebBotTradeSide.Long);
		
		// create a market entry strategy 
		
		WebBotEntryStrategy entry = new WebBotEntryStrategy();
		entry.setType(WebBotEntryType.Market); // set to market order type 
		entry.setTimeout(30); // set timeout to 30 seconds; 
		
		// set entry 
		play1.setEntryStrategy(entry);
		
		
		// create exit strategy 
		WebBotExitStrategy exit = new WebBotExitStrategy();
		
		// add a stop exit trigger
		WebBotExitTrigger stopTrigger = new WebBotExitTrigger(); 
		stopTrigger.setType(WebBotExitTriggerType.Stop); // sets the type to stop 
		stopTrigger.setStopPercent(3.0); // sets a stop at 3% loss 
		stopTrigger.setStopPriceType(WebBotPriceType.AskPrice); // evaluates the ask price to compute a 3% loss 
		
		// add the stop trigger 
		exit.getTriggers().add(stopTrigger);
		
		// create a timer trigger
		WebBotExitTrigger timerTrigger = new WebBotExitTrigger();
		timerTrigger.setType(WebBotExitTriggerType.Timer); // set the type to timer 
		timerTrigger.setTimer(30); // set the timer to 30 (seconds) 
		// add the second exit trigger to exit strategy 
		exit.getTriggers().add(timerTrigger);
		
		// set the exit on the trade play 
		play1.setExitStrategy(exit);
		
		// set the play1 on the bot 
		
		bot.getPlays().add(play1);
		
		try {
			System.out.println(DJson.serializePretty(bot));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}
}
