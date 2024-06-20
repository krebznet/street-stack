package com.dunkware.trade.api.broker.model;

import com.dunkware.utils.core.observable.ObservableBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BrokerAccountBean extends ObservableBean {

	private String name;
	private String broker;
	private String status;
	
}
