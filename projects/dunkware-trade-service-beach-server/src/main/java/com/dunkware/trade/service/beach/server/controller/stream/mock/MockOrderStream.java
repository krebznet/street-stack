package com.dunkware.trade.service.beach.server.controller.stream.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dunkware.common.util.datagrid.DataGrid;
import com.dunkware.common.util.datagrid.DataGridConsumer;
import com.dunkware.common.util.datagrid.DataGridUpdate;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.spring.streaming.StreamingAdapter;
import com.dunkware.spring.streaming.StreamingAdapterListener;
import com.dunkware.trade.service.beach.server.runtime.BeachOrderBean;

public class MockOrderStream implements StreamingAdapterListener, DataGridConsumer {

	public static final int rowCount = 100;
	private StreamingAdapter adapter;
	private List<BeachOrderBean> orders = new ArrayList<BeachOrderBean>();

	private DataGrid dataGrid = DataGrid.newInstance(new DExecutor(3), "getId");

	private Mocker mocker = new Mocker();

	public MockOrderStream(StreamingAdapter adapter) {
		this.adapter = adapter;
		
		// init the rows
		int i = 0;
		while (i != rowCount) {
			BeachOrderBean bean = new BeachOrderBean();
			bean.setId(i);
			bean.setFilled(DRandom.getRandom(1, 440));
			bean.setSize(300);
			bean.setSymbol("SYMBOL" + i);
			bean.setCommission(3223.3);
			orders.add(bean);
			i++;
		}
		mocker.start();
		dataGrid.addConsumer(this);
		adapter.addListener(this);

	}

	@Override
	public void clientDisconnect(StreamingAdapter adapter) {
		dataGrid.removeConsumer(this);
		dataGrid.dispose();
		mocker.interrupt();

	}

	@Override
	public void serverDisconnect(StreamingAdapter adapter) {
		dataGrid.removeConsumer(this);
		dataGrid.dispose();
		mocker.interrupt();
	}

	@Override
	public void consumeUpdate(DataGridUpdate update) {
		adapter.send(Arrays.asList(update));
		;

	}
	
	private class Mocker extends Thread {
		private int state = 0;
		private volatile int idCounter = 40;

		public void run() {
			
			while (!interrupted()) {
				try {
					Thread.sleep(1000);
						BeachOrderBean bean = new BeachOrderBean();
						int id = idCounter++;
						bean.setAccount("Order" + id);
						bean.setId(id);
						bean.setSize(DRandom.getRandom(2, 399));
						bean.setFilled(DRandom.getRandom(1, 544));
						dataGrid.insert(bean);
						orders.add(bean);
						state = 1;
						continue;
					
				} catch (Exception e) {
					return;
					
				}

			}
		}
	}

	private class Mocker2 extends Thread {
		private int state = 0;
		private int idCounter = 40;

		public void run() {
			for (BeachOrderBean beachOrderBean : orders) {
				try {
					dataGrid.insert(beachOrderBean);
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
				
			}
			while (!interrupted()) {
				try {
					Thread.sleep(30);
					if (state == 0) {
						for (BeachOrderBean beachOrderBean : orders) {
							beachOrderBean.setFilled(DRandom.getRandom(3, 499));
							dataGrid.update(beachOrderBean);
						}
						state = 1;
						continue;
					}
					if(state == 1) { 
						BeachOrderBean order = orders.get(orders.size() -1);
						dataGrid.delete(order);
						state = 2;
						continue;
					}
					if(state == 2) { 
						BeachOrderBean bean = new BeachOrderBean();
						int id = idCounter++;
						bean.setAccount("Order" + id);
						bean.setId(id);
						bean.setSize(30);
						bean.setFilled(DRandom.getRandom(1, 544));
						dataGrid.insert(bean);
						orders.add(bean);
						state = 1;
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
	}
}
