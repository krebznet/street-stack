package com.dunkware.trade.service.data.service.stream;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.helpers.DProtoHelper;
import com.dunkware.net.proto.stream.GStreamSpec;
import com.dunkware.net.proto.stream.GStreamSpecsResponse;
import com.dunkware.trade.service.data.json.data.stream.StreamStats;
import com.dunkware.trade.service.data.service.domain.StreamDO;
import com.dunkware.trade.service.data.service.grpc.GrpcStreamServiceClient;
import com.dunkware.trade.service.data.service.repository.StreamRepository;

@Service
public class StreamService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private ConcurrentHashMap<String, Stream> streams = new ConcurrentHashMap<String,Stream>();
	
	@Autowired
	private GrpcStreamServiceClient streamClient; 
	
	@Autowired
	private ApplicationContext ac; 
	
	@Autowired
	private StreamRepository streamRepo;

	
	@PostConstruct
	private void init() { 
		/*
		 * List<StreamDO> entities = streamRepo.findAll(); GStreamSpecsResponse resp =
		 * null; try { resp = streamClient.streamSpecs(); } catch (Exception e) {
		 * logger.error(MarkerFactory.getMarker("Data"),
		 * "Exception getting stream specs exiting " + e.toString()); System.exit(-1); }
		 * 
		 * for (GStreamSpec spec : resp.getSpecsList()) { boolean exists = false; for
		 * (StreamDO ent : entities) { if(ent.getStream().equals(spec.getIdentifier()))
		 * { try { Stream stream = new Stream();
		 * ac.getAutowireCapableBeanFactory().autowireBean(stream); stream.init(ent);
		 * this.streams.put(ent.getStream(), stream);
		 * logger.debug(MarkerFactory.getMarker("Data"),
		 * "Initialized existing stream from spec " + spec.getIdentifier()); exists =
		 * true; break; } catch (Exception e) {
		 * logger.error(MarkerFactory.getMarker("Data"), "Exception init stream " +
		 * ent.getStream() + " " + e.toString()); } } } if(!exists) { StreamDO newEnt =
		 * new StreamDO(); newEnt.setStream(spec.getIdentifier());
		 * newEnt.setTimeZone(DProtoHelper.toTimeZoneName(spec.getTimeZone()));
		 * newEnt.setCreated(LocalDateTime.now(DProtoHelper.toZoneId(spec.getTimeZone())
		 * )); try { streamRepo.save(newEnt); } catch (Exception e) {
		 * logger.error(MarkerFactory.getMarker("Data"),
		 * "Exception creating new stream entity " + e.toString()); } try { Stream
		 * stream = new Stream();
		 * ac.getAutowireCapableBeanFactory().autowireBean(stream); stream.init(newEnt);
		 * logger.debug(MarkerFactory.getMarker("Data"), "Created new stream from spec "
		 * + spec.getIdentifier()); this.streams.put(spec.getIdentifier(), stream); }
		 * catch (Exception e) { logger.error(MarkerFactory.getMarker("Data"),
		 * "Exception init new stream from spec " + e.toString()); } } }
		 */
	}
	
	public Collection<Stream> getStreams() { 
		return streams.values();
	}
	
	public List<StreamStats> getStreamsStats() { 
		List<StreamStats> stats = new ArrayList<StreamStats>();
		for (Stream stream : streams.values()) {
			stats.add(stream.getStats());
		}
		return stats;
	}
	
	public Stream getStream(String name) throws Exception { 
		Stream stream = streams.get(name);
		if(stream == null) { 
			throw new Exception("Stream " + stream + " not found");
		}
		return stream;
	}
	
	
	
	

	

}

