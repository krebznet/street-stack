package com.dunkware.trade.service.beach.server.controller;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

import com.dunkware.common.util.datagrid.DataGridConsumer;
import com.dunkware.common.util.datagrid.DataGridUpdate;
import com.dunkware.common.util.datagrid.GlazedDataGrid;
import com.dunkware.common.util.executor.DExecutor;

import ca.odell.glazedlists.ObservableElementList;

public class GlazedGridWrapper   { 
	
	private ObservableElementList<?> list; 
	private DExecutor executor; 
	private String idMethod;
	
	private GlazedDataGrid dataGrid; 
	
	private BlockingQueue<DataGridUpdate> updateQueue = new LinkedBlockingQueue<DataGridUpdate>();
	
	private Fucker fucker; 
	
	public GlazedGridWrapper(ObservableElementList<?> list, DExecutor executor, String idMethod	 ) {
		this.list = list;
		this.executor = executor;
		this.idMethod = idMethod;
		
	}
	
	public BlockingQueue<DataGridUpdate> updateQueue() { 
		return updateQueue;
	}
	
	public void start() { 
		fucker = new Fucker();
		fucker.start();
	}

	
	public void dispose() { 
		fucker.dispose();
	}
	
	private class Fucker extends Thread implements DataGridConsumer { 
		
		public void run() { 
				dataGrid = new GlazedDataGrid(list,executor, idMethod);
			dataGrid.addConsumer(this);
			dataGrid.start();
		}
		
		public void dispose() { 
			dataGrid.removeConsumer(this);
			dataGrid.dispose();
		}
		

		@Override
		public void consumeUpdate(DataGridUpdate updates) {
			updateQueue.add(updates);
		}
		
		
	}
	
	

}
