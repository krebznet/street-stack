package com.dunkware.trade.service.stream.server.controller;

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
import com.dunkware.trade.service.stream.json.controller.spec.StreamControllerSpec;
import com.dunkware.trade.service.stream.server.repository.StreamEntity;
import com.dunkware.trade.service.stream.server.repository.StreamRepo;
import com.dunkware.trade.service.stream.server.repository.StreamVersionEntity;
import com.dunkware.trade.service.stream.server.repository.StreamVersionRepo;
import com.dunkware.trade.service.stream.server.tick.StreamTickService;


@Service
public class StreamControllerService {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

	private List<StreamController> controllers = new ArrayList<StreamController>();

	@Autowired
	private StreamRepo streamRepo;

	@Autowired
	private StreamVersionRepo versionRepo;

	@Autowired
	private ApplicationContext ac;
	
	@Autowired
	private StreamTickService tickService; 
	
	@Value("${streams.schedule.enable}")
	private boolean enableSchedule = true; 
	

	@PostConstruct
	private void load() {
		logger.info("Starting Stream Controller Servie");
		Thread runner = new Thread() { 
			public void run() { 
				try {
					//Thread.sleep(500);
					Iterable<StreamEntity> ents = streamRepo.findAll();
					for (StreamEntity ent : ents) {
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

	
	@Transactional
	public StreamController addStream(StreamControllerSpec spec) throws Exception {
		StreamEntity ent = new StreamEntity();
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
		StreamVersionEntity ver = new StreamVersionEntity();
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


	public void updateStream(StreamControllerSpec spec) throws Exception {
		StreamController stream = getStreamByName(spec.getName());
		Double newVersion = spec.getVersion();
		StreamVersionEntity newVersionDO = new StreamVersionEntity();
		newVersionDO.setStream(stream.getEntity());
		newVersionDO.setVersion(newVersion);
		newVersionDO.setBundle(DJson.serialize(spec.getBundle()));
		newVersionDO.setTimestamp(LocalDateTime.now());
		
		Iterable<StreamVersionEntity> versions = versionRepo.findAll();
		boolean specVerisionGreater = true;
		for (StreamVersionEntity version : versions) {
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
		stream.specUpdate(spec);

	}

}
