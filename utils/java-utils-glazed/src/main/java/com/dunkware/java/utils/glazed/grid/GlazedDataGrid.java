package com.dunkware.java.utils.glazed.grid;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.utils.core.concurrent.DunkExecutor;
import com.dunkware.utils.core.helpers.DunkUUID;

import ca.odell.glazedlists.ObservableElementList;
import ca.odell.glazedlists.event.ListEvent;
import ca.odell.glazedlists.event.ListEventListener;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
//TODO: AVINASHANV-40 Glazed Data Grid
/**
 * this is where it all comes together a glazed data grid has an ObservableElement list
 * that is configured to observe objects in the list and it also trigges events for 
 * item inserts and deletes. what happens a listener like the interface this class
 * implements gets notified on all bean updates deletes and inserts. when it gets notified
 * it used DataGrid to create update model and puts them in a list that another worker will
 * pick up and send. even cooler is these things are hooked up to streaming spring boot web
 * services using react. so a WebFlux is created on line 66 and references a method to to 
 * be notified in dispose or when the client http stream disconnects so it can clean up
 * the events from the observable element list create DataGridUpdate objects line 141 is
 * method emitUpdates called every second to use a FluxSing created from the flux to publish
 * the messages, in the context of a reactive web service it has a subscription to the Flux
 * and uses the publisher for the http stream to consume DataGrid Updates and send to web 
 * client. i wil show you example of web service  next. 
 */
/**
 * this is the money one, what happens is we use a ObservableElementList that is configured
 * to listen to observable beans within its list and it also notifies events on item inserts
 * and item deletes. We configure this to have a DataGrid
 */
public class GlazedDataGrid implements ListEventListener<Object> {

	private ObservableElementList<?> list;
	private DataGrid dataGrid;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("glazedgrid");
	private List<DataGridUpdate> updates = new ArrayList<DataGridUpdate>();
	private Semaphore updatesLock = new Semaphore(1);
	private Flux<List<DataGridUpdate>> flux;
	private FluxSink<List<DataGridUpdate>> fluxSink;
	private String id = DunkUUID.randomUUID(5);
	private boolean running = false;
	private boolean disposed = false;

	private List<GlazedDataGridListener> listeners = new ArrayList<GlazedDataGridListener>();
	private Semaphore listenerLock = new Semaphore(1);
	
	private AtomicInteger pingCounter = new AtomicInteger(0);
	private DunkExecutor executor;
	
	private Flusher flusher;
	public static GlazedDataGrid newInstance(ObservableElementList<?> list, DunkExecutor executor, String idMethod) {
		return new GlazedDataGrid(list, executor, idMethod);
	}

	public GlazedDataGrid(ObservableElementList<?> list, DunkExecutor executor, String idMethod) {
		this.list = list;
		this.executor = executor;
		this.dataGrid = DataGrid.newInstance(idMethod);
		
		flux = Flux.create(this::process);
		
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
				
				
			} finally {
				list.getReadWriteLock().readLock().unlock();
			}
			running = true;

		} catch (Exception e) {
			logger.error(marker, "Exception strarting glazed lists " + e.toString());

		} finally {

		}

	}
	
	public Flux<List<DataGridUpdate>> getUpdates() { 
		return flux;
	}
	
	 private void process(final FluxSink<List<DataGridUpdate>> sink) {
	    this.fluxSink = sink;
	    sink.onCancel(() -> dispose());

	    sink.onDispose(() -> dispose());
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
			updatesLock.acquire();
			if (!running) {
				return;
			}
		
			int pingCount = pingCounter.incrementAndGet();
			if(pingCount > 10) { 
				DataGridUpdate update = new DataGridUpdate();
				update.setId(-1);
				update.setType("PING");
			
				pingCounter.set(0);;
				
				updates.add(update);
			}
			

			
			if (updates.size() == 0) {
				return;
			}

			fluxSink.next(updates);
			updates.clear();
		} catch (Exception e) {
			logger.error("exception emiting updates, should be disposed " + e.toString());
		} finally {
			updatesLock.release();
		}
	}

	public void start() {
		running = true;
		if (list.size() == 0) {
			DataGridUpdate update = new DataGridUpdate();
			update.setType("INIT");
			update.setId(-1);
			addUpdate(update);
		}
		flusher = new Flusher();
		flusher.start();
		
		
	}

	public void dispose() {
		if(disposed) { 
			return;
		}
		Thread disposer = new Thread() { 
		
			public void run() { 
				flusher.interrupt();
				if(!running) { 
					logger.error("calling dispose when not running");
					return;
					
				}
				running = false;
				for (GlazedDataGridListener listener : listeners) {
					try {
						listener.onGridDispose(GlazedDataGrid.this);	
					} catch (Exception e) {
						logger.error(marker, "Dispose listener {} exceptopn {} ",listener.getClass().getName(),e.toString(),e);

					}
					
				}
				
				list.removeListEventListener(GlazedDataGrid.this);
	
				dataGrid.dispose();

				if (logger.isDebugEnabled()) {
					logger.debug("Disposed {} ", id);
				}	
				
			}
		};
		disposer.start();
		disposed = true;
		


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
			
		} finally {
			updatesLock.release();
		}
	}
	
	
	private class Flusher extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				try {
					emitUpdates();
					Thread.sleep(100);
				} catch (Exception e) {
					
				}
			}
		}
	}
	
	
	
	


}
