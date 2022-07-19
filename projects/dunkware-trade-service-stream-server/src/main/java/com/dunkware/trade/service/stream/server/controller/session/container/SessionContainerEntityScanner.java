package com.dunkware.trade.service.stream.server.controller.session.container;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.dunkware.common.util.data.NetScanner;
import com.dunkware.common.util.data.NetScannerDelta;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.net.proto.cluster.GContainerServerMessage;
import com.dunkware.net.proto.cluster.GContainerServerMessage.TypeCase;
import com.dunkware.net.proto.cluster.GContainerWorkerMessage;
import com.dunkware.net.proto.netstream.GNetEntityScannerStartRequest;
import com.dunkware.net.proto.netstream.GNetEntityScannerStartResponse;
import com.dunkware.trade.service.stream.server.streaming.StreamingAdapter;
import com.dunkware.xstream.model.scanner.SessionEntityScanner;

public class SessionContainerEntityScanner {

	private NetScanner netScanner;
	private SessionEntityScanner entityScanner;
	private SessionContainer sessionContainer;
	private GContainerWorkerMessage startRequestMessage;
	private String scannerIdentifier;

	private List<WorkerNodeScanner> workerScanners = new ArrayList<WorkerNodeScanner>();
	private AtomicInteger pendingWorkerStartResponseCounter = new AtomicInteger(0);
	private AtomicInteger pendingWorkerUpdateResponseCounter = new AtomicInteger(0); 
	
	private StreamingAdapter streamingAdapter; 
	
	private StartController startController;
	
	private BlockingQueue<WorkerScannerStartResponse> startResponseQueue = new LinkedBlockingQueue<WorkerScannerStartResponse>();
	private BlockingQueue<WorkerScannerUpdateResponse> updateResponseQeue = new LinkedBlockingQueue<WorkerScannerUpdateResponse>();
	
	public void start(StreamingAdapter streamingAdapter, SessionEntityScanner scanner, SessionContainer container, String scannerIdentifier)
			throws Exception {
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
		
		// Generiic entity scanner start request for all workers. 
		GNetEntityScannerStartRequest startReq = GNetEntityScannerStartRequest.newBuilder()
				.setScannerIdent(scannerIdentifier).setModel(model).build();
		startRequestMessage = GContainerWorkerMessage.newBuilder().setEntityScannerStartRequest(startReq).build();
		
		// we created Worker Scanner for each session container worker. 
		for (SessionContainerWorker worker : sessionContainer.getWorkers()) {
			WorkerNodeScanner workerScanner = new WorkerNodeScanner(worker);
			workerScanners.add(workerScanner);
			pendingWorkerStartResponseCounter.incrementAndGet();
		}
		startController = new StartController();
		startController.start();
		for (WorkerNodeScanner worker : workerScanners) {
			// each send a message 
			// then send to blockign queue hey I'm done. 
			// can't we have a listener on that. 
		}
		

	}
	
	public StreamingAdapter getStreamingAdapter() { 
		return streamingAdapter;
	}

	public void dispose() {

	}

	public void update() {

	}

	public NetScanner getNetScanner() {
		return netScanner;
	}

	private interface WorkerNodeScannerListener { 
		
		public void startException(SessionContainerWorker worker, String error); 
		
		public void updateException(SessionContainerWorker worker, String error);
		
		public void updated(SessionContainerWorker worker); 
		
		public void started(SessionContainerWorker worker, Object message);
		
		public void stopped(SessionContainerWorker worker);
		
	}
	
	private class WorkerNodeScanner extends Thread implements SessionContainerHandler {

		private SessionContainerWorker worker;

		public WorkerNodeScanner(SessionContainerWorker worker) {
			this.worker = worker;
		}

		public void run() {
			sessionContainer.addMessageHandler(this);
			worker.sendMessage(startRequestMessage);
		}

		public void dispose() {

		}

		@Override
		public void onControllerMessage(GContainerServerMessage message) {
			if (message.getTypeCase() == TypeCase.ENTITYSCANNERSTARTRESPONSE) {
				GNetEntityScannerStartResponse resp = message.getEntityScannerStartResponse();
				// if good we should have our results
				// if bad we should have a bunk error
			}

		}
		
		
		private class UpdateController extends Thread { 
			
			
		}
		
		
		
	
	}
	
	private class StartController extends Thread { 
		
		public void run() { 
			DStopWatch watch = DStopWatch.create();
			watch.start();
			List<WorkerScannerStartResponse> responses = new ArrayList<WorkerScannerStartResponse>();
			AtomicInteger pendingResponses = new AtomicInteger(sessionContainer.getWorkers().size());
			while(!interrupted()) { 
				try {
					WorkerScannerStartResponse response = startResponseQueue.poll(5, TimeUnit.SECONDS);
					if(response == null) { 
						// okay so we need a return object here --> right ---> 
					}
					responses.add(response);
					int pendingCount = pendingResponses.decrementAndGet();
					if(pendingCount == 0) { 
						watch.stop();
					}
					break;
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}
	
	
	private static class WorkerScannerUpdateResponse { 
		private boolean error = false; 
		private String errorMessage; 
		private SessionContainerWorker worker; 
		private NetScannerDelta scannerDelta; 
		private String scannerIdentifier; 
	}
	
	private static class WorkerScannerStartResponse { 
		private boolean error = false; 
		private String errorMessage; 
		private SessionContainerWorker worker; 
		private NetScannerDelta scannerDelta; 
		private String scannerIdentifier; 
		
	}

}
