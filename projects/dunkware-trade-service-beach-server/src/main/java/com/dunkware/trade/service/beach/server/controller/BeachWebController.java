package com.dunkware.trade.service.beach.server.controller;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.dunkware.common.util.datagrid.DataGridUpdate;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.controller.mock.MockBrokerEventList;
import com.dunkware.trade.service.beach.server.controller.mock.MockTradeEventList;
import com.dunkware.trade.service.beach.server.runtime.BeachService;

@CrossOrigin()
@RestController
public class BeachWebController {

	@Autowired
	private ApplicationContext ac;

	@Autowired
	private BeachService beachService;

	@Autowired
	private BeachRuntime runtime;

	private Logger logger = LoggerFactory.getLogger(getClass());

	


}
