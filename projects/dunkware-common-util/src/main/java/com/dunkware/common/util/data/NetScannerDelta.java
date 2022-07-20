package com.dunkware.common.util.data;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.json.DJson;

public class NetScannerDelta {

	private List<NetBean> inserts = new ArrayList<NetBean>();
	private List<Object> deletes = new ArrayList<Object>();
	private List<NetBean> updates = new ArrayList<NetBean>();
	
	public List<NetBean> getInserts() {
		return inserts;
	}
	public void setInserts(List<NetBean> inserts) {
		this.inserts = inserts;
	}
	
	public List<NetBean> getUpdates() {
		return updates;
	}
	public void setUpdates(List<NetBean> updates) {
		this.updates = updates;
	}
	public List<Object> getDeletes() {
		return deletes;
	}
	public void setDeletes(List<Object> deletes) {
		this.deletes = deletes;
	}
	
	public static void main(String[] args) {
		NetBean bean = new NetBean();
		bean.setValue("name", "Duncan");
		NetScannerDelta delta = new NetScannerDelta();
		delta.inserts.add(bean);
		delta.deletes.add("Duncan");
		delta.updates.add(bean);
		try {
			System.out.println(DJson.serializePretty(delta));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}
	
	
	
	
}
