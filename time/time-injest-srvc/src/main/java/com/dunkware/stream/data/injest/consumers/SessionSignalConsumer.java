package com.dunkware.stream.data.injest.consumers;

import org.springframework.stereotype.Service;

@Service
public class SessionSignalConsumer {

	/*
	 * @Autowired private CassyQueryService queryService;
	 * 
	 * 
	 * private SessionSignalEntityLoader loader;
	 * 
	 * 
	 * private CqlSessionBuilder sessionBuilder;
	 * 
	 * public SessionSignalConsumer(CqlSessionBuilder sessionBuilder) {
	 * this.sessionBuilder = sessionBuilder;
	 * 
	 * }
	 * 
	 * @PostConstruct public void init() {
	 * 
	 * loader = new SessionSignalEntityLoader(sessionBuilder.build(),1,50);
	 * 
	 * Thread tester = new Thread() {
	 * 
	 * 
	 * public void run() {
	 * 
	 * int i = 0;
	 * 
	 * while(i < 50000) { EntitySignalModel sig = new EntitySignalModel();
	 * sig.setEntity(i); sig.setId(i); sig.setStream(1);
	 * sig.setTimestamp(LocalDateTime.of(20224, 01, 01, 01, 2,2,9).plusSeconds(i));
	 * Map<Integer, Number> vars = new HashMap<Integer,Number>(); int x = 0; while(x
	 * < 2) { vars.put(x, 323.3); x++; } sig.setVars(vars); loader.load(sig);; i++;
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * } };
	 * 
	 * tester.start();
	 * 
	 * 
	 * }
	 */
			
}
