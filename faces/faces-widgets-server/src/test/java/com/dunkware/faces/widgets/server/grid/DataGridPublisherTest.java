package com.dunkware.faces.widgets.server.grid;

import java.util.Base64;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dunkware.faces.widgets.model.grid.DataGridUpdate;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class DataGridPublisherTest {

    private DataGridPublisher publisher;

    @BeforeEach
    public void setup() {
        // Initialize the DataGridPublisher with a test implementation
        publisher = new TestDataGridPublisher();
    }

    @Test
    public void testPublishUpdates() {
        // Start the stream
        Flux<List<byte[]>> flux = publisher.startStream();

        // Prepare test data
        for(int i = 0; i < 1000; i++) { 
        	DataGridUpdate update1 = new DataGridUpdate();
            update1.setId(1);
            update1.setJson("{ \"value\": 100 }");
            update1.setType("insert");

            DataGridUpdate update2 = new DataGridUpdate();
            update2.setId(2);
            update2.setJson("{ \"value\": 200 }");
            update2.setType("update");

            // Publish updates
            publisher.publishUpdates(update1);
            publisher.publishUpdates(update2);
	
        }
        
        // Schedule the emission of updates
        publisher.scheduleEmitUpdates();

        // Verify the output stream and print serialized data to the console
        StepVerifier.create(flux)
                .expectNextMatches(byteArrayList -> {
                    // Iterate through each byte array (representing serialized DataGridUpdateProto)
                    for (byte[] byteArray : byteArrayList) {
                        // Convert to readable Base64 format for console output
                        String serializedData = Base64.getEncoder().encodeToString(byteArray);
                        System.out.println("Serialized Data (Base64): " + serializedData);
                    }
                    return byteArrayList.size() == 2;
                })
                .thenCancel()
                .verify();
    }

    // A simple test implementation of DataGridPublisher
    private static class TestDataGridPublisher extends DataGridPublisher {

        private final CopyOnWriteArrayList<DataGridUpdate> buffer = new CopyOnWriteArrayList<>();

        @Override
        protected void onClientDisconnect() {
            // Simulate client disconnection
        }

        @Override
        protected void onDispose() {
            // Simulate resource disposal
        }

        // Overrides the publishUpdates to allow adding to buffer
        @Override
        public void publishUpdates(DataGridUpdate update) {
            buffer.add(update);
            super.publishUpdates(update);  // Add update to the buffer in the base class
        }

    
    }
}