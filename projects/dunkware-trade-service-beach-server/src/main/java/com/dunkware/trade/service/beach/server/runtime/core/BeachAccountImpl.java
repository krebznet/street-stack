package com.dunkware.trade.service.beach.server.runtime.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.sdk.core.model.broker.BrokerAccountSpec;
import com.dunkware.trade.sdk.core.model.order.OrderType;
import com.dunkware.trade.sdk.core.runtime.broker.BrokerAccount;
import com.dunkware.trade.sdk.core.runtime.order.Order;
import com.dunkware.trade.sdk.core.runtime.order.OrderException;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderCreated;
import com.dunkware.trade.service.beach.protocol.spec.BeachAccountSpec;
import com.dunkware.trade.service.beach.server.repository.BeachAccountDO;
import com.dunkware.trade.service.beach.server.repository.BeachOrderDO;
import com.dunkware.trade.service.beach.server.repository.BeachTradeRepo;
import com.dunkware.trade.service.beach.server.runtime.BeachAccount;
import com.dunkware.trade.service.beach.server.runtime.BeachBot;
import com.dunkware.trade.service.beach.server.runtime.BeachBroker;
import com.dunkware.trade.service.beach.server.runtime.BeachOrder;
import com.dunkware.trade.service.beach.server.runtime.BeachTrade;

@Transactional
public class BeachAccountImpl implements BeachAccount {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

	@Autowired
	private BeachTradeRepo tradeRepo;

	@Autowired
	private ApplicationContext ac;

	private BeachAccountSpec spec;
	private List<BeachTrade> activeTrades = new ArrayList<BeachTrade>();
	private Semaphore activeTradeLock = new Semaphore(1);

	private BeachBroker broker;
	private BeachAccountDO entity;
	private BrokerAccount account;

	private DEventNode eventNode;

	private ConcurrentHashMap<String, BeachBot> bots = new ConcurrentHashMap<String, BeachBot>();

	private ConcurrentHashMap<Integer, BeachOrder> orders = new ConcurrentHashMap<Integer, BeachOrder>();

	public void init(BeachAccountDO entity, BeachBroker broker, BrokerAccount brokerAccount) {
		this.broker = broker;
		this.entity = entity;
		this.account = brokerAccount;
		this.eventNode = broker.getEventNode().createChild("/accounts/" + entity.getIdentifier());
		
	}

	@Override
	public Collection<BeachBot> getBots() {
		return bots.values();
	}

	@Override
	public BeachBot getBot(String identifier) throws Exception {
		BeachBot bot = bots.get(identifier);
		if(bot == null) { 
			throw new Exception("Beach Bot " + identifier + " does not exist on account " + entity.getIdentifier());
		}
		return bot;
	}

	@Override
	public String getIdentifier() {
		return entity.getIdentifier();
	}

	@Override
	public BeachAccountDO getEntity() {
		return entity;
	}

	@Override
	public BeachBroker getBroker() {
		return broker;
	}

	@Override
	public DEventNode getEventNode() {
		return eventNode;
	}

	@Override
	public Order createOrder(OrderType orderType) throws OrderException {
		// try to create the order
		Order order = account.createOrder(orderType);
		// at this point it should create the entity
		BeachOrderDO ent = new BeachOrderDO();

		ent.setOrderId(order.getSpec().getId());
		ent.setFilled(0);
		ent.setLastStatus(order.getStatus());
		ent.setLastUpdate(DDateTime.now(DTimeZone.NewYork).get());
		ent.setAction(order.getSpec().getAction());
		ent.setKind(order.getSpec().getKind());
		ent.setSize(order.getSpec().getSize());

		ent.setAccount(getEntity());
		ent.setBroker(getBroker().getEntity());
		EntityManager em = tradeRepo.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(ent);
			em.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Fatal Problem Persist Order Failed " + e.toString(), e);
			throw new OrderException("Internal Error Persist Order Failed " + e.toString(), e);
		} finally {
			em.close();
		}
		BeachOrderImpl beachOrder = new BeachOrderImpl();
		try {
			ac.getAutowireCapableBeanFactory().autowireBean(beachOrder);
			beachOrder.init(this, order, ent);
			// notify created event
			eventNode.event(new EOrderCreated(beachOrder));
			orders.put(order.getSpec().getId(), beachOrder);
		} catch (Exception e) {
			// figure out what type of exception
			throw new OrderException("Internal Beach Order Init Exeption Catch " + e.toString(), e);
		}

		return beachOrder;
	}

	@Override
	public BrokerAccountSpec getSpec() {
		// okay need to listen and mock up shit here
		// right duncan?
		// TODO: Code this!
		return null;
	}

}
