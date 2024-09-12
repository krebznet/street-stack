package com.dunkware.trade.tick.service.server.ticker;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.trade.tick.model.ticker.TradeTickerType;
import com.dunkware.trade.tick.service.protocol.ticker.TSTickerListGetReq;
import com.dunkware.trade.tick.service.protocol.ticker.TickerListAddReq;
import com.dunkware.trade.tick.service.protocol.ticker.TickerListAddResp;
import com.dunkware.trade.tick.service.protocol.ticker.TickerListGetResp;
import com.dunkware.trade.tick.service.server.ticker.repsoitory.TickerDO;
import com.dunkware.trade.tick.service.server.ticker.repsoitory.TickerListDO;

@Controller
@Profile("TickerWebService")

public class TickerWebService {

	@Autowired
	private TickerService service;
	
	private Logger logger = LoggerFactory.getLogger(getClass().getSimpleName())
;

	@GetMapping(path = "/ticker/echo")
	public @ResponseBody String echo(@RequestParam String name) { 
		return name;
	}
	@PostMapping(path = "/ticker/list/add")
	public @ResponseBody() TickerListAddResp addTickerList(@RequestBody() TickerListAddReq req) {
		try {
			TickerListAddResp resp = new TickerListAddResp();
			TickerListDO response = service.insertList(req.getName(), req.getQuery(),req.isOverride());
			resp.setSize(response.getSize());
			resp.setList(TickerListDO.toTickerList(response));
			resp.setCode("SUCCESS");
			return resp;
		} catch (Exception e) {
			TickerListAddResp resp = new TickerListAddResp();
			resp.setCode("ERROR");
			resp.setError(e.toString());
			return resp;
		}
	}

	
	@GetMapping(path = "/ticker/symbol/get")
	public TradeTickerSpec getTickerBySymbol(@RequestParam String symbol) { 
		try {
			TickerDO ticker  = service.findTIckerBySymbol(symbol);
			if(ticker == null) { 
				TradeTickerSpec spec = new TradeTickerSpec();
				spec.setId(-1);
				spec.setName(null);
				spec.setType(TradeTickerType.Equity);
				return spec;
			} else { 
				TradeTickerSpec spec = new TradeTickerSpec();
				spec.setId(ticker.getId().intValue());
				spec.setName(ticker.getSecurityName());
				spec.setSymbol(ticker.getSymbol());
				spec.setType(TradeTickerType.Equity);
				return spec;
			}
		} catch (Exception e) {
			TradeTickerSpec spec = new TradeTickerSpec();
			spec.setId(-1);
			return spec;
		}
	}


	@PostMapping(path = "/ticker/list/get")
	public @ResponseBody() TickerListGetResp deleteTickerList(@RequestBody() TSTickerListGetReq req) {
		if(logger.isDebugEnabled()) { 
			logger.info("REST REQ REQ {} List {} ","/ticker/list/get",req.getName());
		}
		TickerListGetResp resp  = new TickerListGetResp();
		
		try {
			try {
				
				List<TickerListDO> lists = service.findListByName(req.getName());
				if(lists.size() == 0) { 
					resp.setCode("ERROR");
					resp.setError("Ticker List " + req.getName() + " Not Found in DB");
					return resp;
				}	
				// return the first list 
				resp.setList(TickerListDO.toTickerList(lists.get(0)));
				resp.setCode("SUCCESS");
				
				if(logger.isDebugEnabled()) { 
					logger.debug("Returning TickerList API {} Size {}",req.getName(),resp.getList().getSize());
				}
				
			} catch (Exception e) {
				logger.error("Exception eturning TickerList API {} Exception {}",req.getName(),e.toString());
			}
			
			
			return resp;
		} catch (Exception e) {
			resp.setCode("ERROR");
			resp.setError(e.toString());
			return resp;
		}
	

	}
	
	@GetMapping(path = "/ticker/symbol/id")
	public @ResponseBody() Integer getSymbolId(@RequestParam String symbol) { 
		return service.getSymbolId(symbol);
	}
}
