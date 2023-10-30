package com.dunkware.trade.net.data.server.stream.signals.session;

import ca.odell.glazedlists.ObservableElementList;

public interface StreamSessionSignalService {
	
	ObservableElementList<StreamSessionSignalTypeBean> getSignalTypeBeans();
	
	

}
