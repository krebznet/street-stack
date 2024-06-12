package com.dunkware.trade.service.stream.server.controller.session.extensions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.stream.data.codec.session.meta.SessionModelCodec;
import com.dunkware.stream.data.model.session.StreamSessionModel;
import com.dunkware.stream.data.model.stats.entity.EntityStatTypes;
import com.dunkware.stream.data.util.constants.StreamDataTopics;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerEntitiesReq;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerEntitiesResp;
import com.dunkware.trade.service.stream.server.controller.session.StreamSession;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionExtension;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionNode;
import com.dunkware.trade.service.stream.server.controller.session.anot.AStreamSessionExt;

/**
 * Blanket Extension that will publish data to specific capture topics for stream data
 * that require aggregate data from all session nodes. 
 */
@AStreamSessionExt
public class StreamDataPublisher implements StreamSessionExtension {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private DunkNet dunkNet; 
	
	private StreamSession session; 
	
	List<Integer> entities = new ArrayList<Integer>();
	
	@Override
	public void sessionStopping(StreamSession session) {
		
		for (StreamSessionNode node : session.getNodes()) {
			try {
				StreamSessionWorkerEntitiesResp resp = (StreamSessionWorkerEntitiesResp)node.getChannel().serviceBlocking(new StreamSessionWorkerEntitiesReq());	
				for (Integer integer : resp.getEntities()) {
					if(entities.contains(integer) == true) { 
						logger.error("Stream found duplicate entity id across workers " + integer);
					} else { 
						entities.add(integer);
					}
				}
			} catch (Exception e) {
				logger.error("Exception getting stream session worker entities " + e.toString());
			}
			
			
		}
		
	}

	


	@Override
	public void sessionStarted(StreamSession session) {
	
	}




	@Override
	public void sessionStopped(StreamSession session) {
		// construct 
		StreamSessionModel metaSession = new StreamSessionModel();
	 	metaSession.setEntities(entities);
	 	metaSession.setStream(session.getStreamId());
	 	metaSession.setDate(session.getStream().getDateTime().toLocalDate());
	 	metaSession.setStart(session.getStartTime());
	 	metaSession.setStop(session.getStopTime());
	 	metaSession.setVars(session.getScriptProject().getStreamVarIds());
	 	metaSession.setSignals(session.getScriptProject().getStreamSignalIds());
	 	metaSession.setStats(Arrays.asList(EntityStatTypes.VarHigh, EntityStatTypes.VarLow));
	 	// okay now a producer to send to the fuckin topic
	 	try {
	 		byte[] bytes = SessionModelCodec.encode(metaSession);
	 		ProducerRecord<Integer, byte[]> record = new ProducerRecord<Integer, byte[]>(StreamDataTopics.CaptureSession, bytes);
	 		dunkNet.getKafkaProducer().send(record);
		} catch (Exception e) {
			logger.error("Exception creating/sending SessionMetaSession " + e.toString());
		}
	 	
	 
	 	
	}




	
	

	
}
// com.dunkware.common
// common-kafka
// common-util
// common-codec
// 

// dunkware-common
