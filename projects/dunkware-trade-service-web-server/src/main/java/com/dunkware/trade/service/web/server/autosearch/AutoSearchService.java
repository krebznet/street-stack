package com.dunkware.trade.service.web.server.autosearch;

import org.springframework.stereotype.Service;

@Service
public class AutoSearchService {

	
	public AutoSearchContext createContext() { 
		return new AutoSearchContext();
	}
}
