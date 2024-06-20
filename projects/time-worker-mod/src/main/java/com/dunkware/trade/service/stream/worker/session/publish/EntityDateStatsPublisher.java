package com.dunkware.trade.service.stream.worker.session.publish;

import java.util.List;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.tiime.data.util.constants.StreamDataTopics;
import com.dunkware.time.data.codec.stat.EntityStatsModelCodec;
import com.dunkware.time.data.model.stats.entity.EntityStatsModel;
import com.dunkware.trade.service.stream.worker.session.StreamWorker;
import com.dunkware.trade.service.stream.worker.session.StreamWorkerExtension;
import com.dunkware.trade.service.stream.worker.session.anot.AStreamWorkerExtension;
import com.dunkware.xstream.api.XStream;

@AStreamWorkerExtension
public class EntityDateStatsPublisher implements StreamWorkerExtension {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Marker marker = MarkerFactory.getMarker("VarStatKafkaPublisher");

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

    @Override
    public void stop() throws Exception {

        Thread runner = new Thread() {

            public void run() {
                logger.info(marker, "Starting Stat Collector Kafka Publisher Process");
                EntityDateStatsCollector collector = EntityDateStatsCollector.newInstance(stream.getRows(),stream.getClock().getLocalDateTime().toLocalDate(), (int)stream.getInput().getId());
                List<EntityStatsModel> stats = null;
                try {
                    stats = collector.collectStats();
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
                for(EntityStatsModel stat : stats) {
                    try {
                        byte[] bytes = EntityStatsModelCodec.encode(stat);
                        ProducerRecord<Integer,byte[]> record = new ProducerRecord<Integer,byte[]>(StreamDataTopics.CaptureEntityStats,bytes);
                        producer.send(record);
                    } catch (Exception e) {
                        logger.error(marker, "Exception sending stats byytes " + e.toString());
                    }
                }
                logger.info(marker, "Worker Node Publisher {} stats to stream_capture_var_stat",stats.size());

            }
        };

        runner.start();
    }
}
