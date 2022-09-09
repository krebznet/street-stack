package com.dunkware.common.util.grid;

public class GridOptionsBuilder {
	
	public static GridOptionsBuilder newInstnace(Object id) { 
		return new GridOptionsBuilder(id);
	}

	private GridOptions options = new GridOptions(); 
	
	private Object id; 
	
	private GridOptionsBuilder(Object id) { 
		this.id = id;
		column("id", "id",GridFormat.TEXT,true);
		
	}
	
	
	public GridOptionsBuilder column(String header, Object field, GridFormat format) { 
		GridColumn column = new GridColumn();
		 column.setField(field);
		 column.setFormat(format);
		 column.setHeaderName(header);
		 options.getColumns().add(column);
		 return this;
	}
	
	public GridOptionsBuilder column(String header, Object field, GridFormat format, boolean hide) { 
		GridColumn column = new GridColumn();
		 column.setField(field);
		 column.setFormat(format);
		 column.setHide(hide);
		 column.setHeaderName(header);
		 options.getColumns().add(column);
		 return this;
	}
	
	public GridOptions build() { 
		return options;
	}

}
