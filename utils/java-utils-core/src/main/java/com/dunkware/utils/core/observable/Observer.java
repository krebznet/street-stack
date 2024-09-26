package com.dunkware.utils.core.observable;

public interface Observer<T extends Observable<T>> {

	// public default void propertyUpdate(ObservablePropertyBean bean, Object
	// oldValue, Object newVaalue);

	public default void listInsert(ObservableList<T> list, T bean) {

	}

	public default void listRemove(ObservableList<T> list, T bean) {

	}
	
	public default void beanUpdate(T bean) {
		
	}
	
	public default void propertyUpdate(T bean, String property, Object value) {
		
	}

}

