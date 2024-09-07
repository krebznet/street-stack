package com.dunkware.time.stream.mod.stream;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dunkware.time.stream.model.admin.config.StreamConfig;
import com.dunkware.utils.core.events.DunkEventNode;

@Service
public interface StreamService {
	
	public Stream getStream(String identifier) throws Exception; 
	
	public Stream getStream(int id) throws Exception;
	
	public Stream createStream(StreamConfig config) throws Exception;
	
	public void deleteStream(Stream stream, boolean deleteSignals, boolean beleteSnapshots);
	
	public Collection<Stream> getStreams();
	
	public List<String> getStreamLabels();
	
	public DunkEventNode getEventNode();
}
