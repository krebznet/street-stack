package com.dunkware.street.smart.runtime.adapter;

import java.time.LocalDateTime;

import com.dunkware.utils.core.observable.ObservableBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionEvent extends ObservableBean {

	private LocalDateTime timestamp;
	private String tradeRef;
	private String type; 
	private String message; 
}
