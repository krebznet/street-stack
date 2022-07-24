package com.dunkware.trade.service.stream.worker.session.container.scanners;

import com.dunkware.spring.channel.anot.AChannelHandler;
import com.dunkware.spring.channel.anot.AChannelSetter;
import com.dunkware.spring.channel.anot.AMessageReply;
import com.dunkware.trade.service.stream.worker.session.container.WorkerContainer;
import com.dunkware.xstream.container.proto.EntityScannerStartReq;
import com.dunkware.xstream.container.proto.EntityScannerStartResp;

@AChannelHandler("WorkerContainer")
public class WorkerContainerScannerHandler {

	
	private WorkerContainer workerContainer; 
	
	private WorkerContainerEntityScanner workerScanner; 
	
	private String scannerId; 
	
	@AMessageReply()
	public EntityScannerStartResp entityScannerStartReq(EntityScannerStartReq req) { 
		workerScanner = new WorkerContainerEntityScanner();
		EntityScannerStartResp resp = new EntityScannerStartResp(); 
		try {
			scannerId = workerScanner.start(req, workerContainer);
			resp.setSuccess(true);
			resp.setScannerId(scannerId);
			return resp;
		} catch (Exception e) {
			resp.setException(e.toString());
			resp.setSuccess(false);
			return resp;
		}
	}
	
		
	@AChannelSetter()
	public void setWorkerContainer(WorkerContainer container) { 
		this.workerContainer = container;
	}
	
}
