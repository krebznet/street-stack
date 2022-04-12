package com.dunkware.trade.tick.service.server.monitor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.tick.model.provider.TickProviderStatsSpec;
import com.dunkware.trade.tick.service.protocol.service.spec.TickServiceStatusSpec;
import com.dunkware.trade.tick.service.server.feed.FeedService;
import com.dunkware.trade.tick.service.server.feed.FeedServiceProvider;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TickMonitorWebService {

	@Autowired
	private TickMonitorService statusService; 
	
	
	@Autowired
	private FeedService feedService; 
	
	@RequestMapping(path = "/monitor/status")
	public @ResponseBody TickServiceStatusSpec getStatus() { 
		TickServiceStatusSpec spec = new TickServiceStatusSpec();
		List<FeedServiceProvider> providers = feedService.getProviders();
		for (FeedServiceProvider feedServiceProvider : providers) {
			TickProviderStatsSpec providerSpec = new TickProviderStatsSpec();
			providerSpec.setStatus(feedServiceProvider.getProviderStatus());
			providerSpec.setName(feedServiceProvider.getEntity().getIdentifier());
			spec.getProviders().add(providerSpec);
		}
		
		spec.setAvailable(true);
		
		return spec;
	}
}
