package com.dunkware.time.script.mod.repo.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.time.script.mod.repo.Script;
import com.dunkware.time.script.mod.repo.ScriptRelease;
import com.dunkware.time.script.mod.repo.ScriptService;
import com.dunkware.time.script.mod.repo.entity.ScriptEntity;
import com.dunkware.time.script.mod.repo.entity.ScriptReleaseEntity;
import com.dunkware.utils.core.events.DunkEventNode;
import com.dunkware.utils.core.json.DunkJson;
import com.dunkware.xstream.model.script.model.XScriptModel;
import com.dunkware.xstream.model.script.model.XScriptRelease;
import com.dunkware.xstream.model.script.model.XScriptVersion;
import com.dunkware.xstream.xproject.bundle.XscriptBundleHelper;
import com.dunkware.xstream.xproject.model.XScriptBundle;


public class ScriptImpl implements Script {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private ScriptEntity entity; 
	
	private List<ScriptRelease> releases = new ArrayList<ScriptRelease>();
	
	private ScriptRelease currentRelease = null;
	
	private DunkEventNode eventNode; 
	
	@Autowired
	private ScriptService scriptService; 
	
	
	public void create(XScriptBundle script, String name) throws Exception { 
		
	}
	
	public void init(ScriptEntity entity) throws Exception { 
		this.entity = entity;
		this.eventNode = scriptService.getEventNode().createChild(this); 
		List<ScriptReleaseEntity> relEntiies = entity.getReleases();
		Collections.sort(relEntiies, new Comparator<ScriptReleaseEntity>() {

			@Override
			public int compare(ScriptReleaseEntity o1, ScriptReleaseEntity o2) {
				XScriptVersion v1 = XScriptVersion.fromString(o1.getVersion());
				XScriptVersion v2 = XScriptVersion.fromString(o2.getVersion());
				if(v1.getMajor() == v2.getMajor()) { 
					if(v1.getMinor() == v2.getMinor()) { 
						if(v1.getRevision() == v2.getRevision()) { 
							return 0;
						}
					}
				}
				if(v1.getMajor() > v2.getMajor()) { 
					return 1;
				}
				if(v1.getMinor() > v2.getMinor()) {
					return 1;
				}
				if(v1.getRevision() > v2.getRevision()) {
					return 1;
				}
				return -1;
			}
		});
		
		int count = 0;
		for (ScriptReleaseEntity relEnt : relEntiies) {
			ScriptReleaseImpl rel = new ScriptReleaseImpl();
			rel.init(this, relEnt);
			if(count == 0) {
				this.currentRelease = rel;
			}
			count++;
		}
		
		
		
	}
	
	@Override
	public ScriptRelease getRelease() {
		return currentRelease;
	}

	@Override
	public XScriptModel getReleaseModel() {
		return currentRelease.getModel().getModel();
	}

	@Override
	public List<ScriptRelease> getReleaseHistory() {
		return releases;
	}

	@Override
	public DunkEventNode getEventNode() {
		return eventNode;
	}


	@Override
	public String getName() {
		return entity.getName();
	}

	@Override
	public String getType() {
		return entity.getType();
	}

	@Override
	public ScriptEntity getEntity() {
		return entity;
	}
	
	
	
	
}
