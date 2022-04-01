package com.dunkware.net.chart.grid.builder;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.net.chart.grid.builder.json.GridJson;
import com.dunkware.net.chart.grid.builder.json.GridJsonColumnDef;
import com.dunkware.net.chart.grid.builder.model.GridModel;
import com.dunkware.net.chart.grid.builder.model.GridModelColumn;
import com.dunkware.net.chart.grid.builder.model.GridModelValueParser;
import com.dunkware.net.proto.chart.Grid;
import com.dunkware.net.proto.chart.GridColumn;
import com.dunkware.net.proto.chart.GridValueParser;

public class GridBuilderUtil {

	public static GridJson modelToJson(GridModel grid) throws GridBuilderException { 
		GridJson gridjson = new GridJson();
		gridjson.setPivot(grid.isPivot());
		
		List<GridJsonColumnDef> jsonColumns = new ArrayList<GridJsonColumnDef>();
		for (GridModelColumn column : grid.getColumns()) {
			GridJsonColumnDef def = new GridJsonColumnDef();
			def.setField(column.getField());;
			if(column.getHeaderName() != null) { 
				def.setHeaderName(column.getHeaderName());
			}
			def.setSortable(column.isSortable());
			def.setRowDrag(column.isRowDrag());
			def.setFilter(column.isFilter());
			if(column.getValueParser() != null) { 
				def.setCellRenderer(column.getValueParser().name());
			}
			jsonColumns.add(def);
		}
		gridjson.setData(grid.getData());
		gridjson.setColumnDefs(jsonColumns.toArray(new GridJsonColumnDef[jsonColumns.size()]));
		return gridjson;
	}
	
	
	
	public static Grid modelToGrid(GridModel model) throws GridBuilderException { 
		Grid.Builder builder = Grid.newBuilder();
		builder.setGridId(model.getId());
		builder.setData(model.getData());
		if(model.isStreaming()) { 
			builder.setStreaming(true);
		} else { 
			builder.setStreaming(false);
		}
		if(model.isPivot()) { 
			builder.setPivot(true);
		} else { 
			builder.setPivot(false);
		}
		if(model.getRowNodeId() == null && model.isStreaming() == true) { 
			throw new GridBuilderException("Grid model is set to streaming but row node id not set");
		}
		if(model.getRowNodeId() != null) { 
			builder.setRowNodeId(model.getRowNodeId());
		}
		for (GridModelColumn modelCol : model.getColumns()) {
			GridColumn.Builder colBuilder = GridColumn.newBuilder();
			colBuilder.setField(modelCol.getField());
			if(modelCol.getHeaderName() != null) { 
				colBuilder.setHeaderName(modelCol.getHeaderName());
			}
			if(modelCol.getValueParser() != null) {
				boolean set = false; 
				if(modelCol.getValueParser() == GridModelValueParser.numberParser) {
					colBuilder.setValueParser(GridValueParser.numberParser);
					set = true; 
				}
				if(modelCol.getValueParser() == GridModelValueParser.dateParser) { 
					set = true; 
					colBuilder.setValueParser(GridValueParser.dateParser);
				}
				if(!set) { 
					throw new GridBuilderException("Value Parser " + modelCol.getValueParser().name() + " not associated with grpc enum");
				}
			}
			if(modelCol.isFilter()) { 
				colBuilder.setFilter(true);
			} else { 
				colBuilder.setFilter(false);
			}
			if(modelCol.isRowDrag()) { 
				colBuilder.setRowDrag(true);
			} else { 
				colBuilder.setRowDrag(false);
			}
			if(modelCol.isSortable()) { 
				colBuilder.setSortable(true);
			} else { 
				colBuilder.setSortable(false);
			}
			
				for (String rule : modelCol.getCellClassRules()) {
					colBuilder.addCellClassRules(rule);
				}
		
			builder.addColumns(colBuilder.build());
			
		}
		
		
		return builder.build();
		
	}
}
