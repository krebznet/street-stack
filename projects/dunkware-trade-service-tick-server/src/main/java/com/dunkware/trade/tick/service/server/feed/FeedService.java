package com.dunkware.trade.tick.service.server.feed;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.admin.TopicListing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.dunkware.common.kafka.admin.DKafkaAdmin;
import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.helpers.DConverter;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.tick.model.feed.TickFeedSpec;
import com.dunkware.trade.tick.model.feed.TickFeedTicker;
import com.dunkware.trade.tick.model.provider.TickProviderSpec;
import com.dunkware.trade.tick.model.provider.TickProviderStatsSpec;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.trade.tick.service.protocol.service.spec.FeedServiceState;
import com.dunkware.trade.tick.service.protocol.service.spec.FeedServiceStats;
import com.dunkware.trade.tick.service.server.feed.repository.FeedProviderDO;
import com.dunkware.trade.tick.service.server.feed.repository.FeedRepository;
import com.dunkware.trade.tick.service.server.logging.LoggingService;
import com.dunkware.trade.tick.service.server.ticker.repsoitory.TickerListSubscribeDO;
import com.dunkware.trade.tick.service.server.ticker.repsoitory.TickerListSubscribeRepo;
import com.dunkware.trade.tick.service.server.ticker.repsoitory.TickerListTickerDO;

