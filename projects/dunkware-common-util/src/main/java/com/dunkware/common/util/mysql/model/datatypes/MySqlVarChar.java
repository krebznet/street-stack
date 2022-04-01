package com.dunkware.common.util.mysql.model.datatypes;

import com.dunkware.common.util.mysql.model.MySqlDataType;
import com.dunkware.common.util.mysql.model.MySqlModelException;

public class MySqlVarChar implements MySqlDataType {

	private int size;

	public MySqlVarChar() { 
		
	}
	
	public MySqlVarChar(int size) { 
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public void writeDataType(StringBuilder writer) throws MySqlModelException {
		writer.append("varchar");
		writer.append("(");
		writer.append(size);
		writer.append(")");
	}
	
	
	
	
	
	
	
}
