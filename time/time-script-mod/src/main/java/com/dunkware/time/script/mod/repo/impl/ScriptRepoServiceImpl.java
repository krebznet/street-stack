package com.dunkware.time.script.mod.repo.impl;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.spring.runtime.services.EventService;
import com.dunkware.time.script.mod.repo.ScriptRepo;
import com.dunkware.time.script.mod.repo.ScriptRepoService;
import com.dunkware.time.script.mod.repo.entity.DBScriptRepo;
import com.dunkware.time.script.mod.repo.entity.DBScriptRepoRelease;
import com.dunkware.time.script.mod.repo.repository.DBScriptRepoReleaseRepo;
import com.dunkware.time.script.mod.repo.repository.DBScriptRepoRepo;
import com.dunkware.utils.core.event.EventNode;
import com.dunkware.utils.core.json.DunkJson;
import com.dunkware.xstream.model.script.descriptor.XScriptDescriptor;
import com.dunkware.xstream.model.script.release.XScriptRelease;
import com.dunkware.xstream.model.script.release.XScriptUpdate;
import com.dunkware.xstream.model.script.utils.XScriptUpdateInitializer;
import com.dunkware.xstream.script.XScriptModelInitializer;
import com.dunkware.xstream.xproject.XScriptProject;
import com.dunkware.xstream.xproject.bundle.XscriptBundleHelper;
import com.dunkware.xstream.xproject.model.XScriptBundle;

@Service
public class ScriptRepoServiceImpl implements ScriptRepoService  {


	@Autowired
	private ApplicationContext ac; 

	@Autowired
	private  AutowireCapableBeanFactory autowireCapableBeanFactory;
	
	private EventService eventService; 

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private Map<String,ScriptRepo> scripts = new ConcurrentHashMap<String,ScriptRepo>();

	private DBScriptRepoRepo scriptRepository;
	
	private DBScriptRepoReleaseRepo scriptReleaseRepository;
	
	private EventNode eventNode; 
	
	
	public ScriptRepoServiceImpl(DBScriptRepoRepo repo, DBScriptRepoReleaseRepo relRepo, EventService service, ApplicationContext ac) { 
		this.scriptRepository = repo;
		this.scriptReleaseRepository = relRepo;
		this.eventService = service; 
		this.ac = ac; 
		eventNode = eventService.getEventRoot().createChild(this);
	}
	
	//@PostConstruct
	private void init() { 
		for (DBScriptRepo entity : scriptRepository.findAll()) {
			ScriptRepoImpl script = new ScriptRepoImpl();
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
	public void deleteRepo(String scriptId) throws Exception {
		ScriptRepo script = getRepo(scriptId);
		
	}
	


	@Override
	public boolean repoExists(String name) {
		if(scripts.containsKey(name)) {
			return true;
		}
		return false;
	}

	@Override
	public ScriptRepo createRepo(String script, String repoName, String type) throws Exception {
		if(repoExists(repoName)) { 
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
		DBScriptRepo scriptEnt = new DBScriptRepo();
		scriptEnt.setActive(true);
		scriptEnt.setCreated(LocalDateTime.now());
		scriptEnt.setName(repoName);
		scriptEnt.setUpdated(LocalDateTime.now());
		scriptEnt.setType(type);
		scriptEnt.setVersion("1.0.0");
		
		XScriptDescriptor model = XScriptModelInitializer.generate(script, repoName, type, "1.0.0");
		XScriptUpdate update = XScriptUpdateInitializer.intialize(model);
		XScriptRelease relModel = new XScriptRelease();
		relModel.setVersion("1.0.0");
		relModel.setModel(model);
		relModel.setScript(script);
		relModel.setType(type);
		relModel.setTimestamp(LocalDateTime.now());
		relModel.setUpdate(update);
		relModel.setName(model.getName());
		
		DBScriptRepoRelease relEnt = new DBScriptRepoRelease();
		relEnt.setTimestamp(LocalDateTime.now());
		relEnt.setRepo(scriptEnt);
		relEnt.setScriptName(repoName);
		relEnt.setMetadata(DunkJson.serialize(relModel));
		relEnt.setVersion("1.0.0");
		relEnt.setSource(script);
		scriptEnt.getReleases().add(relEnt);
		try {
			scriptRepository.save(scriptEnt);
			scriptReleaseRepository.save(relEnt);
		} catch (Exception e) {
			throw new Exception("Exception persisting script entities " + e.toString());
		}
		
		ScriptRepoImpl scriptImpl = autowireCapableBeanFactory.createBean(ScriptRepoImpl.class);
		autowireCapableBeanFactory.autowireBean(scriptImpl);
		scriptImpl.init(scriptEnt);
		return scriptImpl;
	}

	@Override
	public ScriptRepo getRepo(String name) throws Exception {
		if(scripts.containsKey(name) == false) { 
			Exception e = new Exception("Script " + name + " not found");
			throw e;
		}
		return scripts.get(name);
	}

	@Override
	public Collection<ScriptRepo> getRepos() {
		return scripts.values();
	}

	

	@Override
	public EventNode getEventNode() {
		return eventNode; 
	}
	
	
	
	
	
	
	
	
}
