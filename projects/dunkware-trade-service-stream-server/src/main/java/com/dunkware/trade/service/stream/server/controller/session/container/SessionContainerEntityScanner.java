package com.dunkware.trade.service.stream.server.controller.session.container;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.data.NetScanner;
import com.dunkware.common.util.data.NetScannerDelta;
import com.dunkware.common.util.json.DJson;
import com.dunkware.net.proto.cluster.GContainerServerMessage;
import com.dunkware.net.proto.cluster.GContainerServerMessage.TypeCase;
import com.dunkware.net.proto.cluster.GContainerWorkerMessage;
import com.dunkware.net.proto.netstream.GNetEntityScannerStartRequest;
import com.dunkware.net.proto.netstream.GNetEntityScannerStartResponse;
import com.dunkware.net.proto.netstream.GNetEntityScannerStopRequest;
import com.dunkware.net.proto.netstream.GNetEntityScannerUpdate;
import com.dunkware.net.proto.netstream.GNetEntityScannerUpdateRequest;
import com.dunkware.net.proto.netstream.GNetEntityScannerUpdateResponse;
import com.dunkware.trade.service.stream.server.streaming.StreamingAdapter;
import com.dunkware.trade.service.stream.server.streaming.StreamingListener;
import com.dunkware.xstream.model.scanner.SessionEntityScanner;

public class SessionContainerEntityScanner implements StreamingListener {

	private NetScanner netScanner;
	private SessionEntityScanner entityScanner;
	private SessionContainer sessionContainer;
	private GContainerWorkerMessage startRequestMessage;
	private String scannerIdentifier;

	private List<WorkerNodeScanner> workerScanners = new ArrayList<WorkerNodeScanner>();
	private StreamingAdapter streamingAdapter;
	private ScannerController scannerController;  
	
	private ControllerState state = ControllerState.PendingStartResponses;
	
	private AtomicInteger pendingStartResponses = new AtomicInteger(0);
	private AtomicInteger pendingUpdateResponses = new AtomicInteger(0);
	private AtomicInteger pendingStopResponses = new AtomicInteger(0);
	
	private String updateError = null;
	private String startError = null;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private boolean disposed = false; 
	
	private BlockingQueue<NetScannerDelta> streamDeltas = new LinkedBlockingQueue<NetScannerDelta>();
	
	private BlockingQueue<Boolean> startBlocker = new LinkedBlockingQueue<Boolean>();
	private BlockingQueue<Boolean> updateBlocker = new LinkedBlockingQueue<Boolean>();
	
	private DataStreamer dataStreamer; 
	
	private static enum ControllerState { 
		PendingStartResponses,StartException,Running,PendingUpdateResponses,Disposed,UpdateException
	}

	public void start(StreamingAdapter streamingAdapter, SessionEntityScanner scanner, SessionContainer container,
			String scannerIdentifier) throws Exception {
		this.entityScanner = scanner;
		this.streamingAdapter = streamingAdapter;
		this.scannerIdentifier = scannerIdentifier;
		this.sessionContainer = container;

		String model = null;
		try {
			model = DJson.serialize(scanner);
		} catch (Exception e) {
			throw new Exception("Exception Serializing Your Session Entity Scanner Request " + e.toString());
		}

		this.scannerController = new ScannerController();
		
		// Generiic entity scanner start request for all workers.
		GNetEntityScannerStartRequest startReq = GNetEntityScannerStartRequest.newBuilder()
				.setScannerIdent(scannerIdentifier).setModel(model).build();
		startRequestMessage = GContainerWorkerMessage.newBuilder().setEntityScannerStartRequest(startReq).build();

		// we created Worker Scanner for each session container worker.
		for (SessionContainerWorker worker : sessionContainer.getWorkers()) {
			WorkerNodeScanner workerScanner = new WorkerNodeScanner(worker,scannerController);
			workerScanners.add(workerScanner);
		}
		
		// start the data streamer 
		dataStreamer.start();
		
		// start the controller which will send Boolean to startBlock with start flag 
		scannerController.start();

		for (WorkerNodeScanner worker : workerScanners) {
			worker.start();
		}
		

	}

	public StreamingAdapter getStreamingAdapter() {
		return streamingAdapter;
	}

