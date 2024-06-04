package com.dunkware.spring.runtime.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.events.DEventTree;

import jakarta.annotation.PostConstruct;

@Service
public class EventService {

	private DEventTree eventTree; 
	
	
	@Autowired
	private ExecutorService executorService; 
	
	@PostConstruct
	public void init() { 
		eventTree = DEventTree.newInstance(executorService.get());
	}
	
	public DEventNode getEventRoot() { 
		return eventTree.getRoot();
	}
}
