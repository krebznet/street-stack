package com.dunkware.trade.service.beach.server.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity(name = "BeachAccountDO")
@Table(name = "beach_account")
public class BeachAccountDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	@ManyToOne
	private BeachBrokerDO broker; 
	private String identifier;
	

	
	//@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
	@Transient
	private List<BeachOrderDO> orders = new ArrayList<BeachOrderDO>();
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public BeachBrokerDO getBroker() {
		return broker;
	}
	public void setBroker(BeachBrokerDO broker) {
		this.broker = broker;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public List<BeachOrderDO> getOrders() {
		return orders;
	}

	public void setOrders(List<BeachOrderDO> orders) {
		this.orders = orders;
	}
	
	
}
