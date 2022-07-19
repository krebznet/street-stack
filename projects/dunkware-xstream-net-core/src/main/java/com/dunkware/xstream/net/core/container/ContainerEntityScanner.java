package com.dunkware.xstream.net.core.container;

import java.util.List;

import com.dunkware.common.util.data.NetScanner;
import com.dunkware.xstream.model.scanner.SessionEntityScanner;
import com.dunkware.xstream.model.search.SessionEntitySearch;

/**
 * Builds a scanner on container entities given a 
 * entity search model. Uses NetScanner as data output
 * 
 * @author Duncan Krebs 
 *
 */
public interface ContainerEntityScanner {
	
	/**
	 * Disposes the scanner 
	 */
	public void dispose(); 
	
	
	/**
	 * Returns the net scanner for data 
	 * @return
	 */
	public NetScanner netScanner(); 
	


	/**
	 * Starts a scanner with a search model and list of variables 
	 * to include in scanner results. 
	 * @param search
	 * @param vars
	 */
	public void init(Container container, SessionEntityScanner scanner) throws ContainerSearchException;
	
	/**
	 * Updates scanner criteria with search criteria and with variables as output. 
	 * @param search
	 * @param vars
	 * @throws ContainerSearchException
	 */
	public void update(SessionEntitySearch search, List<String> vars) throws ContainerSearchException; 

}
