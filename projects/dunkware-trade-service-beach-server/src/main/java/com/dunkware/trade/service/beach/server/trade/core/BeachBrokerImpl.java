package com.dunkware.trade.service.beach.server.trade.core;

import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.sdk.core.model.broker.BrokerSpec;
import com.dunkware.trade.sdk.core.model.broker.BrokerStatus;
import com.dunkware.trade.sdk.core.model.broker.BrokerType;
import com.dunkware.trade.sdk.core.runtime.broker.Broker;
import com.dunkware.trade.sdk.core.runtime.broker.BrokerAccount;
import com.dunkware.trade.sdk.core.runtime.registry.TradeRegistry;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.trade.BeachAccount;
import com.dunkware.trade.service.beach.server.trade.BeachBroker;
import com.dunkware.trade.service.beach.server.trade.BeachTradeService;
import com.dunkware.trade.service.beach.server.trade.entity.BeachAccountDO;
import com.dunkware.trade.service.beach.server.trade.entity.BeachBrokerDO;
import com.dunkware.trade.service.beach.server.trade.entity.BeachTradeRepo;
import com.dunkware.trade.tick.api.instrument.Instrument;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

@Transactional
public class BeachBrokerImpl implements BeachBroker {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@PersistenceContext(unitName = "trade")
	private EntityManager em;

	@Autowired
	private BeachRuntime runtime;

	@Autowired
	private ApplicationContext ac;

	@Autowired
	private BeachTradeService tradeService;

	@Autowired
	private BeachTradeRepo tradeRepo;

	private BeachBrokerDO entity;

	private Broker broker;

	private DEventNode eventNode;

	private ConcurrentHashMap<String, BeachAccount> accounts = new ConcurrentHashMap<String, BeachAccount>();

	private BrokerType brokerType;

	@Override
	public BeachBrokerDO getEntity() {
		return entity;
	}

	@Transactional
	public void init(BeachBrokerDO entity) throws Exception {
		this.entity = entity;
		eventNode = tradeService.getEventNode().createChild("/brokers/" + entity.getId());
		try {
			brokerType = DJson.getObjectMapper().readValue(entity.getType(), BrokerType.class);
			broker = TradeRegistry.get().createBroker(brokerType);
		} catch (Exception e) {
			throw e; // let trade service log it
		}
		try {
			broker.connect(brokerType, eventNode,runtime.getExecutor());
		} catch (Exception e) {
			// broker connection exception log it
			logger.error("Broker Connection Exception On Load Broker {} Exception {}", brokerType.getIdentifier(),
					e.toString());
			// so if we can't connect to the broker then what dynamic shit?
		}
		// give the broker a chance to connect
		Thread.sleep(1000);
		// check if we are connected to do account sync
		if (broker.getStatus() == BrokerStatus.Connected) {
			logger.info("Broker {} Connected During Broker Load ", brokerType.getIdentifier());
			loadAndSyncAccounts();
		}

	}

	@Override
	public Broker getBroker() {
		return broker;
	}

	@Override
	public String getIdentifier() {
		return entity.getIdentifier();
	}

	@Override
	public BeachAccount getAccount(String identifier) throws Exception {
		if (accounts.containsKey(identifier)) {
			return accounts.get(identifier);
		}
		throw new Exception("Broker " + getIdentifier() + " Account " + identifier + " Not Found.");
	}

	@Override
	public void connect(BrokerType type, DEventNode eventNode, DExecutor executor) throws Exception {
		//TODO: not what we should do? 
	}

	@Override
	public void disconnect() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public BrokerStatus getStatus() {
		return broker.getStatus();
	}

	@Override
	public BrokerSpec getSpec() {
		return broker.getSpec();
	}

	@Override
	public DEventNode getEventNode() {
		return broker.getEventNode();
	}

	@Override
	public BrokerAccount[] getAccounts() {
		return accounts.values().toArray(new BeachAccount[accounts.size()]);
	}
	

	@Override
	public DExecutor getExecutor() {
		return broker.getExecutor();
	}

	@Override
	public Instrument acquireInstrument(TradeTickerSpec ticker) throws Exception {
		return broker.acquireInstrument(ticker);
	}

	@Override
	public void releaseInstrument(Instrument instrument) {
		broker.releaseInstrument(instrument);
		
	}

	/**
	 * Here we will create new BeachAccounts for any broker accounts that does not
	 * have a matching beach account Invoked upon broker connection established
	 */
	@Transactional
	private void loadAndSyncAccounts() {
		BrokerAccount[] brokerAccounts = broker.getAccounts();
		for (BrokerAccount brokerAccount : brokerAccounts) {
			// query an account
			BeachAccountDO actEnt = tradeRepo.getAccount(brokerAccount.getIdentifier());
			if (actEnt == null) {
				logger.info("Broker {} Creating New Account {}", getEntity().getIdentifier(),brokerAccount.getIdentifier(),
						brokerType.getIdentifier());
				actEnt = new BeachAccountDO();
				actEnt.setIdentifier(brokerAccount.getIdentifier());
				actEnt.setBroker(getEntity());
				try {
					em.persist(actEnt);
				} catch (Exception e) {
					logger.error("Fatal DB Error persisting new beach account " + e.toString(), e);
					continue;
				}
			} else {
				logger.info("Loading Broker Account {} On Broker {}", brokerAccount.getIdentifier(),
						brokerType.getIdentifier());
			}
			BeachAccountImpl act = new BeachAccountImpl();
			ac.getAutowireCapableBeanFactory().autowireBean(act);
			act.init(actEnt, this,brokerAccount);
			accounts.put(actEnt.getIdentifier(), act);
		}
		// now what we are not doing yet is what if we have a beach account that exists for this
		// broker but that account is no longer on the broker? Fuck IT! 
	}

}
