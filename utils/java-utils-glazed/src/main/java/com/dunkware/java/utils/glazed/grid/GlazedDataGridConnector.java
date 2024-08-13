package com.dunkware.java.utils.glazed.grid;

import java.util.EventListener;

import com.dunkware.utils.core.observable.ObservableBean;
import com.dunkware.utils.core.observable.ObservableBeanListener;

import ca.odell.glazedlists.ObservableElementChangeHandler;
import ca.odell.glazedlists.ObservableElementList;

public class GlazedDataGridConnector<E extends ObservableBean> implements ObservableElementList.Connector<E>, ObservableBeanListener, EventListener  {

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
	public void observableUpdate(ObservableBean bean) {
		list.elementChanged(bean);;
	}

	
	
	

	

}
