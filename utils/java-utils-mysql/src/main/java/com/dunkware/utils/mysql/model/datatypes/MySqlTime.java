package com.dunkware.utils.mysql.model.datatypes;

import com.dunkware.utils.mysql.model.MySqlDataType;
import com.dunkware.utils.mysql.model.MySqlModelException;

public class MySqlTime implements MySqlDataType {

	public MySqlTime() {
		
	}
	
	@Override
	public void writeDataType(StringBuilder builder) throws MySqlModelException {
		builder.append("TIME");
	}

	
}