@Component
@Profile("FeedService")
public class FeedService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private List<FeedServiceProvider> serviceProviders = new ArrayList<FeedServiceProvider>();

	@Autowired
	private FeedRepository providerRepo;
	
	@Autowired
	private TickerListSubscribeRepo subRepo; 

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
	
	private FeedServiceState state = FeedServiceState.NoProviders;

	private Map<String, TradeTickerSpec> tickerSubscriptions = new ConcurrentHashMap<String, TradeTickerSpec>();

	private DDateTime startTime;

	private FeedServiceProvider activeServiceProvider = null;
	
	LocalDate today = null;
	
	private ResetDayChecker dayChecker;
	
	@PostConstruct
	private void load() {
		today = LocalDate.now(DTimeZone.toZoneId(DTimeZone.NewYork));
		startTime = DDateTime.now(DTimeZone.NewYork);
		dayChecker = new ResetDayChecker();
		dayChecker.start();
		logMarker = logging.getMarker();
		List<FeedProviderDO> providers = providerRepo.getProviders();
		for (FeedProviderDO tickProviderEntity : providers) {
			FeedServiceProvider provider = new FeedServiceProvider();
			ac.getAutowireCapableBeanFactory().autowireBean(provider);
			try {

				provider.load(tickProviderEntity);
				logger.debug("Tick Provider  " + provider.getEntity().getIdentifier() + "Loaded");
				serviceProviders.add(provider);
				state = FeedServiceState.Active;
				if (logger.isDebugEnabled()) {
					logger.debug(logMarker, "Loaded Tick Provider " + tickProviderEntity.getIdentifier());
				}
			} catch (Exception e) {
				logger.error(
						"Exception Loading Tick Provider " + e.toString() + " " + provider.getEntity().getIdentifier());
			}

		}
		if(serviceProviders.size() > 0) { 
			activeServiceProvider = serviceProviders.get(0);
		}
		
		
		
		for (TickerListSubscribeDO sub : subRepo.findAll()) {
			if(logger.isDebugEnabled()) { 
				logger.debug("Adding Default Subscription ticker list " +  sub.getList().getName());
			}
			List<TickerListTickerDO> subTickers = sub.getList().getTickers();
			for (TickerListTickerDO ticker : subTickers) {
				
				if (tickerSubscriptions.get(ticker.getTicker().getSymbol()) == null) {
					TradeTickerSpec spec = new TradeTickerSpec();
					spec.setId(DConverter.longToInt(ticker.getTicker().getId()));
					spec.setSymbol(ticker.getTicker().getSymbol());
					spec.setName(ticker.getTicker().getSecurityName());
					tickerSubscriptions.put(spec.getSymbol(), spec);
				}

			}
			logger.info("Loaded " + tickerSubscriptions.size() + " Default Subscriptions");
			
			
		}
		

		// clean up stream topics
		DKafkaAdmin admin = null;
		try {
			try {
				logger.info("Deleting Old Feed Topics");
				admin = DKafkaAdmin.newInstance(zookeepers);
				try {
					Collection<TopicListing> topics = admin.getTopics();
					List<String> deletes = new ArrayList<String>();
					for (TopicListing topicListing : topics) {
						if (topicListing.name().startsWith("street_feed")) {
							
							deletes.add(topicListing.name());
						}
					}
					if (deletes.size() > 0) {
						admin.deleteTopics(deletes.toArray(new String[deletes.size()]));
						logger.info("Deleted " + deletes.size() + " Feed Topics");
					}
				} catch (Exception e) {
					logger.error("Exception Deleting Old Feed Topics " + e.toString());
				}
			} catch (Exception e) {
				logger.error("Exception creating DKafkaAdmin from ZooKeepers {}", zookeepers);
			}

		} catch (Exception e) {
			logger.error("Exception connecting to KafkaAdmin to delete feed topics " + e.toString() + " url " + zookeepers);
		}
		
		if(hasTickProvider()) { 
			logger.info("Adding " + tickerSubscriptions.size() + " to active provider");
			activeServiceProvider.getProvider().subscribeTickers(tickerSubscriptions.values());
		}
	

	}
	
	
	public boolean hasTickProvider() { 
		if(activeServiceProvider == null) { 
			return false;
		}
		return true; 
	}
	
	public FeedServiceState getState() { 
		return state; 
	}
	
	public List<TradeTickerSpec> getValidatedSubscriptions() { 
		
		if(activeServiceProvider == null) { 
			return new ArrayList<TradeTickerSpec>();
		}
		return activeServiceProvider.getProvider().getValidatedSubscriptions();
	}

	public FeedServiceProvider addServiceProvider(TickProviderSpec spec) throws Exception {
		for (FeedServiceProvider feedServiceProvider : serviceProviders) {
			if (feedServiceProvider.getEntity().getIdentifier().equals(spec.getName())) {
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
			if (stream.getId().equals(streamId)) {
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
			if (stream.getName().equals(spec.getName())) {
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
	
	public List<TradeTickerSpec> getInvalidatedSubscriptions() { 
		if(activeServiceProvider == null) { 
			return new ArrayList<TradeTickerSpec>();
		}
		return activeServiceProvider.getProvider().getInValidatedSubscriptions();
	}
	
	public TickFeedTicker getFeedTicker(String symbol) throws Exception { 
		if(this.serviceProviders.size() == 0) { 
			throw new Exception("Tick Provider Not Found");
		}
		return serviceProviders.get(0).getProvider().getFeedTicker(symbol);
	}

	public Map<String,TradeTickerSpec> getTickerSubscriptions() throws Exception {
		return tickerSubscriptions;
	}
	
	public FeedServiceStats getStats() { 
		FeedServiceStats stats = new FeedServiceStats();
		stats.setStartTime(startTime);
		stats.setState(state);
		stats.setSubscriptionCount(tickerSubscriptions.size());
		if(activeServiceProvider == null) { 
			return stats;
		}
		TickProviderStatsSpec providerStats = activeServiceProvider.getProviderStats();
		stats.setMessageCount(providerStats.getMessageCount());
		stats.setAggCount(providerStats.getSecondAggCount());
		stats.setQuoteCount(providerStats.getQuoteCount());
		stats.setQps(providerStats.getQps());
		stats.setMps(providerStats.getMps());
		stats.setAps(providerStats.getAps());
		
		stats.setValidatedSubscriptionCount(activeServiceProvider.getProvider().getValidatedSubscriptions().size());
		stats.setInvalidatedSubscriptionCount(activeServiceProvider.getProvider().getInValidatedSubscriptions().size());
		stats.setMessageQueueSize(providerStats.getMessageQueueSize());
		stats.setFeedCount(this.streams.size());
		return stats;
	}
	
	
	private class ResetDayChecker extends Thread { 
		
		public void run() { 
			try {
				while(!interrupted()) { 
					LocalDate rightNow = LocalDate.now(DTimeZone.toZoneId(DTimeZone.NewYork));
					if(rightNow.isAfter(today)) { 
						logger.info("Feed Service Reset Day Triggered");
						for (FeedServiceProvider feedServiceProvider : serviceProviders) {
							feedServiceProvider.getProvider().resetDay();
						}
						today = rightNow;
					}
					Thread.sleep(5000);
				}
			} catch (Exception e) {
				logger.error("Reset day checker exception " + e.toString());
			}
		}
		
	}

}
