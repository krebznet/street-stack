package com.dunkware.trade.service.beach.server.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.dunkware.spring.streaming.StreamingAdapter;
import com.dunkware.trade.service.beach.server.controller.stream.BeachBrokersStream;
import com.dunkware.trade.service.beach.server.runtime.BeachAccount;
import com.dunkware.trade.service.beach.server.runtime.BeachAccountBean;
import com.dunkware.trade.service.beach.server.runtime.BeachBroker;
import com.dunkware.trade.service.beach.server.runtime.BeachBrokerBean;
import com.dunkware.trade.service.beach.server.runtime.BeachPlayBean;
import com.dunkware.trade.service.beach.server.runtime.BeachService;
import com.dunkware.trade.service.beach.server.runtime.BeachTradeBean;

@RestController
public class BeachWebController {

	@Autowired
	private ApplicationContext ac;

	@Autowired
	private BeachService beachService;

	@GetMapping(path = "/trade/web/brokers/stream")
	public ResponseEntity<StreamingResponseBody> brokersStream() throws Exception {
		StreamingAdapter adapter = new StreamingAdapter("Brokers");
		BeachBrokersStream stream = new BeachBrokersStream();
		ac.getAutowireCapableBeanFactory().autowireBean(stream);
		stream.start(adapter);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(adapter);
	}

	@GetMapping(path = "/trade/web/brokers")
	public @ResponseBody() List<BeachBrokerBean> getBrokers() throws Exception {
		List<BeachBrokerBean> beans = new ArrayList<BeachBrokerBean>();
		for (BeachBroker broker : beachService.getBrokers()) {
			beans.add(broker.getBean());
		}
		return beans;
	}

	@GetMapping(path = "/trade/web/accounts")
	public @ResponseBody() List<BeachAccountBean> getAccounts() throws Exception {
		List<BeachAccountBean> beans = new ArrayList<BeachAccountBean>();
		for (BeachBroker broker : beachService.getBrokers()) {
			for (BeachAccount beachAccountBean : broker.getAccounts()) {
				beans.add(beachAccountBean.getBean());
			}
		}
		return beans;
	}

	@GetMapping(path = "/trade/web/accounts/stream")
	public ResponseEntity<StreamingResponseBody> accountsStream() throws Exception {
		return null;
	}

	@GetMapping(path = "/trade/web/play/stats")
	public @ResponseBody() BeachPlayBean playStats(@RequestParam() long id) throws Exception {
		return beachService.getPlay(id).getBean();
	}

	@GetMapping(path = "/trade/web/play/stats/stream")
	public ResponseEntity<StreamingResponseBody> playStatsStream() throws Exception {
		return null;

	}

	@GetMapping(path = "/trade/web/play/trades")
	public @ResponseBody() List<BeachTradeBean> playTrades(@RequestParam() long id) throws Exception {
		return null;
		//return beachService.getPlay(id).getBean();
	}

	@GetMapping(path = "/trade/web/play/trades/stream")
	public ResponseEntity<StreamingResponseBody> playTradesStream() throws Exception {
		return null;
	}

	@GetMapping(path = "/trade/web/play/orders/stream")
	public ResponseEntity<StreamingResponseBody> playOrdersStream() throws Exception {
		return null;
	}
}
