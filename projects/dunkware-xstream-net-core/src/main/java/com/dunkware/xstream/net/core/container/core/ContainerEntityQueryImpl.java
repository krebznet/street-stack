package com.dunkware.xstream.net.core.container.core;

import java.util.List;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.xstream.model.search.SessionEntitySearch;
import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerEntityQuery;
import com.dunkware.xstream.net.core.container.ContainerListener;
import com.dunkware.xstream.net.core.container.ContainerSearchException;
import com.dunkware.xstream.net.core.container.ContainerSearchResults;
import com.dunkware.xstream.net.core.container.search.entity.EntitySearchHelper;

public class ContainerEntityQueryImpl implements ContainerEntityQuery, ContainerListener  {

	
	private Container container; 
	private SessionEntitySearch search; 
	private List<String> retVars;
	
	private List<Predicate<ContainerEntity>> predicates;
	private boolean disposed = false; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void init(Container container, SessionEntitySearch search) throws ContainerSearchException {
		this.container = container; 
		this.search = search; 
		this.retVars = search.getRetVars();
		
		try {
			predicates = EntitySearchHelper.createEntitySearchPredicates(search, container);
		} catch (Exception e) {
			logger.error("Exception creating entity search predicates " + e.toString());
			throw new ContainerSearchException("Entity Filter Predicates Build Exception " + e.toString());
		}
		
		container.addListener(this);
	}

	@Override
	public void dispose() {
		if(!disposed) { 
			container.removeListener(this);
			disposed = true; 
		}
	}

	@Override
	public ContainerSearchResults<ContainerEntity> execute() throws ContainerSearchException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void timeUpdate(Container container) {
		
	}

	@Override
	public void newSession() {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
