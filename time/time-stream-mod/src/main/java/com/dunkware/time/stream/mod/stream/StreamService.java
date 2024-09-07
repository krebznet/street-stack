package com.dunkware.time.stream.mod.stream;

import org.springframework.stereotype.Service;

import com.dunkware.time.stream.model.admin.proto.StreamDeployReq;
import com.dunkware.utils.core.events.DunkEventNode;

@Service
public interface StreamService {
	
	public Stream getStream(String identifier) throws Exception; 
	
	public Stream getStream(int id) throws Exception;
	
	public Stream createStream(StreamDeployReq req) throws Exception;
	
	public DunkEventNode getEventNode();
}
