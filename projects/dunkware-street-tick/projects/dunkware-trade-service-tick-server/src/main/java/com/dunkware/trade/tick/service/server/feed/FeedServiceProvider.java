package com.dunkware.trade.tick.service.server.feed;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.util.json.DJson;
import com.dunkware.spring.runtime.services.ExecutorService;
import com.dunkware.trade.tick.api.provider.TickProvider;
import com.dunkware.trade.tick.api.provider.TickProviderFactory;
import com.dunkware.trade.tick.model.provider.TickProviderSpec;
import com.dunkware.trade.tick.model.provider.TickProviderStatsSpec;
import com.dunkware.trade.tick.provider.atick.ActiveTickProvider;
import com.dunkware.trade.tick.service.server.feed.repository.FeedProviderDO;
import com.dunkware.trade.tick.service.server.ticker.TickerService;

public class FeedServiceProvider {
	
	private TickProvider provider;
	
	private FeedProviderDO ent;
	
	@Autowired
	private TickerService tickerService; 
	
	
	@Autowired
	private ExecutorService executorService; 

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public void load(FeedProviderDO ent, FeedService service) throws Exception { 
		this.ent = ent; 
		TickProviderSpec type = DJson.getObjectMapper().readValue(ent.getJson(), TickProviderSpec.class);
		provider = null;
		try {
			provider = TickProviderFactory.createProvider(type);	
		} catch (Exception e) {
			logger.error("what the fuck no provider found " + e.toString());
			provider = new ActiveTickProvider();
		}
		
		provider.connect(type, service.getFeed(), executorService.get());
		
	}
	
	public TickProvider getProvider() { 
		return provider;
	}
	
	
	public TickProviderStatsSpec getProviderStats() { 
		return provider.getStats();
	}
	
	public FeedProviderDO getEntity() { 
		return ent;
	}

}
