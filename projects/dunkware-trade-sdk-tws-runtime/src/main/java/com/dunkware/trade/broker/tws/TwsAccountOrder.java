package com.dunkware.trade.broker.tws;

import java.math.BigDecimal;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.dtime.DZonedDateTime;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.broker.tws.connector.TwsSocketReader;
import com.dunkware.trade.sdk.core.model.order.OrderAction;
import com.dunkware.trade.sdk.core.model.order.OrderKind;
import com.dunkware.trade.sdk.core.model.order.OrderSpec;
import com.dunkware.trade.sdk.core.model.order.OrderStatus;
import com.dunkware.trade.sdk.core.model.order.OrderType;
import com.dunkware.trade.sdk.core.runtime.order.Order;
import com.dunkware.trade.sdk.core.runtime.order.OrderException;
import com.dunkware.trade.sdk.core.runtime.order.OrderPreview;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderCancelled;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderException;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderFilled;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderPendingSubmit;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderPreSubmitted;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderRejected;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderSent;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderStatusUpdate;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderSubmitted;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderUpdate;
import com.ib.client.Contract;
import com.ib.client.Decimal;
import com.ib.client.TwsOrder;
import com.ib.client.TwsOrderState;

public class TwsAccountOrder implements Order {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private TwsAccount account;
	private TwsBroker broker;
	private TwsOrder twsOrder;

	private OrderSocketReader reader;
	private boolean readerAttached = false;

	private boolean notifyInactive = false;
	private boolean notifyPendingSubmit = false;
	private boolean notifyPresubmit = false;
	private boolean notifySubmit = false;
	private boolean notifyFilled = false;
	private boolean notifyCancelled = false;
	private boolean notifyRejected = false;
	private boolean notifyException = false;

	private DEventNode eventNode;

	private OrderSpec spec;
	
	private BlockingQueue<OrderPreview> orderPreviewQueue = new LinkedBlockingQueue<OrderPreview>();

	public TwsAccountOrder(TwsAccount account, OrderType type) throws OrderException {

		this.account = account;
		this.broker = (TwsBroker) account.getBroker();

		spec = new OrderSpec();
		spec.setTicker(type.getTicker());
		spec.setSize(type.getSize());
		spec.setAction(type.getAction());
		spec.setKind(type.getKind());
		spec.setStatus(OrderStatus.Created);
		spec.setTransmit(true);
		spec.setLimitPrice(type.getLimit());
		spec.setTrailingPercent(type.getTrailingPercent());
		spec.setStopTrigger(type.getStopTrigger());
		spec.setTrailingStopPrice(type.getTrailingStopPrice());
		
		getSpec().setRemaining(getSpec().getSize());
		this.eventNode = account.getEventNode().createChild(this);

		this.twsOrder = createTwsOrder();
		spec.setId(twsOrder.orderId());

	}
	
	public OrderPreview preview() throws OrderException { 
		this.twsOrder.whatIf(true); 
		send();
		try {
			OrderPreview preview = orderPreviewQueue.poll(10, TimeUnit.SECONDS);
			if(preview == null) { 
				throw new OrderException("Order Preview on TWS callback not received after 10 seconds" );
			}
			return preview; 
		} catch (Exception e) {
			throw new OrderException("Exception polling order preview q " + e.toString());
		}
		
	}

	@Override
	public void send() throws OrderException {
		// validate status send(); -->
		// Sent
		if (getStatus() != OrderStatus.Created) {
			throw new OrderException("Cannot Submit Order that is not in Created status");
		}
		if (logger.isTraceEnabled()) {
			logger.trace("{} TwsOrder Send Size={} Action={} Type={}", twsOrder.orderId(), twsOrder.totalQuantity().longValue(),
					twsOrder.action(), twsOrder.orderType());
		}
		reader = new OrderSocketReader();
		broker.addSocketReader(reader);
		readerAttached = true;
		try {
			broker.getClientSocket().placeOrder(twsOrder.orderId(),
					twsOrder.getContract(), twsOrder);
			
			if (logger.isTraceEnabled()) {
				logger.trace("{} TwsOrder Submitted", twsOrder.orderId());
			}
			// Update Status To Sent
			if (logger.isTraceEnabled()) {
				logger.trace("TwsOrder Sent {} ", spec.getId());
			}
			spec.setStatus(OrderStatus.Sent);
			if(twsOrder.whatIf() == false) {
				// only send a send notification if its a real order. 
				EOrderSent sent = new EOrderSent(this);
				eventNode.event(sent);	
			}
			
		} catch (Exception e) {
			logger.error("{} Order Submit Exception " + e.toString(), twsOrder.orderId());
			throw new OrderException("Internal TwsSocket OpenOrder Exception " + e.toString());
		}

	}

