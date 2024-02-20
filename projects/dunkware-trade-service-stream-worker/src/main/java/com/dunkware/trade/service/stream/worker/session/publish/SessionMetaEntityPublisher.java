package com.dunkware.trade.service.stream.worker.session.publish;

import java.util.Arrays;
import java.util.List;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.stream.data.codec.session.meta.SessionMetaEntityCodec;
import com.dunkware.stream.data.codec.stat.EntityDateStatsCodec;
import com.dunkware.stream.data.session.meta.SessionMetaEntity;
import com.dunkware.stream.data.stats.entity.EntityStatType;
import com.dunkware.stream.data.stats.entity.capture.EntityDateStats;
import com.dunkware.stream.data.util.constants.StreamDataTopics;
import com.dunkware.trade.service.stream.worker.session.StreamWorker;
import com.dunkware.trade.service.stream.worker.session.StreamWorkerExtension;
import com.dunkware.xstream.api.XStream;

public class SessionMetaEntityPublisher implements StreamWorkerExtension {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Marker marker = MarkerFactory.getMarker("SessionMetaEntityPublisher");

    private StreamWorker worker;
    private XStream stream;

    @Override
    public void init(StreamWorker worker) throws Exception {
        this.worker = worker;
    }

    @Override
    public void start() throws Exception {
        this.stream = worker.getStream();
    }
// SesssionEntityNotifier 
   // Channel Session
    @Override
    public void stop() throws Exception {

        Thread runner = new Thread() {

            public void run() {
                logger.info(marker, "Starting Stat Collector Kafka Publisher Process");
                EntityDateStatsCollector collector = EntityDateStatsCollector.newInstance(stream.getRows(),stream.getClock().getLocalDateTime().toLocalDate(), (int)stream.getInput().getId());
                List<SessionMetaEntity> metaEnts = SessionMetaEntityCollector.collect(stream, Arrays.asList(EntityStatType.VarHigh,EntityStatType.VarLow));
                try {
                   
                } catch(Exception e) {
                    logger.error(marker, "Exception collecting var stats " + e.toString(),e);
                    return;
                }
                Producer<Integer,byte[]> producer = null;
                try {
                    producer = worker.getDunkNet().getKafkaProducer();
                } catch(Exception e) {
                    logger.error(marker, "Exception creating kafka producer "  + e.toString(),e);
                    return;		
                    																				
                }																					
                for(SessionMetaEntity stat : metaEnts) {													
                    try {																			
                        byte[] bytes = SessionMetaEntityCodec.encode(stat);							
                        ProducerRecord<Integer,byte[]> record = new ProducerRecord<Integer,byte[]>(StreamDataTopics.CaptureMetaEntity,bytes);
                        producer.send(record);
                    } catch (Exception e) {
                        logger.error(marker, "Exception sending meta entity byytes " + e.toString());
                    }
                }
                logger.info(marker, "Worker Node Publisher {} meta entitties to to kafka",metaEnts.size());

            }
        };

        runner.start();
    }
}
