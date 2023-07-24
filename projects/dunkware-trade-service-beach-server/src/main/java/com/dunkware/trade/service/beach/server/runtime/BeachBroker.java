package com.dunkware.trade.service.beach.server.runtime;

import java.awt.dnd.DragGestureEvent;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.databean.DataBeanConnector;
import com.dunkware.common.util.events.DEvent;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.events.anot.ADEventMethod;
import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.broker.tws.TwsBrokerType;
import com.dunkware.trade.sdk.core.model.broker.BrokerStatus;
import com.dunkware.trade.sdk.core.model.broker.BrokerType;
import com.dunkware.trade.sdk.core.runtime.broker.Broker;
import com.dunkware.trade.sdk.core.runtime.broker.BrokerAccount;
import com.dunkware.trade.sdk.core.runtime.broker.event.EBrokerConnecting;
import com.dunkware.trade.sdk.core.runtime.broker.event.EBrokerDisconnected;
import com.dunkware.trade.sdk.core.runtime.broker.event.EBrokerInitialized;
import com.dunkware.trade.sdk.core.runtime.registry.TradeRegistry;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.entity.BeachAccountEnt;
import com.dunkware.trade.service.beach.server.entity.BeachBrokerEnt;
import com.dunkware.trade.service.beach.server.entity.BeachRepo;
import com.dunkware.trade.service.beach.server.event.EBeachAcccountInitialized;
import com.dunkware.trade.service.beach.server.event.EBeachBrokerInitialized;
import com.dunkware.trade.service.beach.server.event.EBeachBrokerUpdate;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.ObservableElementList;

public class BeachBroker {

	
	@Autowired
	private BeachRuntime runtime;

	@Autowired
	private BeachRepo repo;

	@PersistenceContext(unitName = "trade")
	private EntityManager em;

	@Autowired
	private ApplicationContext ac;

	private Logger logger = LoggerFactory.getLogger(getClass());
	private BeachBrokerEnt entity;
	private Map<String, BeachAccount> accounts = new ConcurrentHashMap<String, BeachAccount>();

	private TwsBrokerType brokerType;
	private Broker broker;
	
	@Autowired
	private BeachService beachService; 

	private DEventNode eventNode;
  
	private BeachBrokerBean bean;
	
	private BeachBrokerStatus status = BeachBrokerStatus.Pending;
	
	private String exception = null;
	
	private ObservableElementList<BeachAccountBean> accountBeans = null;

	
	public BeachBroker() { 
		
		
	}
	
	public BeachBrokerStatus getStatus() { 
		return status;
	}
	
	public String getException() {
		return exception;
	}

	
	public ObservableElementList<BeachAccountBean> getAccountBeans() { 
		return accountBeans;
	}
	
	public void init(BeachBrokerEnt ent)  {
		accountBeans = new ObservableElementList<BeachAccountBean>(
				GlazedLists.threadSafeList(new BasicEventList<BeachAccountBean>()),
				new DataBeanConnector<BeachAccountBean>());
		eventNode = beachService.getEventNode().createChild(this);
		eventNode.addEventHandler(this);
		this.entity = ent;
		bean = new BeachBrokerBean(); 
		bean.setId(entity.getId());
		bean.setName(ent.getIdentifier());
		bean.setStatus(status.name());
		try {
			brokerType = (TwsBrokerType)DJson.getObjectMapper().readValue(entity.getType(), BrokerType.class);
			brokerType.setConnectionId(DRandom.getRandom(0, 200));
		} catch (Exception e) {
			status = BeachBrokerStatus.Exception;
			bean.setStatus("Exception");
			bean.notifyUpdate();
			this.exception = "Broker Type " + e.toString();
			logger.error("Exception deserializing broker type model in broker init " + e.toString());
			eventNode.event(new EBeachBrokerInitialized(this));
		}
		try {
			broker = TradeRegistry.get().createBroker(brokerType);
		} catch (Exception e) {
			exception = "Broker Type registry " + e.toString();
			status = BeachBrokerStatus.Exception;
			bean.setStatus("Exception");
			bean.setSummary("Exception creating broker type ");
			bean.notifyUpdate();
			eventNode.event(new EBeachBrokerInitialized(this));
			logger.error("Exception creating broker type " + e.toString());
			return;
		}
		try {
			broker.connect(brokerType, getEventNode().createChild(broker), runtime.getExecutor());
			bean.setStatus("Connected Inovked");
			bean.notifyUpdate();
		} catch (Exception e) {
			bean.setStatus("Disconnected");
			bean.setSummary(e.toString());
			bean.notifyUpdate();
			logger.error("FIX ME: Broker connect exception " + e.toString());
		}
		
	}

