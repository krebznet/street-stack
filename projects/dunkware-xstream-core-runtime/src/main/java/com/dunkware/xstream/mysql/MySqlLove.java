package com.dunkware.xstream.mysql;

import java.util.List;

import com.dunkware.common.util.mysql.model.MySqlColumn;
import com.dunkware.common.util.mysql.model.MySqlTable;
import com.dunkware.common.util.mysql.model.datatypes.MySqlDouble;
import com.dunkware.common.util.mysql.model.datatypes.MySqlInteger;
import com.dunkware.common.util.mysql.model.datatypes.MySqlLong;
import com.dunkware.common.util.mysql.model.datatypes.MySqlTime;
import com.dunkware.common.util.mysql.model.datatypes.MySqlVarChar;
import com.dunkware.common.util.mysql.writers.MySqlTableWriter;
import com.dunkware.xstream.xScript.DataType;
import com.dunkware.xstream.xScript.VarType;
import com.dunkware.xstream.xproject.XScriptProject;

public class MySqlLove {

	public static String buildCreateSignalTableSql(String tableName, XScriptProject project) throws Exception {
		try {
			// okay lets not worry about key columns for now
			// 
			// VARIABLES -- TIME STAMP; 
			
			MySqlTable table = new MySqlTable();
			MySqlColumn timeStampCol = new MySqlColumn();
			MySqlColumn primaryKeyCol = new MySqlColumn();
			primaryKeyCol.setName("_id");
			primaryKeyCol.setAutoIncrement(true);
			primaryKeyCol.setNotNull(true);
			primaryKeyCol.setPrimaryKey(true);
			primaryKeyCol.setDataType(new MySqlInteger());
			table.getColumns().add(primaryKeyCol);
			MySqlColumn rowCol = new MySqlColumn();
			rowCol.setName("_row");
			rowCol.setDataType(new MySqlVarChar(30));
			rowCol.setIndexColumn(true);
			rowCol.setIndexOrder(1);
			table.getColumns().add(rowCol);
			timeStampCol.setName("_time");
			timeStampCol.setIndexColumn(true);
			timeStampCol.setIndexOrder(2);
			timeStampCol.setDataType(new MySqlTime());
			table.setName(tableName);
			table.getColumns().add(timeStampCol);
		
			MySqlColumn signal = new MySqlColumn();
			signal.setName("_signal");
			signal.setDataType(new MySqlVarChar(100));
			table.getColumns().add(signal);
			
			List<VarType> varTypes = project.getStreamVars();
			for (VarType varType : varTypes) {
				MySqlColumn col = new MySqlColumn();
				col.setName(varType.getName());
				boolean f = false;
				if(varType.getType() == DataType.DUB) {
					MySqlDouble type = new MySqlDouble();
					col.setDataType(type);
					f = true;
				}
				
				if(varType.getType() == DataType.INT) {
					MySqlInteger type = new MySqlInteger();
					col.setDataType(type);
					f = true;
				}
				if(varType.getType() == DataType.STR) {
					MySqlVarChar type = new MySqlVarChar(400);
					col.setDataType(type);
					f = true;
				}
				if(varType.getType() == DataType.LONG) {
					MySqlLong type = new MySqlLong();
					col.setDataType(type);
					f = true;
				}
				
				if(!f) { 
					throw new Exception(" Var Data Type Not Handled " + varType.getType().getName());
				}
				table.getColumns().add(col);
			}
			
			MySqlTableWriter writer =  new MySqlTableWriter();
			String results = writer.createTableSql(table);
			System.out.println(results);
			return results;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	
	public  static String buildCreateTableSql(String tableName, XScriptProject project) throws Exception { 
		try {
			// okay lets not worry about key columns for now
			// 
			// VARIABLES -- TIME STAMP; 
			
			MySqlTable table = new MySqlTable();
			MySqlColumn timeStampCol = new MySqlColumn();
			MySqlColumn primaryKeyCol = new MySqlColumn();
			primaryKeyCol.setName("_id");
			primaryKeyCol.setAutoIncrement(true);
			primaryKeyCol.setNotNull(true);
			primaryKeyCol.setPrimaryKey(true);
			primaryKeyCol.setDataType(new MySqlInteger());
			table.getColumns().add(primaryKeyCol);
			MySqlColumn rowCol = new MySqlColumn();
			rowCol.setName("_row");
			rowCol.setDataType(new MySqlVarChar(30));
			rowCol.setIndexColumn(true);
			rowCol.setIndexOrder(1);
			table.getColumns().add(rowCol);
			timeStampCol.setName("_time");
			timeStampCol.setIndexColumn(true);
			timeStampCol.setIndexOrder(2);
			timeStampCol.setDataType(new MySqlTime());
			table.setName(tableName);
			table.getColumns().add(timeStampCol);
			
			List<VarType> varTypes = project.getStreamVars();
			for (VarType varType : varTypes) {
				MySqlColumn col = new MySqlColumn();
				col.setName(varType.getName());
				boolean f = false;
				if(varType.getType() == DataType.DUB) {
					MySqlDouble type = new MySqlDouble();
					col.setDataType(type);
					f = true;
				}
				
				if(varType.getType() == DataType.INT) {
					MySqlInteger type = new MySqlInteger();
					col.setDataType(type);
					f = true;
				}
				if(varType.getType() == DataType.STR) {
					MySqlVarChar type = new MySqlVarChar(400);
					col.setDataType(type);
					f = true;
				}
				if(varType.getType() == DataType.LONG) {
					MySqlLong type = new MySqlLong();
					col.setDataType(type);
					f = true;
				}
				
				if(!f) { 
					throw new Exception(" Var Data Type Not Handled " + varType.getType().getName());
				}
				table.getColumns().add(col);
			}
			
			MySqlTableWriter writer =  new MySqlTableWriter();
			String results = writer.createTableSql(table);
			System.out.println(results);
			return results;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
