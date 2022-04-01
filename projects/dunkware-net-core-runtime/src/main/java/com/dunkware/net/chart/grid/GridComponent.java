package com.dunkware.net.chart.grid;

import com.dunkware.net.chart.component.ComponentException;
import com.dunkware.net.chart.grid.builder.model.GridModel;

public interface GridComponent {
	
	public GridModel getModel();
	
	public void start() throws ComponentException;
	
	
	

}
