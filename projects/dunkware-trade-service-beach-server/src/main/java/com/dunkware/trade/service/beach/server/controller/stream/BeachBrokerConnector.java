package com.dunkware.trade.service.beach.server.controller.stream;

import java.util.EventListener;

import com.dunkware.trade.service.beach.server.runtime.BeachBrokerBean;

import ca.odell.glazedlists.ObservableElementChangeHandler;
import ca.odell.glazedlists.ObservableElementList;

public class BeachBrokerConnector implements ObservableElementList.Connector<BeachBrokerBean> {

	@Override
	public EventListener installListener(BeachBrokerBean element) {
		// TODO Auto-generated method stub
		return new EventListener() {
			
			
		};
		
	}

	@Override
	public void uninstallListener(BeachBrokerBean element, EventListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setObservableElementList(ObservableElementChangeHandler<? extends BeachBrokerBean> list) {
		// TODO Auto-generated method stub
		
	}

	

}
