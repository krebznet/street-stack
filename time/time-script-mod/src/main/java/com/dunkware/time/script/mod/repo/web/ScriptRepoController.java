package com.dunkware.time.script.mod.repo.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.time.script.mod.repo.ScriptService;
import com.dunkware.time.script.model.proto.ScriptDeployRep;
import com.dunkware.time.script.model.proto.ScriptDeployReq;



@RestController
public class ScriptRepoController {
	
	private ScriptService repoService; 
	
	public ScriptRepoController(ScriptService repoService) {
		this.repoService = repoService;
	}
	
	@PostMapping(path = "/stream/script/deploy")
	public @ResponseBody ScriptDeployRep deployScript(@RequestBody ScriptDeployReq req) {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	

}
