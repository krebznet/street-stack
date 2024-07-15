package com.dunkware.street.smart.runtime.adapter;

import java.util.EventListener;

import com.dunkware.utils.core.observable.ObservableBean;
import com.dunkware.utils.core.observable.ObservableListener;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.ObservableElementChangeHandler;
import ca.odell.glazedlists.ObservableElementList;

public class SessionViewer {

	private ObservableElementList<SessionTrade> trades;
	
	private ObservableElementList<SessionOrder> orders;
	
	private ObservableElementList<SessionEvent> events;
	
	private SessionMetrics metrics = new SessionMetrics();
	
	public static SessionViewer newInstance() { 
		return new SessionViewer();
	}
	
	private SessionViewer() { 
		trades =  new ObservableElementList<SessionTrade>(
				GlazedLists.threadSafeList(new BasicEventList<SessionTrade>()),
				new GlazedDataGridConnector<SessionTrade>());
		
		orders =  new ObservableElementList<SessionOrder>(
				GlazedLists.threadSafeList(new BasicEventList<SessionOrder>()),
				new GlazedDataGridConnector<SessionOrder>());
		
		events =  new ObservableElementList<SessionEvent>(
				GlazedLists.threadSafeList(new BasicEventList<SessionEvent>()),
				new GlazedDataGridConnector<SessionEvent>());
		
		
	}
	
	/**
	 * want a tool bar item for this. 
	 * @throws Exception
	 */
	public void stopSession() throws Exception { 
		
	}
	
	/**
	 * want a tool bar iem for this 
	 * @throws Exception
	 */
	public void startSession() throws Exception { 
		
	}
	
	/**
	 * want a tool bar item for this 
	 * @throws Excepion
	 */
	public void kilLSession() throws Exception { 
		
	}
	
	
	public ObservableElementList<SessionTrade> getTrades() {
		return trades;
	}

	public ObservableElementList<SessionOrder> getOrders() {
		return orders;
	}

	public ObservableElementList<SessionEvent> getEvents() {
		return events;
	}

	public SessionMetrics getMetrics() {
		return metrics;
	}




	public class GlazedDataGridConnector<E extends ObservableBean> implements ObservableElementList.Connector<E>, ObservableListener, EventListener  {

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
	
	
}
