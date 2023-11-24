package com.dunkware.common.util.glazed;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.datagrid.DataGrid;
import com.dunkware.common.util.datagrid.DataGridUpdate;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.uuid.DUUID;

import ca.odell.glazedlists.ObservableElementList;
import ca.odell.glazedlists.event.ListEvent;
import ca.odell.glazedlists.event.ListEventListener;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class GlazedDataGrid implements ListEventListener<Object> {

	private ObservableElementList<?> list;
	private DataGrid dataGrid;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("glazedgrid");
	private List<DataGridUpdate> updates = new ArrayList<DataGridUpdate>();
	private Semaphore updatesLock = new Semaphore(1);
	private Sinks.Many<List<DataGridUpdate>> sink = Sinks.many().multicast().onBackpressureBuffer();
	private Flux<List<DataGridUpdate>> flux;
	private String id = DUUID.randomUUID(5);
	private boolean running = false;

	private List<GlazedDataGridListener> listeners = new ArrayList<GlazedDataGridListener>();
	private Semaphore listenerLock = new Semaphore(1);

	public static GlazedDataGrid newInstance(ObservableElementList<?> list, DExecutor executor, String idMethod) {
		return new GlazedDataGrid(list, executor, idMethod);
	}

	public GlazedDataGrid(ObservableElementList<?> list, DExecutor executor, String idMethod) {
		this.list = list;
		this.dataGrid = DataGrid.newInstance(idMethod);
		flux = sink.asFlux().doOnCancel(() -> {
			running = false;
			if (logger.isDebugEnabled()) {
				logger.debug("Flux onCancel invoked " + id);
			}
			sink.tryEmitComplete();

			try {
				dispose();
			} catch (Exception e) {
				logger.error(marker, "Exception on DoCacnel dispose" + e.toString());
			}
			System.gc();

		});

		logger.debug(marker, "starting glazed data grid " + id);
		;

		list.addListEventListener(this);
		try {

			logger.debug(marker, "registered glazed data grid to service");
			try {

				list.getReadWriteLock().readLock().lock();
				if (list.size() == 0) {
					DataGridUpdate update = new DataGridUpdate();
					update.setType("INIT");
					update.setId(-1);
					addUpdate(update);
				}
				int count = 0;
				for (Object object : list) {
					try {
						count++;
						addUpdate(dataGrid.insert(object));
					} catch (Exception e) {
						logger.error(marker, "Exception adding new insert in glazed " + e.toString());
					} finally {

					}

				}
			} catch (Exception e2) {
				// TODO: handle exception
			} finally {
				list.getReadWriteLock().readLock().unlock();
			}
			running = true;

		} catch (Exception e) {
			logger.error(marker, "Exception strarting glazed lists " + e.toString());

		} finally {

		}

	}

	public boolean isRunning() {
		return running;
	}

	public String getId() {
		return id;
	}

	/**
	 * Called from single thread service, what we need is a scheduler or runnable
	 * registered and unregistered on grid servie
	 */
	public void emitUpdates() {
		try {
			if (!running) {
				return;
			}
			DataGridUpdate update = new DataGridUpdate();
			update.setId(-1);
			update.setType("PING");

			updatesLock.acquire();
			updates.add(update);
			if (updates.size() == 0) {
				return;
			}

			sink.tryEmitNext(updates);
			updates.clear();
		} catch (Exception e) {
			logger.error("exception emiting updates, should be disposed " + e.toString());
		} finally {
			updatesLock.release();
		}
	}

	public Flux<List<DataGridUpdate>> getUpdates() {
		
		  flux.subscribe(new Subscriber<List<DataGridUpdate>>() {
		  
		  private Subscription sub;
		  
		  @Override public void onSubscribe(Subscription s) { this.sub = s;
		  sub.request(1);; }
		  
		  @Override public void onNext(List<DataGridUpdate> t) { try {
		  logger.debug("on next " + DJson.serialize(t)); } catch (Exception e) {
		  e.printStackTrace(); // TODO: handle exception } sub.request(1);
		  
		  }
		  }
		  
		  @Override public void onError(Throwable t) { 
			  
			  sub.cancel();
			  
		  	
		  	
		
		  }
		  
		  
		  
		  @Override public void onComplete() { sub.cancel(); if(logger.isDebugEnabled()
		  ) { logger.debug("disposing mocked broker list"); } // list.dispose();
		  
		  
		  }});
		 
						  return flux;
								 
			
	
	}

	public void start() {
		running = true;
		if (list.size() == 0) {
			DataGridUpdate update = new DataGridUpdate();
			update.setType("INIT");
			update.setId(-1);
			addUpdate(update);
		}
		Thread register = new Thread() {

			public void run() {
				try {
					Thread.sleep(100);
					GlazedDataGridService.get().register(GlazedDataGrid.this);

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		};
		register.start();

	}

	public void dispose() {
		running = false;
		list.removeListEventListener(this);
		GlazedDataGridService.get().unregister(this);

		dataGrid.dispose();

		if (logger.isDebugEnabled()) {
			logger.debug("Disposed {} ", id);
		}

	}

	public void addListener(GlazedDataGridListener listener) {
		try {
			listenerLock.acquire();
			this.listeners.add(listener);
		} catch (Exception e) {
			logger.error(marker, "Exception adding listener " + e.toString());
			;
		} finally {
			listenerLock.release();
		}
	}

	public void removeListener(GlazedDataGridListener listener) {
		try {
			listenerLock.acquire();
			this.listeners.remove(listener);
		} catch (Exception e) {
			logger.error(marker, "Exception removing listener " + e.toString());
			;
		} finally {
			listenerLock.release();
		}
	}

	private void notifyDispose() {
		try {
			listenerLock.acquire();
			for (GlazedDataGridListener glazedDataGridListener : listeners) {
				glazedDataGridListener.onGridDispose(this);
				;
			}
		} catch (Exception e) {
			logger.error(marker, "Exception adding listener " + e.toString());
			;
		} finally {
			listenerLock.release();
		}
	}

	private void notifyInit() {
		try {
			listenerLock.acquire();
			for (GlazedDataGridListener glazedDataGridListener : listeners) {
				glazedDataGridListener.onGridInit(this);
			}
		} catch (Exception e) {
			logger.error(marker, "Exception adding listener " + e.toString());
			;
		} finally {
			listenerLock.release();
		}
	}

	@Override
	public void listChanged(ListEvent<Object> listChanges) {
		if (!running) {
			return;
		}
		while (listChanges.next()) {

			final int type = listChanges.getType();
			final int index = listChanges.getIndex();
			Object old = null;
			Object newme = null;
			try {
				old = listChanges.getOldValue();
				newme = listChanges.getNewValue();
			} catch (Exception e) {
				logger.error("Exception getting old new value " + e.toString());

			}
			Object object = null;
			try {
				listChanges.getSourceList().getReadWriteLock().readLock().lock();
				if (newme.equals("UNKNOWN VALUE") && old.equals("UNKNOWN VALUE")) {
					object = listChanges.getSourceList().get(index);

				}

			} catch (Exception e) {
				if (e instanceof InterruptedException) {
					return;
				}
				logger.error("Exception taking list change " + e.toString());

			} finally {
				listChanges.getSourceList().getReadWriteLock().readLock().unlock();
			}

			if (type == ListEvent.DELETE) {
				try {
					if (old.equals("UNKNOWN VALUE")) {

						addUpdate(dataGrid.delete(object));
					} else {

						addUpdate(dataGrid.delete(old));
					}

				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
					logger.error("exceptin delete " + e.toString());

				}

			}
			if (type == ListEvent.INSERT) {
				try {
					if (newme.equals("UNKNOWN VALUE")) {
						if (object == null) {

							continue;
						}

						addUpdate(dataGrid.insert(object));
						;
					} else {

						addUpdate(dataGrid.insert(newme));
					}

				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
					logger.error("Exception inserting " + e.toString());
				}

			}
			if (type == ListEvent.UPDATE) {
				try {
					if (old.equals("UNKNOWN VALUE")) {
						if (object != null) {

							addUpdate(dataGrid.update(object));
						}
					} else {

						addUpdate(dataGrid.update(old));
					}

				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
					logger.error("Exception update " + e.toString());

				}

			}
			if (listChanges.hasNext() == false) {
				return;
			}
		}

	}

	private void addUpdate(DataGridUpdate update) {
		try {
			logger.debug("adding update");
			updatesLock.acquire();
			updates.add(update);
			logger.debug("added update");
			;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			updatesLock.release();
		}
	}

}
