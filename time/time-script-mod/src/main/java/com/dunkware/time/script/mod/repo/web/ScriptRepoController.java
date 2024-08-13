package com.dunkware.time.script.mod.repo.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.time.script.mod.repo.ScriptRepo;
import com.dunkware.time.script.mod.repo.ScriptRepoService;
import com.dunkware.time.script.model.proto.XScriptDeployRep;
import com.dunkware.time.script.model.proto.XScriptDeployReq;
import com.dunkware.time.script.model.proto.XScriptSyncRep;
import com.dunkware.time.script.model.proto.XScriptSyncReq;
import com.dunkware.xstream.model.script.descriptor.XScriptDescriptor;
import com.dunkware.xstream.model.script.release.XScriptProblems;



@RestController
public class ScriptRepoController {
	
	private ScriptRepoService repoService; 
	
	public ScriptRepoController(ScriptRepoService repoService) {
		this.repoService = repoService;
	}
	
	@PostMapping(path = "/stream/script/deploy")
	public @ResponseBody XScriptDeployRep deployScript(@RequestBody XScriptDeployReq req) {
		XScriptDeployRep rep = new XScriptDeployRep();
		try {
			ScriptRepo script = repoService.createRepo(req.getScript(), req.getName(), req.getType());
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
	
	
	@PostMapping(path =  "/stream/script/sync")
	public @ResponseBody XScriptSyncRep syncScript(@RequestBody XScriptSyncReq req) { 
		XScriptSyncRep rep = new XScriptSyncRep(); 
		try {
			ScriptRepo script = repoService.getRepo(req.getScriptName());
			XScriptDescriptor model = script.getReleaseModel();
		
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	

}
