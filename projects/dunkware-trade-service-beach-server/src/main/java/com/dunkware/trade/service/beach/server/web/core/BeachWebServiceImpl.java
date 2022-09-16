package com.dunkware.trade.service.beach.server.web.core;

import com.dunkware.trade.service.beach.server.web.BeachWebDash;
import com.dunkware.trade.service.beach.server.web.BeachWebService;

public class BeachWebServiceImpl implements BeachWebService {

	private BeachWebDash dash;
	
	@Override
	public BeachWebDash getDash() {
		return dash;
	}
	
	

}
