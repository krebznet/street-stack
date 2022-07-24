package com.dunkware.trade.service.stream.server.controller.session.container.scanner;

 
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.spring.channel.anot.AMessageHandler;
import com.dunkware.spring.message.Message;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainer;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainerNode;
import com.dunkware.xstream.container.proto.EntityScannerDelta;
import com.dunkware.xstream.container.proto.EntityScannerStartReq;
import com.dunkware.xstream.container.proto.EntityScannerStartResp;


public class SessionContainerEntityScanner {

	private SessionContainer sessionContainer; 
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private EntityScannerStartReq req; 
	
	private BlockingQueue<SessionEntityScannerNode> nodeStartCallbackQueue = new LinkedBlockingQueue<SessionEntityScannerNode>(); 
	private List<SessionEntityScannerNode> scannerNodes = new ArrayList<SessionEntityScannerNode>();
	
	private List<SessionContainerEntityScannerListener> listeners = new ArrayList<SessionContainerEntityScannerListener>();
	
	private boolean disposed = false; 
	
	private Marker marker = MarkerFactory.getMarker("SessionContainerEntityScanner");
	
	private String scannerId; 
	
	public String start(EntityScannerStartReq req, SessionContainer sessionContainer) throws Exception { 
		this.req = req;
		scannerId = DUUID.randomUUID(5);
		
		this.sessionContainer = sessionContainer;
		try {
			for (SessionContainerNode node : sessionContainer.getNodes()) {
				SessionEntityScannerNode scannerNode = new SessionEntityScannerNode(node);
				this.scannerNodes.add(scannerNode);
			}
		} catch (Exception e) {
			logger.error("Exception creating scanner entity nodes wtf " + e.toString());
			throw new Exception("Exception creating scanner nodes " + e.toString());
		}
		
		// now try to start them 
		for (SessionEntityScannerNode sessionEntityScannerNode : scannerNodes) {
			sessionEntityScannerNode.start();
		}
		AtomicInteger pendingStartCallback = new AtomicInteger(scannerNodes.size());
		int nodeNullCount = 0;
		while(pendingStartCallback.get() != 0) { 
			SessionEntityScannerNode scannerNode = nodeStartCallbackQueue.poll(15, TimeUnit.SECONDS); 
			if(scannerNode == null) { 
				nodeNullCount++;
				if(nodeNullCount > 30) { 
					logger.error(marker, "Did not get all start callbacks");
					throw new Exception("Start node callbacks are not returning within time limit fix me");
				} else { 
					
				}
				continue;
			}
			if(scannerNode.startException == true) {
				throw new Exception("Scanner Node Start Exception " + scannerNode.getStartError());
				
			}
			pendingStartCallback.decrementAndGet();
		}
		
		// okay we are started 
		return scannerId;
	}
	
	
	public String getScannerId() { 
		return scannerId;
	}
	
	public void dispose() { 
		if(disposed)
			return;
		for (SessionEntityScannerNode sessionEntityScannerNode : scannerNodes) {
			sessionEntityScannerNode.dispose();
		}
		disposed = true;
	}
	
	
	public void addListener(SessionContainerEntityScannerListener listener) { 
		this.listeners.add(listener);
	}
	
	public void removeListener(SessionContainerEntityScannerListener listener) { 
		this.listeners.remove(listener);
	}
	
	
	public class SessionEntityScannerNode extends Thread  { 
		
		private String startError; 
		private SessionContainerNode node; 
		private boolean startException = false; 
		private String scannerId; 
		private boolean disposed = false; 
		public SessionEntityScannerNode(SessionContainerNode node) { 
			this.node = node; 
			
		}
		
		public String getStartError() { 
			return startError; 
		}
		
		public boolean startException() { 
			return startException;
		}
		
		
		public void run() { 
			// send a start request 
			try {
			Message response = node.getChannel().sendReply(req);
			EntityScannerStartResp resp = (EntityScannerStartResp)response.getPayload();
			if(resp.getException() != null) { 
				startException = true; 
				startError = "Returned Response Error " + resp.getException(); 
				nodeStartCallbackQueue.add(this);
				return;
			}
			// else we are good 
			this.scannerId = resp.getScannerId(); 
			node.getChannel().addHandler(this);
			nodeStartCallbackQueue.add(this);
			} catch (Exception e) {
				logger.error("Exception sending entity scanner start request to worker node " + e.toString());;
				startError = e.toString();
				startException = true; 
				nodeStartCallbackQueue.add(this);
			}
		}
		
		
		public void dispose() {
			if(disposed = true)
			try {
				
				node.getChannel().removeHandler(this);	
				disposed = true; 
			} catch (Exception e) {
				logger.error("Exception disposing entitys canner session node " + e.toString());
			}
			
		}
		
		@AMessageHandler()
		public void scannerUpdate(EntityScannerDelta delta) { 
			if(logger.isDebugEnabled()) { 
				logger.debug(marker, "Received EntityScanner Delta inserts {} updates {} deletes {}",delta.getDelta().getInserts().size(), delta.getDelta().getUpdates().size(), delta.getDelta().getDeletes().size());
			}
			if(delta.getScannerId().equals(scannerId)) { 
				for (SessionContainerEntityScannerListener sessionEntityScannerListener : listeners) {
					sessionEntityScannerListener.scannerDelta(delta.getDelta());
				}
			}
		}
	}
	
	
	
}
