package com.dunkware.common.util.databean;

import java.util.EventListener;

import ca.odell.glazedlists.ObservableElementChangeHandler;
import ca.odell.glazedlists.ObservableElementList;

public class DataBeanConnector<E extends DataBean> implements ObservableElementList.Connector<E>, DataBeanObserver, EventListener  {

	ObservableElementChangeHandler<? extends E> list;
	
	@Override
	public EventListener installListener(E element) {
		element.addObserver(this);
		return this;
		
	}

	@Override
	public void uninstallListener(E element, EventListener listener) {
		element.removeListener(this);
	}

	@Override
	public void setObservableElementList(ObservableElementChangeHandler<? extends E> list) {
		this.list = list;
		
	}

	@Override
	public void dataBeanUpdate(DataBean bean) {
		list.elementChanged(bean);;
	}
	
	

	

}
