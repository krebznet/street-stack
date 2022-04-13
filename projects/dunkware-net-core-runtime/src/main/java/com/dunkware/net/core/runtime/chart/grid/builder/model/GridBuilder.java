package com.dunkware.net.core.runtime.chart.grid.builder.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.dunkware.net.core.runtime.chart.grid.builder.GridBuilderException;
import com.dunkware.net.core.runtime.util.JsonHelper;

public class GridBuilder {
	
	private GridModel grid = new GridModel();
	
	public static GridBuilder newBuilder() { 
		return new GridBuilder();
	}
	
	public GridBuilder setPivot(boolean value) { 
		grid.setPivot(value);
		return this;
	}
	
	public GridBuilder setStreaming(boolean value) { 
		grid.setStreaming(value);
		return this;
	}
	
	public GridBuilder setRowNodeId(String value) { 
		grid.setRowNodeId(value);
		return this;
	}
	
	public GridBuilder setId(int id) { 
		grid.setId(id);
		return this;
	}
	
	public GridBuilder setData(String data) { 
		grid.setData(data);
		return this;
	}
	
	public GridColumnBuider addColumn(String field) { 
		GridColumnBuider builder = new GridColumnBuider(field);
		return builder;
	}
	
	public GridModel build() throws GridBuilderException { 
		return grid;
	}
	
	
	public class GridColumnBuider { 
		
		private GridModelColumn column; 
		
	
		public GridColumnBuider(String field) { 
			column = new GridModelColumn(field);
		}
		
		public GridColumnBuider setField(String field) { 
			column.setField(field); 
			return this; 
		}
		
		public GridColumnBuider setHeaderName(String headerName) { 
			column.setHeaderName(headerName);
			return this;
		}
		
		public GridBuilder add() throws Exception { 
			GridBuilder.this.grid.getColumns().add(column);
			return GridBuilder.this;
		}
		
		public GridColumnBuider setValueParser(GridModelValueParser parser) {
			column.setValueParser(parser);
			return this;
		}
		
		public CellClassRuleBuilder addCellClassRule() { 
			return new CellClassRuleBuilder(this);
		}
		
		public GridModelColumn getModelColumn() {
			return column;
		}
	}
	
	
	private class CellClassStyleRule { 
		
	}
	
	public static class CellClassRuleBuilder { 
		
		private GridColumnBuider columnBuilder;
		
		public CellClassRuleBuilder(GridColumnBuider columnBuilder) { 
			this.columnBuilder = columnBuilder;
		}
		
		public CellClassRuleBuilder doubleExpression(String style,  GridModelColumnStyleRuleOperator operator, double value) throws IOException {
//			StringBuilder builder = new StringBuilder();
//
//
//			builder.append("" + style + "").append(": " + "x ");
//			if(operator == GridModelColumnStyleRuleOperator.GreaterThan) {
//				builder.append(">");
//			}
//			if(operator == GridModelColumnStyleRuleOperator.LessThan) {
//			}
//			if(operator == GridModelColumnStyleRuleOperator.Equal) {
//				builder.append("=");
//			}
//			builder.append(" ");
//			builder.append(String.valueOf(value));
//			builder.append("");
//			System.out.println(builder.toString());

			String tempValue = new String();
			if(operator == GridModelColumnStyleRuleOperator.GreaterThan) {
				tempValue = "x > "+value;
			}
				tempValue ="x < "+value;
			if(operator == GridModelColumnStyleRuleOperator.Equal) {
				tempValue = "x ="+value;

			}
			Map<String,Object> map = new HashMap<>();
            map.put(style,tempValue);
			columnBuilder.getModelColumn().getCellClassRules().add(JsonHelper.serialize(map));
			return this;
		}
	
	public GridColumnBuider add() { 
		return CellClassRuleBuilder.this.columnBuilder;
	}
		
		
	}
	
	
	

}
