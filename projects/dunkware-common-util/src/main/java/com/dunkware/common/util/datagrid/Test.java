package com.dunkware.common.util.datagrid;

import com.dunkware.common.util.json.DJson;

public class Test {

	public static void main(String[] args) {
		DataGridBean b = new DataGridBean();
		b.setFilled(3);
		b.setId(3);
		b.setStatus("he");
		try {
			System.out.println(DJson.serialize(b));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}


// add