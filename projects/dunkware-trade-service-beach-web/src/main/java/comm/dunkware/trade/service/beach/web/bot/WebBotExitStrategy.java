package comm.dunkware.trade.service.beach.web.bot;

import java.util.ArrayList;
import java.util.List;

public class WebBotExitStrategy {
	
	private List<WebBotExitTrigger> triggers = new ArrayList<WebBotExitTrigger>();

	public List<WebBotExitTrigger> getTriggers() {
		return triggers;
	}

	public void setTriggers(List<WebBotExitTrigger> triggers) {
		this.triggers = triggers;
	}
	
	
	
	

}
