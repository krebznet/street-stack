package com.dunkware.time.entity.mod;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface ServerEntityService {

	public EntityRepository getProvider(String typeName) throws Exception;
	
	public Map<String,EntityRepository> getProviders();
	
	public boolean entityTypeExists(String typeName) throws Exception;
}
