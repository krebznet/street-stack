package com.dunkware.common.util.datagrid;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.common.util.json.DJson;

public class DataGrid {

	private String idMethod;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private List<DataGridConsumer> consumers = new ArrayList<DataGridConsumer>();
	private Semaphore consumerLock = new Semaphore(1);
	private UpdateRouter updaateRunner;

	private BlockingQueue<DataGridUpdate> updates = new LinkedBlockingQueue<DataGridUpdate>();
	private boolean disposed = false;
	private DExecutor executor;
	private List<Number> rowIds = new ArrayList<Number>();
	private Semaphore rowIdLock = new Semaphore(1);

	public static DataGrid newInstance(DExecutor executor, String idMethod) {
		return new DataGrid(executor, idMethod);

	}

	private DataGrid(DExecutor executor, String idMethod) {
		this.idMethod = idMethod;
		this.executor = executor;
		updaateRunner = new UpdateRouter();
		updaateRunner.start();
	}

	public void dispose() {
		if (!disposed) {
			updaateRunner.interrupt();
			disposed = true;
		}
	}

	public void insert(final Object object) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				try {
					DataGridUpdate update = new DataGridUpdate();
					Number id = getObjectId(object);
					rowIdLock.acquire();
					if(rowIds.contains(id)) { 
						logger.error("DUP MOTHER FUCKER " + id);
						rowIdLock.release();
						return;
					}
					rowIds.add(id);
					rowIdLock.release();
					
					update.setId(id);
					update.setType(DataGridUpdateType.ADD.name());
					update.setJson(object);
					try {
						
						updates.put(update);
						
					} catch (Exception e) {
						logger.error("Exception serializing new row object class " + object.getClass().getName() + " "
								+ e.toString());

					}

				} catch (Exception e) {
					logger.error("Exception getting object id " + e.toString());

				}
			}
		};
		executor.execute(runnable);

	}

	public void update(Object object) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				DataGridUpdate update = new DataGridUpdate();
				try {
					update.setId(getObjectId(object));
				} catch (Exception e) {
					logger.error(
							"exception getting id on object type " + object.getClass().getName() + " " + e.toString());
					return;
				}

				update.setType(DataGridUpdateType.UPDATE.name());
				;
				try {
					update.setJson(object);
					updates.put(update);
				} catch (Exception e) {
					logger.error("Exception serializing new row object " + e.toString());
				}
			}
		};
		executor.execute(runnable);

	}

	public void delete(final Object object) {
		
		Runnable runnable = new Runnable() {

			@Override
			public void run() {

				DataGridUpdate update = new DataGridUpdate();
				try {
					update.setId(getObjectId(object));
					//update.setJson(DJson.serialize(object));
					update.setType("DELETE");
				} catch (Exception e) {
					logger.error("error getting id on method " + idMethod + " " + e.toString());
					return;
				}
				
				
				;
				try {
					updates.put(update);
				} catch (Exception e) {
					logger.error("Exception putting delete object on queue " + e.toString());
				}
			}
		};
		executor.execute(runnable);

	}

	public void addConsumer(DataGridConsumer consumer) {
		try {
			consumerLock.acquire();
			consumers.add(consumer);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			consumerLock.release();
		}
	}

	public void removeConsumer(DataGridConsumer consumer) {
		try {
			consumerLock.acquire();
			consumers.remove(consumer);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			consumerLock.release();
		}
	}

	private Number getObjectId(Object object) throws DataGridException {

		try {
			Method method = object.getClass().getMethod(idMethod);
			Number id = (Number) method.invoke(object, null);
			return id;
		} catch (Exception e) {
			throw new DataGridException("Exception getting object id via reflection " + e.toString());
		}
	}

	private class UpdateRouter extends Thread {

		public void run() {
			while (!interrupted()) {
				try {

					DataGridUpdate update = updates.take();
					
					if (update == null) {
						continue;
					}
					
					try {
						consumerLock.acquire();
						
						for (DataGridConsumer consumer : consumers) {
							try {
								consumer.consumeUpdate(update);
							} catch (Exception e) {
								logger.error("Exception invoking data grid consumer updates " + e.toString());
								;
							}

						}
					} catch (Exception e) {
						logger.error("outer router consumer dispatch exception " + e.toString());
						;
					} finally {
						consumerLock.release();
					}

				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
					logger.error("Exception in update router " + e.toString());
				}
			}
		}
	}

}
