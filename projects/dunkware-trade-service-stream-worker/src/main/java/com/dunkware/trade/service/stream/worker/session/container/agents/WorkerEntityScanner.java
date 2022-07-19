package com.dunkware.trade.service.stream.worker.session.container.agents;

import com.dunkware.net.proto.cluster.GContainerWorkerMessage;
import com.dunkware.net.proto.cluster.GContainerWorkerMessage.TypeCase;
import com.dunkware.net.proto.netstream.GNetEntityScannerUpdateRequest;
import com.dunkware.trade.service.stream.worker.session.container.WorkerContainer;
import com.dunkware.trade.service.stream.worker.session.container.WorkerContainerHandler;
import com.dunkware.xstream.model.scanner.SessionEntityScanner;
import com.dunkware.xstream.net.core.container.ContainerEntityScanner;
import com.dunkware.xstream.net.core.container.ContainerSearchException;

public class WorkerEntityScanner implements WorkerContainerHandler {
	
	private WorkerContainer container; 
	private SessionEntityScanner scanner; 
	String scannerIdentifier ;
	private ContainerEntityScanner entityScanner; 
	
	public void init(WorkerContainer container, SessionEntityScanner scannerRequest) throws ContainerSearchException { 
		try {
			//entityScanner = container.getContainer().entityScanner(scannerRequest);
		//	entityScanner.netScanner().
		} catch (Exception e) {
			// or do we throw exception. 
			//throw new ContainerSearchException("Worker node " + container.getWorkerId() + " exception creating container entity scanner on scanner id " + scannerId + " " + e.toString());
		}
	}
	
	public void dispose() { 
		
	}

	/**
	 * We will listen for dispose and update messages here 
	 * @param message
	 */
	@Override
	public void consumeMessage(GContainerWorkerMessage message) {
		if(message.getTypeCase() == TypeCase.ENTITYSCANNERUPDATEREQUEST) {
			GNetEntityScannerUpdateRequest req = message.getEntityScannerUpdateRequest();
			// 
			
		}
	}
	
	

}