	@Override
	public void cancel() throws OrderException {
		if (logger.isTraceEnabled()) {
			logger.trace("{} cancel() ", getSpec().getId());
		}
		if (getSpec().getStatus() == OrderStatus.Filled || getSpec().getStatus() == OrderStatus.PendingCancel
				|| getSpec().getStatus() == OrderStatus.Cancelled) {
			throw new OrderException("Order invalid state to cancel status is " + getStatus());
		}
		if (logger.isTraceEnabled()) {
			logger.trace("{} sending cancel requst ", getSpec().getId());
		}
		broker.getClientSocket().cancelOrder(twsOrder.orderId(),null);
		if (logger.isTraceEnabled()) {
			logger.trace("{} sent cancel requst ", getSpec().getId());
		}
	}

	@Override
	public DEventNode getEventNode() {
		return eventNode;
	}

	@Override
	public OrderStatus getStatus() {
		return spec.getStatus();
	}

	@Override
	public OrderSpec getSpec() {
		return spec;
	}

	private TwsOrder createTwsOrder() throws OrderException {
		TwsOrder twsOrder = new TwsOrder();

		int twsOrderId = broker.getNextOrderId();
		twsOrder.action(getSpec().getAction().Value());
		if (getSpec().getAction() == OrderAction.SSHORT) {
			twsOrder.action("SSHORT");
		}
		twsOrder.account(account.getIdentifier());
		twsOrder.orderType(getSpec().getKind().name());
		twsOrder.outsideRth(true);
	    twsOrder.tif("DAY");
		twsOrder.orderId(twsOrderId);
		twsOrder.transmit(getSpec().isTransmit());
		twsOrder.whatIf(getSpec().isWhatif());
		twsOrder.totalQuantity(Decimal.get(getSpec().getSize()));
		twsOrder.lmtPrice(getSpec().getLimitPrice());
		if (getSpec().getKind().equals(OrderKind.LMT)) {
			twsOrder.auxPrice(getSpec().getLimitPrice());
			twsOrder.lmtPrice(getSpec().getLimitPrice());
		}

		if (getSpec().getKind().equals(OrderKind.TRAIL_PERCENT)) {
			twsOrder.orderType("TRAIL");
			twsOrder.trailingPercent(getSpec().getTrailingPercent());
			twsOrder.auxPrice(Double.MAX_VALUE);
			if (getSpec().getTrailingStopPrice() != Double.MIN_VALUE) {
				twsOrder.trailStopPrice(getSpec().getTrailingStopPrice());
			}
		}
		if (getSpec().getKind().equals(OrderKind.TRAIL_AMOUNT)) {
			twsOrder.orderType("TRAIL");
			twsOrder.auxPrice(getSpec().getTrailingAmount());

			if (getSpec().getTrailingStopPrice() != Double.MAX_VALUE) {
				twsOrder.trailStopPrice(getSpec().getTrailingStopPrice());
			}

		}
		if (getSpec().getKind().equals(OrderKind.STP)) {
			twsOrder.orderType("STOP");
			twsOrder.auxPrice(getSpec().getStopPrice());
			if (getSpec().getStopTrigger() != null) {
				twsOrder.triggerMethod(getSpec().getStopTrigger().val());
			}

		}
		try {
			Contract contract = TwsUtil.tickerToContract(getSpec().getTicker());
			twsOrder.setContract(contract);
			twsOrder.referenceContractId(contract.conid());
		} catch (Exception e) {
			throw new OrderException("Exception Creating Tws Contract " + e.toString());
		}

		return twsOrder;

	}

	private void detachReader() {
		if (logger.isTraceEnabled()) {
			logger.trace("{} Detach Reader ", twsOrder.orderId());
		}
		if (readerAttached) {
			if (logger.isTraceEnabled()) {
				logger.trace("{} detachReader() ", getSpec().getId());
			}
			broker.removeSocketReader(reader);
			readerAttached = false;
		}

	}

	private class OrderSocketReader implements TwsSocketReader {
		
		

