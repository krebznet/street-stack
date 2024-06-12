package com.dunkware.xstream.core.scanner;

import com.dunkware.java.utils.glazed.grid.GlazedDataGrid;
import com.dunkware.java.utils.glazed.grid.GlazedDataGridConnector;
import com.dunkware.utils.core.observable.ObservableBean;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntityQuery;
import com.dunkware.xstream.api.XStreamEntityScannerException;
import com.dunkware.xstream.model.scanner.XStreamEntityScannerModel;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.ObservableElementList;

public class XStreamEntityScannerImpl { // a channel 
	
	private XStreamEntityScannerModel model; 
	
	private XStream stream; 
	
	private GlazedDataGrid dataGrid;
	
	private ObservableElementList<ObservableBean> scannerList;
	
	private XStreamEntityQuery entityQuery;
	
	public void start(XStreamEntityScannerModel model, XStream stream) throws XStreamEntityScannerException {
		this.stream = stream;
		scannerList = new ObservableElementList<ObservableBean>(
			GlazedLists.threadSafeList(new BasicEventList<ObservableBean>()),
					new GlazedDataGridConnector<ObservableBean>());
		try {
		//stream.entityQuery(model.getQueryModel());
		} catch (Exception e) {
			throw new XStreamEntityScannerException();
		}
	}
	
	public GlazedDataGrid getDataGrid() { 
		return dataGrid; 
	}
	
	

}