	public BeachBrokerBean getBean() {
		return bean;
	}

	public boolean isConnected() {
		if (broker.getStatus() == BrokerStatus.Connected) {
			return true;
		}
		return false;
	}

	public Collection<BeachAccount> getAccounts() {
		return accounts.values();
	}

	public Broker getConnector() {
		return broker;
	}

	public DEventNode getEventNode() {
		return eventNode;
	}

	public String getIdentifier() {
		return entity.getIdentifier();
	}

	public BeachBrokerEnt getEntity() {
		return entity;
	}

	/**
	 * Here we will create new BeachAccounts for any broker accounts that does not
	 * have a matching beach account Invoked upon broker connection established
	 */
	@Transactional
	private void loadAndSyncAccounts() {
		em = repo.createEntityManager();
		BrokerAccount[] brokerAccounts = broker.getAccounts();
		for (BrokerAccount brokerAccount : brokerAccounts) {
			// query an account
			BeachAccountEnt actEnt = repo.getAccount(brokerAccount.getIdentifier());
			if (actEnt == null) {
				logger.info("Broker {} Creating New Account {}", getEntity().getIdentifier(),
						brokerAccount.getIdentifier(), brokerType.getIdentifier());
				actEnt = new BeachAccountEnt();
				actEnt.setIdentifier(brokerAccount.getIdentifier());
				actEnt.setBroker(getEntity());
				try {
					em.getTransaction().begin();
					em.persist(actEnt);
					em.getTransaction().commit();
					em.close();
				} catch (Exception e) {
					logger.error("Fatal DB Error persisting new beach account " + e.toString(), e);
					continue;
				}
			} else {
				logger.info("Loading Broker Account {} On Broker {}", brokerAccount.getIdentifier(),
						brokerType.getIdentifier());
			}
			BeachAccount act = new BeachAccount(actEnt,this,brokerAccount);
			ac.getAutowireCapableBeanFactory().autowireBean(act);
			
			act.init();
			
		
			
	
		}
		
	}

	@ADEventMethod()
	public void brokerDisconnected(EBrokerDisconnected event) {
		bean.setStatus("Disconnected");
		bean.notifyUpdate(); // this should work as is.
		eventNode.event(new EBeachBrokerUpdate(this));
	}

	@ADEventMethod
	public void brokerInitialized(EBrokerInitialized event) {
		bean.setStatus("Connected");
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				loadAndSyncAccounts();
			}
		};
		runtime.getExecutor().execute(runnable);
		bean.notifyUpdate();
		eventNode.event(new EBeachBrokerInitialized(this));
		
	}
	
	@ADEventMethod()
	public void beachAccountInitialized(EBeachAcccountInitialized event) {
		this.accounts.put(event.getAcccount().getIdentifier(), event.getAcccount());
		this.accountBeans.getReadWriteLock().writeLock().lock();
		this.accountBeans.add(event.getAcccount().getBean());
		this.accountBeans.getReadWriteLock().writeLock().unlock();
	}

	@ADEventMethod
	public void brokerConnecting(EBrokerConnecting event) {
		bean.setStatus("Connecting");
		bean.notifyUpdate();
	
	}
	
	
	

}
