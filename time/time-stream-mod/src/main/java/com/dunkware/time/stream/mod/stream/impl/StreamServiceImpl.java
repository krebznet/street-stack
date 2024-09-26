package com.dunkware.time.stream.mod.stream.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.dunkware.time.stream.mod.stream.IStream;
import com.dunkware.time.stream.mod.stream.IStreamService;
import com.dunkware.utils.core.event.EventNode;

import jakarta.annotation.PostConstruct;

@Service
public class StreamServiceImpl implements IStreamService {
	
	
	private Map<Integer,IStream> streamIds = new ConcurrentHashMap<Integer,IStream>();
	private Map<String, IStream> streamIdents = new ConcurrentHashMap<String,IStream>();
	
	@PostConstruct
	private void init() { 
		
		
	}
	
	

	@Override
	public IStream getStream(String identifier) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IStream getStream(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void deleteStream(IStream stream, boolean deleteSignals, boolean beleteSnapshots) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<IStream> getStreams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getStreamLabels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventNode getEventNode() {
		// TODO Auto-generated method stub
		return null;
	}





	
}
