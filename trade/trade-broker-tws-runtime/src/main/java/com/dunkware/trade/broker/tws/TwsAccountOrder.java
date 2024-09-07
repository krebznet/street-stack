package com.dunkware.trade.broker.tws;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.dunkware.trade.broker.api.model.order.OrderBean;
import com.dunkware.trade.broker.api.model.order.OrderPreview;
import com.dunkware.trade.broker.api.model.order.OrderStatus;
import com.dunkware.trade.broker.api.model.order.OrderType;
import com.dunkware.trade.broker.api.runtime.Order;
import com.dunkware.trade.broker.api.runtime.OrderException;
import com.dunkware.trade.broker.api.runtime.event.EOrderCancelled;
import com.dunkware.trade.broker.api.runtime.event.EOrderException;
import com.dunkware.trade.broker.api.runtime.event.EOrderFilled;
import com.dunkware.trade.broker.api.runtime.event.EOrderPendingSubmit;
import com.dunkware.trade.broker.api.runtime.event.EOrderPreSubmitted;
import com.dunkware.trade.broker.api.runtime.event.EOrderRejected;
import com.dunkware.trade.broker.api.runtime.event.EOrderSent;
import com.dunkware.trade.broker.api.runtime.event.EOrderStatusUpdate;
import com.dunkware.trade.broker.api.runtime.event.EOrderSubmitted;
import com.dunkware.trade.broker.api.runtime.event.EOrderUpdate;
import com.dunkware.trade.broker.tws.connector.TwsSocketReader;
import com.dunkware.utils.core.events.DunkEventNode;
import com.ib.client.Contract;
import com.ib.client.Decimal;
import com.ib.client.TwsOrder;
import com.ib.client.TwsOrderState;

