package com.dunkware.time.script.mod.repo.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.time.script.mod.repo.Script;
import com.dunkware.time.script.mod.repo.ScriptService;
import com.dunkware.time.script.model.proto.XScriptDeployRep;
import com.dunkware.time.script.model.proto.XScriptDeployReq;
import com.dunkware.xstream.model.script.model.XScriptProblems;



@RestController
public class ScriptRepoController {
	
	private ScriptService repoService; 
	
	public ScriptRepoController(ScriptService repoService) {
		this.repoService = repoService;
	}
	
	@PostMapping(path = "/stream/script/deploy")
	public @ResponseBody XScriptDeployRep deployScript(@RequestBody XScriptDeployReq req) {
		XScriptDeployRep rep = new XScriptDeployRep();
		try {
			Script script = repoService.createScript(req.getScript(), req.getName(), req.getType());
			rep.setError(false);
			rep.setModel(script.getReleaseModel());
			rep.setVersion(script.getReleaseModel().getVersion());
			return rep;
		} catch (Exception e) {
			rep.setError(true);
			XScriptProblems problems = XScriptProblems.instance();
			problems.addProblem(e.toString());
			rep.setProblems(problems);
			return rep;
		}
	}
	

}
