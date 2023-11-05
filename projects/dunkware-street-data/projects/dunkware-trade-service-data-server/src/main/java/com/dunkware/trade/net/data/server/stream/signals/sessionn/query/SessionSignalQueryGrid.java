package com.dunkware.trade.net.data.server.stream.signals.sessionn.query;

import com.dunkware.common.util.glazed.GlazedDataGrid;
import com.dunkware.common.util.glazed.GlazedDataGridListener;
import com.dunkware.common.util.observable.ObservableBeanListConnector;
import com.dunkware.trade.net.data.server.stream.signals.sessionn.StreamSignalsSession;
import com.dunkware.trade.service.data.model.signals.bean.StreamSignalBean;
import com.dunkware.trade.service.data.model.signals.query.StreamSignalSessionQuery;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.ObservableElementList;

/**
 * OKay strams the stram signals session type search 
 * as streaming data.
 * @author duncankrebs
 *
 */
public class SessionSignalQueryGrid implements GlazedDataGridListener {

	private StreamSignalsSession session; 
	private StreamSignalSessionQuery query;
	
	private ObservableElementList<StreamSignalBean> beans;
	
	private GlazedDataGrid dataGrid;
	
	public void start(StreamSignalSessionQuery query, StreamSignalsSession session) throws Exception {
		this.query = query;
		this.session = session; 
		beans =  new ObservableElementList<StreamSignalBean>(
				GlazedLists.threadSafeList(new BasicEventList<StreamSignalBean>()),
				new ObservableBeanListConnector<StreamSignalBean>());
		
		
		
	}
	
	
	
	public GlazedDataGrid getDataGrid() { 
		return dataGrid;
	}
	
	
	@Override
	public void onGridDispose(GlazedDataGrid dataGrid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGridInit(GlazedDataGrid dataGrid) {
		// TODO Auto-generated method stub
		
	}

	
	
}
