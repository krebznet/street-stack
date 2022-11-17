package com.dunkware.trade.service.data.server.spring;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dunkware.common.util.events.DEventTree;
import com.dunkware.common.util.executor.DExecutor;

@Component
public class RuntimeService {
	
	@Autowired
	private ExecutorService executorService; 

	private DEventTree eventTree; 

	
	@PostConstruct
	private void load() { 
		eventTree = DEventTree.newInstance(getExecutor());
	}
	
	public DExecutor getExecutor() { 
		return executorService.getExecutor();
	}
	
	public DEventTree getEventTree() { 
		return eventTree;
	}
	


}
