package com.dunkware.trade.service.beach.server.runtime;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.events.anot.ADEventMethod;
import com.dunkware.trade.sdk.core.model.order.OrderSpec;
import com.dunkware.trade.sdk.core.model.order.OrderStatus;
import com.dunkware.trade.sdk.core.model.order.OrderType;
import com.dunkware.trade.sdk.core.runtime.order.Order;
import com.dunkware.trade.sdk.core.runtime.order.OrderException;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderCancelled;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderFilled;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderRejected;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderSubmitted;
import com.dunkware.trade.sdk.core.runtime.util.TradeHelper;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.entity.BeachOrderEnt;
import com.dunkware.trade.service.beach.server.entity.BeachRepo;

public class BeachOrder implements Order {

	@Autowired
	private BeachRuntime runtime;

	@Autowired
	private BeachRepo repo;

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private BeachOrderEnt entity;

	private BeachTrade trade;

	private Order order;
	
	private BeachOrderBean bean;

	public void create(BeachTrade trade, String source, String log, OrderType orderType) throws Exception {
		order = trade.getPlay().getAccount().getConnection().createOrder(orderType);
		entity = new BeachOrderEnt();
		entity.setAccount(trade.getPlay().getAccount().getEntity());
		entity.setTrade(trade.getEntity());
		entity.setBroker(entity.getAccount().getBroker());
		entity.setLog(log);
		entity.setAction(orderType.getAction());
		entity.setCreateTime(BeachRuntime.dateTime());
		entity.setFilled(0);
		entity.setKind(orderType.getKind());
		entity.setLastStatus(order.getStatus());
		entity.setSource(source);
		EntityManager em = repo.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Fatal Problem Persist Order Failed " + e.toString(), e);
			throw new OrderException("Internal Error Persist Order Failed " + e.toString(), e);
		} finally {
			em.close();
		}
		
		
		bean = new BeachOrderBean();
		bean.setAccount(trade.getAccount().getIdentifier());
		bean.setTrade(trade.getIdentifier());
		bean.setPlay(trade.getPlay().getName());
		bean.setFilled(order.getSpec().getFilled());
		bean.setRemaining(order.getSpec().getRemaining());
		

	}
	
	public BeachOrderBean getBean() { 
		return bean;
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
		entity.setCancelRequestTime(BeachRuntime.dateTime());
		persistAsync();
		order.cancel();
	}

	@Override
	public OrderSpec getSpec() {
		return order.getSpec();
	}

	@Override
	public DEventNode getEventNode() {
		return order.getEventNode();
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

	/**
	 * Async Persist In Another Thread it will update the Order Entity
	 */
	private void persistAsync() {
		PersistRunnable runnable = new PersistRunnable();
		runtime.getExecutor().execute(runnable);
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
				// em.persist(entity);
				em.getTransaction().commit();
			} catch (Exception e) {
				logger.error("Broker Order Persist Runnable Exception " + e.toString(), e);
			} finally {
				em.close();
			}
		}
	}

}
