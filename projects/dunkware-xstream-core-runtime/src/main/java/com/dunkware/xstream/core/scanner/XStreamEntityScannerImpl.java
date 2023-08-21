package com.dunkware.xstream.core.scanner;

import com.dunkware.common.util.glazed.GlazedDataGrid;
import com.dunkware.common.util.observable.ObservableBeanListConnector;
import com.dunkware.common.util.observable.ObservableNetBean;
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
	
	private ObservableElementList<ObservableNetBean> scannerList;
	
	private XStreamEntityQuery entityQuery;
	
	public void start(XStreamEntityScannerModel model, XStream stream) throws XStreamEntityScannerException {
		this.stream = stream;
		scannerList = new ObservableElementList<ObservableNetBean>(
			GlazedLists.threadSafeList(new BasicEventList<ObservableNetBean>()),
					new ObservableBeanListConnector<ObservableNetBean>());
		try {
			entityQuery = stream.entityQuery(model.getQueryModel());
		} catch (Exception e) {
			throw new XStreamEntityScannerException();
		}
	}
	
	public GlazedDataGrid getDataGrid() { 
		return dataGrid; 
	}
	
	

}
