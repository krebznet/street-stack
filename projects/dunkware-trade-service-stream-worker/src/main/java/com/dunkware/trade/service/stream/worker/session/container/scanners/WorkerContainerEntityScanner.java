package com.dunkware.trade.service.stream.worker.session.container.scanners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.data.NetScanner;
import com.dunkware.common.util.data.NetScannerDelta;
import com.dunkware.common.util.data.NetScannerWatcher;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.spring.channel.anot.AMessageHandler;
import com.dunkware.spring.message.Message;
import com.dunkware.trade.service.stream.worker.session.container.WorkerContainer;
import com.dunkware.xstream.container.proto.EntityScannerDelta;
import com.dunkware.xstream.container.proto.EntityScannerStartReq;
import com.dunkware.xstream.container.proto.EntityScannerStopReq;
import com.dunkware.xstream.net.core.container.ContainerEntityScanner;

public class WorkerContainerEntityScanner implements NetScannerWatcher {

	private String scannerId = null;
	private ContainerEntityScanner scanner = null; 
	private WorkerContainer container; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private Marker marker = MarkerFactory.getMarker("WorkerContainerEntityScanner");
	
	public String start(EntityScannerStartReq req, WorkerContainer container) throws Exception {
		try {
			this.container = container; 
		   // ID ? 
			scannerId = DUUID.randomUUID(5);
			scanner = container.getCache().entityScanner(req.getScanner());
			scanner.netScanner().addWatcher(this);
			container.getChannel().addHandler(this);
		} catch (Exception e) {
			throw new Exception("Exception creating core container scanner on worker node " + e.toString());
		}
		return scannerId;
		//
		
	}
	
	// very nice -- this is disposing shit then or what
	@AMessageHandler
	public void dispose(EntityScannerStopReq req) { 
		if(logger.isDebugEnabled()) { 
			logger.debug(marker, "Disposing Worker Container Entity Scanner {}", scannerId);
		}
		scanner.netScanner().removeWatcher(this);
		scanner.dispose();
	}
	
	@Override
	public void scannerDelta(NetScanner scanner, NetScannerDelta delta) {
		if(logger.isDebugEnabled()) { 
			logger.debug(marker, "Scanner Delta Update, inserts {} updates {} deletes {}", delta.getInserts().size(), delta.getUpdates().size(),delta.getDeletes().size());
		}
		EntityScannerDelta scannerDelta = new EntityScannerDelta(); 
		scannerDelta.setScannerId(scannerId);
		scannerDelta.setDelta(delta);
		try {
			container.getChannel().send(Message.newInstance(scannerDelta));
		} catch (Exception e) {
			logger.error("Exception sending entity scanner delta from worker node to session container " + e.toString());
		}
		
		
	}
	
	
}
