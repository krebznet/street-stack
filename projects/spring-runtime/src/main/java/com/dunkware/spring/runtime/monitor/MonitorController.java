package com.dunkware.spring.runtime.monitor;

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.utils.core.jvm.JvmStats;

@RestController
public class MonitorController {
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("Monitor");
	
	@GetMapping(path = "/health")
	public @ResponseBody() JvmStats jvmStats() {
		logger.info(marker, "Health Output " + JvmStats.newInstance().toString());
		return JvmStats.newInstance();
	}

}
