package com.dunkware.trade.tick.service.server.feed;

import java.util.HashMap;
import java.util.Map;

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
	
	public static void main(String[] args) {
		TickProviderSpec spec = new TickProviderSpec();
		spec.setName("Polygon");
		spec.setType("Polygon");
		Map<String,Object> props = new HashMap<String,Object>();
		props.put("apiKey", "n95x4f7AK_Am6AZ9qNSxcn9u4obpKsMA");
		spec.setProperties(props);
		try {
			String out = DJson.serialize(spec);
			System.out.println(out);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public void load(FeedProviderDO ent) throws Exception { 
		this.ent = ent; 
		TickProviderSpec type = DJson.getObjectMapper().readValue(ent.getJson(), TickProviderSpec.class);
		provider = TickProviderFactory.createProvider(type);
		provider.connect(type,tickerService,cluster.getExecutor());
		
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
