package com.dunkware.common.util.glazed;

/**
 * Listener to hook into when a data grid is disposed, this comes in handy 
 * when being used as a streaming web service ant he http connnection is closed
 * and another resource or update provide needs to be made away to dispose its 
 * resources as well. 
 * 
 * @author Duncan Krebs 
 *
 */
public interface GlazedDataGridListener {

	public void onGridDispose(GlazedDataGrid dataGrid); 
	
	public void onGridInit(GlazedDataGrid dataGrid);
}
