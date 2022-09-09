package com.dunkware.common.util.grid;

import com.dunkware.common.util.json.DJson;

public class GridTest {

	public static void main(String[] args) {
		
		GridOptions options = GridOptionsBuilder.newInstnace().column("Entity","Entity",GridFormat.TEXT).column("Last", "Last", GridFormat.CURRENCY).column("Volume", "Volume", GridFormat.INTEGER).
				build();
		try {
			System.out.println(DJson.serializePretty(options));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
