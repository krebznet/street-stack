package com.dunkware.spring.runtime.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.utils.core.events.DunkEventNode;
import com.dunkware.utils.core.events.DunkEventTree;

import jakarta.annotation.PostConstruct;

@Service
public class EventService {

	private DunkEventTree eventTree; 
	
	
	@Autowired
	private ExecutorService executorService;
	
	@PostConstruct
	public void init() { 
		eventTree = DunkEventTree.newInstance(executorService.get());
	}
	
	public DunkEventNode getEventRoot() { 
		return eventTree.getRoot();
	}
}
