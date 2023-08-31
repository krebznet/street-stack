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
import com.dunkware.common.util.uuid.DUUID;

import ca.odell.glazedlists.ObservableElementList;
import ca.odell.glazedlists.event.ListEvent;
import ca.odell.glazedlists.event.ListEventListener;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;

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
		flux = flux.subscribeOn(Schedulers.boundedElastic());
		flux.subscribe(new Subscriber<List<DataGridUpdate>>() {

			private Subscription s;

			@Override
			public void onSubscribe(Subscription s) {
				this.s = s;
				s.request(1);
				;
			}

			@Override
			public void onError(Throwable t) {
				s.cancel();
			}

			@Override
			public void onComplete() {
				s.cancel();
			}

			@Override
			public void onNext(List<DataGridUpdate> t) {
				s.request(1);
			}
		});

		logger.debug(marker, "starting glazed data grid " + id);
		;

		list.addListEventListener(this);
		try {

			logger.debug(marker, "registered glazed data grid to service");
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
			running = true;

			Thread register = new Thread() {

				public void run() {
					try {
						Thread.sleep(1000);
						GlazedDataGridService.get().register(GlazedDataGrid.this);

					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			};
			register.start();

		} catch (Exception e) {
			logger.error(marker, "Exception strarting glazed lists " + e.toString());

		} finally {
			list.getReadWriteLock().readLock().unlock();
		}

	}

	public void sendInserts() {
		try {
			list.getReadWriteLock().readLock().lock();
			int count = 0;
			for (Object object : list) {
				addUpdate(dataGrid.insert(object));
				count++;
			}
			logger.info(marker, "Sent {} inserts on grid", count);
		} catch (Exception e) {
			logger.error(marker, "Exception sending inserts " + e.toString());
		} finally {
			list.getReadWriteLock().readLock().unlock();
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
			// update = new DataGridUpdate();
			// update.setId(-1);
			// update.setType("PING");

			updatesLock.acquire();
			// updates.add(update);
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

			@Override
			public void onSubscribe(Subscription s) {
				this.sub = s;
				sub.request(1);
				;
			}

			@Override
			public void onNext(List<DataGridUpdate> t) {
				try {
					logger.info(marker, "On next in web subscription");
					sub.request(1);
				} catch (Exception e) {
					logger.error("error on next in bluepritn signal stream" + e.toString());
				}

			}

			@Override
			public void onError(Throwable t) {
				logger.info(marker, "on error in web subscription " + t.toString());
				sub.cancel();
				if (logger.isDebugEnabled()) {
					logger.debug("one error dispposing mocked broker list" + t.toString());
				}
				// list.dispose();
			}

			@Override
			public void onComplete() {
				logger.info(marker, "one complete in web sub");
				sub.cancel();
				if (logger.isDebugEnabled()) {
					logger.debug("disposing mocked broker list");
				}
				// list.dispose();

			}

		});
		return flux;
	}

	public void start() {

	}

	public void dispose() {
		GlazedDataGridService.get().unregister(this);
		running = false;
		list.removeListEventListener(this);
		dataGrid.dispose();

		if (logger.isDebugEnabled()) {
			logger.debug("Disposed {} ", id);
		}

	}

	@Override
	public void listChanged(ListEvent<Object> listChanges) {
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
