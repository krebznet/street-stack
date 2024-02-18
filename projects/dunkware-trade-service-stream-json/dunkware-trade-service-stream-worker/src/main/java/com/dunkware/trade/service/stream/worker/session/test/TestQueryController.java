package com.dunkware.trade.service.stream.worker.session.test;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.common.util.json.DJson;
import com.dunkware.xstream.model.stats.proto.EntityStatBulkReq;
import com.dunkware.xstream.model.stats.proto.EntityStatBulkResp;
import com.dunkware.xstream.model.stats.proto.EntityStatReqType;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

@RestController
public class TestQueryController {
	
	@Autowired
	private TestQueryService service; 
	
	@GetMapping(path = "/builder/test/1")
	public void test1() { 
		try {
			service.test1();
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}


	@GetMapping(path = "/builder/test/2")
	public void test2() { 
			
			
		service.test2();
	}
}
