
package com.dunkware.trade.service.stream.serverd.server.controller.session.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.service.stream.serverd.controller.session.StreamSessionService;

@RestController
public class StreamSessionWebServiceImpl {


	@Autowired
	private StreamSessionService sessionService; 
	


}
