package com.dunkware.trade.service.stream.server.controller.session.extensions;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.trade.service.stream.server.controller.session.StreamSession;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionExtension;
import com.dunkware.trade.service.stream.server.controller.session.anot.AStreamSessionExt;
import com.dunkware.utils.core.time.DunkTime;
import com.dunkware.utils.kafka.admin.DunkKafkaAdmin;
import com.dunkware.utils.kafka.admin.model.KafkaNewTopicResult;



@AStreamSessionExt
public class EntitySignalsTopicCreator implements StreamSessionExtension {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	private DunkNet dunkNet;
	
	
	private StreamSession session; 
	
	
	@Override
	public void sessionStarting(StreamSession session) {
		try {
			this.session = session;
			session.getInput().getProperties().put("signals_topic", getStatTopicName(session.getSessionEntityId()));
			this.session = session;
			Thread runner = new Thread() { 
			
				public void run() { 
					try {
						DunkKafkaAdmin client = dunkNet.createAdminClient();						
						KafkaNewTopicResult result = client.createPersistentTopic(getStatTopicName(session.getEntity().getId()),1,1);
						if(result.isException() || result.isTimeout()) { 
							if(result.isException())  {
							logger.error("Fucked up creating entity stat session topic exception " + result.getCause());
							}
							if(result.isTimeout()) {
								logger.error("Fucked up creating entity stat session topic timeout bro");
							}
									
						} else { 
							logger.info("Entity Stat Session Topic Created " + getStatTopicName(session.getEntity().getId()));
						}
					} catch (Exception e) {
						logger.error("Fuck Man no kafka admin client!!! in create stats session topic " + e.toString());;
					}
					
				}
				
			};
			runner.start();
			
		} catch (Exception e) {
			logger.error("Fuck Man no kafka admin client!!! in create stats session topic " + e.toString());;
		}

		
	}
	
	
	private String getStatTopicName(long  sessionId) { 
		StringBuilder b = new StringBuilder();
		b.append("stream_data_" + session.getStream().getName());
		b.append("_entity_signals");
		
		LocalDate now = session.getStream().getDateTime().toLocalDate();
		DunkTime.format(now,DunkTime.YYMMDD);
		b.append("_");
		b.append(DunkTime.format(now,DunkTime.YYMMDD));
		b.append("_");
		b.append(sessionId);
		
		return b.toString();
	}
	
	

}
