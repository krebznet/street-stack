package com.dunkware.common.util.mysql.model.datatypes;

import com.dunkware.common.util.mysql.model.MySqlDataType;
import com.dunkware.common.util.mysql.model.MySqlModelException;

public class MySqlDouble implements MySqlDataType {

	@Override
	public void writeDataType(StringBuilder writer) throws MySqlModelException {
		writer.append("DOUBLE");
	}

	
}
