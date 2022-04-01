package com.dunkware.genesis.service.grid;

import com.dunkware.net.chart.grid.builder.GridBuilder;
import com.dunkware.net.chart.grid.builder.GridBuilderUtil;
import com.dunkware.net.chart.grid.builder.model.GridModel;
import com.dunkware.net.data.helpers.GDataJson;
import com.dunkware.net.proto.chart.Grid;
import com.dunkware.net.proto.core.GList;

public class EntityGrids {


	public static Grid grid1(GList entities) throws Exception { 
		GridBuilder builder = GridBuilder.newBuilder();
		builder.addColumn("id").setHeaderName("Symbol").add().addColumn("LastPrice").setHeaderName("Price").add().addColumn("time").setHeaderName("Time")
		.add().build();
	
		String json = GDataJson.listToJsonString(entities);
		builder.setData(json);
		builder.setStreaming(false);
		builder.setPivot(false);
		builder.setRowNodeId("symbol");
		
		GridModel model = builder.build();
		Grid grid  = GridBuilderUtil.modelToGrid(model);
		return grid;
		
	}
}
