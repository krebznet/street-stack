package com.dunkware.trade.service.beach.server.broker.impl;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.observable.ObservableBeanListConnector;
import com.dunkware.trade.broker.tws.TwsBrokerType;
import com.dunkware.trade.sdk.core.model.broker.BrokerType;
import com.dunkware.trade.sdk.core.runtime.broker.Broker;
import com.dunkware.trade.sdk.core.runtime.broker.BrokerAccount;
import com.dunkware.trade.sdk.core.runtime.registry.TradeRegistry;
import com.dunkware.trade.service.beach.server.broker.BeachBroker;
import com.dunkware.trade.service.beach.server.broker.BeachBrokerAccount;
import com.dunkware.trade.service.beach.server.broker.BeachBrokerAccountBean;
import com.dunkware.trade.service.beach.server.broker.BeachBrokerBean;
import com.dunkware.trade.service.beach.server.broker.BeachBrokerService;
import com.dunkware.trade.service.beach.server.broker.BeachBrokerStatus;
import com.dunkware.trade.service.beach.server.common.BeachMarkers;
import com.dunkware.trade.service.beach.server.entity.BeachBrokerAccountEntity;
import com.dunkware.trade.service.beach.server.entity.BeachBrokerEnttity;
import com.dunkware.trade.service.beach.server.entity.BeachRepo;
import com.dunkware.trade.service.beach.server.event.EBeachBrokerConnected;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.ObservableElementList;

public class BeachBrokerImpl implements BeachBroker  {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("BeachBrokers");

	private BeachBrokerBean bean = new BeachBrokerBean();
	
	private ObservableElementList<BeachBrokerAccountBean> accountBeans;
	
	private DEventNode eventNode; 
	
	@Autowired
	private BeachBrokerService brokerService; 
	
	@Autowired
	private BeachRepo beachRepo; 
	
	private BeachBrokerEnttity entity; 
	
	private BeachBrokerStatus status = BeachBrokerStatus.Connecting;
	
	private TwsBrokerType brokerType; 
	
	private String exception;
	
	
	private Map<Integer,BeachBrokerAccount> accounts = new ConcurrentHashMap<Integer,BeachBrokerAccount>();
	
	Broker broker;
	
	@Override
	public void init(BeachBrokerEnttity entity) {
		accountBeans = new ObservableElementList<BeachBrokerAccountBean>(
				GlazedLists.threadSafeList(new BasicEventList<BeachBrokerAccountBean>()),
				new ObservableBeanListConnector<BeachBrokerAccountBean>());
		eventNode = brokerService.getEventNode().createChild(this);
		eventNode.addEventHandler(this);
		this.entity = entity;
		bean = new BeachBrokerBean();
		bean.setId(entity.getId());
		bean.setName(entity.getIdentifier());
		bean.setStatus(status.name());
		try {
			brokerType = (TwsBrokerType) DJson.getObjectMapper().readValue(entity.getType(), BrokerType.class);
			brokerType.setConnectionId(DRandom.getRandom(0, 200));
		} catch (Exception e) {
			status = BeachBrokerStatus.Exception;
			bean.setStatus("Exception");
			bean.notifyUpdate();
			this.exception = "Broker Type " + e.toString();
			logger.error("Exception deserializing broker type model in broker init " + e.toString());
			eventNode.event(new EBeachBrokerConnected(this));
		}
		try {
			broker = TradeRegistry.get().createBroker(brokerType);
		} catch (Exception e) {
			exception = "Broker Type registry " + e.toString();
			status = BeachBrokerStatus.Exception;
			bean.setStatus("Exception");
			bean.setSummary("Exception creating broker type ");
			bean.notifyUpdate();
			eventNode.event(new EBeachBrokerConnected(this));
			logger.error(marker, "Exception creating broker type " + e.toString());
			return;
		}
		try {
			broker.connect(brokerType, getEventNode().createChild(broker), brokerService.getExecutor());
			bean.setStatus("Connected Inovked");
			bean.notifyUpdate();
		} catch (Exception e) {
			bean.setStatus("Disconnected");
			bean.setSummary(e.toString());
			bean.notifyUpdate();
			logger.error("FIX ME: Broker connect exception " + e.toString());
		}
	}

	@Override
	public BeachBrokerBean getBean() {
		return bean; 
	}

	@Override
	public DEventNode getEventNode() {
		return eventNode;
	}

	@Override
	public long getId() {
		return entity.getId();
	}

	@Override
	public BeachBrokerEnttity getEntity() {
		return entity;
	}
	
	

	@Override
	public String getIdentifier() {
		return entity.getIdentifier();
	}

	@Override
	public Collection<BeachBrokerAccount> getAccounts() {
		return accounts.values();
	}

	/**
	 * Here we will create new BeachAccounts for any broker accounts that does not
	 * have a matching beach account Invoked upon broker connection established
	 */
	@Transactional
	private void loadAndSyncAccounts() {
		EntityManager em;
		em = beachRepo.createEntityManager();
		BrokerAccount[] brokerAccounts = broker.getAccounts();
		for (BrokerAccount brokerAccount : brokerAccounts) {
			// query an account
			BeachBrokerAccountEntity actEnt = beachRepo.getAccount(brokerAccount.getIdentifier());
			if (actEnt == null) {
				logger.info(marker, "Broker {} Creating New Account {}", getEntity().getIdentifier(),
						brokerAccount.getIdentifier(), brokerType.getIdentifier());
				actEnt = new BeachBrokerAccountEntity();
				actEnt.setIdentifier(brokerAccount.getIdentifier());
				actEnt.setBroker(getEntity());
				try {
					em.getTransaction().begin();
					em.persist(actEnt);
					em.getTransaction().commit();
					em.close();
				} catch (Exception e) {
					logger.error(BeachMarkers.systemException(), "Fatal DB Error persisting new beach account " + e.toString(), e);
					continue;
				}
			} else {
				logger.info(marker, "Loading Broker Account {} On Broker {}", brokerAccount.getIdentifier(),
						brokerType.getIdentifier());
			}
			// act = new BeachAccount(actEnt, this, brokerAccount);
			//ac.getAutowireCapableBeanFactory().autowireBean(act);
			//act.init();

		}

	}

}
