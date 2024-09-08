package com.dunkware.faces.widgets.client.grid;

import com.dunkware.faces.widgets.model.grid.DataGridUpdate;

public interface DataGridConsumerListener {

    // Method called for each data grid update
    void onDataGridUpdate(DataGridUpdate update);

    // Method called in case of errors
    void onError(Throwable error);

    // Method called when data stream completes
    void onComplete();
}
