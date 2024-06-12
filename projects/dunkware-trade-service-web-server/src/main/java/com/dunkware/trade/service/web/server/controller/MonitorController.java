package com.dunkware.trade.service.web.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.utils.core.jvm.JvmStats;

@RestController
public class MonitorController {
	
	@GetMapping(path = "/health")
	public @ResponseBody() JvmStats jvmStats() {
		return JvmStats.newInstance();
	}

}
