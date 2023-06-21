package com.dunkware.common.util.datagrid;

import java.util.List;

import com.dunkware.common.util.databean.DataBeanConnector;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.common.util.json.DJson;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.ObservableElementList;

public class GlazedDataGridTest2 implements DataGridConsumer {

	public static void main(String[] args) {
		new GlazedDataGridTest2();
	}

	ObservableElementList<DataGridBean> list = null;
	GlazedDataGrid dataGrid; 
	public GlazedDataGridTest2() {
		list = new  ObservableElementList<DataGridBean>(GlazedLists.threadSafeList(new BasicEventList<DataGridBean>()), new DataBeanConnector<DataGridBean>());
		dataGrid = new GlazedDataGrid( list,new DExecutor(4), "getId");
		dataGrid.addConsumer(this);
		dataGrid.start();
		Updater updater = new Updater();
		updater.start();
	}
	
	

	@Override
	public void consumeUpdate(DataGridUpdate updates) {
		try {
			//System.out.println(DJson.serialize(updates));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}



	

	private class Updater extends Thread {

		private int nextId = 0;
		public void run() { 
			while(true) { 
				try {
					Thread.sleep(1000);
					
					DataGridBean b = new DataGridBean();
					b.setFilled(2);
					b.setSize(5);
					nextId++;
					b.setId(nextId);
					try {
						list.getReadWriteLock().writeLock().lock();
						list.add(b);
						list.getReadWriteLock().writeLock().unlock();

					} catch (Exception e) {
						e.printStackTrace();
						// TODO: handle exception
					}
					finally { 
					
					}
					b.setSize(DRandom.getRandom(2, 322));
					b.notifyUpdate();
					
					try {
						list.getReadWriteLock().writeLock().lock();
							list.remove(b);
						} catch (Exception e) {
							e.printStackTrace();
							// TODO: handle exception
						}
						finally { 
							list.getReadWriteLock().writeLock().unlock();
						}
						
						
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
			}
		}
	}

}
