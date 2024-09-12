package com.dunkware.faces.widgets.client.grid;

public interface IDataGridConsumer {

	public void dispose();

	void addListener(DataGridConsumerListener listener);

	void removeListener(DataGridConsumerListener listener);

	
}
