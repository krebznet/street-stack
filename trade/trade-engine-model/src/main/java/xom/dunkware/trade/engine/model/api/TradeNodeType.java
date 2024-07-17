package com.dunkware.trade.engine.model.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public abstract class TradeNodeType {

	private TradeNodeType parent; 
	private List<TradeNodeType> children; 
	private String name; 
}
