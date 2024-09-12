package com.dunkware.utils.core.observable;

public interface ObservableMonitor<T extends Observable<T>> {

	// public default void propertyUpdate(ObservablePropertyBean bean, Object
	// oldValue, Object newVaalue);

	public default void ListInsert(ObservableList<T> list, T bean) {

	}

	public default void ListRemove(ObservableList<T> list, T bean) {

	}
	
	public default void beanUpdate(T bean) { 
		
	}
	
	public default void beanPropertyUpdate(T bean, String property, Object oldValue, Object newValue) { 
		
	}

}

