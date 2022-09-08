package com.dunkware.common.util.grid;

public class GridOptionsBuilder {
	
	public static GridOptionsBuilder newInstnace(Object id) { 
		return new GridOptionsBuilder(id);
	}

	private GridOptions options = new GridOptions(); 
	
	private Object id; // FUCK YOU 
	
	private GridOptionsBuilder(Object id) { 
		this.id = id;
	}
	
	
	public GridOptionsBuilder column(String header, String field, GridFormat format) { 
		GridColumn column = new GridColumn();
		 column.setField(field);
		 column.setFormat(format);
		 column.setHeader(header);
		 options.getColumns().add(column);
		 return this;
	}
	
	public GridOptions build() { 
		return options;
	}

}
