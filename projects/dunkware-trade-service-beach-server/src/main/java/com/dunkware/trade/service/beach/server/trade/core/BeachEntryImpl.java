package com.dunkware.trade.service.beach.server.trade.core;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.events.anot.ADEventMethod;
import com.dunkware.trade.sdk.core.model.trade.EntrySpec;
import com.dunkware.trade.sdk.core.model.trade.EntryStatus;
import com.dunkware.trade.sdk.core.model.trade.EntryType;
import com.dunkware.trade.sdk.core.runtime.trade.Trade;
import com.dunkware.trade.sdk.core.runtime.trade.TradeEntry;
import com.dunkware.trade.sdk.core.runtime.trade.event.ETradeEntryCompleted;
import com.dunkware.trade.sdk.core.runtime.trade.event.ETradeEntryOrderCreated;
import com.dunkware.trade.service.beach.server.trade.BeachEntry;
import com.dunkware.trade.service.beach.server.trade.BeachOrder;
import com.dunkware.trade.service.beach.server.trade.entity.BeachEntryDO;
import com.dunkware.trade.service.beach.server.trade.entity.BeachTradeRepo;

public class BeachEntryImpl implements BeachEntry {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BeachTradeRepo tradeRepo;

	private BeachEntryDO entity;
	private TradeEntry entry;

	public void init(BeachEntryDO entity, TradeEntry entry) {
		this.entity = entity;
		this.entry = entry;
	}

	@Override
	public EntrySpec getSpec() {
		return entry.getSpec();
	}

	@Override
	public void init(EntryType type) throws Exception {
		entry.init(type);
	}

	@Override
	public void start(Trade trade) throws Exception {
		entry.start(trade);
	}

	@Override
	public void cancel() throws Exception {
		entry.cancel();
	}

	@Override
	public EntryStatus getStatus() {
		return entry.getStatus();
	}

	@Override
	public Trade getTrade() {
		return entry.getTrade();
	}

	@Override
	public DEventNode getEventNode() {
		return entry.getEventNode();
	}

	@Override
	public BeachEntryDO getEntity() {
		return entity;
	}

	@ADEventMethod()
	public void ETradeEntryCompleted(ETradeEntryCompleted completed) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				entity.setStatus(completed.getEntry().getStatus());
				EntityManager em = tradeRepo.createEntityManager();
				try {
					em.getTransaction().begin();
					em.persist(entity);
					em.getTransaction().commit();
				} catch (Exception e) {
					logger.error("Internal Exception Saving Beach Entry Entity On Order Created Event " + e.toString());
				} finally {
					em.close();
				}
			}
		};
		getTrade().getContext().execute(runnable);
	}

	@ADEventMethod()
	public void entryOrderCreated(ETradeEntryOrderCreated created) {
		final BeachOrder order = (BeachOrder) created.getOrder();
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				entity.getOrders().add(order.getEntity());
				EntityManager em = tradeRepo.createEntityManager();
				try {
					em.getTransaction().begin();
					em.persist(entity);
					em.getTransaction().commit();
				} catch (Exception e) {
					logger.error("Internal Exception Saving Beach Entry Entity On Order Created Event " + e.toString());
				} finally {
					em.close();
				}
			}
		};
		getTrade().getContext().execute(runnable);
	}

}
