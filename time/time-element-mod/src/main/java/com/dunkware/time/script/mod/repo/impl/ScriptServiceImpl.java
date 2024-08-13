package com.dunkware.time.script.mod.repo.impl;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.spring.runtime.services.EventService;
import com.dunkware.time.script.mod.repo.Script;
import com.dunkware.time.script.mod.repo.ScriptService;
import com.dunkware.time.script.mod.repo.entity.ScriptEntity;
import com.dunkware.time.script.mod.repo.entity.ScriptReleaseEntity;
import com.dunkware.time.script.mod.repo.repository.ScriptReleaseRepository;
import com.dunkware.time.script.mod.repo.repository.ScriptRepository;
import com.dunkware.utils.core.events.DunkEventNode;
import com.dunkware.utils.core.json.DunkJson;
import com.dunkware.xstream.model.script.model.XScriptModel;
import com.dunkware.xstream.model.script.model.XScriptRelease;
import com.dunkware.xstream.model.script.model.XScriptUpdate;
import com.dunkware.xstream.model.script.utils.XScriptUpdateInitializer;
import com.dunkware.xstream.script.XScriptModelInitializer;
import com.dunkware.xstream.xproject.XScriptProject;
import com.dunkware.xstream.xproject.bundle.XscriptBundleHelper;
import com.dunkware.xstream.xproject.model.XScriptBundle;

import jakarta.annotation.PostConstruct;

@Service
public class ScriptServiceImpl implements ScriptService  {


	private ApplicationContext ac; 

	private EventService eventService; 

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private Map<String,Script> scripts = new ConcurrentHashMap<String,Script>();

	private ScriptRepository scriptRepository;
	
	private ScriptReleaseRepository scriptReleaseRepository;
	
	private DunkEventNode eventNode; 
	
	
	public ScriptServiceImpl(ScriptRepository repo, ScriptReleaseRepository relRepo, EventService service, ApplicationContext ac) { 
		this.scriptRepository = repo;
		this.scriptReleaseRepository = relRepo;
		this.eventService = service; 
		this.ac = ac; 
		eventNode = eventService.getEventRoot().createChild(this);
	}
	
	@PostConstruct
	private void init() { 
		for (ScriptEntity entity : scriptRepository.findAll()) {
			ScriptImpl script = new ScriptImpl();
			ac.getAutowireCapableBeanFactory().autowireBean(script);
			try {
				script.init(entity);
				scripts.put(script.getName(), script);
			} catch (Exception e) {
				logger.error("Fatal exception script repo failed to init " + e.toString());
				System.exit(-1);
			}
		}
	}
	
	

	
	/**
	 * Okay interesting
	 */
	@Override
	public void deleteScript(String scriptId) throws Exception {
		Script script = getScript(scriptId);
		
	}
	


	@Override
	public boolean hasScript(String name) {
		if(scripts.containsKey(name)) {
			return true;
		}
		return false;
	}

	@Override
	public Script createScript(String script, String repoName, String type) throws Exception {
		if(hasScript(repoName)) { 
			throw new Exception("Script name " + repoName + " already exists");
		}
		
		XScriptBundle bundle = XscriptBundleHelper.createBundleFromFileContents(script);
		XScriptProject project = null;
		try {
			project = new XScriptProject(bundle);
		} catch (Exception e) {
			throw new Exception("Script Parse Exception " + e.toString());
		}
		// new revision 
		ScriptEntity scriptEnt = new ScriptEntity();
		scriptEnt.setActive(true);
		scriptEnt.setCreated(LocalDateTime.now());
		scriptEnt.setName(repoName);
		scriptEnt.setUpdated(LocalDateTime.now());
		scriptEnt.setType(type);
		scriptEnt.setVersion("1.0.0");
		
		XScriptModel model = XScriptModelInitializer.generate(script, repoName, type, "1.0.0");
		XScriptUpdate update = XScriptUpdateInitializer.intialize(model);
		XScriptRelease relModel = new XScriptRelease();
		relModel.setVersion("1.0.0");
		relModel.setModel(model);
		relModel.setScript(script);
		relModel.setType(type);
		relModel.setTimestamp(LocalDateTime.now());
		relModel.setUpdate(update);
		relModel.setName(model.getName());
		
		ScriptReleaseEntity relEnt = new ScriptReleaseEntity();
		relEnt.setReleaseTimestamp(LocalDateTime.now());
		relEnt.setScript(scriptEnt);
		relEnt.setScriptName(repoName);
		relEnt.setReleaseModel(DunkJson.serialize(relModel));
		relEnt.setVersion("1.0.0");
		relEnt.setXscript(script);
		
		try {
			
		} catch (Exception e) {
			
		}
		
		
		ScriptImpl scriptImpl = new ScriptImpl();
		ac.getAutowireCapableBeanFactory().autowireBean(script);
		
		
		
		
		
		
		return null;
	}

	@Override
	public Script getScript(String name) throws Exception {
		if(scripts.containsKey(name) == false) { 
			Exception e = new Exception("Script " + name + " not found");
			throw e;
		}
		return scripts.get(name);
	}

	@Override
	public Collection<Script> getScripts() {
		return scripts.values();
	}

	

	@Override
	public DunkEventNode getEventNode() {
		return eventNode; 
	}
	
	
	
	
	
	
	
	
}
