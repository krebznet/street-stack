package com.dunkware.common.util.datagrid;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.json.DJson;

public class DataGrid {

	private String idMethod;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private List<DataGridConsumer> consumers = new ArrayList<DataGridConsumer>();
	private Semaphore consumerLock = new Semaphore(1);
	private UpdateRouter updaateRunner;

	private BlockingQueue<DataGridUpdate> updates = new LinkedBlockingQueue<DataGridUpdate>();
	private boolean disposed = false;

	public static DataGrid newInstance(String idMethod) {
		return new DataGrid(idMethod);

	}

	private DataGrid(String idMethod) {
		this.idMethod = idMethod;
		updaateRunner = new UpdateRouter();
		updaateRunner.start();
	}

	public void dispose() {
		if (!disposed) {
			updaateRunner.interrupt();
			disposed = true;
		}
	}

	public void insert(Object object) throws DataGridException {
		DataGridUpdate update = new DataGridUpdate();
		update.setRowId(getObjectId(object));
		update.setType(DataGridUpdateType.INSERT.name());
		;
		try {
			update.setData(DJson.serialize(object));
			updates.put(update);
		} catch (Exception e) {
			throw new DataGridException("Exception serializing new row object " + e.toString());
		}

	}

	public void update(Object object) throws DataGridException {
		DataGridUpdate update = new DataGridUpdate();
		update.setRowId(getObjectId(object));
		update.setType(DataGridUpdateType.UPDATE.name());
		;
		try {
			update.setData(DJson.serialize(object));
			updates.put(update);
		} catch (Exception e) {
			throw new DataGridException("Exception serializing new row object " + e.toString());
		}
	}

	public void delete(Object object) throws DataGridException {
		DataGridUpdate update = new DataGridUpdate();
		update.setRowId(getObjectId(object));
		update.setType(DataGridUpdateType.DELETE.name());
		;
		try {
			updates.put(update);
		} catch (Exception e) {
			throw new DataGridException("Exception putting delete object on queue " + e.toString());
		}

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
					
					if(update == null) { 
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
