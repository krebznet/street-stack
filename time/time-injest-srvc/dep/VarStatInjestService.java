package com.dunkware.stream.data.injest.services;


import com.dunkware.stream.data.cassy.entity.VarStatEntity;
import com.dunkware.stream.data.cassy.entity.VarStatKey;
import com.dunkware.stream.data.cassy.repository.VarStatRepository;
import com.dunkware.stream.data.codec.stat.EntityVarStatCodec;
import com.dunkware.stream.data.injest.kafka.KafkaByteConsumer;
import com.dunkware.stream.data.injest.kafka.KafkaByteConsumerSpec;
import com.dunkware.stream.data.injest.kafka.KafkaByteHandler;
import com.dunkware.stream.data.stats.capture.entity.date.EntityDateStatsSignalCount;

import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class VarStatInjestService implements KafkaByteHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private Marker marker = MarkerFactory.getMarker("VarStatInjestService");

    @Value("${injest.brokers}")
    private String brokers;
    @Value("${injest.topic.varstats}")
    private String topic;
    @Value("${injest.consumer}")
    private String consumerId;
    @Value("${injest.group}")
    private String groupId;

    @Value("${injest.varstat.threads}")
    private int threadCount;

    private BlockingQueue<EntityDateStatsSignalCount> queue = new LinkedBlockingQueue<EntityDateStatsSignalCount>();
    private List<InjestorThread> injestorThreads = new ArrayList<InjestorThread>();

    private VarStatRepository statRepo;

    private KafkaByteConsumer kafkaConsumer;

    public VarStatInjestService(VarStatRepository statRepo) {
        this.statRepo = statRepo;
    }

    private AtomicLong injestCounter = new AtomicLong(0);

    @PostConstruct
    private void postConstruct() {
            System.out.println("fuck");
            Thread init = new Thread() {

                public void run() {
                    try {
                        KafkaByteConsumerSpec spec = KafkaByteConsumerSpec.newBuilder(KafkaByteConsumerSpec.ConsumerType.Auto, KafkaByteConsumerSpec.OffsetType.Earliest)
                                .addBroker(brokers).addTopic(topic).setClientAndGroup(consumerId,groupId).setThrottle(500000).build();
                        kafkaConsumer = KafkaByteConsumer.newInstance(spec);
                        kafkaConsumer.addStreamHandler(VarStatInjestService.this);
                        kafkaConsumer.start();

                        int i = 0;
                        while(i < threadCount) {
                            InjestorThread injestor = new InjestorThread();
                            injestor.start();;
                            injestorThreads.add(injestor);
                            i++;
                        }
                    } catch(Exception e) {
                        logger.error(marker, "Exceptoin Starting Var Start Kafka Consumer " + e.toString(),e);

                    }

                }

            };

            init.start();

    }

    @Override
    public void record(ConsumerRecord<String, byte[]> record) {
        try {
           queue.add(EntityVarStatCodec.decode(record.value()));
        } catch(Exception e) {
            logger.error("Exception Decoding VarStat " + e.toString());
        }
    }


    private class InjestorThread extends Thread {

        private List<VarStatEntity> list = new ArrayList<VarStatEntity>();

        public void run() {

            while(!interrupted()) {
                try {
                    EntityDateStatsSignalCount inbound = queue.poll(5,TimeUnit.SECONDS);
                    if(inbound == null) {
                        if(!list.isEmpty()) {
                            statRepo.ingest(list);
                            injestCounter.addAndGet(list.size());
                            list.clear();;

                        }
                        continue;
                    }

                    VarStatKey key = new VarStatKey(inbound.getStream(),inbound.getEntity(),inbound.getDate(),inbound.getStat(),inbound.getVar());
                    VarStatEntity entity = new VarStatEntity(key,inbound.getValue());
                    list.add(entity);
                    if(list.size() > 15000) {
                        statRepo.ingest(list);
                        injestCounter.addAndGet(list.size());
                        list.clear();;
                        System.out.println(injestCounter.get());
                    }


                } catch (InterruptedException e) {
                    if(!list.isEmpty()) {
                        statRepo.ingest(list);
                        injestCounter.addAndGet(list.size());
                        list.clear();

                    }
                }
            }
        }

    }
}
