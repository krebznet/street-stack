package com.dunkware.common.util.grid;

import com.dunkware.common.util.json.DJson;

public class GridTest {

	public static void main(String[] args) {
		
		GridOptions options = GridOptionsBuilder.newInstnace(32).column("Last", "last", GridFormat.CURRENCY)
		.column("Volume", "volume", GridFormat.INTEGER).build();
		
		try {
			System.out.println(DJson.serializePretty(options));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
