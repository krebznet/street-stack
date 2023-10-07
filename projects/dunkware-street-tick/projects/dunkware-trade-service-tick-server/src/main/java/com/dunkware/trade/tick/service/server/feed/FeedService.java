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
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.dunkware.common.kafka.admin.DKafkaAdminClient;
import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.common.util.helpers.DConverter;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.tick.api.consumer.TickConsumer;
import com.dunkware.trade.tick.api.feed.TickFeed;
import com.dunkware.trade.tick.api.feed.TickFeedSubscription;
import com.dunkware.trade.tick.api.feed.impl.TickFeedImpl;
import com.dunkware.trade.tick.api.provider.TickProvider;
import com.dunkware.trade.tick.model.consumer.TickConsumerSession;
import com.dunkware.trade.tick.model.consumer.TickConsumerSpec;
import com.dunkware.trade.tick.model.feed.TickFeedSubscriptionBean;
import com.dunkware.trade.tick.model.provider.TickProviderSpec;
import com.dunkware.trade.tick.model.provider.TickProviderStatsSpec;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.trade.tick.model.ticker.TradeTickerType;
import com.dunkware.trade.tick.service.protocol.service.spec.FeedServiceState;
import com.dunkware.trade.tick.service.protocol.service.spec.FeedServiceStats;
import com.dunkware.trade.tick.service.server.feed.repository.FeedProviderDO;
import com.dunkware.trade.tick.service.server.feed.repository.FeedRepository;
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
	
	private TickFeed tickFeed; 

	@Autowired
	private ApplicationContext ac;

	@Value("${kafka.brokers}")
	private String propKafkaBrokes;
	@Value("${kafka.zookeepers}")
	private String zookeepers;

	
	
	
	
	private Map<String,TickConsumer> consumers = new ConcurrentHashMap<String, TickConsumer>();


	private Marker logMarker = null;
	
	private FeedServiceState state = FeedServiceState.NoProviders;

	private Map<String, TradeTickerSpec> tickerSubscriptions = new ConcurrentHashMap<String, TradeTickerSpec>();

	private DDateTime startTime;

	private TickProvider activeProvider;
	
	LocalDate today = null;
	
	private ResetDayChecker dayChecker;
	
	@PostConstruct
	private void load() {
		tickFeed = new TickFeedImpl();
		today = LocalDate.now(DTimeZone.toZoneId(DTimeZone.NewYork));
		startTime = DDateTime.now(DTimeZone.NewYork);
		dayChecker = new ResetDayChecker();
		dayChecker.start();
		logMarker = MarkerFactory.getMarker("FeedService");
		List<FeedProviderDO> providers = providerRepo.getProviders();
		for (FeedProviderDO tickProviderEntity : providers) {
			FeedServiceProvider provider = new FeedServiceProvider();
			ac.getAutowireCapableBeanFactory().autowireBean(provider);
			try {

				provider.load(tickProviderEntity,this);
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
		if(serviceProviders.size() == 0) { 
			logger.error(MarkerFactory.getMarker("crash"), "Not Data Providers Configured on Tick Server");
			//System.exit(-1);
		}
		activeProvider = serviceProviders.get(0).getProvider();
		if(serviceProviders.size() > 0) { 
			
			try {
				tickFeed.start(activeProvider, new DExecutor(5),propKafkaBrokes);		
			} catch (Exception e) {
				logger.error(MarkerFactory.getMarker("Crash"), "Exception starting tick feed " + e.toString());
			//	System.exit(-1);
			}
		
			
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
			try {
				TradeTickerSpec ap = new TradeTickerSpec();
				ap.setName("Apple");
				ap.setId(1);
				ap.setSymbol("AAPL");
				ap.setType(TradeTickerType.Equity);
				
				
				TradeTickerSpec ap2 = new TradeTickerSpec();
				ap2.setName("SP INdex");
				ap2.setId(1);
				ap2.setSymbol("SPY");
				ap2.setType(TradeTickerType.Equity);
				
				tickFeed.subscribe(tickerSubscriptions.values());	
			} catch (Exception e) {
				logger.error("Exception subscribing default tickers to tick feed " + e.toString(),e);
			}

			logger.info("Loaded " + tickerSubscriptions.size() + " Default Subscriptions");
		}
		

		// clean up stream topics
		DKafkaAdminClient admin = null;
		try {
			try {
				logger.info("Deleting Old Feed Topics");
				admin = DKafkaAdminClient.newInstance(getKafkaBrokers());
				try {
					Collection<TopicListing> topics = new ArrayList<TopicListing>();
					List<String> deletes = new ArrayList<String>();
					for (TopicListing topicListing : topics) {
						if (topicListing.name().startsWith("street_feed")) {
							
							deletes.add(topicListing.name());
						}
					}
					if (deletes.size() > 0) {
					//	admin.deleteTopics(deletes.toArray(new String[deletes.size()]));
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
		
	}
	
	public TickConsumer getConsumer(String sessionId) { 
		return consumers.get(sessionId);
	}
	
	public void disposeConsumer(String sessionId) { 
		TickConsumer consumer = consumers.get(sessionId);
		if(consumer != null) { 
			consumer.dispose();
			consumers.remove(sessionId);
		}
	}

	public FeedServiceState getState() { 
		return state; 
	}
	
	public List<TradeTickerSpec> getValidatedSubscriptions() { 
		return null;
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
		sp.load(ent,this);
		serviceProviders.add(sp);
		return sp;
	}

	public List<FeedServiceProvider> getProviders() {
		return serviceProviders;
	}
	
	

	public TickFeed getFeed() { 
		return tickFeed;
	}

	public TickConsumerSession createConsumer(TickConsumerSpec spec) throws Exception { 
		try {
			TickConsumer consumer = new TickConsumer();
			TickConsumerSession session = consumer.start(spec, tickFeed, propKafkaBrokes, "street_tick");
			this.consumers.put(session.getSessionId(), consumer);
			return session;
		} catch (Exception e) {
			logger.error("Exception creating tick consumer " + e.toString());
			throw e;
		}
	}

	

	public String getKafkaBrokers() {
		return propKafkaBrokes;
	}

	public String getZookeepers() {
		return zookeepers;
	}
	
	public List<TradeTickerSpec> getInvalidatedSubscriptions() { 
		return null;
	}
	


	public Map<String,TradeTickerSpec> getTickerSubscriptions() throws Exception {
		return tickerSubscriptions;
	}
	
	public FeedServiceStats getStats() { 
		FeedServiceStats stats = new FeedServiceStats();
		stats.setStartTime(startTime);
		stats.setState(state);
		stats.setSubscriptionCount(tickerSubscriptions.size());
		
		TickProviderStatsSpec providerStats = activeProvider.getStats();
		stats.setMessageCount(providerStats.getMessageCount());
		stats.setAggCount(providerStats.getSecondAggCount());
		stats.setQuoteCount(providerStats.getQuoteCount());
		stats.setQps(providerStats.getQps());
		stats.setMps(providerStats.getMps());
		stats.setAps(providerStats.getAps());
		
		stats.setValidatedSubscriptionCount(0);
		stats.setInvalidatedSubscriptionCount(0);
		stats.setMessageQueueSize(providerStats.getMessageQueueSize());
		stats.setFeedCount(this.consumers.size());
		return stats;
	}
	
	
	public List<TickFeedSubscriptionBean> getFeedSubscriptionBeans() { 
		List<TickFeedSubscriptionBean> beans = new ArrayList<TickFeedSubscriptionBean>();
		for (TickFeedSubscription tickFeedSubscriptionBean : tickFeed.getSubscriptions()) {
			beans.add(tickFeedSubscriptionBean.getBean());
		}
		return beans;
	}
	
	public TickFeedSubscriptionBean getSubscriptionBean(String s) throws Exception { 
		return tickFeed.getSubscription(s).getBean();
	}
	
	private class ResetDayChecker extends Thread { 
		
		public void run() { 
			try {
				while(!interrupted()) { 
					LocalDate rightNow = LocalDate.now(DTimeZone.toZoneId(DTimeZone.NewYork));
					if(rightNow.isAfter(today)) { 
						logger.info("Feed Service Reset Day Triggered");
						for (FeedServiceProvider feedServiceProvider : serviceProviders) {
							feedServiceProvider.getProvider().newDay();
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
