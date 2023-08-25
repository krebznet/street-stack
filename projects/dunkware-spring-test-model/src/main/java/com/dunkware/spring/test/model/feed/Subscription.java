package com.dunkware.spring.test.model.feed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Getter
@Setter
@AllArgsConstructor
public class Subscription {

	private String symbol;
}
