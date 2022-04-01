package com.dunkware.trade.service.stream.server.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dunkware.trade.service.stream.protocol.store.AddStoreReq;
import com.dunkware.trade.service.stream.protocol.store.AddStoreResp;

@Controller
@Profile("StreamStore")
public class StreamStoreWebService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private StreamStoreService service; 


	@PostMapping(path = "/store/add")
	public @ResponseBody()AddStoreResp addStore(@RequestBody() AddStoreReq rep) {
		AddStoreResp resp = new AddStoreResp();
		try {
			StreamStore store = service.createStore(rep.getConnection());	
			resp.setCode("SUCCESS");
			return resp;
		} catch (Exception e) {
			resp.setCode("ERROR");
			resp.setError(e.toString());
			return resp;
		}
		
		
	}
}
