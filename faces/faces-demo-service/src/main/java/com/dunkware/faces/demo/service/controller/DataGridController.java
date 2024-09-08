package com.dunkware.faces.demo.service.controller;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.faces.widgets.model.grid.DataGridUpdate;
import com.dunkware.faces.widgets.server.grid.DataGridPublisher;
import com.faces.widget.proto.grid.Datagrid;
import com.faces.widget.proto.grid.Datagrid.DataGridUpdateProto;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@RestController
public class DataGridController {

    private static final Logger logger = LoggerFactory.getLogger(DataGridController.class);

    // Creates a new TestDataGridPublisher for every API call
    @GetMapping("/api/stream/datagrid")
    public Flux<byte[]> streamDataGridUpdates() {
        logger.info("Client connected to /api/stream/datagrid");

        // Create a new instance of the TestDataGridPublisher for this client
        TestDataGridPublisher publisher = new TestDataGridPublisher();
        publisher.scheduleEmitUpdates(); // Start emitting updates for this instance

        return publisher.startStream()  // Flux<List<byte[]>>
                .flatMapIterable(list -> list)  // Flatten the List<byte[]> to individual byte[]
                .doOnSubscribe(sub -> logger.info("Client subscribed to data stream"))
                .doOnCancel(() -> {
                    logger.info("Client connection canceled, triggering disconnect.");
                    publisher.onClientDisconnect();  // Trigger client disconnect logic
                })
                .doOnNext(data -> {
                    logger.info("Streaming data to client: {} bytes", data.length);
                    // Force flush the system output to avoid delays
                    System.out.flush();
                })
                .map(protoBytes -> {
                    // Create a DataGridUpdateProtoList builder
                    Datagrid.DataGridUpdateProtoList.Builder protoListBuilder = Datagrid.DataGridUpdateProtoList.newBuilder();

                    // Convert each byte[] to DataGridUpdateProto and add to the builder
                    try {
                        Datagrid.DataGridUpdateProto proto = Datagrid.DataGridUpdateProto.parseFrom(protoBytes);
                        protoListBuilder.addUpdates(proto);  // Add each DataGridUpdateProto to the list
                    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                        logger.error("Failed to parse DataGridUpdateProto from bytes", e);
                        throw new RuntimeException("Failed to parse DataGridUpdateProto from bytes", e);
                    }

                    // Serialize the DataGridUpdateProtoList to a byte array
                    return protoListBuilder.build().toByteArray();
                })
                .doOnTerminate(publisher::onDispose);  // Trigger disposal of resources on completion
              //  .delayElements(Duration.ofSeconds(1));  // Adding delay to simulate real-time streaming
    }

    public class TestDataGridPublisher extends DataGridPublisher {

        private final Sinks.Many<List<byte[]>> sink = Sinks.many().multicast().onBackpressureBuffer();
        private static final Logger logger = LoggerFactory.getLogger(TestDataGridPublisher.class);
        private Disposable updateEmitter;

        @Override
        protected void onClientDisconnect() {
            logger.info("Client disconnected from stream");

            // Stop emitting data and complete the sink
            if (updateEmitter != null && !updateEmitter.isDisposed()) {
                updateEmitter.dispose();  // Cancel the periodic emission
            }
            sink.tryEmitComplete();  // Complete the data stream
        }

        @Override
        protected void onDispose() {
            logger.info("Disposing resources");

            // Stop emitting updates and complete the stream
            if (updateEmitter != null && !updateEmitter.isDisposed()) {
                updateEmitter.dispose();
            }
            sink.tryEmitComplete();
        }

        @Override
        public Flux<List<byte[]>> startStream() {
            return sink.asFlux();
        }

        // Emit mock updates every 250 milliseconds
        public void scheduleEmitUpdates() {
            logger.info("Starting to emit mock updates every 250ms");

            // Schedule periodic emission of data
            updateEmitter = Flux.interval(Duration.ofMillis(250))
                    .subscribe(tick -> {
                        DataGridUpdate update = new DataGridUpdate();
                        update.setId(tick);
                        update.setJson("{ \"value\": " + tick + " }");
                        update.setType("insert");

                        DataGridUpdateProto proto = DataGridUpdateProto.newBuilder()
                                .setId(tick.intValue())
                                .setJson("{ \"value\": " + tick + " }")
                                .setType("insert")
                                .build();

                        logger.info("Emitting data: {}", proto);

                        // Create a List of byte[] to simulate batching
                        List<byte[]> protoList = new ArrayList<>();
                        protoList.add(proto.toByteArray());

                        // Publish the list of byte[] updates
                        sink.tryEmitNext(protoList);
                    });
        }
    }
}