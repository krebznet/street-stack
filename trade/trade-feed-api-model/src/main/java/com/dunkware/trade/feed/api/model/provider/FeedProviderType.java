package com.dunkware.trade.feed.api.model.provider;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedProviderType {

	private String type; 
	private Map<String,Object> properties;
}