	public void dispose() {
		if(!disposed) { 
			for (WorkerNodeScanner scanner : workerScanners) {
				scanner.dispose();
			}
			dataStreamer.interrupt();
			state = ControllerState.Disposed;
		}
	}

	public void update(SessionEntityScanner scanner) throws Exception  {
		scannerController.updateScanner(scanner); // block here 
		
	}

	public NetScanner getNetScanner() {
		return netScanner;
	}

	private interface WorkerListener {
		
		public void starting(SessionContainerWorker worker);

		public void startException(SessionContainerWorker worker, String error);

		public void updateException(SessionContainerWorker worker, String error);
		
		public void updateStarting(SessionContainerWorker worker);

		public void updateComplete(SessionContainerWorker worker);
		
		public void scannerData(SessionContainerWorker worker, NetScannerDelta delta);

		public void started(SessionContainerWorker worker);

		public void stopped(SessionContainerWorker worker);

	}
	
	
	@Override
	public void clientDisconnect(StreamingAdapter adapter) {
		// logg it here 
		dispose();
		
	}

	@Override
	public void serverDisconnect(StreamingAdapter adapter) {
		// TODO Auto-generated method stub
		
	}

	private class ScannerController extends Thread implements WorkerListener { 
		
		
		
		
		private List<SessionContainerWorker> startErrorWorkers = new ArrayList<SessionContainerWorker>();
		
		private LocalTime startTime;
		
		public void run() { 
			startTime = LocalTime.now();
			
			while(!interrupted()) { 
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return; 
					}
				}
				
