package com.dunkware.trade.system.controllers;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * Seperate end points only for serving web ui components, like streaming grids and other 
 * things that don't make sense as part of the API or are useful inpostman. 
 * @author duncankrebs
 *
 */
public class SystemWebController {

	public Object sessionTradesStream(@RequestParam long systemId) throws Exception {
		return null;
	}

	public Object sessionOrdersStream(@RequestParam long systemId) throws Exception {
		return null;
	}
}
