package com.dunkware.spring.test.model.feed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Data
@NoArgsConstructor()
@AllArgsConstructor()
@Setter
public class Quote {

	private String symbol;
	private int price; 
	
	
}
