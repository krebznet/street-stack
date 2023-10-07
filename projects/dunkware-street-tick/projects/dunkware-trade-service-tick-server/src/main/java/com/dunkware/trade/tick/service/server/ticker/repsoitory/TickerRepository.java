package com.dunkware.trade.tick.service.server.ticker.repsoitory;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;


public interface TickerRepository extends PagingAndSortingRepository<TickerDO, Long> {

	
	public List<TickerDO> findBySymbol(String symbol);
	
	

}
