package com.dunkware.common.util.grid;

import com.dunkware.common.util.grid.format.GridFormat;
import com.dunkware.common.util.grid.format.GridFormats;

public class GridOptionsBuilder {
	
	public static GridOptionsBuilder newInstnace() { 
		return new GridOptionsBuilder();
	}

	private GridOptions options = new GridOptions(); 
	

	private GridOptionsBuilder() { 
		
		column("id", "id",GridFormat.TEXT,true);
		
	}
	
	
	public GridOptionsBuilder column(String header, Object field, GridFormat format) { 
		GridColumn column = new GridColumn();
		 column.setField(field);
		 column.setValueFormatter(GridFormats.valueFormatter(format));
		 column.setHeaderName(header);
		 options.getColumns().add(column);
		 return this;
	}
	
	public GridOptionsBuilder column(String header, Object field, GridFormat format, boolean hide) { 
		GridColumn column = new GridColumn();
		 column.setField(field);
		 column.setValueFormatter(GridFormats.valueFormatter(format));
		 column.setHide(hide);
		 column.setHeaderName(header);
		 options.getColumns().add(column);
		 return this;
	}
	
	

	
	
	public GridOptions build() { 
		return options;
		
	}
	
	public static class GridOptionsColumnBuilder { 
		
		private GridOptionsBuilder parent; 
		
		public GridOptionsColumnBuilder(GridOptionsBuilder parent) { 
			this.parent = parent; 
		}
		
		
	}
	
	
	
	
	
	
}
