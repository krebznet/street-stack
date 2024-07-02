package com.dunkware.time.script.mod.repo.impl;

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
import com.dunkware.time.script.mod.repo.persist.ScriptEntity;
import com.dunkware.time.script.mod.repo.persist.ScriptEntityRepo;
import com.dunkware.utils.core.events.DunkEventNode;
import com.dunkware.xstream.xproject.model.XScriptBundle;

import jakarta.annotation.PostConstruct;

@Service
public class ScriptServiceImpl implements ScriptService  {


	private ApplicationContext ac; 

	private EventService eventService; 

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private Map<String,Script> repos = new ConcurrentHashMap<String,Script>();

	private ScriptEntityRepo entityRepo; 
	
	private DunkEventNode eventNode; 
	
	
	public ScriptServiceImpl(ScriptEntityRepo repo, EventService service, ApplicationContext ac) { 
		this.entityRepo = repo;
		this.eventService = service; 
		this.ac = ac; 
		eventNode = eventService.getEventRoot().createChild(this);
	}
	
	@PostConstruct
	private void init() { 
		for (ScriptEntity entity : entityRepo.findAll()) {
			ScriptImpl scriptRepo = new ScriptImpl();
			ac.getAutowireCapableBeanFactory().autowireBean(scriptRepo);
			try {
				scriptRepo.init(entity);
				repos.put(scriptRepo.getName(), scriptRepo);
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
	public void archiveScript(String scriptId) throws Exception {
		Script script = getScript(scriptId);
		
			if(script.isArchieved() == true) { 
				throw new  Exception("Script " + scriptId  + " is already archived");
			}
			
		//	script.
		
	}

	@Override
	public Script createScript(XScriptBundle script, String repoName) throws Exception {
		if(scriptExists(repoName)) { 
			throw new Exception("Script name " + repoName + " already exists");
		}
		ScriptImpl scriptImpl = new ScriptImpl();
		ac.getAutowireCapableBeanFactory().autowireBean(script);
		
		
		
		
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Script getScript(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Script> getScripts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean scriptExists(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DunkEventNode getEventNode() {
		return eventNode; 
	}
	
	
	
	
	
	
	
	
}
