package com.dunkware.time.entity.mod;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface EntityService {

	public EntityProvider getProvider(String typeName) throws Exception;
	
	public Map<String,EntityProvider> getProviders();
	
	public boolean entityTypeExists(String typeName) throws Exception;
}
