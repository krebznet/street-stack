package com.dunkware.faces.widgets.server.grid;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.dunkware.faces.widgets.model.grid.DataGridUpdate;
import com.faces.widget.proto.grid.Datagrid.DataGridUpdateProto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

public abstract class DataGridPublisher {

    private final CopyOnWriteArrayList<DataGridUpdate> updateBuffer = new CopyOnWriteArrayList<>();
    private FluxSink<List<byte[]>> fluxSink;  // Send serialized binary data

    // Starts the stream and returns a Flux of byte arrays
    public Flux<List<byte[]>> startStream() {
        return Flux.<List<byte[]>>create(emitter -> {
            this.fluxSink = emitter;

            // Client connection and disconnection handling
            emitter.onCancel(this::onClientDisconnect);
            emitter.onDispose(this::onDispose);
        }).delayElements(Duration.ofMillis(500));  // Emit every 0.5 seconds
    }

    // Abstract method to handle client disconnection
    protected abstract void onClientDisconnect();

    // Abstract method to dispose of resources
    protected abstract void onDispose();

    // Method to publish new updates
    public void publishUpdates(DataGridUpdate update) {
        updateBuffer.add(update);
    }

    // Emits buffered updates in batches as byte arrays
    public void emitUpdates() {
        if (!updateBuffer.isEmpty() && fluxSink != null) {
            List<byte[]> serializedUpdates = new ArrayList<>();

            for (DataGridUpdate update : updateBuffer) {
                DataGridUpdateProto proto = toProto(update);
                serializedUpdates.add(serializeToBytes(proto));  // Convert to byte[] and add to list
            }

            fluxSink.next(serializedUpdates);  // Send the list of byte arrays to the client
            updateBuffer.clear();  // Clear the buffer after sending
        }
    }

    // Schedules periodic emission of updates
    public void scheduleEmitUpdates() {
        Flux.interval(Duration.ofMillis(500))  // Send updates every 0.5 seconds
            .subscribe(tick -> emitUpdates());
    }

    // Conversion from DataGridUpdate to DataGridUpdateProto
    protected DataGridUpdateProto toProto(DataGridUpdate update) {
        return DataGridUpdateProto.newBuilder()
            .setId(update.getId().intValue())  // Assuming ID is a number that can be cast to int
            .setJson(update.getJson().toString())  // Assuming JSON can be serialized to a string
            .setType(update.getType())
            .build();
    }

    // Serialize DataGridUpdateProto to byte array
    protected byte[] serializeToBytes(DataGridUpdateProto proto) {
        return proto.toByteArray();  // Serialize to byte[]
    }
}