//TODO: AVINASHANV-24 Trader Workstaion Order
/**
 * this implemens Order and we'll see how it works. 
 */
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
	
	private OrderType spec; 

	private DunkEventNode eventNode;
	
	private OrderStatus status = OrderStatus.Created;

	private OrderBean bean;
	
	private BlockingQueue<OrderPreview> orderPreviewQueue = new LinkedBlockingQueue<OrderPreview>();

	public TwsAccountOrder(TwsAccount account, OrderType spec) throws OrderException {

		this.spec = spec;
		this.account = account;
		this.broker = (TwsBroker) account.getBroker();

		bean = new OrderBean();
		bean.setSymbol(spec.getTicker().getSymbol());
		bean.setSize(spec.getSize());
	//	bean.setAction(spec.getAction().name());
	//	bean.setType(spec.getType().name());
		bean.setStatus(status.name());
		bean.setTransmit(true);
		bean.setLimitPrice(spec.getLimitPrice());
		bean.setFilled(0);
		bean.setRemaining(spec.getSize());
		//bean.setStopTrigger(spec.getStopPrice())
		
		this.eventNode = account.getEventNode().createChild(this);
		this.twsOrder = createTwsOrder();
		bean.setOrderId(twsOrder.orderId());

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

	/**
	 * //TODO: AVINASHANV-25 Sending an order
	 * this will send a order to trader workstation
	 * it uses something called a Socket Reader to get order 
	 * call backs
	 */
	@Override
	public void send() {
		// validate status send(); -->
		// Sent
		if (getStatus() != OrderStatus.Created) {
			setStatus(OrderStatus.Exception);
			bean.setException("Invalid order state " + getStatus() +  " for sending order");
			eventNode.event(new EOrderException(this));
			return;
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
				logger.trace("TwsOrder Sent {} ", bean.getOrderId());
			}
			status = (OrderStatus.Sent);
			if(twsOrder.whatIf() == false) {
				// only send a send notification if its a real order. 
				EOrderSent sent = new EOrderSent(this);
				eventNode.event(sent);	
			}
			
		} catch (Exception e) {
			setStatus(OrderStatus.Exception);
			bean.setException("Send Exception " + e.toString());
			eventNode.event(new EOrderException(this));
		}

	}

	@Override
	public void cancel() {
		if (logger.isTraceEnabled()) {
			logger.trace("{} cancel() ", getBean().getOrderId());
		}
		if (status == OrderStatus.Filled || status == OrderStatus.PendingCancel
				|| status == OrderStatus.Cancelled) {
			//throw new OrderException("Order invalid state to cancel status is " + getStatus());
		}
		if (logger.isTraceEnabled()) {
			logger.trace("{} sending cancel requst ", getBean().getOrderId());
		}
		broker.getClientSocket().cancelOrder(twsOrder.orderId(),null);
		if (logger.isTraceEnabled()) {
			logger.trace("{} sent cancel requst ", getBean().getOrderId());
		}
	}

	
	
	
	
	@Override
	public OrderStatus getStatus() {
		return status;
	}

	
	
	
	@Override
	public OrderType getType() {
		return spec;
	}

	private void setStatus(OrderStatus status) { 
		this.status = status; 
		this.bean.setStatus(status.name());
		bean.notifyUpdate();
	}

	private TwsOrder createTwsOrder() throws OrderException {
		TwsOrder twsOrder = new TwsOrder();

		/*
		 * int twsOrderId = broker.getNextOrderId();
		 * twsOrder.action(getSpec().getAction().Value()); if (getSpec().getAction() ==
		 * OrderAction.SSHORT) { twsOrder.action("SSHORT"); }
		 * twsOrder.account(account.getIdentifier());
		 * twsOrder.orderType(getSpec().getKind().name()); twsOrder.outsideRth(true);
		 * twsOrder.tif("DAY"); twsOrder.orderId(twsOrderId);
		 * twsOrder.transmit(getSpec().isTransmit());
		 * twsOrder.whatIf(getSpec().isWhatif());
		 * twsOrder.totalQuantity(Decimal.get(getSpec().getSize()));
		 * twsOrder.lmtPrice(getSpec().getLimitPrice()); if
		 * (getSpec().getKind().equals(OrderKind.LMT)) {
		 * twsOrder.auxPrice(getSpec().getLimitPrice());
		 * twsOrder.lmtPrice(getSpec().getLimitPrice()); }
		 * 
		 * if (getSpec().getKind().equals(OrderKind.TRAIL_PERCENT)) {
		 * twsOrder.orderType("TRAIL");
		 * twsOrder.trailingPercent(getSpec().getTrailingPercent());
		 * twsOrder.auxPrice(Double.MAX_VALUE); if (getSpec().getTrailingStopPrice() !=
		 * Double.MIN_VALUE) {
		 * twsOrder.trailStopPrice(getSpec().getTrailingStopPrice()); } } if
		 * (getSpec().getKind().equals(OrderKind.TRAIL_AMOUNT)) {
		 * twsOrder.orderType("TRAIL");
		 * twsOrder.auxPrice(getSpec().getTrailingAmount());
		 * 
		 * if (getSpec().getTrailingStopPrice() != Double.MAX_VALUE) {
		 * twsOrder.trailStopPrice(getSpec().getTrailingStopPrice()); }
		 * 
		 * } if (getSpec().getKind().equals(OrderKind.STP)) {
		 * twsOrder.orderType("STOP"); twsOrder.auxPrice(getSpec().getStopPrice()); if
		 * (getSpec().getStopTrigger() != null) {
		 * twsOrder.triggerMethod(getSpec().getStopTrigger().val()); }
		 * 
		 * } try { Contract contract = TwsUtil.tickerToContract(getSpec().getTicker());
		 * twsOrder.setContract(contract);
		 * twsOrder.referenceContractId(contract.conid()); } catch (Exception e) { throw
		 * new OrderException("Exception Creating Tws Contract " + e.toString()); }
		 */
		return twsOrder;

	}

	private void detachReader() {
		if (logger.isTraceEnabled()) {
			logger.trace("{} Detach Reader ", twsOrder.orderId());
		}
		if (readerAttached) {
			if (logger.isTraceEnabled()) {
				logger.trace("{} detachReader() ", getBean().getOrderId());
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
				//logger.trace("{} TwsOrder Status() status={} filled={} remaining={} avgFillPrice={} lastFillPrice={} ",
				//		getSpec().getId(), status, filled, remaining, avgFillPrice, lastFillPrice);
			}

			getBean().setFilled(filled.value().intValue());
			getBean().setRemaining(remaining.value().intValue());
			//getBean().setAvgFillPrice(avgFillPrice);

			OrderStatus orderStatus = OrderStatus.valueOf(status);
			getBean().setStatus(orderStatus.name());
			//getSpec().setLastUpdate(DZonedDateTime.now(DTimeZone.NewYork));

			EOrderUpdate update = new EOrderUpdate(TwsAccountOrder.this);
			eventNode.event(update);

			//TODO: AVINASHANV-28 Order Filled Event
			/**
			 * this is where we implemented orderStatus on the socket readher we check if the 
			 * order id is our order and if the status is filled we will trigger an event
			 * on the order event node line 289 that all registered handlers on the order, trade,
			 * account and broker will recieve that shows the design behind event tree. 
			 */
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
				logger.trace("{} openOrder() method ", getBean().getOrderId());
			}
			//getSpec().setStatus(OrderStatus.valueOf(orderState.status().name()));
			//getSpec().setCommision(orderState.commission());
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
			logger.trace("TwsOrder error({},{},{}) setting status to Exception (is this right?)", bean.getOrderId(), id,
					errorCode, errorMsg);
			//getSpec().setException(errorCode + "-" + errorMsg);
			//getSpec().setStatus(OrderStatus.Exception);

			eventNode.event(new EOrderException(TwsAccountOrder.this));
			notifyException = true;
			detachReader();
		}



	}

	@Override
	public OrderBean getBean() {
		return bean;
	}

	@Override
	public DunkEventNode getEventNode() {
		return eventNode;
	}

}
