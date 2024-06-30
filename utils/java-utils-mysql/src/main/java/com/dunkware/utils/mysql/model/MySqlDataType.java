package com.dunkware.utils.mysql.model;

public interface MySqlDataType {

	public void writeDataType(StringBuilder builder) throws MySqlModelException;
}
