package com.dunkware.utils.mysql.model.datatypes;

import com.dunkware.utils.mysql.model.MySqlDataType;
import com.dunkware.utils.mysql.model.MySqlModelException;

public class MySqlDouble implements MySqlDataType {

	@Override
	public void writeDataType(StringBuilder writer) throws MySqlModelException {
		writer.append("DOUBLE");
	}

	
}
