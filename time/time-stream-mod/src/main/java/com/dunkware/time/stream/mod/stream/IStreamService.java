package com.dunkware.time.stream.mod.stream;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dunkware.utils.core.event.EventNode;

@Service
public interface IStreamService {
	
	public IStream getStream(String identifier) throws Exception; 
	
	public IStream getStream(int id) throws Exception;
	
	public void deleteStream(IStream stream, boolean deleteSignals, boolean beleteSnapshots);
	
	public Collection<IStream> getStreams();
	
	public List<String> getStreamLabels();
	
	public EventNode getEventNode();
}