		@Override
		public void orderStatus(int orderId, String status, Decimal filled, Decimal remaining, double avgFillPrice,
				int permId, int parentId, double lastFillPrice, int clientId, String whyHeld, double mktCapPrice) {
			if (orderId != twsOrder.orderId()) {
				return;
			}
			if (logger.isTraceEnabled()) {
				logger.trace("{} TwsOrder Status() status={} filled={} remaining={} avgFillPrice={} lastFillPrice={} ",
						getSpec().getId(), status, filled, remaining, avgFillPrice, lastFillPrice);
			}

			getSpec().setFilled(filled.value().intValue());
			getSpec().setRemaining(remaining.value().intValue());
			getSpec().setAvgFillPrice(avgFillPrice);

			OrderStatus orderStatus = OrderStatus.valueOf(status);
			getSpec().setStatus(orderStatus);
			getSpec().setLastUpdate(DZonedDateTime.now(DTimeZone.NewYork));

			EOrderUpdate update = new EOrderUpdate(TwsAccountOrder.this);
			eventNode.event(update);

			if (orderStatus == OrderStatus.Filled) {
				if (!notifyFilled) {
					if (logger.isTraceEnabled()) {
						logger.trace("{} TwsOrder Filled Event Trigger", twsOrder.orderId());
					}
					EOrderFilled fillEvent = new EOrderFilled(TwsAccountOrder.this);
					eventNode.event(fillEvent);
					notifyFilled = true;
					EOrderStatusUpdate statusUpdate = new EOrderStatusUpdate(TwsAccountOrder.this);
					eventNode.event(statusUpdate);
				}
				detachReader();
				return;
			}

			if (orderStatus == OrderStatus.PreSubmitted) {
				if (!notifyPresubmit) {
					EOrderPreSubmitted pending = new EOrderPreSubmitted(TwsAccountOrder.this);
					eventNode.event(pending);
					notifyPresubmit = true;
					EOrderStatusUpdate statusUpdate = new EOrderStatusUpdate(TwsAccountOrder.this);
					eventNode.event(statusUpdate);
				}
			}

			if (orderStatus == OrderStatus.PendingSubmit) {
				if (!notifyPendingSubmit) {
					EOrderPendingSubmit pending = new EOrderPendingSubmit(TwsAccountOrder.this);
					eventNode.event(pending);
					notifyPendingSubmit = true;
					EOrderStatusUpdate statusUpdate = new EOrderStatusUpdate(TwsAccountOrder.this);
					eventNode.event(statusUpdate);
				}
			}

			if (orderStatus == OrderStatus.Submitted) {
				if (!notifySubmit) {
					eventNode.event(new EOrderSubmitted(TwsAccountOrder.this));
					notifySubmit = true;
					EOrderStatusUpdate statusUpdate = new EOrderStatusUpdate(TwsAccountOrder.this);
					eventNode.event(statusUpdate);
				}
			}

			if (orderStatus == OrderStatus.Cancelled) {
				if (!notifyCancelled) {
					eventNode.event(new EOrderCancelled(TwsAccountOrder.this));
					notifyCancelled = true;
					EOrderStatusUpdate statusUpdate = new EOrderStatusUpdate(TwsAccountOrder.this);
					eventNode.event(statusUpdate);
				}
				detachReader();

			}

			if (orderStatus == OrderStatus.Rejected) {
				if (!notifyRejected) {
					eventNode.event(new EOrderRejected(TwsAccountOrder.this));
					notifyRejected = true;
					EOrderStatusUpdate statusUpdate = new EOrderStatusUpdate(TwsAccountOrder.this);
					eventNode.event(statusUpdate);
				}
				detachReader();
			}

		}

		
		/**
		 * this is only invoked when the what_iff flag is = true
		 */
		@Override
		public void openOrder(int orderId, Contract contract, TwsOrder order, TwsOrderState orderState) {
			if (order.orderId() != twsOrder.orderId()) {
				return;
			}
			OrderPreview preview = new OrderPreview(orderState.maxCommission(), orderState.minCommission(), orderState.warningText());
			orderPreviewQueue.add(preview);
			
			
			if (logger.isTraceEnabled()) {
				logger.trace("{} openOrder() method ", getSpec().getId());
			}
			getSpec().setStatus(OrderStatus.valueOf(orderState.status().name()));
			getSpec().setCommision(orderState.commission());
			// yes call it because we got it. 
			detachReader();
		}
		
		

		@Override
		public void error(int id, int errorCode, String errorMsg, String advancedOrderRejectJson) {
			if(id != twsOrder.orderId()) {
				return;
			}
			if (logger.isTraceEnabled()) {
				logger.trace("{} PreFilter Error id={} code={} msg={}", twsOrder.orderId(), id, errorCode, errorMsg);
			}

			if (errorMsg.contains("'Outside Regular Trading Hours'")) {
				return;
			}
			if (errorMsg.contains("Order Canceled - reason:")) {
				return;
			}
			if (errorMsg.contains("Warning: your order will not")) {
				return;
			}
			logger.trace("TwsOrder error({},{},{}) setting status to Exception (is this right?)", getSpec().getId(), id,
					errorCode, errorMsg);
			getSpec().setException(errorCode + "-" + errorMsg);
			getSpec().setStatus(OrderStatus.Exception);

			eventNode.event(new EOrderException(TwsAccountOrder.this));
			notifyException = true;
			detachReader();
		}



	}

}