				// handle state 
				if(state == ControllerState.PendingStartResponses) { 
					
					if(pendingStartResponses.get() == 0) { 
						if(startError != null) { 
							// then we bombed need to send that notification back 
						}
					}
				}
				
			}
		
		}
		
		
		/**
		 * Updates the scanner, creates scanner update request, calls each worker
		 * to send message and handle response back. 
		 * @param scanner
		 * @throws Exception
		 */
		public void updateScanner(SessionEntityScanner scanner) throws Exception { 
			// create the message and send it on to each worker
			
			GNetEntityScannerUpdateRequest req = GNetEntityScannerUpdateRequest.newBuilder().setScannerIdent(scannerIdentifier)
			.setSearch(DJson.serialize(scanner)).build();
			GContainerWorkerMessage message = GContainerWorkerMessage.newBuilder().setEntityScannerUpdateRequest(req).build();
			pendingUpdateResponses.set(0);
			state = ControllerState.PendingUpdateResponses;
			for (WorkerNodeScanner workerScanner : workerScanners) {
				workerScanner.update(message);
			}
			Boolean results = updateBlocker.poll(10, TimeUnit.SECONDS);
			if(results == null) { 
				throw new Exception("Did not get worker responses within 10 seconds");
			}
			if(results == false) { 
				if(updateError == null) { 
					updateError = "Unknown Update Exception Error Not Set but blocker returned false";
				}
				throw new Exception(updateError);
			}
			updateError = null;
			pendingUpdateResponses.set(0);
			
			
		}
		
		

		/**
		 * Called before worker sends scanner start request 
		 * we increment pending start responses 
		 */
		@Override
		public void starting(SessionContainerWorker worker) {
			pendingStartResponses.incrementAndGet();
		}

		/**
		 * called when a worker fucked up we decrement pending start responses
		 * we set the start error and we should update the state 
		 */
		@Override
		public void startException(SessionContainerWorker worker, String error) {
			startErrorWorkers.add(worker);
			startError = "Worker " + worker.getWorkerId() + " Error" + error;
			pendingStartResponses.decrementAndGet();
			state = ControllerState.StartException;
			dispose();
			startBlocker.add(Boolean.FALSE);
		}

		/**
		 * Okay An update exception what we do here is --- 
		 */
		@Override
		public void updateException(SessionContainerWorker worker, String error) {
			pendingUpdateResponses.decrementAndGet();
			updateError = "Worker " + worker.getWorkerId() + " Error " + error;
			state = ControllerState.UpdateException; 
			updateBlocker.add(Boolean.FALSE);
		}
		
	
		@Override
		public void updateStarting(SessionContainerWorker worker) {
			pendingUpdateResponses.incrementAndGet();
		}


		@Override
		public void scannerData(SessionContainerWorker worker, NetScannerDelta delta) {
			streamDeltas.add(delta);
		}
		
	

		@Override
		public void updateComplete(SessionContainerWorker worker) {
			pendingUpdateResponses.decrementAndGet();
			if(pendingUpdateResponses.get() == 0) { 
				if(state != ControllerState.UpdateException) {
					state = ControllerState.Running;
					updateBlocker.add(Boolean.TRUE);
				}
			}
		}


		@Override
		public void started(SessionContainerWorker worker) {
			pendingStartResponses.decrementAndGet();
		}

		@Override
		public void stopped(SessionContainerWorker worker) {
			// TODO Auto-generated method stub
			
		}
		
		
	}

	/**
	 * This deals with controlling a scanner on a worker node
	 * 
	 * @author duncankrebs
	 *
	 */
	private class WorkerNodeScanner implements SessionContainerHandler {

		private SessionContainerWorker worker;
		private WorkerListener listener; 
		
		private LocalTime lastScannerUpdate = null;
		private boolean disposed = false; 
		
		public WorkerNodeScanner(SessionContainerWorker worker, WorkerListener listener) {
			this.worker = worker;
			this.listener = listener; 
		}

		public void start() {
			sessionContainer.addMessageHandler(this);
			listener.starting(worker);
			worker.sendMessage(startRequestMessage);
		}
		
		public void update(GContainerWorkerMessage message) { 
			
		}

		public void dispose() {
			if(!disposed) { 
				
			GContainerWorkerMessage message = 	GContainerWorkerMessage.newBuilder().setEntityScannerStopRequest(GNetEntityScannerStopRequest.newBuilder().setScannerId(scannerIdentifier).build()).build();
			worker.sendMessage(message);
			listener.stopped(worker);
			sessionContainer.removeMessageHandler(this);
			disposed = true;
			}
		}

		
		@Override
		public void onControllerMessage(GContainerServerMessage message) {
			if (message.getTypeCase() == TypeCase.ENTITYSCANNERSTARTRESPONSE) {
				GNetEntityScannerStartResponse resp = message.getEntityScannerStartResponse();
				if (resp.getScannerIdent().equals(scannerIdentifier)) {
					if (resp.getError() != null) {
						listener.startException(worker, resp.getError());
					} else { 
						NetScannerDelta delta = null; 
						try {
							delta = DJson.getObjectMapper().readValue(resp.getModel(), NetScannerDelta.class);
						} catch (Exception e) {
							logger.error("Exception deserializing NetScanner Delta on worker node " + e.toString());
							listener.startException(worker, "NetScanner Delta Deserialize Exception " + e.toString());
							return;
						}
						listener.started(worker);
						listener.scannerData(worker, delta);
					}
				}
			}
			if(message.getTypeCase() == TypeCase.ENTITYSCANNERUPDATE) { 
				GNetEntityScannerUpdate update = message.getEntityScannerUpdate(); 
				if(update.getScannerIdent().equals(scannerIdentifier)) { 
					NetScannerDelta delta = null; 
					try {
						delta = DJson.getObjectMapper().readValue(update.getModel(), NetScannerDelta.class);
						listener.scannerData(worker, delta);
					} catch (Exception e) {
						logger.error("Exception deserializing NetScanner Delta on worker node " + e.toString());
						listener.startException(worker, "NetScanner Delta Deserialize Exception " + e.toString());
						return;
					}
				}
			}
			if(message.getTypeCase() == TypeCase.ENTITYSCANNERUPDATERESPONSE) { 
				GNetEntityScannerUpdateResponse resp = message.getEntityScannerUpdateResponse();
				if(resp.getScannerId().equals(scannerIdentifier)) { 
					if(resp.getError() != null) { 
						listener.updateException(worker, resp.getError());
					} else { 
						listener.updateComplete(worker);
	
					}
				}
			}

		}
		
	}
	
	
	/**
	 * Work around for now we want to just send entire data set not updates/inserts/updates/ 
	 * @author duncankrebs
	 *
	 */
	private class DataStreamer extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				try {
					NetScannerDelta delta = streamDeltas.poll(5, TimeUnit.SECONDS);
					if(delta == null) { 
						// log nothing after 5 minutes
						continue;
					}
					if(streamingAdapter.isConnected() == false) { 
						// dispose? 
					}
					try {
						streamingAdapter.send(delta);
					} catch (Exception e) {
						
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}

}
