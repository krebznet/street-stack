package com.dunkware.stream.data.injest.consumers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.dunkware.stream.data.cassy.loaders.SessionSignalEntityLoader;
import com.dunkware.stream.data.cassy.services.CassyQueryService;
import com.dunkware.stream.data.model.entity.EntitySignal;

import jakarta.annotation.PostConstruct;

@Service
public class SessionSignalConsumer {

	
	@Autowired
	private CassyQueryService queryService;
	
	
	private SessionSignalEntityLoader loader;
	
	  
    private CqlSessionBuilder sessionBuilder; 
    
    public SessionSignalConsumer(CqlSessionBuilder sessionBuilder) { 
    	this.sessionBuilder = sessionBuilder;
    	
    }
    
    @PostConstruct
	public void init() {
	
		loader = new SessionSignalEntityLoader(sessionBuilder.build(),1,50);
		
		Thread tester = new Thread() { 
			
			
			public void run() { 
				
				int i = 0;
				
				while(i < 50000) { 
					EntitySignal sig = new  EntitySignal();
					sig.setEntity(i);
					sig.setId(i);
					sig.setStream(1);
					sig.setTimestamp(LocalDateTime.of(20224, 01, 01, 01, 2,2,9).plusSeconds(i));
					Map<Integer, Number> vars = new HashMap<Integer,Number>();
					int x = 0; 
					while(x < 2) { 
						vars.put(x, 323.3);
						x++;
					}
					sig.setVars(vars);
					loader.load(sig);;
					i++;
					
					
					
				}
				
				
			}
		};
		
		tester.start();
		
		
	}
			
			
}
