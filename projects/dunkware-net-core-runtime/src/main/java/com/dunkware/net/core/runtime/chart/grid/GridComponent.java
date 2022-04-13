package com.dunkware.net.core.runtime.chart.grid;

import com.dunkware.net.core.runtime.chart.component.ComponentException;
import com.dunkware.net.core.runtime.chart.grid.builder.model.GridModel;

public interface GridComponent {
	
	public GridModel getModel();
	
	public void start() throws ComponentException;
	
	
	

}
