package com.dunkware.time.script.mod.repo.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.time.script.mod.repo.ScriptRepo;
import com.dunkware.time.script.mod.repo.ScriptRepoRelease;
import com.dunkware.time.script.mod.repo.ScriptRepoService;
import com.dunkware.time.script.mod.repo.entity.DBScriptRepo;
import com.dunkware.time.script.mod.repo.entity.DBScriptRepoRelease;
import com.dunkware.utils.core.event.EventNode;
import com.dunkware.xstream.model.script.descriptor.XScriptDescriptor;
import com.dunkware.xstream.model.script.release.XScriptVersion;



public class ScriptRepoImpl implements ScriptRepo {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private DBScriptRepo entity; 
	
	private List<ScriptRepoRelease> releases = new ArrayList<ScriptRepoRelease>();
	
	private ScriptRepoRelease currentRelease = null;
	
	private EventNode eventNode; 
	
	@Autowired
	private ScriptRepoService scriptService; 
	
	public void init(DBScriptRepo entity) throws Exception { 
		this.entity = entity;
		this.eventNode = scriptService.getEventNode().createChild(this); 
		List<DBScriptRepoRelease> relEntiies = entity.getReleases();
		Collections.sort(relEntiies, new Comparator<DBScriptRepoRelease>() {

			@Override
			public int compare(DBScriptRepoRelease o1, DBScriptRepoRelease o2) {
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
		for (DBScriptRepoRelease relEnt : relEntiies) {
			ScriptRepoReleaseImpl rel = new ScriptRepoReleaseImpl();
			rel.init(this, relEnt);
			if(count == 0) {
				this.currentRelease = rel;
			}
			count++;
		}
		
		if(this.currentRelease == null) { 
			throw new Exception("Script Repo " + entity.getName() + " ini with no release");
		}
		

	}
	
	@Override
	public ScriptRepoRelease getRelease() {
		return currentRelease;
	}

	@Override
	public XScriptDescriptor getReleaseModel() {
		return currentRelease.getRelease().getModel();
	}

	@Override
	public List<ScriptRepoRelease> getReleaseHistory() {
		return releases;
	}

	@Override
	public EventNode getEventNode() {
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
	public DBScriptRepo getEntity() {
		return entity;
	}
	
	
	
	
}
