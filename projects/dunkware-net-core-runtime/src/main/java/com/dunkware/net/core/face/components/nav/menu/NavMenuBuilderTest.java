package com.dunkware.net.core.face.components.nav.menu;

import com.dunkware.common.util.json.DJson;

public class NavMenuBuilderTest {
	
	public static void main(String[] args) {
		NavMenu menu = 
		NavMenuBuilder.newBuilder().section("system", "System").node("dashboards", "Dashboards", "/dash")
		.link("test", "Stream Dash", "/system/dash/stream").build().link("test", "Cluster Dash","/cluster").build()
		.build().build().section("trading", "Trading").node("fuck", "Accounts", "/icons").build().build().build();
		try {
			System.out.println(DJson.serializePretty(menu));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
