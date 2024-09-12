package com.dunkware.trade.service.stream.serverd.web.components;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.java.utils.glazed.grid.DataGridUpdate;
import com.dunkware.java.utils.glazed.grid.GlazedDataGrid;
import com.dunkware.java.utils.glazed.grid.GlazedDataGridConnector;
import com.dunkware.java.utils.glazed.grid.GlazedDataGridListener;
import com.dunkware.trade.service.stream.serverd.controller.StreamController;
import com.dunkware.trade.service.stream.serverd.controller.StreamControllerService;
import com.dunkware.trade.service.stream.serverd.controller.session.StreamSession;
import com.dunkware.trade.service.stream.serverd.controller.session.StreamSessionNode;
import com.dunkware.utils.core.concurrent.DunkExecutor;
import com.dunkware.utils.core.helpers.DunkRandom;
import com.dunkware.utils.core.helpers.DunkUUID;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.ObservableElementList;
import reactor.core.publisher.Flux;


/**
 * this gets created for each request to get entity variables, we will
 * call service every one second and send streaming data to react app 
 * using DashboardDataGrid component. 
 */
public class EntitySessionVarGrid implements GlazedDataGridListener {

	private int entityId; 
	
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
	
	// this is the list of variables. 
	private ObservableElementList<EntitySessionVarBean> beans;
	
	private GlazedDataGrid dataGrid;
	
		
	@Autowired
	private StreamControllerService streamService; 
	
	private StreamController streamController; 
	

	private StreamSession straeamSession;

	
	private StreamSessionNode sessionNode; 
	
	private VariableUpdater updater; 
	
	/**
	 * Okay great here we are, we are going to stream values of variables for an entity back 
	 * to the UI
	 * @param executor
	 * @param entitySessionNode
	 * @return
	 * @throws Exception
	 */
	public Flux<List<DataGridUpdate>> init(DunkExecutor executor, StreamController streamController, int entityId) throws Exception { 
	  
			  beans = new ObservableElementList<EntitySessionVarBean>(
						GlazedLists.threadSafeList(new BasicEventList<EntitySessionVarBean>()),
						new GlazedDataGridConnector<EntitySessionVarBean>());
				dataGrid = GlazedDataGrid.newInstance(beans,executor,"getId");
				dataGrid.start();
				dataGrid.addListener(this);
				// this is async programming we are starting another thread that will grab variable 
				// values from the correct computing node for now we mock it. 
				updater = new VariableUpdater();
				updater.start();
				
				return dataGrid.getUpdates();
				
				
				
				
	}


	@Override
	public void onGridDispose(GlazedDataGrid dataGrid) {
		// this method is called when the user goes away from the page
		// when the http stream is broken we need to clean up resources
		// all we do is interupt or stop the updater thread 
		updater.interrupt();
		
		
	}



	@Override
	public void onGridInit(GlazedDataGrid dataGrid) {
		// nothing here
		// okay we are ready to go. 
		
	}
	
	
	/** 
	 * for now, create a new thread for each view of variables 
	 * this will update the streaming data every 1 second with 
	 * current values of variable. lets hard code for now. 
	 */
	private class VariableUpdater extends Thread { 
		
		public void run() { 
			int runCount = 0; 
			
			EntitySessionVarBean bean1 = null;
			EntitySessionVarBean bean2 = null;
			
			while(!interrupted()) {
				try {
					// had code right for now? 
					if(runCount == 0) { 
						// just hard code 2 rows of data for the grid 
						 bean1 = new EntitySessionVarBean();
						bean1.setId(1);
						bean1.setIdent("Variable 1");
						bean1.setValue("32.23");
						bean1.setLastUpdate("09:32:33");
						beans.add(bean1);
						
						bean2 = new EntitySessionVarBean();
						bean2.setId(2);
						bean2.setIdent("Variable 2");
						bean2.setValue("55.5");
						bean2.setLastUpdate("10:30:33");
						beans.add(bean2);
						
					} else { 
						// random update on the beans to trigger streaming data update
						bean1.setValue(DunkUUID.randomUUID(5));
						bean1.notifyUpdate();
						bean2.setValue(DunkUUID.randomUUID(5));
						bean2.notifyUpdate();
					}
					runCount++;
					Thread.sleep(1000);
					
				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						// fine interrupted; 
						return;
					}
					// else real exception log to central elastic search 
					logger.error("exception in entity variable data grid updater " + e.toString());
					return;
				}
			}
			
			
		}
	}

	

	
	
	

	
	
}
