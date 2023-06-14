package com.dunkware.common.util.datagrid;

import java.util.List;

import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.uuid.DUUID;

public class DataGridTest implements DataGridConsumer  {

	
	public static void main(String[] args) {
		DataGridTest test = new DataGridTest();
	}
	
	
	private DataGrid grid; 
	private Updater updater = new Updater();
	
	public DataGridTest() { 
		grid = DataGrid.newInstance("getId");
		grid.addConsumer(this);
		updater.start();
	}
	
	
	private class Updater extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				DataGridBean updateBean = new DataGridBean();
				updateBean.setId(1);
				updateBean.setSize(00);
				updateBean.setFilled(32);
				updateBean.setSymbol("BEAN1");;
				try {
					grid.insert(updateBean);
					updateBean.setSize(DRandom.getRandom(2, 032));
					updateBean.setSymbol(DUUID.randomUUID(3));
					grid.update(updateBean);
					grid.delete(updateBean);;
					
				//	Thread.sleep(300);
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
				
			}
		}
	}


	@Override
	public void consumeUpdate(DataGridUpdate update) {
		try {
			System.out.println(DJson.serializePretty(update));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	
}
