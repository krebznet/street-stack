package com.dunkware.common.util.datagrid;

import com.dunkware.common.util.executor.DExecutor;

import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.ObservableElementList;
import ca.odell.glazedlists.event.ListEvent;
import ca.odell.glazedlists.event.ListEventListener;

public class GlazedDataGrid implements ListEventListener<Object>  {
	
	private ObservableElementList<?> list;
	private DataGrid dataGrid; 
	
	
	public GlazedDataGrid(ObservableElementList<?> list, DExecutor executor, String idMethod) { 
		this.list = list; 
		
		this.dataGrid = DataGrid.newInstance(executor, idMethod);
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
		list.addListEventListener(this);
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
						System.out.println("fuck");
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
						dataGrid.insert(object);
					} else { 
						dataGrid.insert(newme);
					}
				
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
				
			}
			if(type == ListEvent.UPDATE) { 
				try {
					dataGrid.update(old);
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
