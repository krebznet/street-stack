package com.dunkware.trade.tick.service.server.feed;

import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.util.json.DJson;
import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.trade.tick.api.provider.TickProvider;
import com.dunkware.trade.tick.api.provider.TickProviderFactory;
import com.dunkware.trade.tick.model.provider.TickProviderSpec;
import com.dunkware.trade.tick.model.provider.TickProviderStatsSpec;
import com.dunkware.trade.tick.service.server.feed.repository.FeedProviderDO;
import com.dunkware.trade.tick.service.server.ticker.TickerService;

public class FeedServiceProvider {
	
	private TickProvider provider;
	
	private FeedProviderDO ent;
	
	@Autowired
	private TickerService tickerService; 
	
	@Autowired
	private Cluster cluster; 

	
	public void load(FeedProviderDO ent, FeedService service) throws Exception { 
		this.ent = ent; 
		TickProviderSpec type = DJson.getObjectMapper().readValue(ent.getJson(), TickProviderSpec.class);
		provider = TickProviderFactory.createProvider(type);
		provider.connect(type, service.getFeed(), cluster.getExecutor());
		
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
