package com.dunkware.time.stream.mod.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.time.stream.model.admin.proto.StreamDeployRep;
import com.dunkware.time.stream.model.admin.proto.StreamDeployReq;

@RestController()
public class AdminController {

	
	public StreamDeployRep deployStream(@RequestBody() StreamDeployReq req) { 
		return null;
	}
	
	
	
	
	

	
}
