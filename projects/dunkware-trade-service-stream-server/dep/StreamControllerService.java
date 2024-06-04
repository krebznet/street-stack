package com.dunkware.stream.controller.stream;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dunkware.common.util.json.DJson;
import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.stream.controller.persistence.DBStream;
import com.dunkware.stream.controller.persistence.DBStreamRepo;
import com.dunkware.stream.controller.persistence.DBStreamVersionRepo;
import com.dunkware.stream.controller.persistence.DStreamVersion;
import com.dunkware.stream.controller.tick.StreamTickService;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptors;
import com.dunkware.trade.service.stream.json.controller.spec.StreamControllerSpec;


@Service
public class StreamControllerService {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

	private List<StreamController> controllers = new ArrayList<StreamController>();

	@Autowired
	private DBStreamRepo streamRepo;

	@Autowired
	private DBStreamVersionRepo versionRepo;

	@Autowired
	private ApplicationContext ac;
	
	@Autowired
	private StreamTickService tickService; 
	
	@Value("${streams.schedule.enable}")
	private boolean enableSchedule = true; 
	
	@Autowired
	private DunkNet dunkNet; 
	
	@PostConstruct
	private void load() {
		logger.info("Starting Stream Controller Servie");
		Thread runner = new Thread() { 
			public void run() { 
				try {
					//Thread.sleep(500);
					Iterable<DBStream> ents = streamRepo.findAll();
					for (DBStream ent : ents) {
						if (logger.isInfoEnabled()) {
							logger.info("Initializing Stream Controller " + ent.getName());
						}
						StreamController con = new StreamController();
						ac.getAutowireCapableBeanFactory().autowireBean(con);
						controllers.add(con);
						con.start(ent);
						
					}
				} catch (Exception e) {
					logger.error("Exception loading streams " + e.toString());
					System.exit(-1);
				}
		
			}
		};
		runner.start();
	}
	
	public StreamDescriptors getStreamDescriptors() { 
		StreamDescriptors desc = new StreamDescriptors();
		for (StreamController streamController : controllers) {
			desc.getDescriptors().add(streamController.getDescriptor());
		}
		return desc;
	}

	@Transactional
	public StreamController addStream(StreamControllerSpec spec) throws Exception {
		DBStream ent = new DBStream();
		String[] tickerLists = spec.getTickers().split(",");
		// we should validate these lists
		for (String list : tickerLists) {
			try {
				tickService.getClient().getTickerList(list);
			} catch (Exception e) {
				throw new Exception("Exception validating Ticker List " + list + " " + e.toString());
			}
		}
		ent.setTickerLists(spec.getTickers());
		ent.setDataTicks(spec.getDataTicks());
		ent.setSpec(DJson.serialize(spec));
		ent.setName(spec.getName());
		ent.setCountry(spec.getCountry());
		DStreamVersion ver = new DStreamVersion();
		ver.setStream(ent);
		ver.setVersion(spec.getVersion());
		ver.setBundle(DJson.serialize(spec.getBundle()));
		System.out.println(ver.getBundle());
		ent.getVersions().add(ver);

		try {
			streamRepo.save(ent);
		} catch (Exception e) {
			throw new Exception("Insert Stream Enttiy expetion " + e.toString());
		}
		// need a spring version
		StreamController controller = new StreamController();
		ac.getAutowireCapableBeanFactory().autowireBean(controller);
		controller.start(ent);
		controllers.add(controller);

		return controller;
	}

	public List<StreamController> getStreams() {
		return controllers;
	}

	public StreamController getStreamByName(String name) throws Exception {
		for (StreamController con : controllers) {
			if (con.getEntity().getName().equals(name)) {
				return con;
			}
		}
		throw new Exception("Stream Controller " + name + " not found");
	}


	public StreamController getStreamById(long id) throws Exception {
		for (StreamController con : controllers) {
			if (con.getEntity().getId() == id) {
				return con;
			}
		}
		throw new Exception("Stream Controller id " + id + " not found");
	}

	

	public void updateStream(StreamControllerSpec spec) throws Exception {
		StreamController stream = getStreamByName(spec.getName());
		Double newVersion = spec.getVersion();
		DStreamVersion newVersionDO = new DStreamVersion();
		newVersionDO.setStream(stream.getEntity());
		newVersionDO.setVersion(newVersion);
		newVersionDO.setBundle(DJson.serialize(spec.getBundle()));
		newVersionDO.setTimestamp(LocalDateTime.now());
		
		Iterable<DStreamVersion> versions = versionRepo.findAll();
		boolean specVerisionGreater = true;
		for (DStreamVersion version : versions) {
			if (spec.getVersion() < version.getVersion() || spec.getVersion() == version.getVersion()) {
				specVerisionGreater = false;
			}
		}
		// plural?
		stream.getEntity().setTickerLists(spec.getTickers());
		if (specVerisionGreater) {
			// save the stream entity after adding new version
			stream.getEntity().setSpec(DJson.serialize(spec));
			stream.getEntity().getVersions().add(newVersionDO);
		} else {
			// else
			StreamControllerSpec entitySpec = DJson.getObjectMapper().readValue(stream.getEntity().getSpec(), StreamControllerSpec.class);
			spec.setBundle(entitySpec.getBundle());
			stream.getEntity().setSpec(DJson.serialize(spec));
		}
		try {
			streamRepo.save(stream.getEntity());
		} catch (Exception e) {
			throw new Exception("Exception Saving Stream Entity " + e.toString(), e);
		}
		// notify spec update
		// no stream controller
		

	}
	
	
	

}
