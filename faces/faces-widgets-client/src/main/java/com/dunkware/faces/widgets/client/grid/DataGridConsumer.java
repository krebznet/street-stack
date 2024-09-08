package com.dunkware.faces.widgets.client.grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dunkware.faces.widgets.model.grid.DataGridUpdate;
import com.dunkware.utils.reactive.client.ReactiveClient;
import com.faces.widget.proto.grid.Datagrid.DataGridUpdateProto;

import reactor.core.publisher.Flux;

public class DataGridConsumer {

    private final ReactiveClient reactiveClient;
    private final List<DataGridConsumerListener> listeners = new ArrayList<>();

    // Constructor to inject a ReactiveClient
    public DataGridConsumer(ReactiveClient reactiveClient) {
        this.reactiveClient = reactiveClient;
    }

    // Method to add a listener
    public void addListener(DataGridConsumerListener listener) {
        listeners.add(listener);
    }

    // Method to remove a listener
    public void removeListener(DataGridConsumerListener listener) {
        listeners.remove(listener);
    }

    // Method to fetch updates from the given endpoint and notify listeners
    public void consumeDataGridUpdates(String endpoint, Map<String, Object> queryParams) {
        // Fetch binary data (as byte[]) and deserialize it into DataGridUpdateProto
        Flux<byte[]> updatesFlux = reactiveClient.getBinaryFluxResponse(endpoint, queryParams);

        updatesFlux.subscribe(
            binaryData -> {
                // Deserialize binary data to DataGridUpdateProto
                try {
                    DataGridUpdateProto proto = DataGridUpdateProto.parseFrom(binaryData);
                    // Convert Proto object to DataGridUpdate and notify listeners
                    DataGridUpdate update = fromProto(proto);
                    listeners.forEach(listener -> listener.onDataGridUpdate(update));
                } catch (Exception e) {
                    listeners.forEach(listener -> listener.onError(e));
                }
            },
            error -> listeners.forEach(listener -> listener.onError(error)),  // OnError: notify listeners about error
            () -> listeners.forEach(DataGridConsumerListener::onComplete)     // OnComplete: notify completion
        );
    }

    // Helper method to convert DataGridUpdateProto to DataGridUpdate
    private DataGridUpdate fromProto(DataGridUpdateProto proto) {
        DataGridUpdate update = new DataGridUpdate();
        update.setId(proto.getId());
        update.setJson(proto.getJson());  // Assuming JSON field is string in both
        update.setType(proto.getType());
        return update;
    }
}