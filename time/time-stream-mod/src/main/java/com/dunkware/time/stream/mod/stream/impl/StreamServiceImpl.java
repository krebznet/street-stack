package com.dunkware.time.stream.mod.stream.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.dunkware.time.stream.mod.stream.Stream;
import com.dunkware.time.stream.mod.stream.StreamService;
import com.dunkware.time.stream.model.admin.config.StreamConfig;
import com.dunkware.utils.core.events.DunkEventNode;

import jakarta.annotation.PostConstruct;

@Service
public class StreamServiceImpl implements StreamService {
	
	
	private Map<Integer,Stream> streamIds = new ConcurrentHashMap<Integer,Stream>();
	private Map<String, Stream> streamIdents = new ConcurrentHashMap<String,Stream>();
	
	@PostConstruct
	private void init() { 
		
		
	}

	@Override
	public Stream getStream(String identifier) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream getStream(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream createStream(StreamConfig config) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteStream(Stream stream, boolean deleteSignals, boolean beleteSnapshots) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Stream> getStreams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getStreamLabels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DunkEventNode getEventNode() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
