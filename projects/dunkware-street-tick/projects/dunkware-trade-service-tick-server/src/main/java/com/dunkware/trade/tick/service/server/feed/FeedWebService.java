package com.dunkware.trade.tick.service.server.feed;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dunkware.trade.tick.api.consumer.TickConsumer;
import com.dunkware.trade.tick.model.consumer.TickConsumerSession;
import com.dunkware.trade.tick.model.feed.TickFeedStats;
import com.dunkware.trade.tick.model.feed.TickFeedSubscriptionBean;
import com.dunkware.trade.tick.service.protocol.feed.TickFeedStartReq;
import com.dunkware.trade.tick.service.protocol.feed.TickFeedStartResp;
import com.dunkware.trade.tick.service.protocol.provider.TickProviderAddReq;
import com.dunkware.trade.tick.service.protocol.provider.TickProviderAddResp;

import jakarta.annotation.PostConstruct;

@RestController
@Profile("FeedWebService")
public class FeedWebService {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

	@Autowired
	private FeedService tickService;

	@PostConstruct
	private void load() {
		logger.info("Starting Tick Feed Web Service");
	}

	@PostMapping(path = "/tick/provider/add")
	public @ResponseBody() TickProviderAddResp addProvider(@RequestBody() TickProviderAddReq request) {
		try {

			FeedServiceProvider provider = tickService.addServiceProvider(request.getSpec());
			TickProviderAddResp resp = new TickProviderAddResp();
			resp.setCode("SUCCESS");
			resp.setStatus(provider.getProviderStats().getName());
			return resp;
		} catch (Exception e) {
			TickProviderAddResp resp = new TickProviderAddResp();
			resp.setCode("ERROR");
			resp.setError(e.toString());
			return resp;

		}

	}

	@PostMapping(path = "/tick/feed/start")
	public @ResponseBody() TickFeedStartResp feedStart(@RequestBody() TickFeedStartReq request) {
		try {

			TickFeedStartResp resp = new TickFeedStartResp();
			TickConsumerSession session = tickService.createConsumer(request.getSpec());
			resp.setSession(session);
			resp.setCode("SUCCESS");
			logger.error("Exception tick/feedStats");
			return resp;
		} catch (Exception e) {
			TickFeedStartResp resp = new TickFeedStartResp();
			resp.setError(e.toString());
			resp.setCode("ERROR");
			return resp;
		}
	}

	@RequestMapping(path = "/tick/feed/ping")
	public void feedPing(@RequestParam(name = "id") String id) {
		TickConsumer consumer = tickService.getConsumer(id);
		if (consumer == null) {
			throw new ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST);
		}
		consumer.clientHeartbeat();
	}

	@RequestMapping(path = "/tick/feed/stop")
	public void feedStop(@RequestParam(name = "id") String id) {
		TickConsumer consumer = tickService.getConsumer(id);
		if (consumer == null) {
			throw new ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST);
		}
		tickService.disposeConsumer(id);

	}

	@RequestMapping(path = "/feed/subscription")
	public @ResponseBody() TickFeedSubscriptionBean getFeedSubscription(@RequestParam() String symbol) {
		try {
			return tickService.getFeed().getSubscription(symbol).getBean();
		} catch (Exception e) {
			TickFeedSubscriptionBean bean = new TickFeedSubscriptionBean();
			bean.setSymbol(symbol + " not found");
			return bean;
		}

	}

	@RequestMapping(path = "/feed/subscriptions")
	public @ResponseBody() List<TickFeedSubscriptionBean> getFeedSubscriptions() {
		return tickService.getFeed().getSubscriptionBeans();

	}

	@GetMapping(path = "/feed/stats")
	public @ResponseBody TickFeedStats getInValidatedSubscriptions() {
		return tickService.getFeed().getStats();
	}

}
