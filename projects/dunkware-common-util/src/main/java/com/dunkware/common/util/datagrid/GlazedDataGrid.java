package com.dunkware.common.util.datagrid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.executor.DExecutor;

import ca.odell.glazedlists.ObservableElementList;
import ca.odell.glazedlists.TransformedList;
import ca.odell.glazedlists.event.ListEvent;
import ca.odell.glazedlists.event.ListEventListener;

public class GlazedDataGrid implements ListEventListener<Object>  {
	
	private ObservableElementList<?> list;
	private DataGrid dataGrid; 
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	public GlazedDataGrid(ObservableElementList<?> list, DExecutor executor, String idMethod) { 
		this.list = list; 
		
		
		this.dataGrid = DataGrid.newInstance(executor, idMethod);
		list.addListEventListener(this);
	}
	
	public void addConsumer(DataGridConsumer consumer) { 
		dataGrid.addConsumer(consumer);
	}
	
	public void removeConsumer(DataGridConsumer consumer) { 
		dataGrid.removeConsumer(consumer);
	}

	public void start() { 
		try {
			
			list.getReadWriteLock().readLock().lock();
			for (Object object : list) {
				// okay good it run in its own thread
				dataGrid.insert(object);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			list.getReadWriteLock().readLock().unlock();
		}
		
	}
	
	public void dispose() { 
		list.removeListEventListener(this);
		dataGrid.dispose();
		
	}

	@Override
	public void listChanged(ListEvent<Object> listChanges) {
		while(listChanges.next()) { 
		
			 final int type = listChanges.getType();
		     final int index = listChanges.getIndex();
		     Object old = null;
		     Object newme = null;
		     try {
				 old = listChanges.getOldValue();
				 newme = listChanges.getNewValue();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		    Object object = null;
		     try {
		    		listChanges.getSourceList().getReadWriteLock().readLock().lock();
		    	 if(newme.equals("UNKNOWN VALUE") && old.equals("UNKNOWN VALUE" )) { 
		    		 object = listChanges.getSourceList().get(index);
		    		 
		    	 }
		    	 
		    	
				
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			} finally { 
				listChanges.getSourceList().getReadWriteLock().readLock().unlock();
			}
		  
			if(type == ListEvent.DELETE) { 
				try {
					if(old.equals("UNKNOWN VALUE")) {
						
						dataGrid.delete(object);
					} else { 
						
						dataGrid.delete(old);
					}
				
					
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
				
			}
			if(type == ListEvent.INSERT) { 
				try {
					if(newme.equals("UNKNOWN VALUE")) { 
						if(object == null) { 
						
							continue;
						}
						
						dataGrid.insert(object);;
					} else { 
						System.out.println("insert on object type "+ newme.getClass().getName());;
						dataGrid.insert(newme);
					}
				
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
				
			}
			if(type == ListEvent.UPDATE) { 
				try {
					if(old.equals("UNKNOWN VALUE")) { 
						if(object != null) { 
							System.out.println("update on object type "+ object.getClass().getName());;
							dataGrid.update(object);
						}
					} else { 
						System.out.println("update on object type "+ old.getClass().getName());;
						dataGrid.update(old);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
				
			}
			if(listChanges.hasNext() == false) { 
				return;
			}
		}
		
	}
	
	

	
	
	
}
