package com.dunkware.trade.net.data.server.injestor;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * This will connect to node channels with StreamInjestor profile in data-worker
 * the kafka topics are hard coded and consumer groups and ids are property configured
 * we don't spin up stream injestors not here just aggregate stats and metrics. 
 * 
 * @author duncankrebs
 *
 */
@Profile("StreamInjestor")
@Service()
public class StreamInjestorService {

	
}
