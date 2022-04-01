package com.dunkware.common.util.mysql.model.datatypes;

import com.dunkware.common.util.mysql.model.MySqlDataType;
import com.dunkware.common.util.mysql.model.MySqlModelException;

public class MySqlTime implements MySqlDataType {

	public MySqlTime() {
		
	}
	
	@Override
	public void writeDataType(StringBuilder builder) throws MySqlModelException {
		builder.append("TIME");
	}

	
}
