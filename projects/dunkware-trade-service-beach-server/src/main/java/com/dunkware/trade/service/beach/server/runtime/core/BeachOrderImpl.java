package com.dunkware.trade.service.beach.server.runtime.core;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.events.anot.ADEventMethod;
import com.dunkware.trade.sdk.core.model.order.OrderSpec;
import com.dunkware.trade.sdk.core.model.order.OrderStatus;
import com.dunkware.trade.sdk.core.runtime.order.Order;
import com.dunkware.trade.sdk.core.runtime.order.OrderException;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderCancelled;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderFilled;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderRejected;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderSubmitted;
import com.dunkware.trade.sdk.core.runtime.util.TradeHelper;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.repository.BeachOrderDO;
import com.dunkware.trade.service.beach.server.repository.BeachTradeRepo;
import com.dunkware.trade.service.beach.server.runtime.BeachAccount;
import com.dunkware.trade.service.beach.server.runtime.BeachOrder;

@Transactional
public class BeachOrderImpl implements BeachOrder {


	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BeachTradeRepo repo; 
	
	@PersistenceContext(unitName = "trade")
	private EntityManager em;

	@Autowired
	private BeachRuntime runtime; 
	
	private BeachAccountImpl account;
	
	private Order order;
	
	private BeachOrderDO entity;
	
	public void init(BeachAccountImpl account, Order order,BeachOrderDO orderEnt) throws Exception {
		this.account = (BeachAccountImpl)account;
		this.entity = orderEnt;
		this.order = order;
		order.getEventNode().addEventHandler(this);
	}


	@Override
	public OrderStatus getStatus() {
		return order.getStatus();
	}

	@Override
	public void send() throws OrderException {
		order.send();
	}

	@Override
	public void cancel() throws OrderException {
		order.cancel();
	}

	@Override
	public OrderSpec getSpec() {
		return order.getSpec();
	}

	
	@Override
	public BeachAccount getAccount() {
		return account;
	}

	@Override
	public DEventNode getEventNode() {
		return order.getEventNode();
	}
	
	
	
	@Override
	public BeachOrderDO getEntity() {
		return entity;
	}


	/**
	 * Async Persist In Another Thread it will update the Order Entity
	 */
	private void persistAsync() { 
		PersistRunnable runnable = new PersistRunnable();
		runtime.getExecutor().execute(runnable);
	}

	@Transactional
	@ADEventMethod()
	public void orderFilled(EOrderFilled event) { 
		entity.setFillTime(LocalDateTime.now());
		persistAsync();
	}
	
	@ADEventMethod()
	public void orderCancelled(EOrderCancelled event) { 
		entity.setCancelTime(TradeHelper.dateTime().get());
		persistAsync();
	}
	
	@ADEventMethod()
	public void orderRejected(EOrderRejected event) { 
		
	}
	
	@ADEventMethod()
	public void orderSubmitted(EOrderSubmitted event) { 
		entity.setSubmitTime(TradeHelper.dateTime().get());
		persistAsync();
		
	}
	
	private class PersistRunnable implements Runnable { 
		
		
		public PersistRunnable() { 
		 
		}
		public void run() { 
			EntityManager em = null;
			try {
				entity.setLastUpdate(entity.getCancelTime());
				entity.setLastStatus(getSpec().getStatus());
				entity.setFilled(getSpec().getFilled());
				
				em = repo.createEntityManager();
				em.getTransaction().begin();
				em.merge(entity);
			//	em.persist(entity);
				em.getTransaction().commit();
			} catch (Exception e) {
				logger.error("Broker Order Persist Runnable Exception " + e.toString(),e);
			} finally { 
				em.close();
			}
		}
	}

}
