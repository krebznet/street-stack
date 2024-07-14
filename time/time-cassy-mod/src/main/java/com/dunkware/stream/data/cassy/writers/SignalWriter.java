package com.dunkware.stream.data.cassy.writers;

import java.net.InetSocketAddress;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import com.datastax.driver.extras.codecs.jdk8.LocalDateTimeCodec;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.datastax.oss.driver.api.core.config.DefaultDriverOption;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
import com.datastax.oss.driver.api.core.config.ProgrammaticDriverConfigLoaderBuilder;
import com.datastax.oss.driver.api.core.cql.BatchStatement;
import com.datastax.oss.driver.api.core.cql.BatchStatementBuilder;
import com.datastax.oss.driver.api.core.cql.BatchType;
import com.datastax.oss.driver.api.core.cql.BatchableStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.type.codec.TypeCodec;
import com.datastax.oss.driver.api.core.type.codec.registry.MutableCodecRegistry;
import com.dunkware.time.data.dto.entity.EntitySignalDTO;
import com.dunkware.time.data.dto.entity.SignalGenerator;
import com.fasterxml.jackson.databind.JavaType;

public class SignalWriter {

    private final CqlSession session;
    private final PreparedStatement entityDailySignalsInsert;
    private final BlockingQueue<EntitySignalDTO> signalQueue = new LinkedBlockingQueue<>();
    private final int BATCH_SIZE = 100;
    private AtomicInteger insertCounter = new AtomicInteger();
    public SignalWriter(CqlSession session) {
        this.session = session;
        String entityDailySignalsInsertQuery = "INSERT INTO timestream.entity_daily_signals (entity_id, signal_date, signal_time, signal_id, stream_id, vars) VALUES (?, ?, ?, ?, ?, ?)";
        this.entityDailySignalsInsert = session.prepare(entityDailySignalsInsertQuery);
        InsertPrinter p = new InsertPrinter();
        p.start();
        startConsumers();
    }

    public void writeSignals(List<EntitySignalDTO> signals) {
        for (EntitySignalDTO EntitySignalDTO : signals) {
            signalQueue.add(EntitySignalDTO);
        }
    }

    private void startConsumers() {
        for (int i = 0; i < 10; i++) {
            new Thread(this::consumeSignals).start();
        }
    }

    private void consumeSignals() {
        while (true) {
            try {
                BatchStatementBuilder batchBuilder = BatchStatement.builder(BatchType.UNLOGGED);
                for (int i = 0; i < BATCH_SIZE; i++) {
                    EntitySignalDTO EntitySignalDTO = signalQueue.take();
                    BatchableStatement<?> boundStatement = entityDailySignalsInsert.bind(
                            EntitySignalDTO.getEntityId(),
                            EntitySignalDTO.getTimestamp().toLocalDate(),
                            EntitySignalDTO.getTimestamp().toLocalTime(),
                            EntitySignalDTO.getSignalId(),
                            EntitySignalDTO.getStreamId(),
                            EntitySignalDTO.getVars()
                    );
                    batchBuilder.addStatement(boundStatement);
                }

                BatchStatement batchStatement = batchBuilder.build();
                session.execute(batchStatement);
                insertCounter.addAndGet(BATCH_SIZE);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
    
    private class InsertPrinter extends Thread { 
    	
    	public void run() { 
    		while(true) { 
    			try {
					Thread.sleep(1000);
					System.out.println(insertCounter.get());
				} catch (Exception e) {
					
				}
    		}
    	}
    }

   

   
}
