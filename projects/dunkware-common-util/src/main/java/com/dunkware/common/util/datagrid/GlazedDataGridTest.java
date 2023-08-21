package com.dunkware.common.util.datagrid;

import java.util.ArrayList;

import com.dunkware.common.util.observable.ObservableBeanListConnector;

import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.ObservableElementList;
import ca.odell.glazedlists.event.ListEvent;
import ca.odell.glazedlists.event.ListEventListener;

public class GlazedDataGridTest implements ListEventListener<DataGridBean> {

	public static void main(String[] args) {
		new GlazedDataGridTest();
	}

	ObservableElementList<DataGridBean> list = null;

	public GlazedDataGridTest() {
		EventList<DataGridBean> personEventList = GlazedLists.eventList(new ArrayList<DataGridBean>());

		list = new ObservableElementList<>(personEventList, new ObservableBeanListConnector<>());
		list.addListEventListener(this);
		Updater updater = new Updater();
		updater.start();
	}

	@Override
	public void listChanged(ListEvent<DataGridBean> listChanges) {
		while(listChanges.hasNext()) { 
			boolean results = listChanges.next();
			if(results) {
			//	System.out.println(listChanges.getType());
				
			}
		}
			
		
	}

	private class Updater extends Thread {

		public void run() { 
			while(true) { 
				try {
					Thread.sleep(1000);
					DataGridBean b = new DataGridBean();
					b.setFilled(2);
					b.setSize(5);
					list.add(b);
					b.notifyUpdate();
					Thread.sleep(340490);
					//list.remove(b);
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
			}
		}
	}

}
