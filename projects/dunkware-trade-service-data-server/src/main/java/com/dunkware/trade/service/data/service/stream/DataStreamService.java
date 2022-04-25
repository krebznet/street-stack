package com.dunkware.trade.service.data.service.stream;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.net.proto.stream.GStreamSpec;
import com.dunkware.net.proto.stream.GStreamSpecsResponse;
import com.dunkware.trade.service.data.service.config.RuntimeConfig;
import com.dunkware.trade.service.data.service.grpc.GrpcStreamServiceClient;
import com.dunkware.trade.service.data.service.repository.DataStreamEntity;
import com.dunkware.trade.service.data.service.repository.DataStreamEntityRepo;

@Service
public class DataStreamService   {

	@Autowired
	private RuntimeConfig configService;

	@Autowired
	private ApplicationContext ac;

	
	@Autowired
	private DataStreamEntityRepo streamRepo;
	
	private Map<String, DataStream> dataStreams = new ConcurrentHashMap<String, DataStream>();

	private Map<String, DataStreamEntity> dataStreamEntities = new ConcurrentHashMap<String, DataStreamEntity>();

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private GrpcStreamServiceClient streamServiceClient;

	private DataStreamSession currentSession = null;

	@Transactional
	@PostConstruct
	public void load() {
		logger.info("Starting Dat Stream Service");
		// first popuplate stream entity map
		for (DataStreamEntity ent : streamRepo.findAll()) {
			dataStreamEntities.put(ent.getName(), ent);
			logger.info("Starting stored data stream " + ent.getName());
			DataStream stream = new DataStream();
			ac.getAutowireCapableBeanFactory().autowireBean(stream);
			dataStreams.put(ent.getName(), stream);
			try {
				stream.start(ent);	
			} catch (Exception e) {
				logger.error("Exception starting stored data stream " + e.toString());
			}
			
		}
		
		GStreamSpecsResponse streamSpecs = null;
		try {
			streamSpecs = streamServiceClient.streamSpecs();
			
		} catch (Exception e) {
			logger.error("Exception Getting GStreamSpecs from Stream GRPC Service " + e.toString());
			// try again or shut down
			System.exit(1);
		}
		for (GStreamSpec gspec : streamSpecs.getSpecsList()) {
			if (dataStreamEntities.get(gspec.getIdentifier()) == null) {
				// load it
				logger.info("Creating new data stream from spec identifier " + gspec.getIdentifier());
				DataStreamEntity ent = new DataStreamEntity();
				ent.setName(gspec.getIdentifier());
				ent.setCreated(LocalDateTime.now(DTimeZone.toZoneId(DTimeZone.NewYork)));
				streamRepo.save(ent);
				dataStreamEntities.put(ent.getName(), ent);
				DataStream dataStream = new DataStream();
				this.dataStreams.put(ent.getName(), dataStream);
				ac.getAutowireCapableBeanFactory().autowireBean(dataStream);
				try {
					logger.info("Starting New Data Stream " + gspec.getIdentifier());
					dataStream.start(dataStreamEntities.get(gspec.getIdentifier()));
				} catch (Exception e) {
					logger.error("Exception Starting Data Stream " + gspec.getIdentifier() + " " + e.toString(), e);
				}

			} else { // else

				logger.info("Creating New DataStream For " + gspec.getIdentifier());
				
			}
		}
		
	}

	public boolean streamExists(String name) {
		if (dataStreams.get(name) == null) {
			return false;
		}
		return true;
	}

	public DataStream getStream(String name) {
		return dataStreams.get(name);
	}

	public Collection<DataStream> getStreams() {
		return dataStreams.values();
	}


	
	// 

}
