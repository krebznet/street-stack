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

/**
 * Stateless Component just gets initialized with a method for getting ID
 * and returns DataGridUpdate on insert/detle/update methods
 * 
 * @author duncankrebs
 *
 */
public class DataGrid {

	private String idMethod;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private List<Number> rowIds = new ArrayList<Number>();
	private Semaphore rowIdLock = new Semaphore(1);

	public static DataGrid newInstance(String idMethod) {
		return new DataGrid(idMethod);

	}
	
	

	private DataGrid(String idMethod) {
		
		this.idMethod = idMethod;
		
	}

	public void dispose() {
		rowIds.clear();
	}

	public DataGridUpdate insert(final Object object) throws DataGridException {
		try {
			
			DataGridUpdate update = new DataGridUpdate();
			Number id = getObjectId(object);
			rowIdLock.acquire();
			if(rowIds.contains(id)) { 
				logger.error("DUP MOTHER FUCKER " + id);
				rowIdLock.release();
			}
			rowIds.add(id);
			rowIdLock.release();
			
			update.setId(id);
			update.setType(DataGridUpdateType.ADD.name());
			update.setJson(object);
			try {
				
				return update;
				
			} catch (Exception e) {
				logger.error("Exception serializing new row object class " + object.getClass().getName() + " "
						+ e.toString());
				throw new DataGridException("Exception insert " + e.toString());

			}

		} catch (Exception e) {
			logger.error("Exception getting object id " + e.toString());
			throw new DataGridException(e.toString());

		}

	}

	public DataGridUpdate update(Object object) throws DataGridException {
		DataGridUpdate update = new DataGridUpdate();
		try {
			update.setId(getObjectId(object));
		} catch (Exception e) {
			logger.error(
					"exception getting id on object type " + object.getClass().getName() + " " + e.toString());
			throw new DataGridException(e.toString());
		}

		update.setType(DataGridUpdateType.UPDATE.name());
		;
		try {
			update.setJson(object);
		} catch (Exception e) {
			logger.error("Exception serializing new row object " + e.toString());
			throw new DataGridException(e.toString());
		}
		return update;
	

	}

	public DataGridUpdate delete(final Object object) throws DataGridException {
		
		DataGridUpdate update = new DataGridUpdate();
		try {
			Number objectId = getObjectId(object);
			update.setId(getObjectId(object));
			update.setType("DELETE");
			
			try {
				rowIdLock.acquire();
				if(rowIds.contains(objectId)) { 
					rowIds.remove(objectId);
				}
			} catch (Exception e) {
				// TODO: handle exception
			} finally { 
				rowIdLock.release();
			}
			
		} catch (Exception e) {
			logger.error("error getting id on method " + idMethod + " " + e.toString());
			throw new DataGridException(e.toString());
		}
		return update;
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

	

}
