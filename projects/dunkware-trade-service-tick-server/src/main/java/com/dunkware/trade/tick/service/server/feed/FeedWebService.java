package com.dunkware.trade.tick.service.server.feed;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dunkware.trade.tick.service.protocol.feed.TickFeedStartReq;
import com.dunkware.trade.tick.service.protocol.feed.TickFeedStartResp;
import com.dunkware.trade.tick.service.protocol.feed.TickFeedUpdateReq;
import com.dunkware.trade.tick.service.protocol.feed.TickFeedUpdateResp;
import com.dunkware.trade.tick.service.protocol.provider.TickProviderAddReq;
import com.dunkware.trade.tick.service.protocol.provider.TickProviderAddResp;
/**
 * /provider/add 
 * /feed/start
 * /feed/ping
 * /feed/update
 * @author dkrebs
 *
 */
@RestController
@Profile("FeedWebService")
public class FeedWebService {
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
	
	@Autowired
	private FeedService tickService;

	@PostConstruct
	private void load() { 
		logger.info("Starting FeedWebService");
	}
	
	@PostMapping(path = "/tick/provider/add")
	public @ResponseBody()TickProviderAddResp addProvider(@RequestBody()TickProviderAddReq request) { 
		try {
			
			FeedServiceProvider provider = tickService.addServiceProvider(request.getSpec());
			TickProviderAddResp resp = new TickProviderAddResp();
			resp.setCode("SUCCESS");
			resp.setStatus(provider.getProviderStatus().name());
			return resp;
		} catch (Exception e) {
			TickProviderAddResp resp = new TickProviderAddResp();
			resp.setCode("ERROR");
			resp.setError(e.toString());
			return resp;
			
		}
		
	}
	
	@PostMapping(path = "/tick/feed/start")
	public @ResponseBody()TickFeedStartResp feedStart(@RequestBody()TickFeedStartReq request) { 
		try {
			
			TickFeedStartResp resp = new TickFeedStartResp();
			FeedStream stream = tickService.startFeed(request.getSpec());
			resp.setBrokers(stream.getKafkaBrokers());
			resp.setId(stream.getId());
			resp.setTopic(stream.getKafkaTopic());
			resp.setCode("SUCCESS");
			return resp;
		} catch (Exception e) {
			TickFeedStartResp resp = new TickFeedStartResp();
			resp.setError(e.toString());
			resp.setCode("ERROR");
			return resp;
		}
	}
	
	@RequestMapping(path = "/tick/feed/ping")
	public void feedPing(@RequestParam(name = "id")String id) { 
		FeedStream stream = tickService.getStream(id);
		if(stream == null) { 
			throw new ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST);
		}
		stream.ping();
	}

	@PostMapping(path = "/tick/feed/update")
	public @ResponseBody()TickFeedUpdateResp feedUpdate(@RequestBody()TickFeedUpdateReq request) { 
		FeedStream stream = tickService.getStream(request.getId());
		if(stream == null) { 
			TickFeedUpdateResp resp = new TickFeedUpdateResp();
			resp.setCode("ERROR");
			resp.setError("Feed Stream " + request.getId() + " not found in ticker service, was it disposed ?");
			return resp;
		}
		try {
			stream.update(request.getSpec());	
		} catch (Exception e) {
			TickFeedUpdateResp resp = new TickFeedUpdateResp();
			resp.setCode("ERROR");
			resp.setError("Tick Feed Update Exeption " + e.toString());
			return resp;
		}
		TickFeedUpdateResp resp = new TickFeedUpdateResp();
		resp.setCode("SUCCESS");
		return resp;
	}
	



}
