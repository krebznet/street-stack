package com.dunkware.spring.runtime.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.utils.core.event.EventNode;
import com.dunkware.utils.core.event.EventTree;

import jakarta.annotation.PostConstruct;

@Service
public class EventService {

	private EventTree eventTree; 
	
	
	@Autowired
	private ExecutorService executorService;
	
	@PostConstruct
	public void init() { 
		eventTree = EventTree.newInstance(executorService.get());
	}
	
	public EventNode getEventRoot() { 
		return eventTree.getRoot();
	}
}
