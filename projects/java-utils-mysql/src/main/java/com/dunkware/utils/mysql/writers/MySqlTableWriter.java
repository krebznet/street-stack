package com.dunkware.utils.mysql.writers;

import java.util.List;

import com.dunkware.utils.mysql.model.MySqlColumn;
import com.dunkware.utils.mysql.model.MySqlModelException;
import com.dunkware.utils.mysql.model.MySqlTable;

public class MySqlTableWriter {

	private StringBuilder writer = new StringBuilder();
	private int columnWrites = 0;
	private MySqlTable table;
	
	/*
	 * CREATE TABLE `Test` ( `column1` int NOT NULL, `jk` varchar(89) NOT NULL, `kk`
	 * varchar(45) NOT NULL, `jjnn` varchar(45) DEFAULT NULL, `Testcol` varchar(45)
	 * DEFAULT NULL, `jkkjkjk` varchar(45) DEFAULT NULL, `hjh` varbinary(500)
	 * DEFAULT NULL, PRIMARY KEY (`column1`,`jk`,`kk`)
	 */
	public String createTableSql(MySqlTable table) throws MySqlModelException { 
		this.table = table;
		writer.append("CREATE TABLE IF NOT EXISTS ");
		
		if(table.getName() == null) { 
			//throw new 
		}
		writer.append(table.getName());
		writer.append(" ");
		writer.append("(");
		if(table.getColumns().size() == 0) { 
			// throw new 
		}
		for (MySqlColumn col : table.getColumns()) {
			writeColumn(col);
		}
		writePrimaryKey();
		writeIndex();
		// close table
		writer.append(");"); 
		

		return writer.toString();
	}
	
	private void writeColumn(MySqlColumn col) throws MySqlModelException { 
		if(columnWrites > 0) {
			writer.append(",");
		}
			
			writer.append(col.getName());
			writer.append(" ");
			col.getDataType().writeDataType(writer);
			if(col.isNotNull()) { 
				writer.append(" NOT NULL");
			} else { 
				writer.append(" DEFAULT NULL");
			}
			if(col.isAutoIncrement()) { 
				writer.append(" ");
				writer.append("AUTO_INCREMENT");
			}
		
		columnWrites++;
	}
	
	private void writePrimaryKey( ) { 
		List<MySqlColumn> primaryKeys = table.getPrimaryKeys();
		if(primaryKeys.size() == 0) { 
			return;
		}
		writer.append(", ");
		writer.append("PRIMARY KEY(");
		int count = 0;
		for (MySqlColumn mySqlColumn : primaryKeys) {
			if(count > 0) { 
				writer.append(",");
			}
			writer.append(mySqlColumn.getName());
			writer.append(" ");
			count++;
		}
		writer.append(")");
	}
	
	private void writeIndex() { 
		List<MySqlColumn> indexCols = table.getIndexColumns();
		if(indexCols.size() == 0) { 
			return;
		}
		//INDEX idx_col1 (col1)
		writer.append(", ");
		
		
		int count = 0;
		for (MySqlColumn mySqlColumn : indexCols) {
			if(count > 0) {
				writer.append(", ");
			} 
			writer.append("INDEX ");
			writer.append("idx_");
			writer.append(mySqlColumn.getName()); 
			writer.append("(");
			writer.append(mySqlColumn.getName());
			writer.append(")");
			count++;
		}
	}
}
