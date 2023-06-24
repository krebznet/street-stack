package com.dunkware.trade.service.data.json;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.json.DJson;

public class DashboardModel {
	
	
	public static void main(String[] args) {
		Dashboard db = new Dashboard();
		db.setName("My Dashboard");
		Tab tab = new Tab(); 
		tab.setName("Time Entry");
		Widget widget1 = new Widget();
		widget1.setId("TimeEntryWidget");
		widget1.setWidth(300);
		widget1.setHeight(200);
		widget1.setX(2);
		widget1.setY(3);
		tab.getWidgets().add(widget1);
		db.getTabs().add(tab);
		
		List<Dashboard> dashboards =new ArrayList<Dashboard>();
		dashboards.add(db);
		db.setName("My Dashboard2");
		dashboards.add(db);
		
		try {
			System.out.println(DJson.serializePretty(dashboards));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
