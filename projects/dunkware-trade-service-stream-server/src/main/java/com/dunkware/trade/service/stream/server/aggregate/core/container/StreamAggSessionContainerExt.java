package com.dunkware.trade.service.stream.server.aggregate.core.container;

import com.dunkware.trade.service.stream.container.worker.WorkerContainerInput;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainer;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainerException;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainerExtension;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainerNode;
import com.dunkware.trade.service.stream.server.controller.session.container.anot.ASessionContainerExtension;

@ASessionContainerExtension()
public class StreamAggSessionContainerExt implements SessionContainerExtension{

	@Override
	public void workerInit(WorkerContainerInput input) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void containerStart(SessionContainer container) throws SessionContainerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void workerStart(SessionContainerNode node) throws SessionContainerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionStart(SessionContainer container) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionStop(SessionContainer container) {
		// so we nned an extesion on worker side, 
		
	}

}
