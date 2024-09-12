package com.dunkware.time.stream.mod.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.time.stream.mod.stream.IStreamService;
import com.dunkware.time.stream.model.rest.DeployStreamRep;
import com.dunkware.time.stream.model.rest.DeployStreamReq;

@RestController(value = "/stream/api/admin")
public class IStreamAdminController {

	private IStreamService service;
	
	public IStreamAdminController(IStreamService service) { 
		this.service = service; 
	}
	
	public @ResponseBody DeployStreamRep deployStream(@RequestBody() DeployStreamReq req) { 
		return null;
	}
	
	
	
	
	
	
	

	
}
