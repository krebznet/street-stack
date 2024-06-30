package com.dunkware.utils.mysql.model.datatypes;

import com.dunkware.utils.mysql.model.MySqlDataType;
import com.dunkware.utils.mysql.model.MySqlModelException;

public class MySqlVarBinary implements MySqlDataType {

	private int size;

	public MySqlVarBinary() { 
		
	}
	
	public MySqlVarBinary(int size) { 
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
		writer.append("varbinary");
		writer.append("(");
		writer.append(size);
		writer.append(")");
	}
	
	

	
	
	
}
