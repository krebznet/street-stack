package com.dunkware.trade.tick.service.server.feed;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.admin.TopicListing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.dunkware.common.kafka.admin.DKafkaAdmin;
import com.dunkware.common.util.json.DJson;
import com.dunkware.net.cluster.node.trace.Trace;
import com.dunkware.net.cluster.node.trace.TraceLogger;
import com.dunkware.net.cluster.node.trace.TraceService;
import com.dunkware.trade.tick.model.feed.TickFeedSpec;
import com.dunkware.trade.tick.model.provider.TickProviderSpec;
import com.dunkware.trade.tick.service.server.feed.repository.FeedProviderDO;
import com.dunkware.trade.tick.service.server.feed.repository.FeedRepository;
import com.dunkware.trade.tick.service.server.logging.LoggingService;

@Component
@Profile("FeedService")
public class FeedService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private List<FeedServiceProvider> serviceProviders = new ArrayList<FeedServiceProvider>();
	
	@Autowired
	private TraceService trace; 
	
	@Autowired
	private FeedRepository providerRepo;
	
	@Autowired
	private ApplicationContext ac;
	
	
	@Value("${kafka.brokers}")
	private String propKafkaBrokes;
	@Value("${kafka.zookeepers}")
	private String zookeepers;
	
	private List<FeedStream> streams = new ArrayList<FeedStream>();
	
	@Autowired
	private LoggingService logging; 
	
	private Marker logMarker = null;
	
	private TraceLogger traceLogger; 
	
	@PostConstruct 
	private void load() {
		 traceLogger = trace.logger(getClass());
		System.out.println("loading feed service");
		traceLogger.info("Starting Feed Service");
		logMarker = logging.getMarker();
		Marker marker = MarkerFactory.getDetachedMarker("Monitor");
		Marker notify = MarkerFactory.getDetachedMarker("Notify");
		logger.info("Marker Test Starting Feed Service", marker);
		logger.info("Starting Feed Service");
		List<FeedProviderDO> providers = providerRepo.getProviders();
		for (FeedProviderDO tickProviderEntity : providers) {
			FeedServiceProvider provider = new FeedServiceProvider();
			ac.getAutowireCapableBeanFactory().autowireBean(provider);
			try {
				
				provider.load(tickProviderEntity);
				logger.info("Started " + provider.getEntity().getIdentifier(),notify);
				serviceProviders.add(provider);
				if(logger.isDebugEnabled()) { 
					logger.debug(logMarker, "Loaded Tick Provider " + tickProviderEntity.getIdentifier());
				}
			} catch (Exception e) {
				logger.error(logMarker, "Exception Loading Tick Provider " + e.toString() + " " + provider.getEntity().getIdentifier());
			}
			
		}
		
		// clean up stream topics 
		try {
			DKafkaAdmin admin = DKafkaAdmin.newInstance(zookeepers);
		Collection<TopicListing> topics = admin.getTopics();
			List<String> deletes = new ArrayList<String>();
			for (TopicListing topicListing : topics) {
				if(topicListing.name().startsWith("dunktrade_tick_feed")) {
					deletes.add(topicListing.name());
				}
	}
			if(deletes.size() > 0) { 
				admin.deleteTopics(deletes.toArray(new String[deletes.size()]));
			}
			
			
		} catch (Exception e) {
		e.printStackTrace();
		}
	
	}
	
	
	public FeedServiceProvider addServiceProvider(TickProviderSpec spec) throws Exception { 
		for (FeedServiceProvider feedServiceProvider : serviceProviders) {
			if(feedServiceProvider.getEntity().getIdentifier().equals(spec.getName())) { 
				throw new Exception("Feed Provider Name " + spec.getName() + " Already Exists");
			}
		}
		FeedProviderDO ent = new FeedProviderDO();
		ent.setIdentifier(spec.getName());
		ent.setSubscriptionLimit(0);
		try {
			String json = DJson.serialize(spec);
			ent.setJson(json);
			
		} catch (Exception e) {
			throw new Exception("Serialize Provider Type Json Exception " + e.toString());
		}
		providerRepo.saveProvider(ent);
		FeedServiceProvider sp = new FeedServiceProvider();
		ac.getAutowireCapableBeanFactory().autowireBean(sp);
		sp.load(ent);
		serviceProviders.add(sp);
		return sp;
	}
	
	public List<FeedServiceProvider> getProviders() { 
		return serviceProviders;
	}
	
	
	public FeedStream getStream(String streamId) { 
		for (FeedStream stream : streams) {
			if(stream.getId().equals(streamId)) { 
				return stream;
			}
		}
		return null;
	}
	
	public List<FeedStream> getStreams() { 
		return streams;
	}
	
	public void removeStream(FeedStream stream) { 
		this.streams.remove(stream);
	}
	
	public FeedStream startFeed(TickFeedSpec spec) throws Exception { 

		for (FeedStream stream : this.streams) {
			if(stream.getName().equals(spec.getName())) { 
				throw new Exception("Tick Feed Name " + spec.getName() + " Already Exists");
			}
		}
		FeedStream newStream = new com.dunkware.trade.tick.service.server.feed.FeedStream();
		newStream.start(spec, this);
		this.streams.add(newStream);
		return newStream;
	}

	public String getKafkaBrokers() { 
		return propKafkaBrokes;
	}
	
	public String getZookeepers() { 
		return zookeepers;
	}
	
	
	
	
}
