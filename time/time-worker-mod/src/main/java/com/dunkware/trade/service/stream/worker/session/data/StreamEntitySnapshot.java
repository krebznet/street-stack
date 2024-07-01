package com.dunkware.trade.service.stream.worker.session.data;

import org.slf4j.Logger;

import com.dunkware.spring.cluster.anot.ADunkNetService;
import com.dunkware.time.data.model.entity.EntitySnapshotModel;
import com.dunkware.trade.service.stream.json.worker.service.EntitySnapshotRep;
import com.dunkware.trade.service.stream.json.worker.service.EntitySnapshotReq;
import com.dunkware.trade.service.stream.worker.session.StreamWorker;
import com.dunkware.trade.service.stream.worker.session.StreamWorkerExtension;
import com.dunkware.trade.service.stream.worker.session.anot.AStreamWorkerExtension;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntity;

@AStreamWorkerExtension
public class StreamEntitySnapshot implements StreamWorkerExtension {
	
	
	private StreamWorker worker; 
	private XStream stream; 
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

	@Override
	public void init(StreamWorker worker) throws Exception {
		worker.getChannel().addExtension(this);
		this.worker = worker; 
	} 

	@Override
	public void start() throws Exception {
		this.stream = worker.getStream();
	}

	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	@ADunkNetService(label = "Stream Entity Snapshot Service")
	public EntitySnapshotRep entitySnapshot(EntitySnapshotReq req) { 
		EntitySnapshotRep rep = new EntitySnapshotRep();
		// okay they want to an entity snapshot and varible values
		try {
			XStreamEntity entity = stream.getRow(req.getEntityId());
			if(entity == null) { 
				rep.setError(true);
				rep.setErrorMessage("entity not found");
			}
			EntitySnapshotModel snapshot = new EntitySnapshotModel();
			snapshot.setStreamId(0);
			snapshot.setEntityId(entity.getIdentifier());
			snapshot.setEntityIdent(req.getEntityIdent());
			snapshot.setVariables(entity.numericVarSnapshot());;
			rep.setSnapshot(snapshot);
			return rep; 
		} catch (Exception e) {
			rep.setError(true);
			rep.setErrorMessage(e.toString());;
			return rep; 
		}
		
		
	}
	
	

}
