package com.dunkware.trade.service.beach.model.gen;

import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.beach.model.system.BeachSystemModel;

public class Test1SystemGen {

	
	public static void main(String[] args) {
		BeachSystemModel model = new BeachSystemModel();
		model.setName("Test System 1");
		model.setAllocatedCapital(100000.00);
		
		try {
			System.out.println(DJson.serializePretty(model));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
