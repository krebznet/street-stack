package com.dunkware.utils.mysql.writers;

import com.dunkware.utils.mysql.model.MySqlColumn;
import com.dunkware.utils.mysql.model.MySqlTable;
import com.dunkware.utils.mysql.model.datatypes.MySqlInteger;
import com.dunkware.utils.mysql.model.datatypes.MySqlTime;
import com.dunkware.utils.mysql.model.datatypes.MySqlVarChar;

public class MySqlTableWriterTest {

	public static void main(String[] args) {
		MySqlTable table = new MySqlTable();
		table.setName("xstream_capture_equity_111520_rows");
		MySqlColumn col1 = new MySqlColumn();
		col1.setName("TickSymbol");
		col1.setDataType(new MySqlVarChar(40));
		col1.setNotNull(true);
		table.getColumns().add(col1);
		
		MySqlColumn col2 = new MySqlColumn();
		col2.setName("_row");
		col2.setIndexColumn(true);
		col2.setIndexOrder(1);
		
		col2.setDataType(new MySqlInteger()	);
		table.getColumns().add(col2);
		
		MySqlColumn col3 = new MySqlColumn();
		col3.setName("_time");
		col3.setIndexColumn(true);
		col3.setIndexOrder(2);
		col3.setDataType(new MySqlTime());
		table.getColumns().add(col3);
		String output = null;
		
		MySqlTableWriter writer = new MySqlTableWriter();
		try {
			output = writer.createTableSql(table);
			System.out.println(output);	
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
}
