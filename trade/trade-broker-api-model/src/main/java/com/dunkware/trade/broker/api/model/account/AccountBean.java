package com.dunkware.trade.broker.api.model.account;

import com.dunkware.utils.core.observable.Observable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountBean extends Observable<AccountBean> {

	private String name;
	private String broker;
	private String status;
	
}
