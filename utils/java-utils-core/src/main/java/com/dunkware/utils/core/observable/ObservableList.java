package com.dunkware.utils.core.observable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ObservableList<E extends Observable<E>> extends ArrayList<E> implements Observer<E> {

	private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private final Vector<Observer<E>> listObservers = new Vector<>();

	public ObservableList() {
	}

	// Add list-level observer (for observing insertions, removals, etc.)
	public void addListObserver(Observer<E> observer) {
		lock.writeLock().lock();
		try {
			if (!listObservers.contains(observer)) {
				listObservers.add(observer);
			}
		} finally {
			lock.writeLock().unlock();
		}
	}

	public void removeListObserver(Observer<E> observer) {
		lock.writeLock().lock();
		try {
			listObservers.remove(observer);
		} finally {
			lock.writeLock().unlock();
		}
	}

	// Notify list observers about element additions
	private void notifyListInsert(E element) {
		lock.readLock().lock();
		try {
			for (Observer<E> observer : listObservers) {
				observer.listInsert(this, element);
			}
		} finally {
			lock.readLock().unlock();
		}
	}

	// Notify list observers about element removals
	private void notifyListRemove(E element) {
		lock.readLock().lock();
		try {
			for (Observer<E> observer : listObservers) {
				observer.listRemove(this, element);
			}
		} finally {
			lock.readLock().unlock();
		}
	}

	// Override add() to notify and observe elements
	@Override
	public boolean add(E e) {
		lock.writeLock().lock();
		try {
			boolean added = super.add(e);
			if (added) {
				e.addListener(this); // Add as observer
				notifyListInsert(e);
			}
			return added;
		} finally {
			lock.writeLock().unlock();
		}
	}

	// Override remove() to notify and unobserve elements
	@Override
	public boolean remove(Object o) {
		lock.writeLock().lock();
		try {
			boolean removed = super.remove(o);
			if (removed) {
				@SuppressWarnings("unchecked")
				E element = (E) o;
				element.removeListener(this); // Remove as observer
				notifyListRemove(element);
			}
			return removed;
		} finally {
			lock.writeLock().unlock();
		}
	}

	// Override addAll() to notify and observe batch insertions
	@Override
	public boolean addAll(Collection<? extends E> c) {
		lock.writeLock().lock();
		try {
			boolean added = super.addAll(c);
			if (added) {
				for (E e : c) {
					e.addListener(this);
					notifyListInsert(e);
				}
			}
			return added;
		} finally {
			lock.writeLock().unlock();
		}
	}

	// Override removeAll() to notify and unobserve batch removals
	@Override
	public boolean removeAll(Collection<?> c) {
		lock.writeLock().lock();
		try {
			boolean removed = super.removeAll(c);
			if (removed) {
				for (Object o : c) {
					@SuppressWarnings("unchecked")
					E element = (E) o;
					element.removeListener(this);
					notifyListRemove(element);
				}
			}
			return removed;
		} finally {
			lock.writeLock().unlock();
		}
	}

	// Override clear() to notify and unobserve all elements
	@Override
	public void clear() {
		lock.writeLock().lock();
		try {
			for (E e : this) {
				e.removeListener(this);
				notifyListRemove(e);
			}
			super.clear();
		} finally {
			lock.writeLock().unlock();
		}
	}

	// Notify list observers of a general update to any element in the list
	@Override
	public void beanUpdate(E bean) {
		// Notify that a specific element in the list has been updated
		lock.readLock().lock();
		try {
			for (Observer<E> observer : listObservers) {
				observer.beanUpdate(bean);  // Notify external listeners that an update occurred in the list
			}
		} finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public void propertyUpdate(E bean, String property, Object newValue) {
		// Notify that a specific element in the list has been updated
		lock.readLock().lock();
		try {
			for (Observer<E> observer : listObservers) {
				observer.propertyUpdate(bean,property,newValue);
			}
		} finally {
			lock.readLock().unlock();
		}
	}
}